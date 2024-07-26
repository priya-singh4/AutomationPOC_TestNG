package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class Common extends WebDriverHelpers{
	
	public static String URL_SEPARATOR = "/";
	public static String PROJECT_DIRECTORY = System.getProperty("user.dir");
	public static String FILE_SEPARATOR = File.separator;
	public static String CONFIGURATION_DIR = "Config";

	public static String CONFIGURATION_PATH = PROJECT_DIRECTORY + FILE_SEPARATOR + CONFIGURATION_DIR + FILE_SEPARATOR;
	public static String BASE_CONFIG_FILE = CONFIGURATION_PATH + "baseConfig.properties";

	
	public static Random rand = new Random();
	public static String screeningId = null;
	public static String folioId = null;
	
	public static void pageRefresh() {
		driver.navigate().refresh();
		hardWaitInSeconds(8);
	}

	public static void hardWaitInSeconds(int unit) {
		try {
			Thread.sleep(unit * 1_000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public static String getDropdownValue(String xpath, String data) {
		String value;
		value = xpath.replace("%s", data);
		return value;
	}
	
	public static void scrollTillElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView();", element);
		
	}
	
	public static String getRandomAlphabet(int range) {
		range = range-4;
		String autoVal = "Auto";
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(range);
		for(int i = 0; i<range; i++) {
			sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		}
		autoVal = autoVal+sb.toString();
		return autoVal.toString();
	}
	
	public static String getRandomNumber(int range) {
		StringBuilder sb = new StringBuilder(range);
		for(int i = 0; i<range; i++) {
			sb.append(Integer.toString(rand.nextInt(9)));
		}
		return sb.toString();
	}
	
	public static void switchToLastTab() {
		ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(tabList.size()-1));
	}
	
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static void closeAllOtherWindowtabs() throws NoSuchWindowException {
		String originalHandle = driver.getWindowHandle();
	    for(String handle : driver.getWindowHandles()) {
	        if (!handle.equals(originalHandle)) {
	            driver.switchTo().window(handle);
	            driver.close();
	        }
	    }
	    driver.switchTo().window(originalHandle);
	}
	
	public static void switchFrame() {
		if(driver.getPageSource().contains("iframe")) {
			driver.switchTo().frame(0);
		}
	}
	
	public static void switchTab(int tabNumber) {
		ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(tabNumber));
		
	}
	
	public static void switchFrameByIndex(int index) {
		driver.switchTo().defaultContent();
		if(driver.getPageSource().contains("iframe")) {	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//iframe)["+index+"]")));
			driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)["+index+"]")));
		}
	}

	public static void switchToMainFrame() {
		if(driver.getPageSource().contains("iframe")) {
			driver.switchTo().defaultContent();
		}
	}
	
	public static String radioButton(String xpath, String data) {
		String value;
		value= xpath.replace("%s", data);
		return value;
	}
	public static String checkBox(String xpath, String data) {
		String value;
		value= xpath.replace("%s", data);
		return value;
	}
	public static String getDate(String format, String whichDate) {
         
		if (whichDate.equalsIgnoreCase("today")) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date date = new Date();
			String strDate = formatter.format(date);
			return strDate;
		}
		 else if (whichDate.equalsIgnoreCase("future")) {

				SimpleDateFormat formatter = new SimpleDateFormat(format);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, +1);
				Date date = cal.getTime();
				String strDate = formatter.format(date);
				return strDate;
			} else if (whichDate.equalsIgnoreCase("past")) {

				SimpleDateFormat formatter = new SimpleDateFormat(format);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				Date date = cal.getTime();
				String strDate = formatter.format(date);
				return strDate;
			} else if (whichDate.equalsIgnoreCase("FutureDate")) {

				SimpleDateFormat formatter = new SimpleDateFormat(format);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, +1);
				Date date = cal.getTime();
				String strDate = formatter.format(date);
				return strDate;
			} else if (whichDate.equalsIgnoreCase("FutureDateAdded")) {

				SimpleDateFormat formatter = new SimpleDateFormat(format);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, +1);
				cal.add(Calendar.DATE, +5);
				Date date = cal.getTime();
				String strDate = formatter.format(date);
				return strDate;
			}
		return null;
	}
	public static String getProperty(String propertyFileName, String propertyName)  {
		String filePath = BASE_CONFIG_FILE;
		String propertyValue = null;
		if (propertyFileName.isEmpty()) {
			try (FileInputStream propFile = new FileInputStream(filePath)) {
				Properties p = new Properties(System.getProperties());
				p.load(propFile);
				System.setProperties(p);
				propertyValue = System.getProperty(propertyName);
			} catch (NullPointerException ex) {
				ex.getStackTrace();
			} catch (FileNotFoundException e) {
			} catch (IOException e1) {
				
			}

			if (propertyValue != null && !propertyValue.isEmpty()) {
				return propertyValue;
			}
		} else {
			filePath = CONFIGURATION_PATH + propertyFileName + ".properties";
		}
		try (FileInputStream propFile = new FileInputStream(filePath)) {
			props.load(propFile);
		} catch (IOException ex) {
		}
		propertyValue = props.getProperty(propertyName);
		if (propertyValue != null) {
			
		}
		return propertyValue;
	}
}
