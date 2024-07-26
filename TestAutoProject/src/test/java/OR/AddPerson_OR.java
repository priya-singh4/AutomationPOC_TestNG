package OR;

public class AddPerson_OR {
	
	public static String roleDrpDwn="//button[contains(@name,'role')]";
	public static String roleDrpDwnOption="//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String collateralTypeDrpDwn="//button[contains(@aria-label,'Collateral Type')]";
	public static String collateralTypeDrpDwnOption="//lightning-base-combobox-item[@exportparts='option']//span[text()='Attorney']";
	public static String countyDrpDwn="//button[contains(@aria-label,'County')]";
	public static String countyDrpDwnOption="//lightning-base-combobox-item[@exportparts='option']//span[text()='Alameda']";
	public static String firstNameTextbox="//label[text()='First Name']//following-sibling::div/input";
	public static String lastNameTextbox="//label[text()='Last Name']//following-sibling::div/input";	
	public static String dateofbirthTextbox="//label[text()='Date of Birth']//parent::div//input";
	public static String approximateAgeTextbox="//label[text()='Approximate Age']//parent::div//input";
	public static String unknownPersonCheckbox="";
	public static String deleteBtn="";
	public static String addRownBtn="//button[text()='+ Add Row']";
	public static String calSAWSvalidationRadioBtn="//legend[text()='CalSAWS Validation']";
	public static String calSAWSvalidationRadioBtnOptions="//legend[text()='CalSAWS Validation']//following-sibling::div//span//input[@value='No']";
	public static String calSAWSvalidationDateTextbox="//label[text()='CalSAWS Validation Date']//parent::div//input";
	public static String calSAWSdetailsTextarea="//label[text()='CalSAWS Details']//following-sibling::div/textarea";
	public static String saveAndProceedBtn="//button[text()='Save and Proceed']";

}
