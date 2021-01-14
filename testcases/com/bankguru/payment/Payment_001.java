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
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Payment_001 extends AbstractTest{

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void beforeTest() {
		getBrowserDriver("firefox");
		
		loginPage=PageGeneratorManager.getLoginPage(driver);
		//Pre-Condition
		loginPage.clickToHereLink();
		registerPage=PageGeneratorManager.getRegisterPage(driver);
		registerPage.inputToEmaiTextBox("");
		registerPahe.clickSubmitButton();
		
		
	
	}

	@Test
	public void Payment_01_CreateNewCustomer() {

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
