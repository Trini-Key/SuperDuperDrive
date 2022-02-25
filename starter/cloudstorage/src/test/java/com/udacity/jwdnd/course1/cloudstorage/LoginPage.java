package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="login-button")
    WebElement login;

    @FindBy(id="signup-link")
    WebElement signup;

//    @FindBy(xpath="/html/body/div/form/div[3]/input")
    @FindBy(id="inputUsername-login")
    WebElement uName;
    
//    @FindBy(xpath="/html/body/div/form/div[4]/input")
    @FindBy(id="inputPassword-login")
    WebElement uPass;

    void login() {
        uName.sendKeys("ttester");
        uPass.sendKeys("passkey");
        login.click();
    }

}
