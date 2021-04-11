/**
 * projectName: Junit5Demo0410
 * fileName: Junit5Demo_2_1_DynamicTestDemo.java
 * packageName: com.junit5.xunit.junit.demo
 * date: 2021-04-10 上午11:12
 */
package xunit.junit.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import xunit.junit.entity.ResultList;
import xunit.junit.entity.ShellResult;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_2_1_DynamicTestDemo
 * @packageName: com.junit5.xunit.junit.demo
 * @description: 动态测试演示
 * @data: 2021-04-10 上午11:12
 **/
public class Junit5Demo_2_1_DynamicTestDemo {
    @TestFactory
    Collection<DynamicTest> dynamicTestCollection(){
        return Arrays.asList(
                dynamicTest("1st dynamic test",() -> {
                    assertTrue(true);
                }),
                dynamicTest("2nd dynamic test",()->assertEquals(4,4))
        );
    }
    @Test
    public void entityTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ResultList resultList = objectMapper.readValue(new File("src/main/resources/data/shell_test_result.yaml"), ResultList.class);
        System.out.println("debugger!");
    }
    @TestFactory
    Collection<DynamicTest> runShellResult() throws IOException {
        List<DynamicTest> dynamicTestList = new ArrayList<>();
        //yaml反序列化
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ResultList resultList = objectMapper.readValue(new File("src/main/resources/data/shell_test_result.yaml"), ResultList.class);
        System.out.println("debugger!");
        //动态遍历生成测试方法
        for(ShellResult shellResult : resultList.getResultList()){
            dynamicTestList.add(dynamicTest(shellResult.getCaseName(),() -> {
                assertTrue(shellResult.isResult());
            }));
        }
        return dynamicTestList;
    }
}