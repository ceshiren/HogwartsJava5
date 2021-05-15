package framework.po;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


//todo: 支持数据参数化
//todo: 支持page object
//todo: web app service
//todo: 命令行参数支持
//todo: 目录读取
//todo: ...

public class POTestCase {
    public String name;
    public List<HashMap<String, Object>> after_all;
    public List<HashMap<String, Object>> before_all;
    public List<HashMap<String, Object>> after;
    public List<HashMap<String, Object>> before;
    public List<HashMap<String, Object>> steps;

    public void run() {
        AtomicReference<By> default_by = new AtomicReference<>();
        AtomicReference<POBasePage> lastPage = new AtomicReference<>();

        steps.forEach(step -> {
            step.entrySet().forEach((entry) -> {
                String action = entry.getKey().toLowerCase();
                Object value = entry.getValue();

                String[] keyArray = action.split("\\.");
                if (keyArray.length > 1) {
                    String poName = keyArray[0];
                    String poMethod = keyArray[1];
                    System.out.println(String.format("%s.%s %s", poName, poMethod, value));

                    if (poMethod.equals("new")) {
                        POBasePage currentPage = null;

                        if (lastPage.get() == null) {
                            currentPage = POBasePage.load(
                                    String.format("src/test/java/framework/po/%s.yaml",
                                            value
                                    ),
                                    null
                            );

                        } else {
                            currentPage = POBasePage.load(
                                    String.format("src/test/java/framework/po/%s.yaml",
                                            value
                                    ),
                                    lastPage.get().driver
                            );

                        }
                        lastPage.set(currentPage);

                        POStore.getInstance().setPO(poName, currentPage);
                    } else {
                        POStore.getInstance().getPO(poName).runPOMethod(poMethod);
                    }

                } else {
                    System.out.println(String.format("%s %s", action, value));
                }


            });
        });
    }
}
