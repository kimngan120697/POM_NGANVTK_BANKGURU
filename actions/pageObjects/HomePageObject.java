package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage{
	
	WebDriver driver;

	public HomePageObject(WebDriver _driver) {
		this.driver= _driver;
	}

	public boolean isWelcomeMessageIsDisplayed() {
		return isElementDisplayed(driver, HomePageUI.WELCOME_MESSAGE);
	}

}
