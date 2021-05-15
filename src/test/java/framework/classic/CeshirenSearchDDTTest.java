package framework.classic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

public class CeshirenSearchDDTTest {
    static WebDriver driver;

//    @BeforeAll
//    public static void beforeAll() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @AfterAll
//    public static void afterAll() {
//        driver.quit();
//    }

    @ParameterizedTest
    @MethodSource("data")
    public void runTestCase(TestCase testcase) {
        /**
         * 统一的步骤处理
         */
//        driver.get("https://ceshiren.com");
//        driver.findElement(By.id("search-button")).click();
//
//        String keyword = "内推 BAT";
//        driver.findElement(By.id("search-term")).sendKeys(keyword + Keys.ENTER);
//        String title = driver.findElements(By.cssSelector(".topic-title")).get(0).getText();

        testcase.run();


    }


    static Stream<TestCase> data() throws IOException {
        /**
         * 统一的数据读取
         */
//        return Stream.of(
//                "src/test/java/framework/CeshirenSearch5Test.yaml",
//                "src/test/java/framework/CeshirenSearch5Test.yaml"
//        );

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testcase = mapper.readValue(
                new File("src/test/java/framework/CeshirenSearchTest.yaml"),
                TestCase.class
        );
        return Stream.of(testcase);

        //todo: read from directory

    }
}
