package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver webDriver) {
        driver = webDriver;
    }

    public ContactPage toMemberAdd() {
        driver.findElement(By.linkText("添加成员")).click();
        return new ContactPage(driver);
    }
}
