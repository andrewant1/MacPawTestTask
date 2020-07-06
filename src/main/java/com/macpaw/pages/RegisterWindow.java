package com.macpaw.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegisterWindow extends BasePage{

    SelenideElement registrationLink = $("a.auth-modal__register-link" );
    SelenideElement  inputName = $("fieldset.form__row.js-name input.ng-untouched.ng-pristine.ng-invalid");
    SelenideElement  inputEmail= $("fieldset.form__row.js-contact input.ng-untouched.ng-pristine.ng-invalid");
    SelenideElement inputPassword = $("div.form__row_with_button input.ng-untouched.ng-pristine.ng-invalid");
    SelenideElement submitButton = $("button.button.button_size_large.button_color_green.auth-modal__submit");


    public  RegisterWindow clickRegistrationLing(){
        click(registrationLink);
        return this;
    }

    public MainPage setFields(String name, String email, String password ){
        inputName.setValue(name);
        inputEmail.setValue(email);
        inputPassword.setValue(password);
        click(submitButton);
        return new MainPage();
    }

}
