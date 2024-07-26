package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.Tribal_Inquery_Collaboration_OR;
import Utility.Common;
import Utility.WebDriverHelpers;

public class Tribal_Inquery_Collaboration_Page extends WebDriverHelpers{

	public static void addTribalInqueryData() {
	
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Tribal_Inquery_Collaboration_OR.tribalInqueryCollaborationTitle)));
		
		driver.findElement(By.xpath(Tribal_Inquery_Collaboration_OR.contact_date)).sendKeys("");
		driver.findElement(By.xpath(Tribal_Inquery_Collaboration_OR.contact_time)).sendKeys("");
		ddSelect.selectByVisibleText(Common.getDropdownValue(Tribal_Inquery_Collaboration_OR.participantTypeDD, ""));
		driver.findElement(By.xpath(Tribal_Inquery_Collaboration_OR.onBehalfOfChild)).sendKeys("");
		ddSelect.selectByVisibleText(Common.getDropdownValue(Tribal_Inquery_Collaboration_OR.methodDD, ""));
		ddSelect.selectByVisibleText(Common.getDropdownValue(Tribal_Inquery_Collaboration_OR.contactPurposeDD, ""));
		ddSelect.selectByVisibleText(Common.getDropdownValue(Tribal_Inquery_Collaboration_OR.contactStatusDD, ""));
		ddSelect.selectByVisibleText(Common.getDropdownValue(Tribal_Inquery_Collaboration_OR.initial_ICWA_InqueryDD, ""));
		
		driver.findElement(By.xpath(Tribal_Inquery_Collaboration_OR.saveAndProceedBtn)).click();
	}
	
}
