package com.macpaw.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.net.URI;
import java.net.URISyntaxException;

public class BasePage {

    private String id;

    public  void click(SelenideElement element){
        element.click();
    }



    public  String getText(SelenideElement element){
       return element.getText();
    }

    public static String getPageURL(){
        String  pageURL= WebDriverRunner.url();
        return  pageURL;
    }

    public static String getKey(){
        String  idx = BasePage.getPageURL();
        URI uri = null;
        try {
            uri = new URI(idx);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] segments = uri.getPath().split("/");
        String id = segments[segments.length-1];
        System.out.println(id);
        return id;
    }




}

