package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage toMemberAdd() {
//        driver.findElement(By.linkText("添加成员")).click();
        click(By.linkText("添加成员"));
        return new ContactPage(driver);
    }

    public ContactPage toContactPage() {
        click(By.linkText("通讯录"));
        return new ContactPage(driver);
    }
}
