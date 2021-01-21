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
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

public class Payment_001 extends AbstractTest {
	private String email, gender, customerName, dateOfBirth, address, city, state, pin, mobile, password, customerID;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest() {
		driver = getBrowserDriver("firefox");
		
		//Create New Customer
		email = "1122a1111@gmail.com";
		gender="female"; //m=male f=female
		customerName="AUTOMATION TESTING";
		dateOfBirth="1997-06-12";
		address="Street Ward 3";
		city="Ho Chi Minh city";
		state="FL";
		pin="466259";
		mobile="2323232323";
		password="automation";

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
	public void Payment_01_CreateNewCustomer() throws InterruptedException {

		homePage.openBankGuruPage(driver, "New Customer");
		newCustomerPage=PageGeneratorManager.getNewCustomerPage(driver);
		
		//Create New Customer
		newCustomerPage.inputToTextboxByName(driver, "name", customerName);
		newCustomerPage.selectRadioButtonByValue(driver,"f");
		newCustomerPage.inputToTextboxByName(driver, "dob", dateOfBirth);
		newCustomerPage.inputToTextAreaByName(driver, "addr",address);
		newCustomerPage.inputToTextboxByName(driver, "city", city);
		newCustomerPage.inputToTextboxByName(driver, "state", state);
		newCustomerPage.inputToTextboxByName(driver, "pinno", pin);
		newCustomerPage.inputToTextboxByName(driver, "telephoneno", mobile);
		newCustomerPage.inputToTextboxByName(driver, "emailid", email);
		newCustomerPage.inputToTextboxByName(driver, "password", password);
		newCustomerPage.clickToButtonByValue(driver, "Submit");
		
		newCustomerPage.threadSleep(3000);
		//Verify Customer created sucessfully
		verifyEquals(newCustomerPage.getHeadingText(driver),"Customer Registered Successfully!!!");
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Customer Name"),customerName);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Gender"),gender);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Birthdate"),dateOfBirth);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "City"),city);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "State"),state);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Address"),address);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Pin"),pin);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Mobile No."),mobile);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Email"),email);

		customerID=newCustomerPage.getColumnValueByColumnName(driver, "Customer ID");
	}

	@Test
	public void Payment_02_EditCustomer() {
		newCustomerPage.openBankGuruPage(driver,"Edit Customer");
		editCustomerPage=PageGeneratorManager.getEditCustomerPage(driver);
		
		editCustomerPage.inputToTextboxByName(driver, "cusid",customerID);
		editCustomerPage.clickToButtonByValue(driver, "Submit");
		
		
		
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


	private WebDriver driver;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
}
