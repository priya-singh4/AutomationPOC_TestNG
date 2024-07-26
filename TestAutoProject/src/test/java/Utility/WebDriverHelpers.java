package Utility;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelpers {
	
	public static WebDriver driver;
	
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	public static Select ddSelect = new Select(null);
	public static JavascriptExecutor js = (JavascriptExecutor)driver;
	public static WebElement we;
	public static Properties props = new Properties();
	
	public static void SelectDriver() throws InterruptedException { 
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\njaikumar\\MavenProject\\AutoProject\\WebDriver\\chromedriver.exe");

		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().deleteAllCookies(); 
		driver.manage().window().maximize(); 
		Thread.sleep(2000);
		driver.navigate().to("Application URL needs to be added"); 
		Thread.sleep(2000);
	}
	
	public static void quitDriver() {
		driver.quit();
		driver.close();
	}
}
