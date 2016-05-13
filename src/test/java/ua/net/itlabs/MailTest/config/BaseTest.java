package ua.net.itlabs.MailTest.config;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void setup() {
        Configuration.timeout = 20000;

    }

}
