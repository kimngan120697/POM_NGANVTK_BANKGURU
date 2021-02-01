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
	public static final String DYNAMIC_DROPDOWN_BY_NAME="//select[@name='%s']";
	
	//Button
	public static final String DYNAMIC_BUTTON="//input[@value='%s']";
	
	//Heading text
	public static final String HEADING_TEXT="//p[@class='heading3']";
	
	//Verify Column Value
	public static final String DYNAMIC_COLUMN_VALUE="//td[contains(text(),'%s')]/following-sibling::td";
	
	//Dynamic Error Message by Textbox/Text area Name
	
	public static final String DYNAMIC_ERROR_MESSAGE_BY_TEXTBOX="//input[@name='%s']/following-sibling::label";
	public static final String DYNAMIC_ERROR_MESSAGE_BY_TEXTAREA="//textarea[@name='%s']/following-sibling::label";

	//Dynamic Fields Lable
	public static final String DYNAMIC_FIELD_LABLE="//tr//td[contains(text(),'%s')]";
}
