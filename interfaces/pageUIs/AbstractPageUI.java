package pageUIs;

public class AbstractPageUI {

	//Menu
	public static final String DYNAMIC_MENU_LINK="//ul[@class='menusubnav']//li//a[text()='%s']";

	//Textbox
	public static final String DYNAMIC_TEXTBOX="//input[@name='%s']";
	
	//Textarea
	public static final String DYNAMIC_TEXTAREA="//textarea[@name='%s']";
	
	//Radio button
	public static final String DYNAMIC_RADIO_BUTTON="//input[@value='%s']";
	
	//Dropdown
	public static final String DYNAMIC_DROPDOWN_BY_NAME="";
	
	//Button
	public static final String DYNAMIC_BUTTON="//input[@value='%s']";
	
	//Heading text
	public static final String HEADING_TEXT="//p[@class='heading3']";
	
	//Verify Column Value
	public static final String DYNAMIC_COLUMN_VALUE="//td[contains(text(),'%s')]/following-sibling::td";
	
	
	
}
