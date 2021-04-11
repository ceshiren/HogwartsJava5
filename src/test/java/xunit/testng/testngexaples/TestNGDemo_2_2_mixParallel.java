package xunit.testng.testngexaples; /**
 * projectName: TestNGDemo0410
 * fileName: TestNGDemo_2_1_Paraller.java
 * packageName: PACKAGE_NAME
 * date: 2021-04-10 下午4:36
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import util.Calculator;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: TestNGDemo_2_1_Paraller
 * @packageName: PACKAGE_NAME
 * @description: 并发场景
 * @data: 2021-04-10 下午4:36
 **/
public class TestNGDemo_2_2_mixParallel {
    @Test(threadPoolSize = 2,invocationCount = 10)
    public void addTest() throws InterruptedException {
        int result = Calculator.add(4,2);
        System.out.println("加法计算结果为： "+result);
        Assert.assertEquals(result,6);
    }

    @Test(threadPoolSize = 2,invocationCount = 10)
    public void subTest02() throws InterruptedException {
        int result = Calculator.subtract(4,2);
        System.out.println("减法计算结果为： "+result);
        Assert.assertEquals(result,2);
    }
}