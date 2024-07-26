package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.Login_OR;
import Utility.WebDriverHelpers;

public class Login_Page extends WebDriverHelpers{
	
	
	public static void login() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Login_OR.loginTitle)));
		driver.findElement(By.xpath(Login_OR.user_Name)).sendKeys("");
		driver.findElement(By.xpath(Login_OR.password)).sendKeys("");
		
		driver.findElement(By.xpath(Login_OR.signInBtn)).click();
	}
	

}
