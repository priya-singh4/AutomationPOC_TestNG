package OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InitialScreening_OR {
	
	WebDriver driver;
	
	public InitialScreening_OR(WebDriver driver) {
		this.driver=driver;
	}
	
	public static String newbtn= "(//a[@title='New'])[1]";
	public static String dateTextbox="//label[text()='Date']//parent::div//input";
	public static String timeTextbox="//label[text()='Time']/following-sibling::div/lightning-base-combobox";
	public static String screeningNameTextbox="//label[text()='Screening Name']//following-sibling::div/input"; 
	public static String screeningNarrativeTextbox="//div[@aria-label='Screening Narrative']//div[@role='textbox']";
	public static String saveAndProceedBtn="//button[text()='Save and Proceed']";
	public static String reasonForCallDrpDwn="//button[contains(@name,'ReasonForCall')]";
	public static String reasonForCallDrpDwnOption="//lightning-base-combobox-item[@exportparts='option']/span[2]/span[text()='%s";
	public static String callerTypeRadioBtnOption1="//lightning-radio-group[@type='radio']//following-sibling::span[text()='%s']]";
	public static String callerTypeRadioBtnOption2="//lightning-radio-group[@type='radio']//following-sibling::span[contains(text(),'Non-Mandated Reporter)')]";
	public static String callerTypeRadioBtnOption3="//lightning-radio-group[@type='radio']//following-sibling::span[text()='Mandated Reporter']";
	public static String callerTypeRadioBtnOption4="//lightning-radio-group[@type='radio']//following-sibling::span[text()='Self Reporter']";
	public static String callerFirstNameTextbox="//label[text()='Caller First Name']//following-sibling::div/input";
	public static String callerLastNameTextbox="//label[text()='Caller Last Name']//following-sibling::div/input";
	public static String faxNumberTextbox="//label[text()='Fax Number']//following-sibling::div/input";
	public static String emailTextbox="//label[text()='Email']//following-sibling::div/input";
	public static String phoneTextbox="//label[text()='Phone']//following-sibling::div/input";
	public static String preferredMethodDrpDwn="//button[@aria-label='Preferred Method to Receive ERNRD']";
	public static String preferredMethodDrpDwnOption2="//lightning-base-combobox[@exportparts='dropdown, option']//span[text()='%s'][1]";
	public static String preferredMethodDrpDwnOption1="//lightning-base-combobox[@exportparts='dropdown, option']//span[text()='Email/Electronic']";
	public static String mandatedReporterTypeDrpDwn="//button[@aria-label='Mandated Reporter Type']";
	public static String mandatedReporterTypeOption="//lightning-base-combobox[@exportparts='dropdown, option']//span[text()='%s']";
	public static String methodOfReportDrpDwn="//button[@aria-label='Method of Report']";
	public static String methodOfReportDrpDwnOption="//lightning-base-combobox[@exportparts='dropdown, option']//span[text()='Telephone'][1]";
	public static String phoneTypeDrpDwn="//button[@aria-label='Phone Type']";
	public static String phoneTypeDrpDwnOption="//lightning-base-combobox[@exportparts='dropdown, option']//span[text()='%s'][1]";
	public static String callBackRequired="//button[contains(@name,'callback')]";
	public static String callBackRequiredOption="//lightning-base-combobox-item[@data-value='%s']";
	public static String saveAndProceedScreeningBtn="//button[text()='Save and Proceed']";

}
