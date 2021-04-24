package app.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    public MainPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public ContactPage toContact(){
//        driver.findElement(By.xpath("//*[@text='通讯录']")).click();
//        click(By.xpath("//*[@text='通讯录']"));
        click(By.cssSelector("*[text='通讯录']"));
        return new ContactPage(driver);
    }
}
