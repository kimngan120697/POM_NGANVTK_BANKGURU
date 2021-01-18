package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DepositPageObject extends AbstractPage{

	WebDriver driver;
	public DepositPageObject(WebDriver _driver) {
		this.driver= _driver;
	}
}
