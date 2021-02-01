package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.BalanceEnquiryPageObject;
import pageObjects.DeleteAccountPageObject;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditAccountPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.WithdrawalPageObject;

public class Payment_001 extends AbstractTest {
	private String email, gender, customerName, dateOfBirth, address, city, state, pin, mobile, password;
	private String editAddress, editCity, editState, editPin, editMobile, editEmail;
	private String customerID, firstAccountID, secondAccountID;
	private String accountType1, initialDeposit1;
	private String editAccountType1;
	private String accountType2, initialDeposit2;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest(@Optional("firefox") String browserName) {
		driver = getBrowserDriver(browserName);

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

		// Edit Customer data
		editAddress = "Nguyen Van Troi Street Ho Chi Minh city";
		editCity = "Ha Noi city";
		editState = "Texas";
		editPin = "111222";
		editMobile = "0123456786";
		editEmail = "julia_auto" + randomNumber() + "@gmail.com";

		// Create First New Account
		accountType1 = "Savings"; // Savings or Current
		initialDeposit1 = "50000";

		// Create Second New Account
		accountType2 = "Savings"; // Savings or Current
		initialDeposit2 = "10000";

		// Edit First New Account
		editAccountType1 = "Current"; // Savings or Current

		// Deposit
//		amount = "5000";
//		desctiption = "Deposit";

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
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Customer Name"), customerName);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Gender"), gender);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Birthdate"), dateOfBirth);

		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "City"), city);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "State"), state);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Address"), address);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Pin"), pin);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Mobile No."), mobile);
		verifyEquals(newCustomerPage.getRowValueByRowName(driver, "Email"), email);

		customerID = newCustomerPage.getRowValueByRowName(driver, "Customer ID");
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
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Customer ID"), customerID);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Customer Name"), customerName);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Gender"), gender);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Birthdate"), dateOfBirth);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Address"), editAddress);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "City"), editCity);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "State"), editState);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Pin"), editPin);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Mobile No."), editMobile);
		verifyEquals(editCustomerPage.getRowValueByRowName(driver, "Email"), editEmail);
	}

	@Test
	public void Payment_03_AddNewAccount() {
		editCustomerPage.openBankGuruPage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

		// Create First Account
		newAccountPage.inputToTextboxByName(driver, "cusid", customerID);
		newAccountPage.selectDropdownItemByName(driver, "selaccount", accountType1);
		newAccountPage.inputToTextboxByName(driver, "inideposit", initialDeposit1);
		newAccountPage.clickToButtonByValue(driver, "submit");

		// Verify New Account created
		verifyEquals(newAccountPage.getHeadingText(driver), "Account Generated Successfully!!!");
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Customer ID"), customerID);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Customer Name"), customerName);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Email"), editEmail);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Account Type"), accountType1);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Date of Opening"), getToday());
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Current Amount"), initialDeposit1);

		firstAccountID = newAccountPage.getRowValueByRowName(driver, "Account ID");

		newAccountPage.openBankGuruPage(driver, "New Account");
		// Create Second Account
		newAccountPage.inputToTextboxByName(driver, "cusid", customerID);
		newAccountPage.selectDropdownItemByName(driver, "selaccount", accountType2);
		newAccountPage.inputToTextboxByName(driver, "inideposit", initialDeposit2);
		newAccountPage.clickToButtonByValue(driver, "submit");

		// Verify New Account created
		verifyEquals(newAccountPage.getHeadingText(driver), "Account Generated Successfully!!!");
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Customer ID"), customerID);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Customer Name"), customerName);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Email"), editEmail);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Account Type"), accountType2);
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Date of Opening"), getToday());
		verifyEquals(newAccountPage.getRowValueByRowName(driver, "Current Amount"), initialDeposit2);

		secondAccountID = newAccountPage.getRowValueByRowName(driver, "Account ID");
		System.out.println("Account ID first: " + firstAccountID);
		System.out.println("Account ID second: " + secondAccountID);

		// Tao 2 account
	}

	@Test
	public void Payment_04_EditAccount() {
		newAccountPage.openBankGuruPage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

		// Edit First Account
		editAccountPage.inputToTextboxByName(driver, "accountno", firstAccountID);
		editAccountPage.clickToButtonByValue(driver, "Submit");

		// Verify textboxes disabled
		verifyFalse(editAccountPage.isTextboxEnabled(driver, "txtcid"));
		verifyFalse(editAccountPage.isTextboxEnabled(driver, "txtinitdep"));

		// Edit First Account Type
		editAccountPage.selectDropdownItemByName(driver, "a_type", editAccountType1);
		editAccountPage.clickToButtonByValue(driver, "Submit");

		// Verify First Account Edited Successfully
		verifyEquals(editAccountPage.getHeadingText(driver), "Account details updated Successfully!!!");
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Account ID"), firstAccountID);
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Customer ID"), customerID);
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Customer Name"), customerName);
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Email"), editEmail);
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Account Type"), editAccountType1);
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Date of Opening"), getToday());
		verifyEquals(editAccountPage.getRowValueByRowName(driver, "Current Amount"), initialDeposit1);

	}

	@Test
	public void Payment_05_DepositToCurrentAccount() {

		editAccountPage.openBankGuruPage(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);

		depositPage.inputToTextboxByName(driver, "accountno", firstAccountID);
		depositPage.inputToTextboxByName(driver, "ammount", "5000");
		depositPage.inputToTextboxByName(driver, "desc", "Deposit");
		depositPage.clickToButtonByValue(driver, "Submit");

		// Verify
		verifyEquals(depositPage.getHeadingText(driver), "Transaction details of Deposit for Account " + firstAccountID);
		verifyEquals(depositPage.getRowValueByRowName(driver, "Account No"), firstAccountID);
		verifyEquals(depositPage.getRowValueByRowName(driver, "Amount Credited"), "5000");
		verifyEquals(depositPage.getRowValueByRowName(driver, "Type of Transaction"), "Deposit");
		verifyEquals(depositPage.getRowValueByRowName(driver, "Description"), "Deposit");
		verifyEquals(depositPage.getRowValueByRowName(driver, "Current Balance"), "55000");
	}

	@Test
	public void Payment_06_WithDrawFromCurrentAccount() {
		depositPage.openBankGuruPage(driver, "Withdrawal");
		withdrawalPage = PageGeneratorManager.getWithdrawalPage(driver);

		withdrawalPage.inputToTextboxByName(driver, "accountno", firstAccountID);
		withdrawalPage.inputToTextboxByName(driver, "ammount", "15000");
		withdrawalPage.inputToTextboxByName(driver, "desc", "Withdraw");
		withdrawalPage.clickToButtonByValue(driver, "Submit");

		// Verify
		verifyEquals(withdrawalPage.getHeadingText(driver), "Transaction details of Withdrawal for Account " + firstAccountID);
		verifyEquals(withdrawalPage.getRowValueByRowName(driver, "Account No"), firstAccountID);
		verifyEquals(withdrawalPage.getRowValueByRowName(driver, "Amount Debited"), "15000");
		verifyEquals(withdrawalPage.getRowValueByRowName(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawalPage.getRowValueByRowName(driver, "Description"), "Withdraw");
		verifyEquals(withdrawalPage.getRowValueByRowName(driver, "Current Balance"), "40000");
	}

	@Test
	public void Payment_07_TransferToAnotherAccount() {

		withdrawalPage.openBankGuruPage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);

		fundTransferPage.inputToTextboxByName(driver, "payersaccount", firstAccountID);
		fundTransferPage.inputToTextboxByName(driver, "payeeaccount", secondAccountID);
		fundTransferPage.inputToTextboxByName(driver, "ammount", "10000");
		fundTransferPage.inputToTextboxByName(driver, "desc", "Transfer");
		fundTransferPage.clickToButtonByValue(driver, "Submit");

		// Verify
		verifyEquals(fundTransferPage.getHeadingText(driver), "Fund Transfer Details");
		verifyEquals(fundTransferPage.getRowValueByRowName(driver, "From Account Number"), firstAccountID);
		verifyEquals(fundTransferPage.getRowValueByRowName(driver, "To Account Number"), secondAccountID);
		verifyEquals(fundTransferPage.getRowValueByRowName(driver, "Amount"), "10000");
		verifyEquals(fundTransferPage.getRowValueByRowName(driver, "Description"), "Transfer");
	}

	@Test
	public void Payment_08_CheckAccountBalance() {
		fundTransferPage.openBankGuruPage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);

		// Check first account
		balanceEnquiryPage.inputToTextboxByName(driver, "accountno", firstAccountID);
		balanceEnquiryPage.clickToButtonByValue(driver, "Submit");

		verifyEquals(balanceEnquiryPage.getHeadingText(driver), "Balance Details for Account " + firstAccountID);
		verifyEquals(balanceEnquiryPage.getRowValueByRowName(driver, "Account No"), firstAccountID);
		verifyEquals(balanceEnquiryPage.getRowValueByRowName(driver, "Type of Account"), "Current");
		verifyEquals(balanceEnquiryPage.getRowValueByRowName(driver, "Balance"), "30000");
	}

	@Test
	public void Payment_09_DeleteAllAccount() {
		// Xoa 2 account

		fundTransferPage.openBankGuruPage(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountPage(driver);

		// Delete the first account
		deleteAccountPage.inputToTextboxByName(driver, "accountno", firstAccountID);
		deleteAccountPage.clickToButtonByValue(driver, "Submit");

		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Do you really want to delete this Account?"));
		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Account Deleted Sucessfully"));

		deleteAccountPage.openBankGuruPage(driver, "Delete Account");

		deleteAccountPage.inputToTextboxByName(driver, "accountno", firstAccountID);
		deleteAccountPage.clickToButtonByValue(driver, "Submit");

		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Do you really want to delete this Account?"));
		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Account does not exist"));

		// Delete the second account
		deleteAccountPage.openBankGuruPage(driver, "Delete Account");

		deleteAccountPage.inputToTextboxByName(driver, "accountno", secondAccountID);
		deleteAccountPage.clickToButtonByValue(driver, "Submit");

		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Do you really want to delete this Account?"));
		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Account Deleted Sucessfully"));

		deleteAccountPage.openBankGuruPage(driver, "Delete Account");

		deleteAccountPage.inputToTextboxByName(driver, "accountno", secondAccountID);
		deleteAccountPage.clickToButtonByValue(driver, "Submit");

		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Do you really want to delete this Account?"));
		verifyTrue(deleteAccountPage.isTextAlertIsDisplayedAndAccept(driver, "Account does not exist"));

	}

	@Test
	public void Payment_10_DeleteCustomer() {
		
		deleteAccountPage.openBankGuruPage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", customerID);
		deleteCustomerPage.clickToButtonByValue(driver, "Submit");
		
		verifyTrue(deleteCustomerPage.isTextAlertIsDisplayedAndAccept(driver, "Do you really want to delete this Customer?"));
		verifyTrue(deleteCustomerPage.isTextAlertIsDisplayedAndAccept(driver, "Customer deleted Successfully"));
		
		deleteCustomerPage.openBankGuruPage(driver, "Delete Customer");
		
		deleteCustomerPage.inputToTextboxByName(driver, "cusid", customerID);
		deleteCustomerPage.clickToButtonByValue(driver, "Submit");
		
		verifyTrue(deleteCustomerPage.isTextAlertIsDisplayedAndAccept(driver, "Do you really want to delete this Customer?"));
		verifyTrue(deleteCustomerPage.isTextAlertIsDisplayedAndAccept(driver, "Customer does not exist!!"));
		
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
	private NewAccountPageObject newAccountPage;
	private EditAccountPageObject editAccountPage;
	private DepositPageObject depositPage;
	private WithdrawalPageObject withdrawalPage;
	private FundTransferPageObject fundTransferPage;
	private BalanceEnquiryPageObject balanceEnquiryPage;
	private DeleteAccountPageObject deleteAccountPage;
	private DeleteCustomerPageObject deleteCustomerPage;

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(99999);
		return number;
	}
}
