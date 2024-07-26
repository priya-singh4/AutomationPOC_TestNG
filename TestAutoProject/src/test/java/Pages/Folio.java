package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import OR.Folio_OR;
import OR.InitialScreening_OR;
import Utility.Common;
import Utility.WebDriverHelpers;

public class Folio extends WebDriverHelpers {
	
	public static void enterFolioId() {
		driver.findElement(By.xpath(Folio_OR.searchBox)).click();
		driver.findElement(By.xpath(Folio_OR.searchBox)).sendKeys(Common.folioId);
		we.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(Folio_OR.folioIdSelected)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Folio_OR.folioNameText)));
		Common.scrollTillElement(Folio_OR.investigationDetails);
		driver.findElement(By.xpath(Folio_OR.investigationDetails)).click();
		
	}
	
	public static void updateALlegation() {
		driver.findElement(By.xpath(Folio_OR.allegatioPartialLink)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Folio_OR.allegationRecord)));
		driver.findElement(By.xpath(Folio_OR.allegationRecord)).click();;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Folio_OR.allegationConclusionDrpDwn)));
		Common.scrollTillElement(Folio_OR.allegationConclusionDrpDwn);
		ddSelect.selectByVisibleText(Common.getDropdownValue(Folio_OR.allegationConclusionDrpDwn, "Unfounded"));
		driver.findElement(By.xpath(Folio_OR.allegationConclusionRationale)).sendKeys("test rationale");
		Common.scrollTillElement(Folio_OR.saveBtn);
		driver.findElement(By.xpath(Folio_OR.saveBtn)).click();
		
	}
	
	public static void updateDisposition() {
		driver.findElement(By.xpath(Folio_OR.dispositionPartialLink)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Folio_OR.dispositionRecord)));
		driver.findElement(By.xpath(Folio_OR.dispositionRecord)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Folio_OR.editDispostion)));
		Common.scrollTillElement(Folio_OR.editDispostion);
	}
   
   public static void addContactLog() {
	   Common.pageRefresh();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Folio_OR.newInvestigationContact)));
	   driver.findElement(By.xpath(Common.radioButton(Folio_OR.contactType, "Structured Investigation Contact")));
	   Common.scrollTillElement(Folio_OR.nextBtn);
	   driver.findElement(By.xpath(Folio_OR.nextBtn)).click();
	   
   }
   
}
