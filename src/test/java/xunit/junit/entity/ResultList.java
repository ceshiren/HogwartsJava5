/**
 * projectName: Junit5Demo0410
 * fileName: ResultList.java
 * packageName: xunit.junit.entity
 * date: 2021-04-10 上午11:25
 */
package xunit.junit.entity;

import lombok.Data;

import java.util.List;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: ResultList
 * @packageName: xunit.junit.entity
 * @description: 结果列表
 * @data: 2021-04-10 上午11:25
 **/
@Data
public class ResultList {
    private List<ShellResult> resultList;
}