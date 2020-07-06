package com.macpaw.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.macpaw.models.Product;
import com.macpaw.pages.BasketPage;
import com.macpaw.pages.MainPage;
import com.macpaw.properties.PropertiesConfig;
import com.macpaw.tests.configuration.BaseTest;
import com.macpaw.utilities.HTMLParser;
import com.macpaw.utilities.Mail;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

public class RegisterTests extends BaseTest {
    SelenideElement title = $("a.header-topline__user-link.link-dashed");
    SelenideElement confirmationMessage = $("div.pos-fix.sprite-side.message.code2");

    MainPage user = new MainPage();
    BasketPage basket;
    SoftAssert softAssert = new SoftAssert();
    PropertiesConfig propConfig = new PropertiesConfig();


    @BeforeTest
    public void setUp() {
        propConfig.loadProperties();
    }


    @Test(priority = 1)
    public void userRegistration() {
        user.openMainPage();
        user.register(propConfig.getProperty("userName"), propConfig.getProperty("userEmail"), propConfig.getProperty("userPassword"));
        title.shouldHave(Condition.text(propConfig.getProperty("userName")));
        user.searchItem(propConfig.getProperty("product"))
                .selectItem();
        String expectedPrice = user.getExpectedPrice();
        String expectedDescription = user.getExpectedDescription();
        basket = user.toBasket();
        String actualPrice = basket.getActualPrice();
        String actualDescription = basket.getActualDescription();
        softAssert.assertEquals(expectedDescription, actualDescription);
        softAssert.assertEquals(expectedPrice, actualPrice);
        softAssert.assertAll();


    }

    //@Test(priority = 2, dependsOnMethods = "userRegistration")
    @Test
    public void checkRegistrationMail() throws Exception {
        String message = Mail.getMsgContent(propConfig.getProperty("userEmail"), propConfig.getProperty("userEmailPassword"), propConfig.getProperty("subject"), propConfig.getProperty("from"));
        HTMLParser parser = new HTMLParser(message);
        String confirmationLink = parser.findLinkThatContains("confirm");
        System.out.println(confirmationLink);
        Selenide.open(confirmationLink);
        assertEquals(confirmationMessage.getText(), "Адрес электронной почты уже подтвержден, но все равно спасибо, что зашли");

    }


}
