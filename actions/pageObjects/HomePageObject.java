package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class HomePageObject extends AbstractPage{
	
	WebDriver driver;

	public HomePageObject(WebDriver _driver) {
		this.driver= _driver;
	}

}
