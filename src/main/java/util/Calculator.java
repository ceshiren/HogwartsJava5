package util; /**
 * projectName: Junit5Demo
 * fileName: Calculator.java
 * packageName: PACKAGE_NAME
 * date: 2020-11-01 6:56 上午
 */

/**
 * @version: V1.0
 * @author: kuo'hai
 * @className: Calculator
 * @packageName: PACKAGE_NAME
 * @description: 计算器类-测试靶场
 * @data: 2020-11-01 6:56 上午
 **/
public class Calculator {
    public static int result = 0;

    public static int add(int x ,int y ) throws InterruptedException {
        result = x+y;
        Thread.sleep(1000);
        return result;
    }
    public  static int count(int x) throws InterruptedException {
        int i= result;
        Thread.sleep(1000);
        result = i + x;
        return result;
    }
    public synchronized static int synCount(int x) throws InterruptedException {
        int i= result;
        Thread.sleep(1000);
        result = i + x;
        return result;
    }
    public static int subtract(int x,int y) throws InterruptedException {
        result = x-y;
        Thread.sleep(1000);
        return result;
    }
    public static int multiply(int x,int y){
        result = x*y;
        return result;
    }
    public static int divide(int x,int y){
        result = x/y;
        return result;
    }
    public static void clear(){
        result =0;
        System.out.println("当前结果已清零！");
    }
    public static int counttest(int x) throws InterruptedException {
        int i = 0;
        Thread.sleep(1000);
        i += x;
        return i;
    }
}