package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.AddAddress_OR;
import OR.InitialScreening_OR;
import Utility.Common;
import Utility.WebDriverHelpers;


public class Initial_Screening extends WebDriverHelpers{
	
	public void enterinitialScreeningInformation() throws InterruptedException {
   
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(InitialScreening_OR.dateTextbox)));
    	driver.findElement(By.xpath(InitialScreening_OR.dateTextbox)).click();   	
    	driver.findElement(By.xpath(InitialScreening_OR.dateTextbox)).sendKeys(Common.getDate("MM-dd-YYYY","today"));
    	driver.findElement(By.xpath(InitialScreening_OR.reasonForCallDrpDwn)).click();
    	ddSelect.selectByVisibleText(Common.getDropdownValue(InitialScreening_OR.reasonForCallDrpDwnOption, "Abuse/Neglect Referral"));
    	driver.findElement(By.xpath(InitialScreening_OR.screeningNameTextbox)).sendKeys("autoscreening");
    	driver.findElement(By.xpath(InitialScreening_OR.screeningNarrativeTextbox)).click();
    	driver.findElement(By.xpath(InitialScreening_OR.screeningNarrativeTextbox)).sendKeys("screening description");
    	driver.findElement(By.xpath(Common.radioButton(InitialScreening_OR.callerTypeRadioBtnOption1,"Mandated Reported"))).click();
    	driver.findElement(By.xpath(InitialScreening_OR.callerFirstNameTextbox)).sendKeys("auto");
    	driver.findElement(By.xpath(InitialScreening_OR.callerLastNameTextbox)).sendKeys("testmandatedcaller");
    	ddSelect.selectByVisibleText(Common.getDropdownValue(InitialScreening_OR.preferredMethodDrpDwnOption2, "Fax"));
    	selectPreferredMethod("Fax");
    	driver.findElement(By.xpath(InitialScreening_OR.faxNumberTextbox)).sendKeys("(234) 567-8900");
    	ddSelect.selectByVisibleText(Common.getDropdownValue(InitialScreening_OR.mandatedReporterTypeOption, "District Attorney"));
    	ddSelect.selectByVisibleText(Common.getDropdownValue(InitialScreening_OR.phoneTypeDrpDwnOption, "Home"));
    	driver.findElement(By.xpath(InitialScreening_OR.phoneTextbox)).sendKeys("3333333333");
    	ddSelect.selectByVisibleText(Common.getDropdownValue(InitialScreening_OR.callBackRequiredOption, "No"));
    	driver.findElement(By.xpath(InitialScreening_OR.saveAndProceedBtn)).click();
    	driver.navigate().refresh();
    	
}
	
	public static void selectPreferredMethod(String preferredMethodtoReceiveErnrd) {
		
		switch(preferredMethodtoReceiveErnrd) {
		
		case "Email/Electronics":
			selectPreferredMethodasEmail();
			break;
			
		case "Fax":
			selectPreferredMethodasFax();
			break;
			
		case "US Postal Mail":
			selectPreferredMethodasUSPostalMail();
			break;
			
		case "Verbal":
			selectPreferredMethodasVerbal();
			break;
			
		default:
			break;
		
		}
		
	}
	public static void selectPreferredMethodasEmail() {
		driver.findElement(By.xpath(InitialScreening_OR.emailTextbox)).sendKeys("autotest@yopmail.com");	
	}
	public static void selectPreferredMethodasFax() {
		driver.findElement(By.xpath(InitialScreening_OR.faxNumberTextbox)).sendKeys("(234) 567-8900");
	}
	public static void selectPreferredMethodasUSPostalMail() {
		AddAddress.validateAddress();
	}
	public static void selectPreferredMethodasVerbal() {
	
	}
	
	 	  
}