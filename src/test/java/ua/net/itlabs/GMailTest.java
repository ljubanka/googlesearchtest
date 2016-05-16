package ua.net.itlabs;

import com.codeborne.selenide.*;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.net.itlabs.pages.Gmail;
import ua.net.itlabs.pages.Mails;
import ua.net.itlabs.pages.Menu;
import ua.net.itlabs.testdata.LoginData;

public class GMailTest {

    @BeforeClass
    public static void setup() {
        Configuration.timeout = 20000;
    }

    @Test
    public void testSendAndSearchEmail()  {
        Gmail.vizit();
        Gmail.logIn(LoginData.email, LoginData.password);

        String subject  = Helpers.getUniqueText("Autotest email ");
        Mails.send(LoginData.email, subject);
        Menu.refresh();
        Mails.assertMail(0, subject);

        Menu.goToSent();
        Mails.assertMail(0, subject);

        Menu.goToInbox();
        Mails.searchBySubject(subject);
        Mails.assertMails(subject);
    }

}
