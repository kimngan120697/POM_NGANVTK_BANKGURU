package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteAccountPageObject extends AbstractPage{

	WebDriver driver;
	public DeleteAccountPageObject(WebDriver _driver) {
		this.driver= _driver;
	}

}
