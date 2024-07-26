package Pages;

import org.openqa.selenium.By;
import OR.AddPerson_OR;
import OR.InitialScreening_OR;

import org.openqa.selenium.support.ui.ExpectedConditions;

import Utility.Common;
import Utility.WebDriverHelpers;

public class AddPerson extends WebDriverHelpers {
	
	public void addPerson() {
	driver.findElement(By.xpath(AddPerson_OR.roleDrpDwn)).click();
	ddSelect.selectByVisibleText(Common.getDropdownValue(AddPerson_OR.roleDrpDwnOption, "Alleged Perpetrator"));
	driver.findElement(By.xpath(AddPerson_OR.firstNameTextbox)).sendKeys("perpetrator");
	driver.findElement(By.xpath(AddPerson_OR.lastNameTextbox)).sendKeys("test");
	Common.scrollTillElement(AddPerson_OR.addRownBtn);
	driver.findElement(By.xpath(AddPerson_OR.addRownBtn)).click();	
	driver.findElement(By.xpath(AddPerson_OR.roleDrpDwnOption)).click();
	Common.scrollTillElement(AddPerson_OR.roleDrpDwnOption);
	ddSelect.selectByVisibleText(Common.getDropdownValue(AddPerson_OR.roleDrpDwnOption, "Alleged Victim"));
	driver.findElement(By.xpath(AddPerson_OR.firstNameTextbox)).sendKeys("victim");
	driver.findElement(By.xpath(AddPerson_OR.lastNameTextbox)).sendKeys("test");
	driver.findElement(By.xpath(AddPerson_OR.saveAndProceedBtn)).click();   
	//page refresh
	}
	
}
