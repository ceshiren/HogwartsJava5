/**
 * projectName: Junit5Demo0410
 * fileName: Junit5Demo_3_1_parallel.java
 * packageName: com.junit5.xunit.junit.demo
 * date: 2021-04-10 下午2:12
 */
package xunit.junit.demo;

import util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_3_1_parallel
 * @packageName: com.junit5.xunit.junit.demo
 * @description: 并发场景测试
 * @data: 2021-04-10 下午2:12
 **/
public class Junit5Demo_3_2_mixparallel {
    @RepeatedTest(10)
    public void add() throws InterruptedException {
        int result = Calculator.add(4, 2);
        System.out.println("加法计算结果： "+result);
        assertEquals(6,result);
    }

    @RepeatedTest(10)
    public void subtract() throws InterruptedException {
        int result = Calculator.subtract(4, 2);
        System.out.println("减法计算结果： "+result);
        assertEquals(2,result);
    }
}