package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

     @FindBy(id="success-msg")
     WebElement signupSuccess;

     @FindBy(id="redirect-login")
     WebElement login;

     @FindBy(id="inputFirstName")
     WebElement firstName;

     @FindBy(id="inputLastName")
     WebElement lastName;

     @FindBy(id="inputUsername")
     WebElement username;

     @FindBy(id="inputPassword")
     WebElement password;

     @FindBy(id="submit-button")
     WebElement submitButton;

     @FindBy(id="login-link")
     WebElement loginLink;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    void signup() {
        firstName.sendKeys("Test");
        lastName.sendKeys("Tester");
        username.sendKeys("ttester");
        password.sendKeys("passkey");
        submitButton.click();
        loginLink.click();
    }


}
