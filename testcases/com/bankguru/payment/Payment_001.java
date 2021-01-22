package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

public class Payment_001 extends AbstractTest {
	private String email, gender, customerName, dateOfBirth, address, city, state, pin, mobile, password, customerID;
	private String editAddress, editCity, editState, editPin, editMobile, editEmail;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest() {
		driver = getBrowserDriver("firefox");

		// Create New Customer data
		email = "autoByJu"+randomNumber()+"@gmail.com";
		gender = "female"; // m=male f=female
		customerName = "AUTOMATION TESTING";
		dateOfBirth = "1997-06-12";
		address = "Street Ward 3";
		city = "Ho Chi Minh city";
		state = "FL";
		pin = "466259";
		mobile = "2323232323";
		password = "automation";

		// Edit Customer data
		editAddress = "Nguyen Van Troi Street Ho Chi Minh city";
		editCity = "Ha Noi city";
		editState = "Texas";
		editPin = "111222";
		editMobile = "0123456786";
		editEmail = "julia_auto"+randomNumber()+"@gmail.com";

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

		System.out.println("User Name: " + userID);
		System.out.println("Password: " + passWord);
	}

	@Test
	public void Payment_01_CreateNewCustomer() {

		homePage.openBankGuruPage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		// Create New Customer
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

		// Verify Customer created sucessfully
		verifyEquals(newCustomerPage.getHeadingText(driver), "Customer Registered Successfully!!!");
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Customer Name"), customerName);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Gender"), gender);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Birthdate"), dateOfBirth);
		
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "City"), city);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "State"), state);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Address"), address);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Pin"), pin);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Mobile No."), mobile);
		verifyEquals(newCustomerPage.getColumnValueByColumnName(driver, "Email"), email);

		customerID = newCustomerPage.getColumnValueByColumnName(driver, "Customer ID");
		System.out.println("Customer ID: " + customerID);
	}

	@Test
	public void Payment_02_EditCustomer() {
		newCustomerPage.openBankGuruPage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		editCustomerPage.inputToTextboxByName(driver, "cusid", customerID);
		editCustomerPage.clickToButtonByValue(driver, "Submit");

		// Verify textboxes disabled
		verifyFalse(editCustomerPage.isTextboxEnabled(driver, "name"));
		verifyFalse(editCustomerPage.isTextboxEnabled(driver, "gender"));
		verifyFalse(editCustomerPage.isTextboxEnabled(driver, "dob"));

		//// Verify some textboxes enabled
		verifyTrue(editCustomerPage.isTextboxEnabled(driver, "city"));
		verifyTrue(editCustomerPage.isTextboxEnabled(driver, "state"));
		
		// Edit Customer
		editCustomerPage.inputToTextAreaByName(driver, "addr", editAddress);
		editCustomerPage.inputToTextboxByName(driver, "city", editCity);
		editCustomerPage.inputToTextboxByName(driver, "state", editState);
		editCustomerPage.inputToTextboxByName(driver, "pinno", editPin);
		editCustomerPage.inputToTextboxByName(driver, "telephoneno", editMobile);
		editCustomerPage.inputToTextboxByName(driver, "emailid", editEmail);
		editCustomerPage.clickToButtonByValue(driver, "Submit");

		// Verify edit successfully
		verifyEquals(editCustomerPage.getHeadingText(driver), "Customer details updated Successfully!!!");
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Customer ID"), customerID);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Customer Name"), customerName);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Gender"), gender);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Birthdate"), dateOfBirth);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Address"), editAddress);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "City"), editCity);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "State"), editState);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Pin"), editPin);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Mobile No."), editMobile);
		verifyEquals(editCustomerPage.getColumnValueByColumnName(driver, "Email"), editEmail);
	}
	

	@Test
	public void Payment_03_AddNewAccount() {

		//Tao 2 account
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
		//Xoa 2 account
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

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(99999);
		return number;
	}
}
