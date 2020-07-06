package com.macpaw.pages;

import com.codeborne.selenide.SelenideElement;
import com.macpaw.models.Product;


import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class BasketPage extends BasePage {
    SelenideElement actualPrice = $("span.cart-modal__check-digits");
    SelenideElement actualDescription = $("a.cart-modal__title");

    Map<String,Product> actProductDetails = new HashMap<String, Product>();



    public Map<String,Product> collectActualResults(){
        actProductDetails.put(getKey(), new Product(actualPrice.getText(),actualDescription.getText()));
        return actProductDetails;
    }

    public String getActualPrice(){
        return collectActualResults().get(getKey()).getPrice();

    }

    public String getActualDescription() {
        return collectActualResults().get(getKey()).getDescription();
    }

}
