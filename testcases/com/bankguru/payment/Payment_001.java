package com.bankguru.payment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Payment_001 extends AbstractTest {

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	String email = "test12345@gmail.com";

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest() {
		driver = getBrowserDriver("firefox");

		loginPage = PageGeneratorManager.getLoginPage(driver);
		String loginUrl = loginPage.getLoginUrl();

		// Pre-Condition
		registerPage = loginPage.clickToHereLink();
		registerPage.inputToEmaiTextBox(email);
		registerPage.clickToSubmitButton();

		String userID = registerPage.getUserIDValue();
		String passWord = registerPage.getPasswordValue();

		loginPage = registerPage.openLoginPage(loginUrl);

		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(passWord);
		homePage = loginPage.clickToLoginButton();
	}

	@Test
	public void Payment_01_CreateNewCustomer() {

		homePage.clickToNewCustomerPage();
		
	}

	@Test
	public void Payment_02_EditCustomer() {

	}

	@Test
	public void Payment_03_AddNewAccount() {

	}

	@Test
	public void Payment_04_EditAccount() {

	}

	@Test
	public void Payment_05_DepositToCurrentAccount() {

	}

	@Test
	public void Payment_06_WithDrawFromCurrentAccount() {

	}

	@Test
	public void Payment_07_TransferToAnotherAccount() {

	}

	@Test
	public void Payment_08_CheckAccountBalance() {

	}

	@Test
	public void Payment_09_DeleteAllAccount() {

	}

	@Test
	public void Payment_10_DeleteCustomer() {

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
