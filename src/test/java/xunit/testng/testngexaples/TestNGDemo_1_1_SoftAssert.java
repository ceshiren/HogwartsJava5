package xunit.testng.testngexaples; /**
 * projectName: TestNGDemo0410
 * fileName: TestNGDemo_1_1_SoftAssert.java
 * packageName: PACKAGE_NAME
 * date: 2021-04-10 下午4:29
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import util.Calculator;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: TestNGDemo_1_1_SoftAssert
 * @packageName: PACKAGE_NAME
 * @description: 软断言
 * @data: 2021-04-10 下午4:29
 **/
public class TestNGDemo_1_1_SoftAssert {
    @Test
    public void addTest() throws InterruptedException {
        int result01 = Calculator.add(4,2);
        System.out.println(result01);
        Assert.assertEquals(result01,6);

        int result02 = Calculator.add(4,2);
        System.out.println(result02);
        Assert.assertEquals(result02,2);

        int result03 = Calculator.add(4,2);
        System.out.println(result03);
        Assert.assertEquals(result03,2);

    }

    @Test
    public void softAddTest() throws InterruptedException {
        SoftAssert assertion = new SoftAssert();

        int result01 = Calculator.add(4,2);
        System.out.println(result01);
        assertion.assertEquals(result01,6);

        int result02 = Calculator.add(4,2);
        System.out.println(result02);
        assertion.assertEquals(result02,2);

        int result03 = Calculator.add(4,2);
        System.out.println(result03);
        assertion.assertEquals(result03,2);

        assertion.assertAll();

    }
}