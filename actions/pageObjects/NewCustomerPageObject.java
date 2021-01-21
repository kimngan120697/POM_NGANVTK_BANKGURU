package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;

	public NewCustomerPageObject(WebDriver _driver) {
		this.driver = _driver;
	}
}
