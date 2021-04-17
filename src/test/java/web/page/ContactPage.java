package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ContactPage {
    WebDriver driver;

    public ContactPage(WebDriver webDriver) {
        driver = webDriver;
    }

    public ContactPage add(String name, String account, String mobile, HashMap<String, String> data) {
        driver.findElement(By.name("username")).sendKeys(name);
        driver.findElement(By.name("acctid")).sendKeys(account);
        driver.findElement(By.name("mobile")).sendKeys(mobile);
        driver.findElement(By.linkText("保存")).click();

        return this;

    }

    public void addWithFail() {

    }


    public void delete() {

    }

    public ContactPage search(String accountId) {
        driver.findElement(By.id("memberSearchInput")).sendKeys(accountId);
        return this;

    }

    public void importMember() {

    }

    public void exportMember() {

    }

    public String getMember() {
        String name=driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
        return name;
    }
}

