package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	
	WebDriver driver;

	public RegisterPageObject(WebDriver _driver) {
		this.driver= _driver;
	}

	public void inputToEmaiTextBox(String emailValue) {
		waitToElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}
	
	public void clickToSubmitButton() {
		waitToElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDValue() {
		waitToElementVisible(driver, RegisterPageUI.USER_ID_VALUE);
		return getTextElement(driver, RegisterPageUI.USER_ID_VALUE);
	}

	public String getPasswordValue() {
		waitToElementVisible(driver, RegisterPageUI.PASSWORD_VALUE);
		return getTextElement(driver, RegisterPageUI.PASSWORD_VALUE);
	}
	
	public LoginPageObject openLoginPage(String loginUrl) {
		openUrl(driver, loginUrl);
		return PageGeneratorManager.getLoginPage(driver);
	}

}
