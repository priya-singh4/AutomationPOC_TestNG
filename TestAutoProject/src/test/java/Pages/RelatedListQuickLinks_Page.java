package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.RelatedListQuickLinks_OR;
import OR.Tribal_Inquery_Collaboration_OR;
import Utility.Common;
import Utility.WebDriverHelpers;

public class RelatedListQuickLinks_Page extends WebDriverHelpers{
	
	public static void validateScreeningPerson(String ScenarioName , int i) {
		
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.screeningPerson_link)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.screeningPersonTitle)));
		
		driver.findElement(By.xpath("//table[@aria-label='Screening Persons']//tbody/tr["+i+"]/th//a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.validatePersonBtn)));
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.validatePersonBtn)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.searchForPersonTitle)));
		
		Common.scrollTillElement(RelatedListQuickLinks_OR.searchBtn);
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.searchBtn)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.searchResult)));
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.newPersonBtn)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.NewPersonPopUp)));
		Common.scrollTillElement(RelatedListQuickLinks_OR.sexAtBirthlabel);
		ddSelect.selectByVisibleText(Common.getDropdownValue(RelatedListQuickLinks_OR.sexAtBirthDD, ""));
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.saveBtn)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.validatePersonBtn)));
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.screeningIdLink)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.relatedListQuickLinksTitle)));
	}
	
	public static void approvalRequest() {
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.approvalHistory_link)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.approvalHistoryTitle)));
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.approvalBtn)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RelatedListQuickLinks_OR.approvalScreenPopUpHeader)));
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.approvalComments)).sendKeys("");
		driver.findElement(By.xpath(RelatedListQuickLinks_OR.appovelBtn)).click();
		
		
		

	}
	
	
	

}
