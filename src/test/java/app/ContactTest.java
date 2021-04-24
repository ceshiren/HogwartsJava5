package app;

import app.page.WeWork;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTest {
    @Test
    void addPerson() throws MalformedURLException {
        WeWork wework=new WeWork();
        String name=wework.startApp().toContact().addPerson().searchPerson().getName();
        assertThat(name, equalTo("ddd"));
    }
}
