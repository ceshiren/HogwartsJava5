package app;

import app.page.WeWork;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class DepartTest {

    @Test
    void subDepart() throws MalformedURLException {
        new WeWork().startApp().toContact().toDepart("Java定向5期").addDepart("11")
                .toDepart("11").deleteCurrentDepart();

    }
}
