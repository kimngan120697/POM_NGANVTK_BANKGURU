package com.bankguru.payment;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Payment_001 {

	WebDriver driver;

	@Parameters({"browser"})
	@BeforeTest
	public void beforeTest() {
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
	}

}
