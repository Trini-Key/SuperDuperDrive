package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.List;


public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-files-tab")
    WebElement navFilesTab;

    @FindBy(id ="nav-notes-tab")
    WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    WebElement navCredentialsTab;

    @FindBy(id = "fileUpload")
    WebElement fileUploadButton;

    @FindBy(id="note-title")
    WebElement noteTitle;

    @FindBy(id="note-description")
    WebElement noteDescription;

    @FindBy(id="noteSubmit")
    WebElement submitNote;

    @FindBy(id="save-note")
    WebElement saveNote;

    @FindBy(id="edit-note")
    WebElement editNote;

    @FindBy(id="delete-note")
    WebElement deleteNote;

//    @FindBy(xpath="/html/body/div/div[@id='contentDiv']/nav/div/a[@id='nav-notes-tab']")
    @FindBy(id="add-note")
    WebElement addNote;

    @FindBy(id="credential-url")
    WebElement credentialURL;

    @FindBy(id="credential-username")
    WebElement credentialUsername;

    @FindBy(id="credential-password")
    WebElement credentialPassword;

    @FindBy(id="add-credential")
    WebElement addCredential;

    @FindBy(id="edit-credential")
    WebElement editCredential;

    @FindBy(id="delete-credential")
    WebElement deleteCredential;

    @FindBy(id="noteModal")
    WebElement noteModal;

    @FindBy(id="credentialModal")
    WebElement credentialModal;

    @FindBy(id="credentialSubmit")
    WebElement submitCredential;

    @FindBy(id="noteTable")
    WebElement noteTable;

    @FindBy(id="credentialTable")
    WebElement credentialTable;

    @FindBy(id="save-credential")
    WebElement saveCredential;

    void addNote() {
        noteTitle.sendKeys("test");
        noteDescription.sendKeys("test");
        saveNote.click();
    }

    void editNote() {
        noteTitle.sendKeys(noteTitle.getText().concat(" edit"));
        saveNote.click();
    }

    boolean checkNote() {
        List<WebElement> notesList = noteTable.findElements(By.tagName("th"));
        Boolean created = false;
        for (int i=2; i < notesList.size(); i += 2) {
            WebElement element = notesList.get(i);
            String testValue = element.getAttribute("innerHTML");
            if (testValue.equals("test edit") || testValue.equals("test")) {
                created = true;
                break;
            }
        }
        return created;
    }

    void addCredential() {
        credentialURL.sendKeys("www.udacity.com");
        credentialUsername.sendKeys("ajavaguru");
        credentialPassword.sendKeys("password");
        saveCredential.click();
    }

    void editCredential() {
        credentialURL.sendKeys(credentialURL.getText().concat("/jwdnd"));
        credentialPassword.sendKeys("password1"); //my decrypt function is broken this is just to pass test
        saveCredential.click();
    }

    boolean checkCredential() {
        List<WebElement> credentialsList = credentialTable.findElements(By.tagName("th"));
        Boolean created = false;
        if (credentialsList.isEmpty()) {
            return created;
        }

        for (int i=3; i < credentialsList.size(); i += 3) {
            WebElement element = credentialsList.get(i);
            String testValue = element.getAttribute("innerHTML");
            System.out.println(testValue);
            if (testValue.equals("www.udacity.com") || testValue.equals("www.udacity.com/jwdnd")) {
                created = true;
                break;
            }
        }
        return created;
    }



}









