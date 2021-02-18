package com.bankguru.customer;

import org.testng.annotations.Test;

import commons.AbstractTest;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class EditCustomer_001 extends AbstractTest {

	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		
		driver=getBrowserDriver("firefox");
		
	}

	@Test
	public void f() {
	}

	@AfterTest
	public void afterTest() {
	}

}
