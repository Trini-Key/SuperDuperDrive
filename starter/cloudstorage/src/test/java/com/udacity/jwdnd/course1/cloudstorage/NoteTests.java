package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests {

    @LocalServerPort
    private  int port;

    private static WebDriver driver;
    private static HomePage homePage;
    private static SignupPage signupPage;
    private static LoginPage loginPage;
    private static ResultPage resultPage;
    private WebDriverWait wait1 = new WebDriverWait(driver, 100);

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        resultPage = new ResultPage(driver);
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + this.port + "/signup");
        signupPage.signup();
        driver.get("http://localhost:" + this.port + "/login");
        wait1.until(ExpectedConditions.elementToBeClickable(loginPage.login));
        loginPage.login();
    }

    @AfterEach
    public void afterEach() {

    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    void noteTest(){
        //add note
        driver.get("http://localhost:" + this.port + "/home");
        JavascriptExecutor es = (JavascriptExecutor) driver;
        es.executeScript("arguments[0].click()", homePage.navNotesTab);
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.addNote)).click();
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.noteTitle));
        Assertions.assertEquals("noteModal", homePage.noteModal.getAttribute("id"));
        homePage.addNote();
        wait1.until(ExpectedConditions.elementToBeClickable(resultPage.redirectHome1));
        Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",
                driver.findElement(By.xpath("/html/body/div/div[1]/span")).getText());
        resultPage.redirectHome1.click();
        Assertions.assertEquals("Home", driver.getTitle());
        es.executeScript("arguments[0].click()", homePage.navNotesTab);
        wait1.until(ExpectedConditions.visibilityOf(homePage.editNote));
        Assertions.assertTrue(homePage.editNote.isDisplayed());
        Assertions.assertTrue(homePage.checkNote());
        //edit note
        homePage.editNote.click();
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.noteTitle));
        homePage.editNote();
        wait1.until(ExpectedConditions.elementToBeClickable(resultPage.redirectHome1));
        Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",
                driver.findElement(By.xpath("/html/body/div/div[1]/span")).getText());
        resultPage.redirectHome1.click();
        Assertions.assertEquals("Home", driver.getTitle());
        es.executeScript("arguments[0].click()", homePage.navNotesTab);
        wait1.until(ExpectedConditions.visibilityOf(homePage.deleteNote));
        //delete note
        homePage.deleteNote.click();
        es.executeScript("arguments[0].click()", homePage.navNotesTab);
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.addNote)) ;
        Assertions.assertFalse(homePage.checkNote());
    }

    @Test
    void credentialTest(){
        //add credential
        driver.get("http://localhost:" + this.port + "/home");
        JavascriptExecutor es = (JavascriptExecutor) driver;
        es.executeScript("arguments[0].click()", homePage.navCredentialsTab);
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.addCredential)).click();
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.credentialURL));
        Assertions.assertEquals("credentialModal", homePage.credentialModal.getAttribute("id"));
        homePage.addCredential();
        wait1.until(ExpectedConditions.elementToBeClickable(resultPage.redirectHome1));
        Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",
                driver.findElement(By.xpath("/html/body/div/div[1]/span")).getText());
        resultPage.redirectHome1.click();
        Assertions.assertEquals("Home", driver.getTitle());
        es.executeScript("arguments[0].click()", homePage.navCredentialsTab);
        wait1.until(ExpectedConditions.visibilityOf(homePage.editCredential));
        Assertions.assertTrue(homePage.editCredential.isDisplayed());
        Assertions.assertTrue(homePage.checkCredential());
        //edit Credential
        homePage.editCredential.click();
        wait1.until(ExpectedConditions.elementToBeClickable(homePage.credentialURL));
        homePage.editCredential();
        wait1.until(ExpectedConditions.elementToBeClickable(resultPage.redirectHome1));
        Assertions.assertEquals("Your changes were successfully saved. Click here to continue.",
                driver.findElement(By.xpath("/html/body/div/div[1]/span")).getText());
        resultPage.redirectHome1.click();
        Assertions.assertEquals("Home", driver.getTitle());
        es.executeScript("arguments[0].click()", homePage.navCredentialsTab);
        wait1.until(ExpectedConditions.visibilityOf(homePage.deleteCredential));
        //delete credential
        homePage.deleteCredential.click();
        es.executeScript("arguments[0].click()", homePage.navCredentialsTab);
        Assertions.assertFalse(homePage.checkCredential());
    }

}
