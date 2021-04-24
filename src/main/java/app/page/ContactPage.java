package app.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class ContactPage extends BasePage{
    public ContactPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public ContactPage addPerson(){
//        click(By.cssSelector(""))

        click(By.xpath("(//android.widget.TextView)[3]"));
        click(By.cssSelector("*[text='添加成员']"));
        click(By.cssSelector("*[text*='手动输入']"));
        sendKeys(By.xpath("(//android.widget.EditText)[1]"), "seveniruby");
        sendKeys(By.xpath("(//android.widget.EditText)[2]"), "15600534764");
        click(By.cssSelector("*[text*='邀请']"));
        click(By.cssSelector("*[text*='保存']"));

        return this;

    }

    public void addDepart(){

    }

    public PersonPage searchPerson(){
        return new PersonPage(driver);
    }
}
