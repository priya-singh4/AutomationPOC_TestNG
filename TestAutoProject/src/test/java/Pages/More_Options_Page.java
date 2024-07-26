package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.More_Options_OR;
import Utility.WebDriverHelpers;
import junit.framework.Assert;

public class More_Options_Page extends WebDriverHelpers{
	
	public static void generateErrDoc() {
		
		driver.findElement(By.xpath(More_Options_OR.generateErrBtn)).click();
		
	}
	
	public static void submitForApproval() {
		
		driver.findElement(By.xpath(More_Options_OR.submitForApprovalBtn)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(More_Options_OR.submitForApprovalPopUp)));
		driver.findElement(By.xpath(More_Options_OR.comments)).sendKeys("");
		driver.findElement(By.xpath(More_Options_OR.submitBtn)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(More_Options_OR.submitForApprovalPopUp)));
		String expectedResult = driver.findElement(By.xpath(More_Options_OR.submitApprovalTextvalidation)).getText();
		Assert.assertEquals(expectedResult, "ActualResult");
		driver.findElement(By.xpath(More_Options_OR.closeBtn)).click();
		
		
	}
}
