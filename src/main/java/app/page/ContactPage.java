package app.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class ContactPage extends BasePage {

    //    private By managerButton = By.xpath("(//android.widget.TextView)[3]");
    private By managerButton = By.id("h8l");


    public ContactPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public ContactPage addPerson() {
//        click(By.cssSelector(""))


        click(managerButton);
        click(By.cssSelector("*[text='添加成员']"));
        click(By.cssSelector("*[text*='手动输入']"));
        sendKeys(By.xpath("(//android.widget.EditText)[1]"), "seveniruby");
        sendKeys(By.xpath("(//android.widget.EditText)[2]"), "15600534764");
        click(By.cssSelector("*[text*='邀请']"));
        click(By.cssSelector("*[text*='保存']"));

        return this;

    }


    public void searchDepart() {

    }

    public DepartPage toDepart(String name) {
        click(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" +
                        name + "\").instance(0));"));
        return new DepartPage(driver);
    }

    public PersonPage searchPerson() {
        return new PersonPage(driver);
    }
}
