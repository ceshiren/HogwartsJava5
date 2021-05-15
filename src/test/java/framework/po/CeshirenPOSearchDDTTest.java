package framework.po;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class CeshirenPOSearchDDTTest {
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
    public void runTestCase(POTestCase testcase) {
        /**
         * 统一的步骤处理
         */
        testcase.run();


    }


    static Stream<POTestCase> data() throws IOException {
        /**
         * 统一的数据读取
         */
//        return Stream.of(
//                "src/test/java/framework/CeshirenSearch5Test.yaml",
//                "src/test/java/framework/CeshirenSearch5Test.yaml"
//        );

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        POTestCase testcase = mapper.readValue(
                new File("src/test/java/framework/po/CeshirenPOSearchTest.yaml"),
                POTestCase.class
        );
        return Stream.of(testcase);

        //todo: read from directory

    }
}
