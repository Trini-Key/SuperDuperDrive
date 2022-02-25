package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


public class ResultPage {
    @FindBy(id="go-home-success")
    WebElement redirectHome1;

    @FindBy(id="go-home-error")
    WebElement redirectHome2;

    @FindBy(id="go-home-other")
    WebElement redirectHome3;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    void goHome1() {
        redirectHome1.click();
    }

    void goHome2() {
        redirectHome2.click();
    }

    void goHome3() {
        redirectHome3.click();
    }

}
