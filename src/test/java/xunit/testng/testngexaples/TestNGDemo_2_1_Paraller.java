package xunit.testng.testngexaples; /**
 * projectName: TestNGDemo0410
 * fileName: TestNGDemo_2_1_Paraller.java
 * packageName: PACKAGE_NAME
 * date: 2021-04-10 下午4:36
 */

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
public class TestNGDemo_2_1_Paraller {
    @Test(threadPoolSize = 2,invocationCount = 10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        long threadId = Thread.currentThread().getId();
        System.out.println("线程ID:"+threadId+"为您计算，结果为： "+result);
    }

    @Test(threadPoolSize = 2,invocationCount = 10)
    public void countTest02() throws InterruptedException {
        int result = Calculator.synCount(1);
        long threadId = Thread.currentThread().getId();
        System.out.println("线程ID:"+threadId+"为您计算，结果为： "+result);
    }
}