package com.macpaw.tests.configuration;

import com.codeborne.selenide.Configuration;
import com.macpaw.models.Product;

import java.util.Map;

public class BaseTest {
    {
        Configuration.baseUrl = "https://rozetka.com.ua/";
        Configuration.timeout = 6000;
    }





}
