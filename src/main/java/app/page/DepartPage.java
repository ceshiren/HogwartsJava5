package app.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class DepartPage extends BasePage {
    private By closeButton=By.id("h8g");
    private By managerButton = By.id("h8l");

    public DepartPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public DepartPage addDepart(String name){
        click(managerButton);
        click(By.cssSelector("*[text*='添加子部门']"));
        sendKeys(By.cssSelector("*[text*='请输入']"), name);
        click(By.cssSelector("*[text*='确定']"));
        click(closeButton);
        return this;
    }

    public DepartPage toDepart(String name){
        click(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" +
                        name + "\").instance(0));"));
        return new DepartPage(driver);
    }

    public DepartPage deleteCurrentDepart(){
        click(managerButton);
        click(By.cssSelector("*[text*='更多管理']"));
        click(By.cssSelector("*[text*='删除']"));
        click(By.cssSelector("*[text*='确定']"));

        click(closeButton);
        click(By.id("h86"));
        return this;
    }
}
