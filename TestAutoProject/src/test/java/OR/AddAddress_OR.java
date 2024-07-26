package OR;

public class AddAddress_OR {
	
	public static String addressTypeDrpDwn="//button[contains(@aria-label,'Address Type')]";
	public static String addressTypeDrpDwnOptions="//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String associateToSelectionbox="//div/span[text()='Available']//following-sibling::div/ul/li";
	public static String moveSelectionToselected="//button[@title='Move to Selected']";
	public static String commentAboutAddressTextbox="";
	public static String addressLine1Textbox="//label[text()='Address Line 1']//parent::div//input";
	public static String addressLine2Textbox="//label[text()='Address Line 2']//parent::div//input";
	public static String cityTextbox="//label[text()='City']//parent::div//input";
	public static String stateDrpDwn="//button[contains(@aria-label,'State')]";
	public static String stateDrpDwnOptions="//lightning-base-combobox-item[@exportparts='option']//span[text()='California']";
	public static String zipcodeTextbox="//label[text()='Zip Code']//parent::div//input";
	public static String countryDrpDwn="//button[contains(@aria-label,'Country')]";
	public static String countryDrpDwnOptions="";
	public static String validateAdressBtn="//button[text()='Validate Address']";
	public static String clearBtn="//button[text()='Clear']";
	public static String searchBtn="//button[text()='Search']";
	public static String useThisAddressRadiobtn="//div[text() ='User Entered Address:']//following::lightning-input//label";
	public static String saveAndProceedBtn="//button[text()='Save and Proceed']";

}
