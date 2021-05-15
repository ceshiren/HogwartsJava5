package framework.po;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

        POBasePage page = null;

        String path = String.format("src/test/java/framework/po/%s.yaml", name);
        if (new File(path).exists()) {
            page = loadFromFile(path);

        } else {
            page = loadFromClassloader(name);
        }

        page.driver = webDriver;
        return page;
    }

    public static POBasePage loadFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            return mapper.readValue(new File(path), POBasePage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static POBasePage loadFromClassloader(String className) {
        /**利用反射冲生成page实例*/
        try {
            return (POBasePage) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void runPOMethod(String methodName) {

        if (methods == null ){
            try {
                this.getClass().getMethod(methodName).invoke(this, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return;
        }
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
                        } else if (locator_by.equals("link_text")){
                            default_by.set(By.partialLinkText(locator_value));
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
