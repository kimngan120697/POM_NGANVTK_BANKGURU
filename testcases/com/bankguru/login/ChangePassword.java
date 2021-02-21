package com.bankguru.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.ChangePasswordPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class ChangePassword extends AbstractTest {

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	ChangePasswordPageObject changePasswordPage;

	private String email, userID, passWord;
	
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

		homePage.openBankGuruPage(driver, "Change Password");
		changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
	}

	@Test
	public void TC_01_verifyErrorMesssageToOldPassword() {
		changePasswordPage.inputToTextboxByName(driver, "oldpassword", "");
		changePasswordPage.pressTabToTextboxByName(driver, "oldpassword");
		verifyEquals(changePasswordPage.getErrorMessageByTextboxName(driver, "oldpassword"), "Old Password must not be blank");
	}

	@Test
	public void TC_02_verifyErrorMessageToNewPassword() {
		changePasswordPage.inputToTextboxByName(driver, "newpassword", "");
		changePasswordPage.pressTabToTextboxByName(driver, "newpassword");
		verifyEquals(changePasswordPage.getErrorMessageByTextboxName(driver, "newpassword"), "New Password must not be blank");

		changePasswordPage.inputToTextboxByName(driver, "newpassword", "!!!aaa");
		changePasswordPage.pressTabToTextboxByName(driver, "newpassword");
		verifyEquals(changePasswordPage.getErrorMessageByTextboxName(driver, "newpassword"), "Enter at-least one numeric value");

		changePasswordPage.inputToTextboxByName(driver, "newpassword", "123aaa");
		changePasswordPage.pressTabToTextboxByName(driver, "newpassword");
		verifyEquals(changePasswordPage.getErrorMessageByTextboxName(driver, "newpassword"), "Enter at-least one special character");

		changePasswordPage.inputToTextboxByName(driver, "newpassword", "Password!@123");
		changePasswordPage.pressTabToTextboxByName(driver, "newpassword");
		verifyEquals(changePasswordPage.getErrorMessageByTextboxName(driver, "newpassword"), "New Password cannot have password string itself!");
	}
	
	@Test
	public void TC_03_verifyErrorMessageToConfirmPassword() {
		changePasswordPage.inputToTextboxByName(driver, "newpassword", "!1q2w3e4r5t");
		changePasswordPage.inputToTextboxByName(driver, "confirmpassword", "!1q2w3e4r5t6y");
		changePasswordPage.pressTabToTextboxByName(driver, "confirmpassword");
		verifyEquals(changePasswordPage.getErrorMessageByTextboxName(driver, "confirmpassword"), "Passwords do not Match");
	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}
	
	public int randomNumber() {
		Random random=new Random();
		int number = random.nextInt(9999);
		return number;
	}

}
