package com.bankguru.payment;

import org.testng.annotations.Test;

import commons.GlobalConstants;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class CreateHost {

	WebDriver driver;
	List<WebElement> elements;
	WebElement element;
	JavascriptExecutor jsExecutor;
	Select select;
	WebDriverWait waitExplicit;

	String linkServer = "https://14.241.224.35:19443/";
	String username = "logcenter";
	String password = "!1q2w3e4r5t";

	String groupName_TC01="1";
	String groupName_TC02="111";
	String hostIP = "192.16.223." + randomNumberHost();
	
	// Host Name create object
	String hostNameItem = "VNIB";

	By usernameTextbox = By.xpath("//input[@id='login-username']");
	By passwordTextbox = By.xpath("//input[@id='login-password']");
	By loginButton = By.xpath("//button[@id='btn-login']");
	By hostInformationButton = By.xpath("//span[@id='btn-route-hosts']");
	By createHostButton = By.xpath("//button[@class='btn btn-link btn-add--host']");

	By timezoneButton = By.xpath("//label[contains(text(),'Timezone')]/following-sibling::div//a[@data-toggle='dropdown']");
	By itemsTimezoneList = By.xpath("//label[contains(text(),'Timezone\n" + 
			"                                ')]//ancestor::form//ancestor::div[@class='tab-content']//ancestor::div[@class='host-tabs']/ancestor::div[@class='modal-content']/ancestor::div[@role='dialog']/following-sibling::div[@role='menu']/a/span");

	By groupButton = By.xpath("//label[contains(text(),'Group')]/following-sibling::div//a[@data-toggle='dropdown']");
	By itemsGroupList = By.xpath("//label[contains(text(),'Group ')]//ancestor::form//ancestor::div[@class='tab-content']//ancestor::div[@class='host-tabs']/ancestor::div[@class='modal-content']/ancestor::div[@role='dialog']/following-sibling::div[@role='menu']/a/span");

	
	By allItem = By.xpath("//div[@class='dropdown-menu lc-dropdown-list top-placement ps ps--active-y show']/a/span");
	By editTopologyButton = By.xpath("//div[@class='ap-tooltip edit']/following-sibling::button");

	By hostNameTextbox = By.xpath("//input[@id='hostName']");
	By hostIPTextbox = By.xpath("//input[@id='hostIp']");
	By saveHostButton = By.xpath("//button[@class='btn btn-loading btn-primary']");

	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(linkServer);
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		driver.findElement(hostInformationButton).click();
		forABC();
	}

//	public void ipNumber() {
//		String ip="192.168.100";
//		int endNumber;
//		for(int i=1;i<255;i++) {
//			endNumber=i++;
//			ip="192.168.100."+endNumber;
//		}
//	}
	
	public void forABC() {
		int i=1;
		//int n;
		
		while(i<256) {
			i=i+1;
			System.out.print(i);
		}
		
//		
//		for(i=1;i<=255;i++)
//		{
//			i=i++;
//		
//		}
//		return i;
	}
	@Test(invocationCount = 25)
	public void TC_01_Create_Host() throws InterruptedException {
		String hostIP1 = "9.9."+randomNumberHost()+"." + randomNumberHost();
		
		try {
			
		Thread.sleep(1000);
		driver.findElement(createHostButton).click();
		Thread.sleep(1000);

		selectItemInCustomDropdown(groupButton, itemsGroupList,groupName_TC01);

		String hostName = hostIP1;
		driver.findElement(hostNameTextbox).sendKeys(hostName);
		driver.findElement(hostIPTextbox).sendKeys(hostIP1);
		//Thread.sleep(500);
		selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
		Thread.sleep(500);
		driver.findElement(saveHostButton).click();
		//Thread.sleep(1000);
		}catch(Exception ex) {
			System.out.println("Error tc01: "+ex);
		}
	}
	
	//@Test(invocationCount = 25)
	public void TC_02_Create_Host() throws InterruptedException {
		String hostIP2 = "1.1.169." + randomNumberHost();
		try {
		driver.findElement(createHostButton).click();
		Thread.sleep(1500);

		selectItemInCustomDropdown(groupButton, itemsGroupList, groupName_TC02);

		String hostName = hostIP2;
		driver.findElement(hostNameTextbox).sendKeys(hostName);
		driver.findElement(hostIPTextbox).sendKeys(hostIP2);
		Thread.sleep(1000);
		selectItemInCustomDropdown(timezoneButton, itemsTimezoneList, "(UTC-10:00) Hawaii");
		Thread.sleep(1000);
		driver.findElement(saveHostButton).click();
		Thread.sleep(2000);
	}catch(Exception ex) {
		System.out.println("Error tc01: "+ex);
	}
	}

	public void selectItemInCustomDropdown(By parentXpath, By allItemsXpath, String expectedText) throws InterruptedException {
		// 01. Click vào thẻ chứa Dropdown để show all items
		//System.out.println("chuan bi wait");
		//waitExplicit.until(ExpectedConditions.elementToBeClickable(parentXpath));
		System.out.println("chua click");
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(parentXpath));
		System.out.println("click roi");

		// 02. Wai để tất cả các item (List WebElement) được xuất hiện trong DOM
		// System.out.println("waiting");
		// waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemsXpath));

		// 03. Khai báo 1 List <Element> chứa all items bên trong.
		List<WebElement> allItems = driver.findElements(allItemsXpath);
		// 04. Tổng số lượng item trong 1 dropdown bằng bao nhiêu
		System.out.println("Item size= " + allItems.size());

		// 05. Duyệt qua từng cái item
		for (WebElement item : allItems) {
			System.out.println("Item: " + item.getText() + ".");

			// 05. Kiểm tra item nào đúng với mình cần chọn thì click vào
			if (item.getText().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				if (item.isDisplayed()) {
					System.out.println("Item click by Selenium: " + item.getText());				
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					Thread.sleep(2000);
					System.out.println("Item click by js: " + item.getText());

					jsExecutor.executeScript("arguments[0].click();", item);
				}
				Thread.sleep(2000);
				break;
			}
		}

	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		//driver.quit ();
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(99999);
		return number;
	}


	public int randomNumberHost() {
		Random rand = new Random();
		int number = rand.nextInt(255);
		return number;
	}

	public void scrollToElement(By locatorXpath) {
		element = driver.findElement(locatorXpath);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
