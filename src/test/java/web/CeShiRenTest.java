package web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CeShiRenTest {
    static WebDriver webDriver;
    @BeforeAll
    public static void init(){
        System.setProperty("webdriver.chrome.driver", "/Users/ashin/work/devruntime/chromedriver/89.0.4389/chromedriver");
        webDriver=new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
    @Test
    public void fun(){
        ///Users/ashin/work/devruntime/chromedriver/89.0.4389

        webDriver.get("https://www.baidu.com");
    }

    @Test
    public void searchTest(){
        try {
            ///Users/ashin/work/devruntime/chromedriver/89.0.4389
            webDriver.get("https://ceshiren.com");
            webDriver.findElement(By.id("search-button")).click();
            webDriver.findElement(By.id("search-term")).sendKeys("微信小程序自动化");
            webDriver.findElements(By.xpath("//span[text()='微信小程序自动化']")).get(1).click();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
