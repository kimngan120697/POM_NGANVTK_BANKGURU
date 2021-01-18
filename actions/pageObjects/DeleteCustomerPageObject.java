package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage{

	WebDriver driver;
	public DeleteCustomerPageObject(WebDriver _driver) {
		this.driver= _driver;
	}
}
