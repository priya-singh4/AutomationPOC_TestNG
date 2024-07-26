package Pages;

import org.openqa.selenium.By;

import OR.screeningProcess_OR;
import Utility.Common;
import Utility.WebDriverHelpers;

public class screeningProcess_Page extends WebDriverHelpers{

	public static void screeningProcess(String title) {
		switch(title) {
		case "Assignment":
			Common.scrollTillElement(screeningProcess_OR.assignment);
			driver.findElement(By.xpath(screeningProcess_OR.assignment)).click();
			break;
		default :
			System.out.println(title + " not selected");
			break;
		}
	}
}
