package framework.po;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class POBasePage {
    public String name;
    public HashMap<String, List<HashMap<String, Object>>> methods;

    WebDriver driver;
    Integer retryTimes = 3;

//    public POBasePage(WebDriver driver) {
//        this.driver = driver;
//    }

    public static POBasePage load(String name, WebDriver webDriver) {
        /**
         * 从po的yaml文件中读取数据，并生成一个BasePage的实例
         */
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        POBasePage page = null;
        try {
            page = mapper.readValue(new File(name), POBasePage.class);
            page.driver=webDriver;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public void runPOMethod(String methodName) {
        AtomicReference<By> default_by = new AtomicReference<>();

        methods.get(methodName).forEach(step -> {
            step.entrySet().forEach(entry -> {
                System.out.println(entry);
                String action = entry.getKey().toLowerCase();
                Object value = entry.getValue();

                switch (action) {
                    case "get":
                        driver.get((String) value);
                        break;
                    case "find":
                        ArrayList<String> values = (ArrayList<String>) value;
                        String locator_by = values.get(0);
                        String locator_value = values.get(1);

                        if (locator_by.equals("id")) {
                            default_by.set(By.id(locator_value));
                        } else if (locator_by.equals("css")) {
                            default_by.set(By.cssSelector(locator_value));
                        }
                        break;
                    case "click":
                        click(default_by.get());
                        break;
                    case "sendkeys":
                        String keys = (String) value;
                        sendKeys(default_by.get(), keys);
                        break;
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "wait_imp":
                        Integer seconds = (Integer) value;
                        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
                        break;
                }
            });
        });
    }

    public boolean click(By by) {
        //todo: 突然的弹框阻挡、异常处理、流程调整
        //todo: find找不到 弹框阻挡 加载延迟
        //todo: click的时候报错
        //todo: click的时候不生效
        //todo: click的时候被弹框阻挡
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            e.printStackTrace();
            retryTimes += 1;
            if (retryTimes < 4) {
                //todo: 解决弹框
                this.handleAlert();
                return click(by);
            } else {
                retryTimes = 0;
                return false;

            }

        }
        return true;
    }

    public void clickUntil(By by, By next) {
        //todo: 用来解决前几次点击不生效，后面点击生效的过程，使用显式等待
    }

    public void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    public void handleAlert() {
//        List black= Arrays.asList("//*[@text='dddd']", "//*[@text='dddd']");
//        driver.getPageSource()


//        List black= Arrays.asList(By.id("ddd"), By.name("ddd"));
//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS)
//        driver.findElement()

    }
}
