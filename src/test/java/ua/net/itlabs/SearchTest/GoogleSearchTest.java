package ua.net.itlabs.SearchTest;

import com.codeborne.selenide.*;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assert.assertEquals;

public class GoogleSearchTest {

    @Test
    public void testSearchAndFollowLink() {
        open("http://google.com/ncr");
        startSearch("Selenium automates browsers").pressEnter();
        searchResults.shouldHaveSize(10);
        searchResults.first().shouldHave(text("Selenium automates browsers"));
        followNthLink(0);
        $("#header>h1>a").shouldHave(exactText("Browser Automation"));
        assertEquals("http://www.seleniumhq.org/", url());
    }

    ElementsCollection searchResults = $$(".srg>.g");

    public SelenideElement startSearch(String text) {
        return $(By.name("q")).setValue(text);
    }

    public void followNthLink(int index) {
        searchResults.get(index).$(".r>a").click();
    }

}
