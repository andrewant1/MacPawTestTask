package com.macpaw.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.macpaw.models.Product;
import com.macpaw.utilities.HTMLParser;
import com.macpaw.utilities.Mail;
import org.testng.Assert;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class TestPract {

    private static boolean compareMaps(Map<String, Product> map1, Map<String, Product> map2) {
        return map1.entrySet().containsAll(map2.entrySet()) && map2.entrySet().containsAll(map1.entrySet());
    }

    public static void main(String[] args) throws Exception {
        String userName = "andrewant78@gmail.com";
        String password = "A1xc23aq1";
        String subject = "Подтверждение регистрации";
        String from = "sales@rozetka.com.ua";


        String message = Mail.getMsgContent(userName, password, subject, from);
        HTMLParser parser = new HTMLParser(message);
        String link = parser.findLinkThatContains("confirm");
        System.out.println(link);



    }
}
