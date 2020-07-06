package com.macpaw.pages;

import com.codeborne.selenide.*;
import com.macpaw.models.Product;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage {

    SelenideElement enterLink = $("a.header-topline__user-link.link-dashed");
    SelenideElement searchInput = $("input.search-form__input.ng-untouched.ng-pristine.ng-valid");
    SelenideElement findButton = $("button.button.button_color_green.button_size_medium.search-form__submit");
    SelenideElement iphoneLink = $("span.goods-tile__title:first-child");
    //SelenideElement iphoneLink = $(By.xpath("//span[contains(text(),'Apple iPhone 11 Pro 512GB Silver')]"));
    SelenideElement priceCheck = $("p.product-prices__big");
    SelenideElement nameCheck = $("h1.product__title");
    SelenideElement backetButton = $("app-buy-button.toOrder button.buy-button.button.button_with_icon.button_color_green.button_size_large");
    Map<String, Product>  expProductDetails = new HashMap<String, Product>();





    public void openMainPage() {
        open("/");
    }

    public MainPage register(String name, String email, String password){
        click(enterLink);
        new RegisterWindow()
                .clickRegistrationLing()
                .setFields(name,email,password);
        return  this;
    }

    public MainPage searchItem(String text){
        searchInput.setValue(text);
        click(findButton);
        return this;
    }

    public MainPage selectItem(){
        click(iphoneLink);
        nameCheck.shouldBe(Condition.visible);
        backetButton.shouldBe(Condition.visible);
        priceCheck.shouldBe(Condition.visible);
        return this;
    }



    public BasketPage toBasket(){
        click(backetButton);
        return new  BasketPage();
    }




    public Map<String,Product> collectExpectedResults(){
          String price = priceCheck.getText();
          price= price.substring(0, price.length() - 1);

        expProductDetails.put(getKey(), new Product(price,nameCheck.getText()));
        return expProductDetails;
    }




   public String getExpectedPrice(){
       return   collectExpectedResults().get(getKey()).getPrice();

    }

    public String getExpectedDescription(){
       return collectExpectedResults().get(getKey()).getDescription();
    }



}

