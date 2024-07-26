package Pages;

import org.openqa.selenium.By;

import OR.Headers_OR;
import Utility.WebDriverHelpers;

public class Headers_Page  extends WebDriverHelpers {
	
	public static void selectHeader(String headerName) {
		switch(headerName) {
		case "Home":
			driver.findElement(By.xpath(Headers_OR.home)).click();
			break;
		case "Screenings":
			driver.findElement(By.xpath(Headers_OR.screenings)).click();
			break;
		default :
			System.out.println(headerName + " not selected");
			break;
		}
	}

}
