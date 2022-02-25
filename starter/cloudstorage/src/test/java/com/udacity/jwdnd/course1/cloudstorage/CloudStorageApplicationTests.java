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
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	private HomePage homePage;

	private static String fname = "key";
	private static String lname = "test";
	private static String username = "ktest";
	private static String password = "password";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeEach
	public void beforeEach() {

	}

	@AfterEach
	public void afterEach() {

	}

	@AfterAll
	public static void afterAll() {
			driver.quit();
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignUp() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void unauthorizedGetHomePage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertNotEquals("Home", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthorizedGetResult() {
		driver.get("http://localhost:" + this.port + "/result");
		Assertions.assertNotEquals("result", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signUpLoginAndOut() {
		driver.get("http://localhost:" + this.port + "/signup");
		//Sign up
		WebElement firstName = driver.findElement(By.id("inputFirstName"));
		WebElement lastName = driver.findElement(By.id("inputLastName"));
		WebElement username = driver.findElement(By.id("inputUsername"));
		WebElement password = driver.findElement(By.id("inputPassword"));
		WebElement signUpButton = driver.findElement(By.id("submit-button"));


		firstName.sendKeys(this.fname);
		lastName.sendKeys(this.lname);
		username.sendKeys(this.username);
		password.sendKeys(this.password);
		signUpButton.click();

		WebElement success = driver.findElement(By.id("success-msg"));
		Assertions.assertTrue(success.isDisplayed());
		WebElement backToLogin = driver.findElement(By.id("login-link"));
		backToLogin.click();
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");
		//login
		username = driver.findElement(By.id("inputUsername"));
		password = driver.findElement(By.id("inputPassword"));
		WebElement loginButton = driver.findElement(By.id("login-button"));

		username.sendKeys(this.username);
		password.sendKeys(this.password);
		loginButton.click();
		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		//logout
		WebElement logout = driver.findElement(By.id("logout-button"));

		logout.click();
	}
}
