package framework.classic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CeshirenSearchTest {
    static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void search() {
        driver.get("https://ceshiren.com");
        driver.findElement(By.id("search-button")).click();

        String keyword = "内推 BAT";
        driver.findElement(By.id("search-term")).sendKeys(keyword + Keys.ENTER);
        String title = driver.findElement(By.cssSelector(".topic-title")).getText();
        assertThat(title, containsString(keyword));

    }
}
