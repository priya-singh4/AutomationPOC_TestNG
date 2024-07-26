package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.Logout_OR;
import Utility.WebDriverHelpers;

public class Logout_Page extends WebDriverHelpers{
	
	public static void logout() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Logout_OR.profileIcon)));
		js.executeScript("arguments[0].click()",Logout_OR.profileIcon);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Logout_OR.logoutLink)));
		driver.findElement(By.xpath(Logout_OR.logoutLink)).click();
	}
}
