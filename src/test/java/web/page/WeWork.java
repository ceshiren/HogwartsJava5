package web.page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeWork {
    WebDriver driver;

    public WeWork startWeb() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        return this;
    }

    public MainPage login() {
        try {
            driver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            List<HashMap<String, Object>> cookies = objectMapper.readValue(new File("cookie.yaml"), typeReference);
            cookies.forEach(cookie -> {
                driver.manage().addCookie(new Cookie(cookie.get("name").toString(), cookie.get("value").toString()));
            });

            driver.navigate().refresh();
            return new MainPage(driver);

//           Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean logout() {
        return true;
    }
}
