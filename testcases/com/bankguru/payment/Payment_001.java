package com.bankguru.payment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

public class Payment_001 extends AbstractTest {

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;

	String email = "test12345@gmail.com";
	String gender="f"; //m=male f=female
	String customerName="AUTOMATION TESTING";
	String dateOfBirth="1997-06-12";
	String address="232 Nguyen Thi Thap, Phuong 3, Quan 1";
	String city="Ho Chi Minh city";
	String state="FL";
	String pin="466259";
	String mobile="2323232323";
	String password="automation";

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
		verifyTrue(homePage.isWelcomeMessageIsDisplayed());
	}

	@Test
	public void Payment_01_CreateNewCustomer() {

		homePage.openBankGuruPage(driver, "New Customer");
		newCustomerPage=PageGeneratorManager.getNewCustomerPage(driver);
		newCustomerPage.inputToTextboxByName(driver, "name", customerName);
		newCustomerPage.selectRadioButtonByValue(driver,gender);
		newCustomerPage.inputToTextboxByName(driver, "dob", dateOfBirth);
		newCustomerPage.inputToTextAreaByName(driver, "addr",address);
		newCustomerPage.inputToTextboxByName(driver, "city", city);
		newCustomerPage.inputToTextboxByName(driver, "state", state);
		newCustomerPage.inputToTextboxByName(driver, "pinno", pin);
		newCustomerPage.inputToTextboxByName(driver, "telephoneno", mobile);
		newCustomerPage.inputToTextboxByName(driver, "emailid", email);
		newCustomerPage.inputToTextboxByName(driver, "password", password);
		newCustomerPage.clickToButtonByValue(driver, "Submit");
		
		verifyTrue(newCustomerPage.isCreateSuccessulMessageIsDisplayed());
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
