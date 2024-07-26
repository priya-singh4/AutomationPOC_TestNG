package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import OR.SuperviserReview_Promotion_OR;
import Utility.Common;
import Utility.WebDriverHelpers;
import java.util.*;

public class SuperviserReview_Promotion_Page extends WebDriverHelpers{
	
	public static void addSupervisorReviewDetails() {
		
		Common.scrollTillElement(SuperviserReview_Promotion_OR.approvalDetails_Title);
		driver.findElement(By.xpath(SuperviserReview_Promotion_OR.approvalSupervisor)).sendKeys("");
		selectApprovalSupervisor("");
		driver.findElement(By.xpath(SuperviserReview_Promotion_OR.saveAndProceedBtn)).click();
	}
	
	public static void selectApprovalSupervisor(String value) {
		List<WebElement> searchlist = driver.findElements(By.xpath(SuperviserReview_Promotion_OR.approvalSupersiorList));
		for(int i = 1 ; i<searchlist.size(); i++) {
			if(searchlist.get(i).getText().equalsIgnoreCase(value)){
				driver.findElement(By.xpath("//lightning-base-combobox-item[@exportparts='option']//parent::div/ul/li["+ i +"]//lightning-base-combobox-formatted-text")).click();
				break;
			}
		}
	}
	

}
