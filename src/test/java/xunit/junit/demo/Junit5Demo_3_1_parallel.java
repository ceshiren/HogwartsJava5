/**
 * projectName: Junit5Demo0410
 * fileName: Junit5Demo_3_1_parallel.java
 * packageName: com.junit5.xunit.junit.demo
 * date: 2021-04-10 下午2:12
 */
package xunit.junit.demo;

import util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_3_1_parallel
 * @packageName: com.junit5.xunit.junit.demo
 * @description: 并发场景测试
 * @data: 2021-04-10 下午2:12
 **/
public class Junit5Demo_3_1_parallel {
    @RepeatedTest(10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        long threadId = Thread.currentThread().getId();
        System.out.println("线程号+"+threadId+"为您计算当前报名人数为：" +result);
    }

    @RepeatedTest(10)
    public void synCountTest() throws InterruptedException {
        int result = Calculator.synCount(1);
        long threadId = Thread.currentThread().getId();
        System.out.println("线程号+"+threadId+"为您计算当前报名人数为：" +result);
    }
}