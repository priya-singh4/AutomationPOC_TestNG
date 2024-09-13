package adautilities;
import org.openqa.selenium.chrome.ChromeOptions;

import com.deque.html.axecore.providers.FileAxeScriptProvider;
import com.deque.html.axecore.results.Node;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;

import testsettings.TestRunSettings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestADA {
	private static final Logger logger =LoggerFactory.getLogger(TestADA.class.getName());
	
	
	public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ronashah\\Desktop\\project\\GPS\\Workspace\\GPSCommon\\GPSAutomation\\GPSCommon\\src\\main\\resources\\BrowserDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
 
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
  
        driver.get("https://10.187.55.103:8443/LoginServlet?ACTION=LOGIN&PAGE_ID=SELOG&fromIndex=true");
        Thread.sleep(5000);

        adaScan(driver,"Login");
        
        driver.findElement(By.xpath("//input[@name='userId']")).sendKeys("adarsgupta");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@name='LoginBtn']")).click();
      
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@id='ARTOP']")).click();

        Thread.sleep(3000);
        adaScan(driver,"ARStep1");
        
	    driver.quit();
	}

	public static void adaScan(WebDriver driver,String screenName)
	{
		    String strHelp = "";
	        String strImpact = "";
	        String strDescription = "";
	        String strHelpUrl = "";
	        String strId = "";
	        String strTags = "";
	     
	        StringBuilder axeResults = new StringBuilder();
	     
	        		
	               AxeBuilder builder = new AxeBuilder();
	               String timeoutFilePath = TestRunSettings.getAdaToolPath();
	               axeResults.append("Screen Name,Help,Impact,Description,Help Url,Id,Tags");
	               axeResults.append(System.lineSeparator());
	               try {
	                     FileAxeScriptProvider axeScriptProvider;
	                     axeScriptProvider = new FileAxeScriptProvider(timeoutFilePath);
	                      builder.setAxeScriptProvider(axeScriptProvider);                      
	                     builder.include(Arrays.asList("html"));
	                     Results result = builder.analyze(driver);
	                     List<Rule> violations = result.getViolations();
	                     logger.info("ScreenName {}{}", screenName, result.getError());
	                     logger.info("ScreenName{}",result.getErrorMessage());                
	                     String url=driver.getCurrentUrl();
	                     String pageID = url.substring(url.lastIndexOf("/") + 1, url.length());
	  
	                     logger.info("Violation of the Screen {}:{}",screenName,violations.size());
	                    
	                     

	                     violationValidation(screenName, violations, pageID);

	                     for (Rule element : violations) {
	                            strHelp = element.getHelp();
	                            strImpact = element.getImpact();
	                            strDescription = "\"" + element.getDescription() + "\"";
	                            strHelpUrl = element.getHelpUrl();
	                            strId = element.getId();
	                            strTags = "\"" + String.join(",", element.getTags()) + "\"";

	                            axeResults.append(screenName + "," + strHelp + "," + strImpact + "," + strDescription + ","
	                                         + strHelpUrl + "," + strId + "," + strTags);
	                            axeResults.append(System.lineSeparator());

	                            if (element.getNodes() != null && !element.getNodes().isEmpty()) {
	                                  for (Node item : element.getNodes()) {
	                                         if (item.getHtml().trim().length() > 0 && item.getTarget().toString().trim().length() > 0) {
	                                               String htmlContent = item.getHtml();
	                                              htmlContent = htmlContent.replace(",", "_");   
	                                              htmlContent = htmlContent.replaceAll("\\s", "");   
	                                               axeResults.append(screenName + "," + strHelp + "," + strImpact + "," + strDescription
	                                                             + "," + strHelpUrl + "," + "\"" + htmlContent + "\"" + "," + "\""
	                                                             + item.getTarget() + "\"");
	                                                axeResults.append(System.lineSeparator());
	                                         }
	                                  }
	                            }
	                     }
	                     BufferedWriter writer = null;
	                     
	                     File file = new File(TestRunSettings.getResultsPath() + File.separator 
	                                               + screenName + ".csv");
	                     
	                     bufferWriter(axeResults, writer, file);
	               } catch (Exception e) {
	            	   logger.info(Arrays.toString(e.getStackTrace()));
	               }

	}

	private static void bufferWriter(StringBuilder axeResults, BufferedWriter writer, File file) {
		
		 try {
	            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
	            Files.setPosixFilePermissions(file.toPath(), perms);
	        } catch (IOException e) {
	        	logger.info(Arrays.toString(e.getStackTrace()));
	        }
		
		try ( BufferedWriter writer1 = new BufferedWriter(new FileWriter(file))) {
		        writer1.write(axeResults.toString());
		        
		 } catch (Exception e1) {
			 logger.info(Arrays.toString(e1.getStackTrace()));

		 } finally {
		        writer(writer);
		 }
	}

	private static void writer(BufferedWriter writer) {
		if (writer != null) {
		      try {
		             writer.close();
		      } catch (IOException e) {

		    	  logger.info(Arrays.toString(e.getStackTrace()));
		      }
		}
	}

	private static void violationValidation(String screenName, List<Rule> violations, String pageID) {
		if (violations.isEmpty()) {
		     
		        logger.info("No violations found: {} with PageID: {}", screenName,pageID);
		                    
		 } 
		 else {
			logger.info("ADA violations exists on the page: {} with PageID:{} Violations found",screenName, pageID);
		             
		 }
	}
}	
