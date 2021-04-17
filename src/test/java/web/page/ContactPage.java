package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ContactPage add(String name, String account, String mobile, HashMap<String, String> data) {
//        driver.findElement(By.name("username")).sendKeys(name);
        sendKeys(By.name("username"), name);
//        driver.findElement(By.name("acctid")).sendKeys(account);
        sendKeys(By.name("acctid"), account);
//        driver.findElement(By.name("mobile")).sendKeys(mobile);
        sendKeys(By.name("mobile"), mobile);
//        driver.findElement(By.linkText("保存")).click();
        click(By.linkText("保存"));

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
        String name = driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
        return name;
    }

    public void addDepart(String name, String parent) {
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"), name);
        click(By.linkText("选择所属部门"));
//        click(By.linkText("霍格沃兹学院"));
        driver.findElement(By.tagName("form")).findElement(By.linkText(parent)).click();
        click(By.linkText("确定"));
    }
}

