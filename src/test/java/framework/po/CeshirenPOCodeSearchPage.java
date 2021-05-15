package framework.po;

import org.openqa.selenium.By;

public class CeshirenPOCodeSearchPage extends POBasePage {
    public String get_first_title() {
//        click(By.cssSelector("topic-titl"));
        String text = driver.findElement(By.cssSelector(".topic-title")).getText();
        System.out.println(text);
        return text;
    }
}
