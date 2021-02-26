package com.bankguru.customer;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class EditCustomer extends AbstractTest {

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;

	private String email, userID, passWord;
	private String gender, customerName, dateOfBirth, address, city, state, pin, mobile, password;
	private String customerID;
	
	@Parameters({"browser"})
	@BeforeTest
	public void beforeTest(String browserName) {

		driver = getBrowserDriver(browserName);

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
		
		newCustomerPage.openBankGuruPage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
	}

	@Test
	public void TC_001_verifyEmptyToCustomerID() {
		editCustomerPage.pressTabToTextboxByName(driver, "cusid");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "Customer ID is required");
	}

	@Test
	public void TC_002_verifyInputLetterToCustomerID() {
		editCustomerPage.inputToTextboxByName(driver, "cusid", "1234Acc");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "cusid", "Acc123");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "Characters are not allowed");
	}

	@Test
	public void TC_003_verifyInputSpecialCharacterToCustomerID() {
		editCustomerPage.inputToTextboxByName(driver, "cusid", "123!@#$%");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "Special characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "cusid", "!@#$%");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "cusid"), "Special characters are not allowed");
	}

	@Test
	public void TC_004_verifyValidCustomerID() {
		editCustomerPage.inputToTextboxByName(driver, "cusid", customerID);
		editCustomerPage.clickToButtonByValue(driver, "Submit");
		verifyTrue(editCustomerPage.isTextAlertIsDisplayedAndAccept(driver, "You are not authorize to edit this customer!!"));
	}

	@Test
	public void TC_005_verifyEmptyToAddress() {
		editCustomerPage.inputToTextAreaByName(driver, "addr", "");
		editCustomerPage.pressTabToTextAreaByName(driver, "addr");
		verifyEquals(editCustomerPage.getErrorMessageByTextAreaName(driver, "addr"), "Address Field must not be blank");
	}

	@Test
	public void TC_006_verifyEmptyToCity() {
		editCustomerPage.inputToTextboxByName(driver, "city", "");
		editCustomerPage.pressTabToTextboxByName(driver, "city");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "city"), "City Field must not be blank");
	}

	@Test
	public void TC_007_verifyInputNumberToCity() {
		editCustomerPage.inputToTextboxByName(driver, "city", "123");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "city", "123abc");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
	}

	@Test
	public void TC_008_verifyInputSpecialCharacterToCity() {
		editCustomerPage.inputToTextboxByName(driver, "city", "!@#$");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "city", "123!@#$%");
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
	}

	@Test
	public void TC_008_verifyEmptyToState() {
		editCustomerPage.inputToTextboxByName(driver, "state", "");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "state"), "State must not be blank");
	}

	@Test
	public void TC_010_verifyInputNumberToState() {
		editCustomerPage.inputToTextboxByName(driver, "state", "123");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "state", "123state");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
	}

	@Test
	public void TC_011_verifyInputSpecialCharacterToState() {
		editCustomerPage.inputToTextboxByName(driver, "state", "!@#!@#");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "state", "!@#state");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");
	}

	@Test
	public void TC_012_verifyInputLetterToPin() {
		editCustomerPage.inputToTextboxByName(driver, "pinno", "abcdre");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "pinno", "abc123");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");
	}

	@Test
	public void TC_013_verifyEmptyToPin() {
		editCustomerPage.inputToTextboxByName(driver, "pinno", "");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "PIN Code must not be blank");
	}

	@Test
	public void TC_014_verifyInputLessThenFiveNumberToPin() {
		editCustomerPage.inputToTextboxByName(driver, "pinno", "1234");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_015_verifyInputSpecialCharacterToPin() {
		editCustomerPage.inputToTextboxByName(driver, "pinno", "!@#");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "pinno", "!@#123");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");
	}

	@Test
	public void TC_016_verifyEmptyToTelephone() {
		editCustomerPage.inputToTextboxByName(driver, "telephoneno", "");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "Mobile no must not be blank");
	}

	@Test
	public void TC_017_verifyInputSpecialCharacterToTelephone() {
		editCustomerPage.inputToTextboxByName(driver, "telephoneno", "!@#");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
		editCustomerPage.inputToTextboxByName(driver, "telephoneno", "!@#123");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
	}

	@Test
	public void TC_018_verifyEmptyToEmail() {
		editCustomerPage.inputToTextboxByName(driver, "emailid", "");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "emailid"), "Email-ID must not be blank");
	}
	@Test
	public void TC_019_verifyInputInvalidToEmail() {
		editCustomerPage.inputToTextboxByName(driver, "emailid", "email");		
		verifyEquals(editCustomerPage.getErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(9999);
		return number;
	}

}
