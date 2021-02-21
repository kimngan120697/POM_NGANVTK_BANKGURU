package com.bankguru.customer;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

public class DeleteCustomer extends AbstractTest {

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;
	DeleteCustomerPageObject deleteCustomerPage;

	private String email, userID, passWord;
	private String gender,customerName, dateOfBirth, address, city, state, pin, mobile, password;
	private String customerID;

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		String loginUrl = loginPage.getLoginUrl();

		// Pre-Condition
		registerPage = loginPage.clickToHereLink();

		email = "customer" + randomNumber() + "@gmail.com";

		registerPage.inputToEmaiTextBox(email);
		registerPage.clickToSubmitButton();

		userID = registerPage.getUserIDValue();
		passWord = registerPage.getPasswordValue();

		loginPage = registerPage.openLoginPage(loginUrl);

		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(passWord);
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isWelcomeMessageIsDisplayed());

		System.out.println("User Name: " + userID);
		System.out.println("Password: " + passWord);

		// Create New Customer data
		email = "autoByJu" + randomNumber() + "@gmail.com";
		gender = "female"; // m=male f=female
		customerName = "AUTOMATION TESTING";
		dateOfBirth = "1997-06-12";
		address = "Street Ward 3";
		city = "Ho Chi Minh city";
		state = "FL";
		pin = "466259";
		mobile = "2323232323";
		password = "automation";

		// Create New Customer
		loginPage.openBankGuruPage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		newCustomerPage.inputToTextboxByName(driver, "name", customerName);
		newCustomerPage.selectRadioButtonByValue(driver, "f");
		newCustomerPage.inputToTextboxByName(driver, "dob", dateOfBirth);
		newCustomerPage.inputToTextAreaByName(driver, "addr", address);
		newCustomerPage.inputToTextboxByName(driver, "city", city);
		newCustomerPage.inputToTextboxByName(driver, "state", state);
		newCustomerPage.inputToTextboxByName(driver, "pinno", pin);
		newCustomerPage.inputToTextboxByName(driver, "telephoneno", mobile);
		newCustomerPage.inputToTextboxByName(driver, "emailid", email);
		newCustomerPage.inputToTextboxByName(driver, "password", password);
		newCustomerPage.clickToButtonByValue(driver, "Submit");

		customerID = newCustomerPage.getRowValueByRowName(driver, "Customer ID");
		System.out.println("Customer ID:" +customerID);
		
		newCustomerPage.openBankGuruPage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
	}

	@Test
	public void TC_01_verifyEmptyToCustomerID() {
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", "");
		deleteCustomerPage.pressTabToTextboxByName(driver, "cusid");
		verifyEquals(deleteCustomerPage.getErrorMessageByTextboxName(driver, "cusid"),"Customer ID is required");
	}
	
	@Test
	public void TC_002_verifyInputCharacterToCustomerID() {
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", "abcd123");
		verifyEquals(deleteCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");
	}
	@Test
	public void TC_003_verifyInputSpecialCharacterToCustomerID() {
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", "!@#123");
		verifyEquals(deleteCustomerPage.getErrorMessageByTextboxName(driver, "cusid"),"Special characters are not allowed");
	}
	
	@Test
	public void TC_004_verifyInputSpaceToCustomerID() {
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", "123 123");
		verifyEquals(deleteCustomerPage.getErrorMessageByTextboxName(driver, "cusid"),"Customer ID can not have blank space");
	}
	@Test
	public void TC_005verifyInputFirstSpaceToCustomerID() {
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", " 12345");
		verifyEquals(deleteCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "First character can not have space");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	
	public int randomNumber() {
		Random random=new Random();
		int number = random.nextInt();
		return number;
	}

}
