package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import OR.InitialScreening_OR;
import OR.AddAllegation_OR;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utility.Common;
import Utility.WebDriverHelpers;

public class AddAllegation extends WebDriverHelpers{
	
	public void addAllegation() {
		
	ddSelect.selectByVisibleText(Common.getDropdownValue(AddAllegation_OR.allegationTypedrpDwn, "Caretaker Absence/Incapacity"));
	Common.scrollTillElement(AddAllegation_OR.allegedVictimdrpDwn);
	ddSelect.selectByVisibleText(Common.getDropdownValue(AddAllegation_OR.allegedVictimdrpDwnOption, "victim test"));
	ddSelect.selectByVisibleText(Common.getDropdownValue(AddAllegation_OR.allegedPerpetratordrpDwnOption, "perpetrator test"));
	driver.findElement(By.xpath(Common.radioButton(AddAllegation_OR.allegationSubtypeValue1,"Abandonment"))).click();
	driver.findElement(By.xpath(AddAllegation_OR.moveToSelectedAllegationBtn)).click();
	driver.findElement(By.xpath(AddAllegation_OR.saveAndProceedBtn)).click();
	driver.navigate().refresh();
	

}
	public static void selectStoredVictim(String value) {
		List<WebElement> storedvictim= driver.findElements(By.xpath(AddAllegation_OR.allegedVictimdrpDwn));
		for(int i=1;i<storedvictim.size(); i++) {
			if(storedvictim.get(i).getText().equalsIgnoreCase(value)) {
				driver.findElement(By.xpath(""));
				break;
			}
		}
		
	}
}