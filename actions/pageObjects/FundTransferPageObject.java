package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class FundTransferPageObject extends AbstractPage{
	WebDriver driver;

	public FundTransferPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
