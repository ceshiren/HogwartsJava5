/**
 * projectName: Junit5Demo0410
 * fileName: Junit5Demo_5_1_Launcher.java
 * packageName: com.junit5.xunit.junit.demo
 * date: 2021-04-10 下午5:15
 */
package xunit.junit.demo;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.ClassNameFilter.excludeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Junit5Demo_5_1_Launcher
 * @packageName: com.junit5.xunit.junit.demo
 * @description: 代码API触发
 * @data: 2021-04-10 下午5:15
 **/
public class Junit5Demo_5_1_Launcher {
    public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage("xunit/junit/junitexamples")
//                        selectClass(ClassATest.class),
//                        selectMethod("examples.packageA.ClassATest#testCaseA")
                ).filters(
//                        includeClassNamePatterns(".*ATest")
                        excludeClassNamePatterns(".*ATest")
                ).build();
        Launcher launcher = LauncherFactory.create();

        // Register a listener of your choice
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);
    }
}