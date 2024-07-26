package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import OR.Assignment_OR;

import Utility.Common;
import Utility.WebDriverHelpers;

public class Assignment_Page extends WebDriverHelpers{

	public static void addPrimaryWorker() {
		Common.scrollTillElement(Assignment_OR.primaryWorker);
		driver.findElement(By.xpath(Assignment_OR.primaryWorkerTB)).sendKeys("");
		selectPrimaryWorker("");
		driver.findElement(By.xpath(Assignment_OR.saveBtn)).click();
		
	}
	
	public static void selectPrimaryWorker(String value) {
		List<WebElement> searchlist = driver.findElements(By.xpath(Assignment_OR.primaryWorkerList));
		for(int i = 1 ; i<searchlist.size(); i++) {
			if(searchlist.get(i).getText().equalsIgnoreCase(value)){
				driver.findElement(By.xpath("//label[text()='Primary Worker']//parent::div//ul/li["+i+"]/span")).click();
				break;
			}
		}
	}
}
