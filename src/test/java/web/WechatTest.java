package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WechatTest {
    static WebDriver webDriver;
    @BeforeAll
    public static void init(){
//        System.setProperty("webdriver.chrome.driver", "/Users/ashin/work/devruntime/chromedriver/89.0.4389/chromedriver");
        webDriver=new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown(){
//        webDriver.quit();
    }


    @Test
    public void saveCookie(){
        try {
            webDriver.get("https://work.weixin.qq.com/wework_admin/frame");
            Thread.sleep(10000);
            Set<Cookie> cookies = webDriver.manage().getCookies();
            webDriver.navigate().refresh();
            ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
            //todo: 使用getResource代替
            objectMapper.writeValue(new File("cookie.yaml"),cookies);
            cookies.forEach(cookie-> System.out.println(cookie.getName()+":"+cookie.getValue()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void loginTest(){
        try {
            webDriver.get("https://work.weixin.qq.com/wework_admin/frame");

            ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String, Object>>> typeReference=new TypeReference<List<HashMap<String, Object>>>(){};
           List<HashMap<String, Object>> cookies = objectMapper.readValue(new File("cookie.yaml"), typeReference);
           cookies.forEach(cookie->{
               webDriver.manage().addCookie(new Cookie(cookie.get("name").toString(),cookie.get("value").toString()));
           });

            webDriver.navigate().refresh();

            webDriver.findElement(By.id("menu_contacts")).click();
            webDriver.findElement(By.id("memberSearchInput")).sendKeys("霍格沃兹");
            webDriver.findElements(By.id("search_party_list")).get(0).click();
            webDriver.findElements(By.cssSelector(".js_add_member")).get(0).click();



//           Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
