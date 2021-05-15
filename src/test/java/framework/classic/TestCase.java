package framework.classic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


//todo: 支持数据参数化
//todo: 支持page object
//todo: web app service
//todo: 命令行参数支持
//todo: 目录读取
//todo: ...

public class TestCase {
    public WebDriver driver;
    public String name;
    public List<HashMap<String, Object>> after_all;
    public List<HashMap<String, Object>> before_all;
    public List<HashMap<String, Object>> after;
    public List<HashMap<String, Object>> before;
    public List<HashMap<String, Object>> steps;

    public void run() {
        AtomicReference<By> default_by = new AtomicReference<>();

        steps.forEach(step -> {
            step.entrySet().forEach((entry) -> {
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
                        driver.findElement(default_by.get()).click();
                        break;
                    case "sendkeys":
                        String keys = (String) value;
                        driver.findElement(default_by.get()).sendKeys(keys);
                        break;
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "wait_imp":
                        Integer seconds = (Integer) value;
                        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
                        break;
                }
//
//                if (action.equals("get")) {
//                    driver.get((String) value);
//                } else if (action.equals("find")) {
//                    ArrayList<String> values = (ArrayList<String>) value;
//                    String locator_by = values.get(0);
//                    String locator_value = values.get(1);
//
//                    if (locator_by.equals("id")) {
//                        default_by.set(By.id(locator_value));
//                    } else if (locator_by.equals("css")) {
//                        default_by.set(By.cssSelector(locator_value));
//                    }
//
//                } else if (action.equals("click")) {
//                    driver.findElement(default_by.get()).click();
//                } else if (action.equals("sendkeys")) {
//                    String keys = (String) value;
//                    driver.findElement(default_by.get()).sendKeys(keys);
//                } else if (action.equals("chrome")) {
//                    driver = new ChromeDriver();
//                } else if (action.equals("wait_imp")) {
//                    Integer seconds = (Integer) value;
//                    driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
//                }
            });
        });
    }
}
