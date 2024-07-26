package Pages;

import org.openqa.selenium.By;
import OR.AddAddress_OR;
import OR.InitialScreening_OR;

import org.openqa.selenium.support.ui.ExpectedConditions;

import Utility.Common;
import Utility.WebDriverHelpers;


public class AddAddress extends WebDriverHelpers {
	
	 public void addAddress() throws InterruptedException {
	    	driver.findElement(By.xpath(AddAddress_OR.addressTypeDrpDwn)).click();
	    	ddSelect.selectByVisibleText(Common.getDropdownValue(AddAddress_OR.addressTypeDrpDwnOptions, "Location of Child(ren)"));
	    	driver.findElement(By.xpath(AddAddress_OR.associateToSelectionbox)).click();
	    	driver.findElement(By.xpath(AddAddress_OR.moveSelectionToselected)).click();
	    	validateAddress();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddAddress_OR.saveAndProceedBtn)));
	    	driver.findElement(By.xpath(AddAddress_OR.saveAndProceedBtn)).click();
	    	driver.navigate().refresh();
	 }
	 public static void validateAddress()  {
	    
	    	driver.findElement(By.xpath(AddAddress_OR.addressLine1Textbox)).sendKeys("220 Main st");
	    	driver.findElement(By.xpath(AddAddress_OR.cityTextbox)).sendKeys("Oakland");
	    	driver.findElement(By.xpath(AddAddress_OR.zipcodeTextbox)).sendKeys("94563");
	    	driver.findElement(By.xpath(AddAddress_OR.searchBtn)).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddAddress_OR.validateAdressBtn)));
	    	driver.findElement(By.xpath(AddAddress_OR.validateAdressBtn)).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AddAddress_OR.useThisAddressRadiobtn)));
	    	driver.findElement(By.xpath(AddAddress_OR.useThisAddressRadiobtn)).click();
	    	
	    	}
   
}
