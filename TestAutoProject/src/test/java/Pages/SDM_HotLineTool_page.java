package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import OR.SDM_HotLineTool_OR;
import Utility.Common;
import Utility.WebDriverHelpers;

public class SDM_HotLineTool_page extends WebDriverHelpers{
	
	public static void addSDMDetails() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SDM_HotLineTool_OR.SDMHotlienTool_Title)));
		ddSelect.selectByVisibleText(Common.getDropdownValue(SDM_HotLineTool_OR.immediateDD, ""));

		driver.findElement(By.xpath(SDM_HotLineTool_OR.saveAndProceedBtn)).click();
	}

}
