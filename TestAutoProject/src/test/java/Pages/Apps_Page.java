package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.Apps_OR;
import Utility.WebDriverHelpers;

public class Apps_Page extends WebDriverHelpers {
	
	public static void selectApp() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Apps_OR.selectApp)));
		driver.findElement(By.xpath(Apps_OR.selectApp)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Apps_OR.caresTitle)));
	}

}
