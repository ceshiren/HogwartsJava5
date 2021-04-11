package xunit.testng.testngexaples; /**
 * projectName: TestNGDemo
 * fileName: TestNGDemo_1_1.java
 * packageName: PACKAGE_NAME
 * date: 2020-11-01 7:48 上午
 */

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.Calculator;
/**
 * 1、编写基础脚本
 * 2、增加断言
 */

/**
 * @version: V1.0
 * @author: wenxiaolong
 * @className: TestNGDemo_1_1
 * @packageName: PACKAGE_NAME
 * @description: TestNGDemo_1_1
 * @data: 2020-11-01 7:48 上午
 **/
@Epic("Epic计算器项目")
@Feature("Feature冒烟测试用例")
public class TestNGDemo_3_1_Allure2 {

    @BeforeMethod
    public void clearResult(){
        Calculator.clear();
        System.out.println("计算器结果清零！");
    }
    @Description("Description加法测试")
    @Story("Story加法测试")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("123")
    @Link(name ="测试社区：",type = "mylink",url = "https://ceshiren.com/t/topic/7611/4")
    @Test(priority = 1)
    public void addTest() throws InterruptedException {
        SoftAssert assertion = new SoftAssert();

        int result01 = Calculator.add(4,2);
        System.out.println("完成加法计算，结果为："+result01);
        assertion.assertEquals(result01,6);

        int result02 = Calculator.add(4,2);
        System.out.println("完成加法计算，结果为："+result02);
        assertion.assertEquals(result02,6);

        int result03 = Calculator.add(4,2);
        System.out.println("完成加法计算，结果为："+result03);
        assertion.assertEquals(result03,6);
        assertion.assertAll();
        Allure.addAttachment("脚本名称", "减法测试用例！");
        Allure.addAttachment("pic","image/png",this.getClass().getResourceAsStream("/pic01.png"),".png");

    }
    @Test(priority = 2)
    public void subtractTest() throws InterruptedException {
        int result = Calculator.subtract(4,2);
        System.out.println("完成减法计算，结果为："+result);
        Assert.assertEquals(result,2);
    }
    @Test(priority = 3)
    public void multiplyTest() throws InterruptedException {
        int result = Calculator.multiply(4,2);
        System.out.println("完成乘法计算，结果为："+result);
        Assert.assertEquals(result,8);
    }
    @Test(priority = 4)
    public void divideTest() throws InterruptedException {
        int result = Calculator.divide(4,2);
        System.out.println("完成除法计算，结果为："+result);
        Assert.assertEquals(result,2);
    }
    @Test(priority = 5)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println("完成计数器计数，结果为："+result);
        Assert.assertEquals(result,4);
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}