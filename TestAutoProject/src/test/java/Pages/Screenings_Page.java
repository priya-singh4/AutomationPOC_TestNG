package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.Screenings_OR;
import Utility.Common;
import Utility.WebDriverHelpers;

public class Screenings_Page extends WebDriverHelpers{
	
	public static void newButtonClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Screenings_OR.newBtn)));
		driver.findElement(By.xpath(Screenings_OR.newBtn)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Screenings_OR.screeningsTitle)));
	}
	
	public static void enterSceenigId() {
		driver.findElement(By.xpath(Screenings_OR.searchBox)).click();
		driver.findElement(By.xpath(Screenings_OR.searchBox)).sendKeys(Common.screeningId);
		we.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(Screenings_OR.screeingIdClick)).click();
		
	}
	public static void storeScreeningId() {
		Common.screeningId = "";
	}
	
	public static void storeFolioId() {
		Common.folioId = driver.findElement(By.xpath(Screenings_OR.folioID)).getText();
	}
	
}
