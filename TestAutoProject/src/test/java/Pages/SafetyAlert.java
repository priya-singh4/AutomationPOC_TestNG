package Pages;

import org.openqa.selenium.By;
import OR.SafetyAlert_OR;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utility.Common;
import Utility.WebDriverHelpers;

public class SafetyAlert extends WebDriverHelpers {
	
	public void safetyAlert() {
			
	Common.scrollTillElement(SafetyAlert_OR.safetyAlertCheckbox);
	driver.findElement(By.xpath(Common.checkBox(SafetyAlert_OR.safetyAlertCheckbox,"Threat or Assault on Staff Member"))).click();
	driver.findElement(By.xpath(SafetyAlert_OR.saveAndProceedBtn)).click();
	driver.navigate().refresh();

    }
}
