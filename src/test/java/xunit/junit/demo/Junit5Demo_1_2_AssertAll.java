/**
 * projectName: Junit5Demo0410
 * fileName: Junit5Demo_1_1_Assert.java
 * packageName: com.junit5.xunit.junit.demo
 * date: 2021-04-10 上午10:28
 */
package xunit.junit.demo;

import util.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_1_1_Assert
 * @packageName: com.junit5.xunit.junit.demo
 * @description: 断言示例
 * @data: 2021-04-10 上午10:28
 **/
public class Junit5Demo_1_2_AssertAll {
    @Test
    public void addTest() throws InterruptedException {
        int result = Calculator.add(4,2);
        System.out.println("加法计算结果： "+ result);

        int result01 = Calculator.add(1,-1);
        System.out.println("加法计算结果： "+ result01);

        int result02 = Calculator.add(0,2);
        System.out.println("加法计算结果： "+ result02);

        assertAll("计算结果校验！",
                    ()->{assertEquals(6,result);
                        System.out.println("Done01!");
                    },
                ()->assertEquals(1,result01),
                ()->assertEquals(1,result02)
        );
    }


    @Test
    public void addTest02() throws InterruptedException {
        ArrayList<Executable> assertList = new ArrayList<>();
        int result = Calculator.add(4,2);
        System.out.println("加法计算结果： "+ result);
        assertList.add(() -> assertEquals(6,result));
        int result01 = Calculator.add(1,-1);
        System.out.println("加法计算结果： "+ result01);
        assertList.add(() -> assertEquals(6,result01));
        int result02 = Calculator.add(0,2);
        System.out.println("加法计算结果： "+ result02);
        assertList.add(() -> assertEquals(6,result02));
        System.out.println("debugger!");

        assertAll("",assertList.stream());
    }
}