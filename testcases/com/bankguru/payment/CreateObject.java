package com.bankguru.payment;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateObject {
	WebDriver driver;
	List<WebElement> elements;
	WebElement element;
	JavascriptExecutor jsExecutor;

	String linkServer = "https://210.221.237.224:29090/";
	String username = "logcenter";
	String password = "!1q2w3e4r5r";
	
	String hostNameItem="172.20.16.25";

	By usernameTextbox = By.xpath("//input[@id='login-username']");
	By passwordTextbox = By.xpath("//input[@id='login-password']");
	By loginButton = By.xpath("//button[@id='btn-login']");
	By hostInformationButton = By.xpath("//span[@id='btn-route-hosts']");
	By hostNameItemBox = By.xpath("//p[@data-toggle='tooltip' and text()='"+ hostNameItem +"']");

	By addObjectButton = By.xpath("//button//span[@class='text' and contains(text(),'Connect')]");
	By loggingTab=By.xpath("//ul//li[@class='nav-item']/a[@class='nav-link' and contains(text(),'Logging')]");
	
	By directoryObject=By.xpath("//div[@class='col directory']//h3");
	By objectNameTextBox = By.xpath("//label[contains(text(),'Object Name')]//following-sibling::div//input");
	By directoryPathTextBox = By.xpath("//label[contains(text(),'Directory Path')]//following-sibling::div//input");
	By fileNameTextBox = By.xpath("//label[contains(text(),'File Name')]//following-sibling::div//input");
	
	//Radio button
	By rawDataAndParsingDataRadioButton=By.xpath("//div[@class='radio custom-radio']//label[contains(text(),'Raw Data & Parsing Data')]");
	
	//Select Data Format
	By selectButton=By.xpath("//div[@class='col-auto']//button[contains(text(),'Select')]");
	By ipsDataFormat=By.xpath("//div[@class='content']//h3[contains(text(),'IPS')]");
	By trendMicroVenderButton=By.xpath("//ul//li//button[contains(text(),'TRENDMICRO')]");
	By tippingPointModelButton=By.xpath("//ul//li//button[contains(text(),'TIPPINGPOINT')]");
	By saveDataFormatButton=By.xpath("//div[@class='modal-dialog']//div[@class='data-format-detail']//button[contains(text(),'Save')]");
	
	// Save object
	By saveObjectButton = By.xpath("//h5[contains(text(),'Connect Directory')]//parent::div//parent::div//div[@class='modal-footer']//button[contains(text(),'Save')]");

	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		// driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(linkServer);
		String connectXpath = "//h1[contains(text(),'Your connection is not private')]";
		if (driver.findElement(By.xpath(connectXpath)).isDisplayed()) {
			driver.findElement(By.xpath("//button[@id='details-button']")).click();
			driver.findElement(By.xpath("//a[@class='small-link']")).click();
		}
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		driver.findElement(hostInformationButton).click();
		driver.findElement(hostNameItemBox).click();
	}

	//@Test
	public void TC_01_createObject() throws InterruptedException {

		driver.findElement(addObjectButton).click();
		driver.findElement(loggingTab).click();
		driver.findElement(directoryObject).click();
		driver.findElement(objectNameTextBox).sendKeys("");
		driver.findElement(directoryPathTextBox).sendKeys("");
		driver.findElement(fileNameTextBox).sendKeys("");
		driver.findElement(rawDataAndParsingDataRadioButton).click();
		driver.findElement(ipsDataFormat).click();
		driver.findElement(trendMicroVenderButton).click();
		driver.findElement(tippingPointModelButton).click();
		driver.findElement(saveDataFormatButton).click();
	}
	
	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(99999);
		return number;
	}

	public void scrollToElement(By locatorXpath) {
		element = driver.findElement(locatorXpath);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
