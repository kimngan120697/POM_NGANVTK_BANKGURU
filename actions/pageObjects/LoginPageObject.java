package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	
	WebDriver driver;

	public LoginPageObject(WebDriver _driver) {
		this.driver= _driver;
	}

	public String getLoginUrl() {
		return getCurrentUrl(driver);
	}

	public RegisterPageObject clickToHereLink() {
		waitToElementClickable(driver,LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userID) {
		waitToElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userID);
	}
	
	public void inputToPasswordTextbox(String passWord) {
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passWord);
	}

	public HomePageObject clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
}
