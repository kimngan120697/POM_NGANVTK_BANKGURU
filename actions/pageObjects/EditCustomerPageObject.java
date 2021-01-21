package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage{
	WebDriver driver;

	public EditCustomerPageObject(WebDriver _driver) {
		this.driver = _driver;
	}

}
