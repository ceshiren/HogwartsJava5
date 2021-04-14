package web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WechatReuseTest {
    @Test
    public void reuseTest(){
        //Change chrome driver path accordingly
        System.setProperty("webdriver.chrome.driver", "/Users/ashin/work/devruntime/chromedriver/89.0.4389/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(webDriver.getTitle());
        webDriver.findElement(By.id("menu_contacts")).click();
        webDriver.findElement(By.id("memberSearchInput")).sendKeys("霍格沃兹");
        webDriver.findElements(By.id("search_party_list")).get(0).click();
        webDriver.findElements(By.cssSelector(".js_add_member")).get(0).click();
    }
}
