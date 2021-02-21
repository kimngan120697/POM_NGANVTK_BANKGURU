package com.bankguru.customer;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class NewCustomer extends AbstractTest {

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	NewCustomerPageObject newCustomerPage;
	WebDriver driver;
	
	private String email, userID, passWord;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest(String browserName) {

		driver = getBrowserDriver(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);
		String loginUrl = loginPage.getLoginUrl();

		// Pre-Condition
		registerPage = loginPage.clickToHereLink();
		
		email="customer"+randomNumber()+"@gmail.com";

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
		
		loginPage.openBankGuruPage(driver, "New Customer");
		newCustomerPage=PageGeneratorManager.getNewCustomerPage(driver);
	}

	@Test
	public void TC01_verifyEmptyCustomerName() {
		newCustomerPage.pressTabToTextboxByName(driver, "name");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "name"), "Customer name must not be blank");
	}
	
	@Test
	public void TC02_verifyInputNumberToCustomerName() {
		newCustomerPage.inputToTextboxByName(driver, "name","1234");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "name"), "Numbers are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "name","name1234");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "name"), "Numbers are not allowed");
	}
	
	@Test
	public void TC03_verifyInputSpecialToCustomerName() {
		newCustomerPage.inputToTextboxByName(driver, "name","name!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "name"), "Special characters are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "name","!@#@!#!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "name"), "Special characters are not allowed");
	}
	
	@Test
	public void TC04_verifyInputFirstSpaceToCustomerName() {
		newCustomerPage.inputToTextboxByName(driver, "name"," ");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "name"), "First character can not have space");
	}
	
	@Test
	public void TC05_verifyEmptyAddress() {
		newCustomerPage.pressTabToTextAreaByName(driver, "addr");
		verifyEquals(newCustomerPage.getErrorMessageByTextAreaName(driver, "addr"), "Address Field must not be blank");
	}

	@Test
	public void TC06_verifyInputFirstSpaceToAddress() {
		newCustomerPage.inputToTextAreaByName(driver, "addr"," ");
		verifyEquals(newCustomerPage.getErrorMessageByTextAreaName(driver, "addr"), "First character can not have space");
	}
	
	@Test
	public void TC08_verifyEmptyCity() {
		newCustomerPage.pressTabToTextboxByName(driver, "city");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "city"), "City Field must not be blank");
	}
	
	@Test
	public void TC09_verifyInputNumberToCity() {
		newCustomerPage.inputToTextboxByName(driver, "city","1234");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "city","city1234");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Numbers are not allowed");
	}

	@Test
	public void TC10_verifyInputSpecialToCity() {
		newCustomerPage.inputToTextboxByName(driver, "city","city!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "city","!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "city"), "Special characters are not allowed");
	}
	
	@Test 
	public void TC11_verifyInputFirstSpaceToCity() {
		newCustomerPage.inputToTextboxByName(driver, "city", " ");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "city"), "First character can not have space");
	}
	
	@Test
	public void TC12_verifyEmptyState() {
		newCustomerPage.pressTabToTextboxByName(driver, "state");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "state"), "State must not be blank");
	}
	
	@Test
	public void TC13_verifyInputNumberToState() {
		newCustomerPage.inputToTextboxByName(driver, "state","1234");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "state","state1234");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Numbers are not allowed");
	}
	
	@Test
	public void TC14_verifyInputSpecialToState() {
		newCustomerPage.inputToTextboxByName(driver, "state","state!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "state","!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "state"), "Special characters are not allowed");
	}
	
	@Test 
	public void TC15_verifyInputFirstSpaceToState() {
		newCustomerPage.inputToTextboxByName(driver, "state", " ");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "state"), "First character can not have space");
	}
	
	@Test
	public void TC16_verifyEmptyPin() {
		newCustomerPage.pressTabToTextboxByName(driver, "pinno");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "PIN Code must not be blank");
	}
	
	@Test
	public void TC17_verifyInputCharacterToPin() {
		newCustomerPage.inputToTextboxByName(driver, "pinno","pin123");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Characters are not allowed");
	}
	

	
	@Test
	public void TC18_verifyInputLessThen6DigitsToPin() {
		newCustomerPage.inputToTextboxByName(driver, "pinno", "123");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "PIN Code must have 6 Digits");
	}
	
	
	@Test
	public void TC19_verifyInputSpecialCharacterToPin() {
		newCustomerPage.inputToTextboxByName(driver, "pinno","pin!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "pinno","!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "Special characters are not allowed");
	}
	
	@Test 
	public void TC20_verifyInputFirstSpaceToPin() {
		newCustomerPage.inputToTextboxByName(driver, "pinno", " ");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "pinno"), "First character can not have space");
	}
	
	@Test
	public void TC21_verifyEmptyTelephone() {
		newCustomerPage.pressTabToTextboxByName(driver, "telephoneno");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "Mobile no must not be blank");
	}
	
	@Test 
	public void TC22_verifyInputFirstSpaceToTelephone() {
		newCustomerPage.inputToTextboxByName(driver, "telephoneno", " ");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "First character can not have space");
	}
	
	@Test
	public void TC23_verifyInputSpecialCharacterToTelephone() {
		newCustomerPage.inputToTextboxByName(driver, "telephoneno","123222!@#");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
		
		newCustomerPage.inputToTextboxByName(driver, "telephoneno","!@#323");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "telephoneno"), "Special characters are not allowed");
	}
	
	@Test
	public void TC24_verifyEmptyEmail() throws InterruptedException {
		newCustomerPage.pressTabToTextboxByName(driver, "emailid");
		Thread.sleep(1000);
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "emailid"), "Email-ID must not be blank");
	}
	
	@Test 
	public void TC25_verifyInputFirstSpaceToEmail() {
		newCustomerPage.inputToTextboxByName(driver, "emailid", " abcd@gmail.com ");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "emailid"), "First character can not have space");
	}
	
	@Test
	public void TC26_verifyInputEmailIncorrect() {
		newCustomerPage.inputToTextboxByName(driver, "emailid","guru99@");
		verifyEquals(newCustomerPage.getErrorMessageByTextboxName(driver, "emailid"), "Email-ID is not valid");
	}
	
	@Test
	public void TC27_verifyFieldLabelsDisplay() {
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "Customer Name"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "Gender"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "Date of Birth"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "Address"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "City"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "State"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "PIN"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "Mobile Number"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "E-mail"));
		verifyTrue(newCustomerPage.isFieldLabelDisplay(driver, "Password"));
	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}
	
	public int randomNumber() {
		Random random=new Random();
		int number= random.nextInt(9999);
		return number;
	}

}
