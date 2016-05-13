package ua.net.itlabs.MailTest;


import com.codeborne.selenide.*;
import org.junit.Test;
import org.openqa.selenium.By;
import ua.net.itlabs.MailTest.config.BaseTest;
import ua.net.itlabs.MailTest.config.LoginData;
import ua.net.itlabs.MailTest.config.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.System.*;
import static ua.net.itlabs.MailTest.config.TestData.SUBJECT;

public class GMailTest extends BaseTest {

    @Test
    public void testSendAndSearchEmail()  {
        open("http://gmail.com");
        login(LoginData.Mail, LoginData.Pass);

        //send email
        SUBJECT  = SUBJECT + currentTimeMillis();
        sendEmail(LoginData.Mail, SUBJECT);
        assertEmail(0, SUBJECT);
        filterSentMail();
        assertEmail(0, SUBJECT);

        searchByButtonClick(SUBJECT);
        assertEmailsCount(1);
    }

    public ElementsCollection emailsShown = $$(".y6");

    public void login(String email, String pass) {
        $("#Email").setValue(email);
        $("#next").click();
        $("#Passwd").setValue(pass);
        $("#signIn").click();
    }

    public void sendEmail(String to, String subject) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).setValue(to);
        $(By.name("subjectbox")).setValue(subject);
        $(byText("Send")).click();
    }

    public void filterSentMail() {
         $(byTitle("Sent Mail")).click();
    }

    public void searchByButtonClick(String text) {
        $(byName("q")).setValue(text);
        $("#gbqfb").click();
    }

    public void assertEmailsCount(int count) {
        $$("div[gh=\"tl\"] tr").shouldHaveSize(count);
    }

    public void assertEmail(int index, String text) {
        emailsShown.get(index).shouldHave(text(text));
    }
}
