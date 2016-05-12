package ua.net.itlabs;

import com.codeborne.selenide.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assert.assertEquals;

public class GoogleSearchSmokeTest {

    @BeforeClass
    public void setup() {
        Configuration.timeout = 40000;
    }

    @Test
    public void testSearchCommonFlow() throws InterruptedException {
        open("http://google.com/ncr");
        searchField.shouldBe(visible).setValue("Selenium automates browsers").pressEnter();
        searchResults.shouldHaveSize(10);
        firstSearchResult.shouldHave(exactText("Selenium automates browsers"));
        firstSearchResultLink.click();
        seleniumHeader.shouldHave(exactText("Browser Automation"));
        assertEquals("http://www.seleniumhq.org/", url());
    }

    SelenideElement searchField = $("[name=q]");
    ElementsCollection searchResults = $$(".srg>.g");
    SelenideElement firstSearchResult = searchResults.first().$(".st>em");
    SelenideElement firstSearchResultLink = searchResults.first().$(".r>a");
    SelenideElement seleniumHeader = $("#header>h1>a");

}
