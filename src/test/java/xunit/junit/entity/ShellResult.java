/**
 * projectName: Junit5Demo0410
 * fileName: ShellResult.java
 * packageName: xunit.junit.entity
 * date: 2021-04-10 上午11:21
 */
package xunit.junit.entity;

import lombok.Data;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: ShellResult
 * @packageName: xunit.junit.entity
 * @description: shell脚本执行结果
 * @data: 2021-04-10 上午11:21
 **/
@Data
public class ShellResult {
    private String caseName;
    private boolean result;
}