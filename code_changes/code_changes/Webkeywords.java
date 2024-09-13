package commonutilities.common.actionkeywords;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reportutilities.common.ReportCommon;
import reportutilities.common.ScreenshotCommon;
import reportutilities.extentmodel.ExtentUtilities;
import reportutilities.extentmodel.PageDetails;
import reportutilities.model.TestCaseParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;




public class Webkeywords
{
	private static final String TITLE_ATTRIBUTE = "title";
	private static final String SUCCESSFULLY_ENTERED_TEXT ="Successfully Entered Text {} to {}";

	private static final String VERIFY_ELEMENT_TEXT = "Verify Element Text";
	private static final String VERIFY_ELEMENT_NOT_DISPLAYED = "Verify Element Not Displayed--";
	private static final String ENTERED_TEXT ="Entered Text -> ";

	private static final String VERIFY_ELEMENT_TITLE = "Verify Element Title";
	private static final String NAVIGATE = "Navigate -> ";
	private static final String CONTAINS_EXPECTED_TEXT = "Contains Expected Text:-";
	private static final String EXPECTED = ":Expected:-";
	private static final String FAILED_FORMAT = "Failed ==> {} {} ";
	private static final String ACTUAL_VALUE_DISPLAYED = "Actual Value Displayed-->";
	private static final String ACTUAL_TEXT_DISPLAYED = "Actual Text Displayed-->";
	private static final String LOG_FAILED_FORMAT = "Failed ==> {} {}";
	private static final String TESTDATA_NOT_APPLICABLE = "n\\\\a";

	private static final String CLICK_ACTION_PREFIX = "Click --> ";

	private static final String VALUE = "value";
	private static final String ERROR_LOG_FORMAT = "Failed ==>{} {} ";
	private static final String LOG_SWITCHED_TO = "Switched to: {}";

	private static final String EXPECTED_VALUE_DELIMITER = "<--> Expected Value-->";



	private static final Webkeywords INSTANCE = new Webkeywords();
	private static final Logger logger =LoggerFactory.getLogger(Webkeywords.class.getName());
	private static final String STATUSPASS="PASS";
	private static final String STATUSFAIL="FAIL";
	private static final String STATUSDONE="DONE";
	private static final String  VRIFYELEMENT= "Verify Element Displayed--";
	private static final String EMPTYTEXTMSG="Textbox is empty. Retrying...";
	private static final String TABLECOLUMNMSG= "Table Columns Text Displayed-->";
	ExtentUtilities extentUtilities = new ExtentUtilities();
	ScreenshotCommon scm = new ScreenshotCommon();

	ReportCommon testStepDetails = new ReportCommon();
	ReportCommon exceptionDetails = new ReportCommon();

	Webkeywords()
	{
		
	}
	
	public static Webkeywords instance()
	{

		return INSTANCE;

	}

	public void navigate(WebDriver driver, String url, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String actionNv = NAVIGATE+url;
		String actionDescriptionNv = "";
		LocalDateTime startTime=  LocalDateTime.now();

		try
		{

				driver.navigate().to(url);

			testStepDetails.logTestStepDetails(driver, testCaseParam, actionNv, actionDescriptionNv,pageDetails, startTime, STATUSDONE);
			logger.info(SUCCESSFULLY_ENTERED_TEXT,url);

		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT, actionNv, actionDescriptionNv);
			testStepDetails.logExceptionDetails(driver, testCaseParam, actionNv, actionDescriptionNv, startTime,e);
			testStepDetails.logTestStepDetails(driver, testCaseParam, actionNv, actionDescriptionNv,pageDetails, startTime, STATUSFAIL);

			throw e;
		}


	}

	public void openUrl(WebDriver drivernew, String url, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Navigate -->"+url ;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{

			if (!(url.startsWith("http://") || url.startsWith("https://")))
				drivernew.navigate().to(url);
		}
		catch (Exception e)
		{
			logger.error(FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			throw e;
		}
	}


	public boolean checkOptions(String options) {
		return options.equals("N//A") || options.equals("N/A") || options.equals("n//a") || options.equals("n/a");
	}

	public void trymultipleclick(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		Webkeywords.instance().fluentWait(drivernew, element);
		String action="";
		action = getaction(drivernew, element, testCaseParam, pageDetails);

		String actionDescription = element.toString();
		LocalDateTime startTime=  LocalDateTime.now();


		try
		{
			Actions actionnew = new Actions(drivernew);
			actionnew.moveToElement(element).build().perform();
			element.click();
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);


		}
		catch (Exception e)
		{
			try
			{

				Actions actionnew = new Actions(drivernew);
				actionnew.moveToElement(element).build().perform();
				element.click();
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);


			}

			catch(Exception ex)
			{
				try
				{

					Actions actionnew = new Actions(drivernew);
					actionnew.moveToElement(element).build().perform();
					element.click();
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);


				}

				catch(Exception exx)
				{
					try
					{

						Actions actionnew = new Actions(drivernew);
						actionnew.moveToElement(element).build().perform();
						element.click();
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);



					}

					catch(Exception exxx)
					{
						try
						{
							Actions actionnew = new Actions(drivernew);
							actionnew.moveToElement(element).build().perform();
							element.click();
							testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);



						}

						catch(Exception exxxx)
						{
							handleActionExc(drivernew, element, testCaseParam, pageDetails, action, actionDescription,
									startTime);

						}

					}

				}

			}
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

			throw e;
		}
	}

	private void handleActionExc(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,
								 PageDetails pageDetails, String action, String actionDescription, LocalDateTime startTime) throws IOException
	{
		try
		{
			Actions actionnew = new Actions(drivernew);
			actionnew.moveToElement(element).build().perform();
			element.click();
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);



		}

		catch(Exception exp)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,exp);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);



		}
	}

	private String getaction(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,
							 PageDetails pageDetails) throws IOException {
		String action;
		try
		{
			action = CLICK_ACTION_PREFIX +getLocatorFromWebElement(element,drivernew,testCaseParam,pageDetails);

		}
		catch(Exception e)
		{
			action = CLICK_ACTION_PREFIX + getLocatorFromWebElement(element,drivernew,testCaseParam,pageDetails);
		}
		return action;
	}




	public void tryAction(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,
						   PageDetails pageDetails, String action, String actionDescription, LocalDateTime startTime) throws IOException, InterruptedException {
			Actions actionnew = new Actions(drivernew);
			actionnew.moveToElement(element).build().perform();

			element.click();


			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			Thread.sleep(900);

	}





		public void waitElementToBeVisibleNew(WebDriver drivernew, WebElement element, int timeOut) throws InterruptedException {
		int count = 0;
		int maxTries = 5;
		while (count < maxTries) {
			try {
				By locatorvalue = getLocatorvalue(element);
				WebDriverWait wait = new WebDriverWait(drivernew, timeOut);
				wait.until(ExpectedConditions.presenceOfElementLocated(locatorvalue));
				break;
			} catch (Exception e) {
				Thread.sleep(10000);
				count++;
				if (++count == maxTries) {
					throw e;
				}
			}
		}
	}

	public void waitForElementToBeVisible(WebDriver drivernew, String elementId) throws InterruptedException  {
		int count = 0;
		int maxTries = 5;
		int timeOut=3000;
		while (count < maxTries) {
			try {
				WebElement element=drivernew.findElement(By.xpath(elementId));
				By locatorvalue = getLocatorvalue(element);
				WebDriverWait wait = new WebDriverWait(drivernew, timeOut);
				wait.until(ExpectedConditions.presenceOfElementLocated(locatorvalue));
				break;
			} catch (Exception e) {
				Thread.sleep(10000);
				count++;
				if (++count == maxTries) {
					throw e;
				}
			}
		}
	}


	public String getLocatorFromWebElement(WebElement element,WebDriver drivernew,TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{

		String locatoroutput= element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
		String[] splitlocator=locatoroutput.split(": ");
		splitlocator[0] =splitlocator[0].replaceAll("\\s", "");
		splitlocator[1] =splitlocator[1].replaceAll("\\s", "");

		getElementText(drivernew, splitlocator[0],splitlocator[1],  testCaseParam,pageDetails);

		return getElementText(drivernew, splitlocator[0],splitlocator[1],  testCaseParam,pageDetails);
	}


	public void switchtoFrame(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{

			drivernew.switchTo().frame(element);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

		}
		catch (Exception e)
		{
			logger.error(FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}

	public void switchToWindowByTitle(WebDriver drivernew, String title, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException, InterruptedException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();

		Thread.sleep(2000);
		Set<String> windows=drivernew.getWindowHandles();


		for (String handle: windows)
		{

			String myTitle = drivernew.switchTo().window(handle).getTitle();
			if (myTitle.equals(title))
			{

				drivernew.switchTo().window(handle);
				logger.info("switched to Window--> {}",title);
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
				Thread.sleep(0);
				break;
			}
			else
			{
				switchToWindowHandle(drivernew, handle);
			}


		}

	}

	private void switchToWindowHandle(WebDriver drivernew, String handle) {
		try
		{
			drivernew.switchTo().window(handle);
			logger.info("switched to main Window");
		}
		catch (Exception e)
		{
			logger.info("Unable to switch to Main Window");
		}
	}


	public void refresh(WebDriver drivernew, TestCaseParam testCaseParam,PageDetails pageDetails)
	{
		String action = "Refresh the page ";
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{
			drivernew.navigate().refresh();
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);


		}
	}
	public void jsClick(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException {
		// Use default value if timeOut is not provided

		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime = LocalDateTime.now();

		try {
			JavascriptExecutor js = (JavascriptExecutor) drivernew;
			js.executeScript("arguments[0].click();", element);

			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription, pageDetails, startTime, STATUSDONE);
		} catch (Exception e) {
			logger.error(FAILED_FORMAT, action, actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime, e);
			throw e;
		}
	}



	public void zoombackToOriginal(WebDriver drivernew, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{


			JavascriptExecutor js = (JavascriptExecutor)drivernew;

			js.executeScript("document.body.style.zoom='100%'");

		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action ,actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			throw e;
		}
	}

	public void clickwithaction(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,PageDetails pageDetails,String testData) throws IOException, InterruptedException
	{

		String action="";
		String actionDescription="";
		LocalDateTime startTime=  LocalDateTime.now();
		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{
			try {


				Webkeywords.instance().fluentWait(drivernew, element);
				Webkeywords.instance().waitElementforelementclickable(drivernew, element, 1000);
				scrollIntoViewElement(drivernew, element);
				action = CLICK_ACTION_PREFIX+getLocatorFromWebElement(element,drivernew,testCaseParam,pageDetails);
				actionDescription = CLICK_ACTION_PREFIX+getLocatorFromWebElement(element,drivernew,testCaseParam,pageDetails);

				tryAction(drivernew, element, testCaseParam, pageDetails, action, actionDescription, startTime);

			}
			catch(IOException e)
			{
				logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			}
		}
	}



	public void clickRadioButton(WebDriver driver, TestCaseParam testCaseParam, WebElement element) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{

			for (int i = 0; i < 10; i++)
			{
				element.click();

				if (element.isSelected())
				{
					break;
				}


			}

		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action, actionDescription);
			testStepDetails.logExceptionDetails(driver, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}




	private boolean checktextboxtestExp(String textBoxValue, String textBoxtest) {

		return textBoxValue.matches("\\d")||textBoxtest.matches("\\d");
	}

	private boolean checktextboxExpression(String textBoxValue, String textBoxtest) {
		return textBoxValue.matches("^[a-zA-Z0-9]+$")||textBoxtest.matches("^[a-zA-Z0-9]+$");
	}


	public boolean checkIfTrue(String text) {
		return text.equalsIgnoreCase("N/A")|| text.equalsIgnoreCase("N//A");
	}

	public void setText(WebDriver drivernew, WebElement element, String text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException, InterruptedException
	{
		String action = ENTERED_TEXT + text;
		String actionDescription = ENTERED_TEXT+ text;

		LocalDateTime startTime=  LocalDateTime.now();

		try
		{

			if(!textCheck(text))
			{
				Webkeywords.instance().fluentWait(drivernew, element);
				WebDriverWait wait = new WebDriverWait(drivernew, 1000);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.clear();
				element.sendKeys(text);
				boolean isDataFilled = false;
				while (!isDataFilled) {


					String textBoxValue=element.getAttribute(VALUE);
					String textBoxtest=element.getText();
					isDataFilled = validateTextBoxValue(drivernew, element, text, isDataFilled, textBoxValue,
							textBoxtest);
				}

				logger.info(SUCCESSFULLY_ENTERED_TEXT ,text , element);

				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);


				Thread.sleep(0);
			}
		}
		catch (IOException e)
		{
			logger.error(FAILED_FORMAT ,action ,actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

		}

	}

	private boolean validateTextBoxValue(WebDriver drivernew, WebElement element, String text, boolean isDataFilled,
										 String textBoxValue, String textBoxtest) {
		if (checktextboxExpression(textBoxValue, textBoxtest))
		{
			isDataFilled = true;
		}

		else if (checkTestboxValue(textBoxValue, textBoxtest))
		{
			isDataFilled = true;
		}
		else if (textBoxValue.matches("^[a-z A-Z]+$")||textBoxtest.matches("^[a-z A-Z]+$"))
		{
			isDataFilled = true;
		}

		else if (checktextboxtestExp(textBoxValue, textBoxtest))
		{
			isDataFilled = true;
		}

		else if (checkTextBoxRegularExp(textBoxValue, textBoxtest)) {
			isDataFilled = true;
		}

		else if (textBoxValue.matches("^(?=.*[@#$!%^&+=-]).+$")||textBoxtest.matches("^(?=.*[@#$!%^&+=-]).+$")) {
			isDataFilled = true;
		}


		else
		{

			logger.info(EMPTYTEXTMSG);
			element.clear();


			JavascriptExecutor jsExecutor = (JavascriptExecutor) drivernew;
			jsExecutor.executeScript("arguments[0].value = arguments[1]", element, text);
		}
		return isDataFilled;
	}

	public void setTextwithoutverification(WebDriver drivernew, WebElement element, String text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException, InterruptedException
	{
		String action = ENTERED_TEXT+ text;
		String actionDescription = ENTERED_TEXT+ text;

		LocalDateTime startTime=  LocalDateTime.now();



		if(!textCheck(text))
		{

			Webkeywords.instance().fluentWait(drivernew, element);
			WebDriverWait wait = new WebDriverWait(drivernew, 1000);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(text);

			boolean isDataFilled = false;
			while (!isDataFilled) {


				String textBoxValue=element.getAttribute(VALUE);
				String textBoxtest=element.getText();
				if (checktextboxExpression(textBoxValue, textBoxtest))
				{
					isDataFilled = true;
				}

				else if (checkTestboxValue(textBoxValue, textBoxtest))
				{
					isDataFilled = true;
				}

				else if (checktextboxtestExp(textBoxValue, textBoxtest))
				{
					isDataFilled = true;
				}
				else if (textBoxValue.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=/_-]).+$") || textBoxtest.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=/_-]).+$")) {

					isDataFilled = true;				        }

				else if (textBoxValue.matches("^(?=.*\\d)(?=.*[@#$%^&+=/_-]).+$") || textBoxtest.matches("^(?=.*\\d)(?=.*[@#$%^&+=/_-]).+$")) {
					isDataFilled = true;				        }

				else if (checkTextBoxRegularExp(textBoxValue, textBoxtest)) {
					isDataFilled = true;
				}



				else
				{

					logger.info(EMPTYTEXTMSG);
					element.clear();


					JavascriptExecutor jsExecutor = (JavascriptExecutor) drivernew;
					jsExecutor.executeScript("arguments[0].value = arguments[1]", element, text);
				}
			}





			logger.info(SUCCESSFULLY_ENTERED_TEXT," {} to {}" ,text ,element);

			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

			Thread.sleep(0);
		}


	}

	private boolean checkTextBoxRegularExp(String textBoxValue, String textBoxtest) {
		return textBoxValue.matches("^(?=.*[a-zA-Z])(?=.*[@#$%^&+=/_-]).+$") || textBoxtest.matches("^(?=.*[a-zA-Z])(?=.*[@#$%^&+=/_-]).+$");

	}

	private boolean checkTestboxValue(String textBoxValue, String textBoxtest) {
		return textBoxValue.matches("^[a-zA-Z]+$")||textBoxtest.matches("^[a-zA-Z]+$");
	}

	private boolean textCheck(String text) {
		return text.equalsIgnoreCase("N/A")|| text.equalsIgnoreCase("N//A");
	}


	public void jsSetText(WebDriver drivernew, WebElement element, String text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{

		LocalDateTime startTime=  LocalDateTime.now();
		String action= "";
		String actionDescription = "";

		try
		{
			waitwebElementVisible(element);

			JavascriptExecutor js = (JavascriptExecutor)drivernew;

			js.executeScript("arguments[0].setAttribute('value','" + text + "');", element);
		}


		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
		}

	}

	public String jsGetText(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();

		String webText = "";

		try
		{
			webText = element.getText().trim();

		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);



		}
		return webText;

	}

	public void submit(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{
			waitwebElementVisible(element);
			element.submit();

			logger.info("Successfully Clicked Button ==> {}" ,element);
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			throw e;
		}

	}

	public void setDate(WebDriver drivernew, WebElement element, String text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{

		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try


		{
			String id = element.getAttribute("ID");
			((JavascriptExecutor)drivernew).executeScript("document.getElementById('" + id + "').removeAttribute('readonly',0);");


			element.click();

			((JavascriptExecutor)drivernew).executeScript("document.getElementById('" + id + "').setAttribute('value', '" + text + "')");

			drivernew.findElement(By.xpath("//div[@id=\"fare_" + text + "\"]")).click();


		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			throw e;
		}

	}

	public void click(WebDriver drivernew, WebElement element, String testData, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException, InterruptedException {
		if (!(testData.equalsIgnoreCase("n/a")) && !(testData.equalsIgnoreCase("n\\a")) && !(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE))) {
			String actionDescription = element.toString();
			LocalDateTime startTime = LocalDateTime.now();
			String action = CLICK_ACTION_PREFIX + getLocatorFromWebElement(element, drivernew, testCaseParam, pageDetails);

			try {
				Actions actionnew = new Actions(drivernew);
				actionnew.moveToElement(element).build().perform();
				element.click();

				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription, pageDetails, startTime, STATUSDONE);
				Thread.sleep(900);

			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
			} catch (Exception e) {
				try {
					Webkeywords.instance().waitElementforelementclickable(drivernew, element, 1000);
					JavascriptExecutor executor = (JavascriptExecutor) drivernew;
					executor.executeScript("arguments[0].scrollIntoView(true);", element);
					Thread.sleep(300);
					executor.executeScript("arguments[0].click();", element);

					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription, pageDetails, startTime, STATUSDONE);

				} catch (InterruptedException f) {
					// Re-interrupt the thread to preserve the interrupted status
					Thread.currentThread().interrupt();
				} catch (Exception f) {
					logger.error(LOG_FAILED_FORMAT, action, actionDescription);
					testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime, e);
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription, pageDetails, startTime, STATUSFAIL);

					throw e;
				}
			}
		}
	}



	public void waitElementforelementclickable(WebDriver drivernew,WebElement element, int timeOut) throws  InterruptedException
	{

		try
		{
			By locatorvalue=getLocatorvalue(element);

			WebDriverWait wait = new WebDriverWait(drivernew, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(locatorvalue));

		}
		catch (WebDriverException e)
		{
			Thread.sleep(1500);
			By locatorvalue=getLocatorvalue(element);

			WebDriverWait wait = new WebDriverWait(drivernew, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(locatorvalue));


		}
	}



	public static void waitForPageLoad(WebDriver driver) {

		throw new UnsupportedOperationException("This method is not supported.");
	}



	public By getLocatorvalue(WebElement element)
	{

		String locatoroutput= element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
		String[] splitlocator=locatoroutput.split(": ");
		splitlocator[0] = splitlocator[0].replaceAll("\\s", "");
		splitlocator[1] =splitlocator[1].replaceAll("\\s", "");


		return getlocatorvalueforwait(splitlocator[0],splitlocator[1]);
	}

	public By getlocatorvalueforwait(String locatorType,String locatorValue)
	{
		String action = NAVIGATE;
		String actionDescription = "";

		try
		{
			By locatorvalue = null;
			locatorType =locatorType.strip().trim();
			locatorValue =locatorValue.strip().trim();

			switch (locatorType.toLowerCase())
			{
				case " id":
					locatorvalue = By.id(locatorValue.trim());
					break;
				case " name":
					locatorvalue = By.name(locatorValue.trim());
					break;
				case " xpath":
					locatorvalue = By.xpath(locatorValue.trim());
					break;
				case " tag":
					locatorvalue = By.name(locatorValue.trim());
					break;
				case " link text":
					locatorvalue = By.linkText(locatorValue.trim());
					break;
				case " css":
					locatorvalue = By.cssSelector(locatorValue.trim());
					break;
				case " class":
					locatorvalue = By.className(locatorValue.trim());
					break;
				default:

			}
			return locatorvalue;
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action ,actionDescription);


			throw e;
		}

	}


	public void waitforinvisibilityofelement(WebDriver drivernew, By locator)
	{
		String action = NAVIGATE;
		String actionDescription = "";
		try
		{
			WebDriverWait wait = new WebDriverWait(drivernew, 3000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}
		catch(Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action ,actionDescription);
			throw e;
		}

	}

	public String wpGetPageTitle(WebDriver drivernew)
	{
		String action = NAVIGATE;
		String actionDescription = "";
		try {
			String wpPageTitle="";
			int len=drivernew.findElements(By.xpath("//*[@alt='Print' and @tabindex='0' and @class='vertical-align-middle cursor-hand height-20px printDoc']/.././../div/h2/span/label")).size();
			if(len>0)
			{
				wpPageTitle=drivernew.findElement(By.xpath("//*[@alt='Print' and @tabindex='0' and @class='vertical-align-middle cursor-hand height-20px printDoc']/.././../div/h2/span/label")).getText();
			}
			else
			{
				wpPageTitle="";
			}
			return wpPageTitle;
		}
		catch(Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action ,actionDescription);

			throw e;
		}
	}




	public void fluentWait(WebDriver drivernew, WebElement element) {
		try {
			Wait<WebDriver> wait2 = new FluentWait<WebDriver>(drivernew)
					.ignoring(Exception.class)
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(ElementNotVisibleException.class);

			wait2.until(driver -> {
				if (element.isDisplayed()) {
					return element;
				} else {
					return null;
				}
			});
		} catch (TimeoutException e) {
			logger.info(Arrays.toString(e.getStackTrace()));
		}
	}


	public void waitElementClickable(WebDriver drivernew, WebElement element)
	{
		String action = NAVIGATE;
		String actionDescription = "";
		try
		{
			logger.info("Waiting for Element = {}" ,element);
			WebDriverWait wait = new WebDriverWait(drivernew, 5000);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action ,actionDescription);

			throw e;
		}
	}


	public void waitElementClickable(WebDriver drivernew, WebElement locatorValue, int timeOut)
	{

		try
		{
			logger.info("Waiting for Element = {} ", locatorValue);
			WebDriverWait wait = new WebDriverWait(drivernew, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(locatorValue));

		}
		catch (TimeoutException e)
		{
			logger.error("Get {} , {} is not visible",e ,locatorValue);

		}
	}
	public void waitElementEnabled(WebElement element, int timeOut)
	{
		for (int i = 0; i < timeOut; i++)
		{

			if (element.isEnabled())
			{
				break;
			}




		}
	}

	public void switchFocusToOtherWindow(WebDriver drivernew, By pagetitlelocator)
	{
		String action = NAVIGATE;
		String actionDescription = "";
		try {

			Set<String> allWindows =  drivernew.getWindowHandles();
			for(String curWindow : allWindows){
				drivernew.switchTo().window(curWindow);
				if(!(drivernew.findElements(pagetitlelocator).isEmpty()))
				{
					String pageTitle=drivernew.findElement(pagetitlelocator).getText();
					logger.info(LOG_SWITCHED_TO,pageTitle);
				}
				else
				{
					String pageTitle=drivernew.getTitle();
					logger.info(LOG_SWITCHED_TO,pageTitle);

				}
			}

		}
		catch(Exception e)
		{
			logger.error(FAILED_FORMAT , action , actionDescription);
			throw e;
		}
	}

	public void switchFocusToMainWindow(WebDriver drivernew, By pagetitlelocator)
	{
		String action = NAVIGATE;
		String actionDescription = "";
		try {

			String pageTitle=drivernew.findElement(pagetitlelocator).getText();
			logger.info(LOG_SWITCHED_TO,pageTitle);
			Set<String> allWindows =  drivernew.getWindowHandles();

			for(String curWindow : allWindows){
				drivernew.switchTo().window(curWindow);
				if(!drivernew.findElements(pagetitlelocator).isEmpty())
				{
					pageTitle=drivernew.findElement(pagetitlelocator).getText();
					logger.info(LOG_SWITCHED_TO,pageTitle);
					break;
				}
				else
				{
					pageTitle=drivernew.getTitle();
					logger.info(LOG_SWITCHED_TO,pageTitle);

				}
			}
		}
		catch(Exception e)
		{
			logger.error(FAILED_FORMAT , action , actionDescription);
			throw e;
		}
	}

	public void waitTitleContains(WebDriver drivernew, String title)
	{
		String action = NAVIGATE;
		String actionDescription = "";
		try
		{
			int timeOut=10000;
			WebDriverWait wait = new WebDriverWait(drivernew, timeOut);
			wait.until(ExpectedConditions.titleContains(title));
		}
		catch (WebDriverException e)
		{
			logger.error(FAILED_FORMAT , action , actionDescription);
			throw e;
		}
	}

	public String getAttribute(WebElement element, String attribute)
	{
		return element.getAttribute(attribute);
	}

	public void verifyAttributeValue(WebDriver drivernew, WebElement element, String attribute,String expectedText,TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{

		String action = "Verify "+element.getAttribute(attribute)+" Attribute value-->"+expectedText;
		String actionDescription = "Verify "+element.getAttribute(attribute)+" Attribute value"+expectedText;
		LocalDateTime startTime = LocalDateTime.now();
		if(element.getAttribute(attribute).equalsIgnoreCase(expectedText))

		{
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
		}
		else
		{
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

		}
	}

	public boolean getTitle(WebDriver drivernew,String expectedTitle, TestCaseParam testCaseParam) throws IOException
	{

		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{

			String pageTitle = drivernew.getTitle();
			boolean validTitle = false;
			if (expectedTitle.contains(pageTitle))
			{
				validTitle = true;
			}
			else
			{
				validTitle = false;
			}

			logger.info(SUCCESSFULLY_ENTERED_TEXT ,pageTitle);
			return validTitle;


		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}

	public String getCssValue( WebElement element, String value)
	{
		return element.getCssValue(value);

	}

	public String getPageSource(WebDriver drivernew)
	{
		return drivernew.getPageSource();
	}



	public void clearText(WebDriver drivernew, WebElement element, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{

			element.clear();
		}
		catch (Exception e)
		{
			logger.error(FAILED_FORMAT ,action ,actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			throw e;
		}
	}

	public JavascriptExecutor javaScript(WebDriver driver)
	{
		return (JavascriptExecutor)driver;
	}

	public WebElement findElement(WebDriver drivernew, String value, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{
			WebElement element = null;
			String locatorType = value.split(";")[0];
			String locatorValue = value.split(";")[1];

			switch (locatorType.toLowerCase())
			{
				case "id":
					element = drivernew.findElement(By.id(locatorValue));
					break;
				case "name":
					element = drivernew.findElement(By.name(locatorValue));
					break;
				case "xpath":
					element = drivernew.findElement(By.xpath(locatorValue));
					break;
				case "tag":
					element = drivernew.findElement(By.name(locatorValue));
					break;
				case "link":
					element = drivernew.findElement(By.linkText(locatorValue));
					break;
				case "css":
					element = drivernew.findElement(By.cssSelector(locatorValue));
					break;
				case "class":
					element = drivernew.findElement(By.className(locatorValue));
					break;
				default:

			}
			return element;
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action ,actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

			throw e;
		}

	}

	public String getElementText(WebDriver drivernew, String locatorType,String locatorValue, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = NAVIGATE;
		String actionDescription = "";
		LocalDateTime startTime=  LocalDateTime.now();
		try
		{


			String text=null;
			WebElement element = null;
			locatorType =locatorType.trim();
			locatorValue =locatorValue.trim();

			switch (locatorType.toLowerCase())
			{
				case " id":
					element = drivernew.findElement(By.id(locatorValue));
					text=element.getAttribute(VALUE);
					text = checkTextNull(text, element);
					break;
				case " name":
				case " tag":
					element = drivernew.findElement(By.name(locatorValue));
					text=element.getAttribute(VALUE);
					text = checkTextNull(text, element);
					break;
				case " xpath":
					element = drivernew.findElement(By.xpath(locatorValue));
					text=element.getAttribute(VALUE);
					text = checkTextNull(text, element);
					break;

				case " link text":
					element = drivernew.findElement(By.linkText(locatorValue));
					text=element.getAttribute(VALUE);
					text = checkTextNull(text, element);
					break;
				case " css":
					element = drivernew.findElement(By.cssSelector(locatorValue));
					text=element.getAttribute(VALUE);
					text = checkTextNull(text, element);
					break;
				case " class":
					element = drivernew.findElement(By.className(locatorValue));
					text=element.getAttribute(VALUE);
					text = checkTextNull(text, element);
					break;
				default:
			}
			return text;
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

			throw e;
		}

	}

	private String checkTextNull(String text, WebElement element) {
		if(text==null)
		{
			text=element.getText();
		}
		return text;
	}

	public List<WebElement> findElements(WebDriver drivernew, String value)
	{
		List<WebElement> elements = null;
		String locatorType = value.split(";")[0];
		String locatorValue = value.split(";")[1];

		switch (locatorType.toLowerCase())
		{
			case "id":
				elements = drivernew.findElements(By.id(locatorValue));
				break;
			case "name":
				elements = drivernew.findElements(By.name(locatorValue));
				break;
			case "xpath":
				elements = drivernew.findElements(By.xpath(locatorValue));
				break;
			case "tag":
				elements = drivernew.findElements(By.name(locatorValue));
				break;
			case "link":
				elements = drivernew.findElements(By.linkText(locatorValue));
				break;
			case "css":
				elements = drivernew.findElements(By.cssSelector(locatorValue));
				break;
			case "class":
				elements = drivernew.findElements(By.className(locatorValue));
				break;
			default:

		}
		return  elements;
	}

	public boolean isElementVisible(WebElement element)
	{
		return element.isDisplayed();
	}

	public void waitwebElementVisible(WebElement locatorValue)
	{

		try
		{
			logger.info("Waiting for Element = {}" , locatorValue);

			boolean visible = isElementVisible(locatorValue);
			while (true)
			{
				if(visible)
				{
					break;
				}
				else
				{

					visible = false;
				}
			}

		}
		catch (Exception e)
		{
			logger.error("Error: {}, Locator value: {} is not visible.", e, locatorValue);
		}
	}




	public void verifyElementPresent(WebDriver driver, WebElement element,TestCaseParam testCaseParam) throws IOException
	{
		String testStepName = "VerifyElementPresent";
		String testStepDescription = "VerifyElementPresent";
		LocalDateTime startTime = LocalDateTime.now();
		Boolean isDisplayed = false;

		try
		{

			waitwebElementVisible(element);
			isDisplayed = isElementVisible(element);
			logger.info("Element present in application: {}" , isDisplayed);
			if (isDisplayed.equals(true))
			{
				logger.info("The Element is displayed in the application");

			}
			else
			{
				logger.info("The Element is not displayed in the application");

			}
		}
		catch (Exception e)
		{
			logger.error("Failed ==> {}" , testStepDescription);
			exceptionDetails.logExceptionDetails(driver, testCaseParam, testStepName, testStepDescription, startTime);
			throw e;
		}
	}


	public void documentUpload(WebDriver drivernew, WebElement element,String path,String docName, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Upload Document";
		String actionDescription = "Upload Document";
		LocalDateTime startTime = LocalDateTime.now();


		try
		{
			Webkeywords.instance().fluentWait(drivernew, element);


			element.sendKeys(path+docName);

			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);


		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}


	//*********************************SCREEN VALIDATIONS**************************************************


	public void verifyDropdownText(WebDriver drivernew, WebElement element, String options, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Verify Dropdown Text-->Actual:"+options+EXPECTED+element.getText();
		String actionDescription = "Verify Dropdown Text-->Actual:"+options+EXPECTED+element.getText();
		LocalDateTime startTime = LocalDateTime.now();
		Boolean valuefound = false;

		try
		{
			if(!(options.equals("N//A")||options==null))
			{

				Webkeywords.instance().fluentWait(drivernew, element);
				Select select = new Select(element);
				List<WebElement> allOptions =select.getAllSelectedOptions();
				for(int i=0; i<allOptions.size();i++)
				{
					if(allOptions.get(i).getText().contains(options))
					{
						valuefound=true;
						action = "Verify Dropdown-->Actual:"+options+EXPECTED+allOptions.get(i).getText();
						actionDescription = "Verify Dropdown-->Actual:"+options+EXPECTED+allOptions.get(i).getText();
						break;
					}
				}

				if(valuefound.equals(true))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}
			}
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}

	public void verifytextentered(WebDriver drivernew, WebElement element, String text, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException
	{
		String action = "Verify Dropdown Selection-->"+text;
		String actionDescription = "Verify Dropdown Selection"+text;
		LocalDateTime startTime = LocalDateTime.now();
		Boolean verified = false;

		try
		{
			if(!(text.equals("N//A")||text==null))
			{

				Webkeywords.instance().fluentWait(drivernew, element);
				String enteredText = element.getText();

				if(enteredText.equals(text))
				{
					verified=true;
				}


				if(verified.equals(true))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSPASS);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
				}
			}



		}
		catch (Exception e)
		{
			logger.error(FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}

	public void verifyElementDisplayed(WebDriver drivernew, WebElement element,String testdata, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String actionv1 = VRIFYELEMENT+element.getAttribute(VALUE);
		String actionDescription =VRIFYELEMENT+element.getAttribute(VALUE);
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testdata.equalsIgnoreCase("n/a"))||!(testdata.equalsIgnoreCase("n\\a"))||!(testdata.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{

			try
			{
				scrollIntoViewElement(drivernew, element);
				Actions action = new Actions(drivernew);
				action.moveToElement(element).perform();
				if(element.isDisplayed())
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionv1, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionv1, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT ,actionv1 , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, actionv1, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyElementDisplayedtextattribute(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String actionNew =VRIFYELEMENT+element.getText();
		String actionDescription =VRIFYELEMENT+element.getText();
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{

			try
			{
				Actions action = new Actions(drivernew);
				action.moveToElement(element).perform();
				if(element.isDisplayed())
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionNew, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionNew, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(FAILED_FORMAT , actionNew , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, actionNew, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyelementnotdisplayed(WebDriver drivernew, List<WebElement> element, String testData, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException
	{
		String action = VERIFY_ELEMENT_NOT_DISPLAYED+ testData;
		String actionDescription = VERIFY_ELEMENT_NOT_DISPLAYED+ testData;
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))|| !(testData.equalsIgnoreCase("n\\a"))|| !(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{


			try
			{

				if(!element.isEmpty())
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSPASS);
				}

			}
			catch (Exception e)
			{
				logger.error(ERROR_LOG_FORMAT, action, actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}


	public void verifyelementnotdisplayed(WebDriver drivernew, String xpath, String testData, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException
	{
		String action = VERIFY_ELEMENT_NOT_DISPLAYED+ testData;
		String actionDescription = VERIFY_ELEMENT_NOT_DISPLAYED+ testData;
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{

			try
			{

				int size=drivernew.findElements(By.xpath(xpath)).size();
				if(size>0)
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSPASS);
				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT , action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}




	public void verifyElementEnabled(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String actionnew = "Verify Element Enabled";
		String actionDescription = "Verify Element Enabled";
		LocalDateTime startTime = LocalDateTime.now();

		if (!testData.equalsIgnoreCase("n/a") || !testData.equalsIgnoreCase("n\\a") || !testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE))

		{

			try
			{
				Actions action=new Actions(drivernew);
				action.moveToElement(element);

				if(element.isEnabled())
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionnew, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionnew, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(ERROR_LOG_FORMAT, actionnew , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, actionnew, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyElementSelected(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Verify Element Selected";
		String actionDescription = "Verify Element Selected";
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{


			try
			{

				if(element.isSelected())
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT ,action ,actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyElementDisabled(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Verify Element Disabled";
		String actionDescription = "Verify Element Disabled";
		LocalDateTime startTime = LocalDateTime.now();
		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{


			try
			{


				if(!element.getAttribute("disabled").equals("true"))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT , action ,actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyCheckBoxChecked(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Verify CheckBox Checked";
		String actionDescription = "Verify CheckBox Checked";
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{

			try
			{

				if(element.getAttribute("checked").equals("true"))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT , action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyElementTitle(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails)throws IOException
	{
		String action = VERIFY_ELEMENT_TITLE;
		String actionDescription = VERIFY_ELEMENT_TITLE;
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{


			try
			{

				if(element.getAttribute(TITLE_ATTRIBUTE).equals(testData))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT ,action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyTextBoxValue(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails)throws IOException
	{
		String action = "Verify TextBox Value";
		String actionDescription = VERIFY_ELEMENT_TITLE;
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{

			try
			{
				Webkeywords.instance().fluentWait(drivernew, element);
				if(element.getAttribute(VALUE).equals(testData))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, "Pass");
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}

			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT , action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public boolean verifyTextDisplayed(WebDriver drivernew, WebElement element,String testdata, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "";
		String actionDescription = "";
		LocalDateTime startTime = LocalDateTime.now();

		boolean isresult=false;

		if(!(testdata.equalsIgnoreCase("n/a"))||!(testdata.equalsIgnoreCase("n\\a"))||!(testdata.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{
			try
			{
				if(!(testdata.equals("N//A")||testdata==null))
				{

					scrollIntoViewElement(drivernew, element);
					zoomWebPage(drivernew,"50%",testCaseParam,pageDetails);
					action=ACTUAL_TEXT_DISPLAYED +element.getText()+CONTAINS_EXPECTED_TEXT+testdata;
					actionDescription = ACTUAL_TEXT_DISPLAYED +element.getText()+CONTAINS_EXPECTED_TEXT+testdata;
					Webkeywords.instance().fluentWait(drivernew, element);
					if(testdata.contains("\""))
					{
						testdata=testdata.replace("\"", "");
					}
					if(element.getText().toLowerCase().equalsIgnoreCase(testdata.toLowerCase()))
					{
						testStepDetails.logVerificationDetails(drivernew, testCaseParam, action,  startTime, STATUSDONE, element.getText(), testdata);
						isresult=true;
					}
					else
					{
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

						isresult=false;
					}
				}
				zoomWebPage(drivernew,"100%",testCaseParam,pageDetails);
			}
			catch (Exception e)
			{
				logger.error(FAILED_FORMAT ,action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
		return isresult;
	}



	public boolean verifyText(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "";
		String actionDescription = "";
		LocalDateTime startTime = LocalDateTime.now();


		try
		{

			if(!(testData.equals("N//A")||testData==null))
			{

				scrollIntoViewElement(drivernew, element);
				zoomWebPage(drivernew,"50%",testCaseParam,pageDetails);
				action=ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+testData;
				actionDescription = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+testData;
				Webkeywords.instance().fluentWait(drivernew, element);
				if(testData.contains("\""))
				{
					testData=testData.replace("\"", "");
				}
				if(element.getText().toLowerCase().equalsIgnoreCase(testData.toLowerCase()))
				{
					testStepDetails.logVerificationDetails(drivernew, testCaseParam, action,  startTime, STATUSDONE, element.getText(), testData);

					return true;
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
					return false;
				}
			}
			zoomWebPage(drivernew,"100%",testCaseParam,pageDetails);
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
		return false;
	}



	public void verifyTextDisplayedValueAttribute(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+testData;
		String actionDescription = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+testData;
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{


			try
			{

				if(!(testData.equals("N//A")||testData==null))
				{

					Webkeywords.instance().fluentWait(drivernew, element);
					if(testData.contains("\""))
					{
						testData=testData.replace("\"", "");
					}
					if(element.getAttribute(VALUE).toLowerCase().equalsIgnoreCase(testData))
					{
						testStepDetails.logVerificationDetails(drivernew, testCaseParam, action,  startTime, STATUSDONE, element.getText(), testData);
					}
					else
					{
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

					}
				}
			}
			catch (Exception e)
			{
				logger.error(FAILED_FORMAT ,action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}

	public void verifyTextDisplayedTitleAttribute(WebDriver drivernew, WebElement element,String testData, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+testData;
		String actionDescription = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+testData;
		LocalDateTime startTime = LocalDateTime.now();

		if(!(testData.equalsIgnoreCase("n/a"))||!(testData.equalsIgnoreCase("n\\a"))||!(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE)))
		{

			try
			{


				if(!(testData.equals("N//A")||testData ==null))
				{

					Webkeywords.instance().fluentWait(drivernew, element);
					if(testData.contains("\""))
					{
						testData=testData.replace("\"", "");
					}
					if(element.getAttribute(TITLE_ATTRIBUTE).toLowerCase().equalsIgnoreCase(testData))
					{
						testStepDetails.logVerificationDetails(drivernew, testCaseParam, action,  startTime, STATUSDONE, element.getText(), testData);

					}
					else
					{
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

					}
				}
			}
			catch (Exception e)
			{
				logger.error(LOG_FAILED_FORMAT , action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;
			}
		}
	}



	public void verifyTextDisplayedNew(WebDriver drivernew, WebElement element,List<String> text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+text;
		String actionDescription = ACTUAL_TEXT_DISPLAYED+element.getText()+CONTAINS_EXPECTED_TEXT+text;
		LocalDateTime startTime = LocalDateTime.now();


		try
		{
			if(!(text.contains("N//A")||text==null))

			{
				Webkeywords.instance().fluentWait(drivernew, element);
				if(element.getText().contains(text.get(0)))
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}
			}
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}

	public boolean verifyTableData(WebDriver drivernew, List<WebElement> element,String text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = TABLECOLUMNMSG;
		String actionDescription = TABLECOLUMNMSG;
		LocalDateTime startTime = LocalDateTime.now();
		boolean verifydata=false;

		try
		{
			if(!(text.equals("N//A")||text==null))
			{


				List<WebElement> elementnew;
				elementnew=element;
				ArrayList<String> dataList = new ArrayList<>();

				String[] dataCount = new String[element.size()];
				int l = 0;
				for (WebElement data : elementnew)
				{
					action = ACTUAL_TEXT_DISPLAYED+data.getText()+"Expected:-"+text;
					actionDescription = ACTUAL_TEXT_DISPLAYED+data.getText()+"Expected:-"+text;
					dataCount[l++] = data.getText();
					dataList.add(data.getText());

					if(data.getText().contains(text))
					{
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action,actionDescription,pageDetails, startTime, STATUSPASS);
						verifydata= true;
					}
					else
					{
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action,actionDescription,pageDetails, startTime, STATUSFAIL);
						verifydata= false;
					}

				}
			}


			return 	verifydata;


		}
		catch (Exception e)
		{
			logger.error(ERROR_LOG_FORMAT, action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			return false;
		}
	}

	public void verifyTableDataifNull(WebDriver drivernew, List<WebElement> element, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = TABLECOLUMNMSG;
		String actionDescription = TABLECOLUMNMSG;
		LocalDateTime startTime = LocalDateTime.now();


		try
		{

			ArrayList<String> dataList = new ArrayList<>();

			String[] dataCount = new String[element.size()];
			int l = 0;
			for (WebElement data : element)
			{
				action = TABLECOLUMNMSG+data.getText();
				actionDescription = TABLECOLUMNMSG+data.getText();
				dataCount[l++] = data.getText();
				dataList.add(data.getText());
				if(data.getText()!=null)
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}
			}
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;
		}
	}

	public void verifyPartialTextDisplayed(WebDriver drivernew, WebElement element,String text, TestCaseParam testCaseParam,PageDetails pageDetails) throws IOException
	{
		String action = "Verify Element Partial Text";
		String actionDescription = "Verify Element Partial Text";
		LocalDateTime startTime = LocalDateTime.now();


		try
		{

			if(element.getText().toLowerCase().contains(text.toLowerCase()))
			{
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			}
			else
			{
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
			}

		}
		catch (Exception e)
		{
			logger.error(FAILED_FORMAT , action ,actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
			throw e;

		}
	}




	public void verifyValueEntered(WebDriver drivernew, WebElement element,String text, TestCaseParam testCaseParam,PageDetails pageDetails)
	{
		String action = VERIFY_ELEMENT_TEXT;
		String actionDescription = VERIFY_ELEMENT_TEXT;
		LocalDateTime startTime = LocalDateTime.now();


		try
		{
			if(!(text.equalsIgnoreCase("N//A")||text.equalsIgnoreCase("N/A")))
			{


				String actualText=element.getAttribute(VALUE);
				if(text.contains("\""))
				{
					text=text.replace("\"", "");
				}
				if(actualText.equals("___-__-____"))
				{
					actualText=actualText.replace("___-__-____", "");
				}
				if(actualText.contains("_-"))
				{
					actualText=actualText.replace("_", "");
					actualText=actualText.replace("-", "");
				}


				action = "Actual Value Displayed" + EXPECTED_VALUE_DELIMITER + actualText + EXPECTED_VALUE_DELIMITER + text;

				actionDescription = "Actual Value Displayed" + EXPECTED_VALUE_DELIMITER + actualText + EXPECTED_VALUE_DELIMITER + text;

				if(actualText.equalsIgnoreCase(text))
				{

					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
				}
			}
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action , actionDescription);


		}
	}

	public void verifyValueSelected(WebDriver drivernew, WebElement element,String options, TestCaseParam testCaseParam,PageDetails pageDetails)
	{
		String action = VERIFY_ELEMENT_TEXT;
		String actionDescription = VERIFY_ELEMENT_TEXT;
		LocalDateTime startTime = LocalDateTime.now();


		try
		{
			if(!isOptionsTrue(options))
			{
				Select select = new Select(element);
				element=select.getFirstSelectedOption();

				String actualText=element.getText();


				action = ACTUAL_VALUE_DISPLAYED+actualText+EXPECTED_VALUE_DELIMITER+options;
				actionDescription = ACTUAL_VALUE_DISPLAYED+actualText+"<-->Expected Value-->"+options;

				if(actualText.equals(options))
				{

					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
				}
			}

		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT, action, actionDescription);

		}
	}




	public String isReadyEmpty(String isReadOnly) {
		if(isReadOnly==null)
		{
			isReadOnly="";
		}
		return isReadOnly;
	}

	private boolean isDisableBlank(String isDisabled) {
		return isDisabled.equals("")||isDisabled==null;
	}

	public boolean isDisableNull(String isDisabled, String isReadOnly) {
		return isReadOnly.equals("true")&&isDisableBlank(isDisabled);
	}

	public boolean isReadblnkisDisabledtrue(String isDisabled, String isReadOnly) {
		return isReadOnly.equals("") && isDisabled.equals("true");
	}

	public boolean isReadDisableTrue(String isDisabled, String isReadOnly) {
		return isDisabled.equals("true") && isReadOnly.equals("true");
	}

	public void verifypropertyofelement(WebDriver drivernew, WebElement element, String dataValue, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException
	{
		String action = "Verify Property of Element-->"+element.getAttribute(VALUE);
		String actionDescription = "Verify Property of Element-->"+element.getAttribute(VALUE);
		LocalDateTime startTime = LocalDateTime.now();
		if(pageDetails != null)
		{


			try
			{
				Boolean displayed = true;
				displayed = element.isDisplayed();
				if (displayed.equals(true))
				{
					String prop = "";
					String[] data = null;

					data = dataValue.split("&");
					prop=element.getAttribute(data[0]) ;

					if (prop.equals(data[1]))
					{
						action = "The " + data[0] + " Property of the element is as expected";
						actionDescription = "The " + data[0] + " Property of the element is as expected";
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

					}
					else
					{
						action = "The " + data[0] + " Property of the element is not as expected";
						actionDescription = "The " + data[0] + " Property of the element is not as expected";
						testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

					}
				}
			}
			catch (Exception e)
			{
				logger.error(FAILED_FORMAT , action , actionDescription);
				testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);
				throw e;

			}
		}
	}

	public void verifyCheckBoxIsNotChecked(WebDriver drivernew,WebElement element,TestCaseParam testCaseParam, PageDetails pageDetails ) throws IOException
	{
		String action="";
		String actionDescription="";
		LocalDateTime startTime = LocalDateTime.now();
		try
		{

			if (!element.isSelected())
			{

				action = "The element is Not checked";
				actionDescription = "The element is Not checked";
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);

			}
			else
			{
				action = "The element is  checked";
				actionDescription = "The element is  checked";
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

			}
		}
		catch (Exception e)
		{

			logger.error(ERROR_LOG_FORMAT, action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);

		}

	}



	public void enterTextByFunctionKeys(WebDriver drivernew,WebElement element,Keys key, TestCaseParam testCaseParam, PageDetails pageDetails) throws IOException
	{


		String action="";
		String actionDescription="";
		LocalDateTime startTime = LocalDateTime.now();


		try
		{
			Webkeywords.instance().fluentWait(drivernew, element);
			WebDriverWait wait = new WebDriverWait(drivernew, 1000);
			wait.until(ExpectedConditions.elementToBeClickable(element));

			element.sendKeys(key);

			logger.info(SUCCESSFULLY_ENTERED_TEXT ," {} to {}",key ,element);
		}
		catch (Exception e)
		{
			action = "Failed to Dismiss Alert Message";
			actionDescription = "Failed to Dismiss Alert Message";
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

		}


	}

	public void zoomWebPage(WebDriver drivernew,String zoomValue, TestCaseParam testCaseParam, PageDetails pageDetails)
	{


		String action="";
		String actionDescription="";
		LocalDateTime startTime = LocalDateTime.now();



		try
		{

			JavascriptExecutor executor = (JavascriptExecutor)drivernew;
			executor.executeScript("document.body.style.zoom = '"+zoomValue+"'");
			action = "Sucessfully Zoomed the page to -->"+zoomValue;
			actionDescription = "Sucessfully Zoomed the page to -->"+zoomValue;
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);


		}
		catch (Exception e)
		{
			action = "Failed to Zoom the page";
			actionDescription = "Failed to Zoom the page";
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);

		}


	}

	public void mouseHover(WebDriver drivernew,WebElement element, TestCaseParam testCaseParam, PageDetails pageDetails)
	{


		String actionnew="Mouse Hover";
		String actionDescription="Mouse Hover";
		LocalDateTime startTime = LocalDateTime.now();



		try
		{
			Webkeywords.instance().fluentWait(drivernew, element);
			Actions action = new Actions(drivernew);

			action.moveToElement(element).build().perform();
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, actionnew, actionDescription,pageDetails, startTime, STATUSDONE);


		}
		catch (Exception e)
		{
			actionnew = "Failed do mouse hover";
			actionDescription = "Failed do mouse hover";
			logger.error(LOG_FAILED_FORMAT , actionnew , actionDescription);
		}


	}



	public void countZeroValidation(WebDriver drivernew, TestCaseParam testCaseParam, PageDetails pageDetails,
									 String action, String actionDescription, LocalDateTime startTime, int count) throws IOException
	{
		if(count==0)
		{
			testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

		}
	}

	private boolean isOptionsTrue(String options) {
		return options.equals("N//A") || options.equals("N/A") || options.equals("n//a") || options.equals("n/a")||options==null;
	}

	public void scrollUpPageToTheTop(WebDriver driver)
	{

		String action = "Scroll Up Page To The Top";
		String actionDescription = "Scroll Up Page To The Top";



		try
		{

			((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");


		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT,action,actionDescription);

		}



	}

	public void scrollIntoViewElement(WebDriver driver, WebElement element)
	{

		Webkeywords.instance().fluentWait(driver, element);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);",element);

	}



	public void verifydropdownoptionnotavailable(WebDriver drivernew, WebElement element, String options, TestCaseParam testCaseParam, PageDetails pageDetails)throws IOException
	{
		String action = "Verifying Dropdown Options -->"+element.getText()+" : does not contain :-"+options;
		String actionDescription = "Verifying Dropdown Options -->"+element.getText()+" : does not contain :-"+options;
		LocalDateTime startTime = LocalDateTime.now();
		Boolean valuefound = true;

		try
		{
			if (!("N//A".equals(options) || options == null))
			{


				Webkeywords.instance().fluentWait(drivernew, element);
				Select select = new Select(element);
				List<WebElement> allOptions = select.getOptions();
				for(int i=0; i<allOptions.size(); i++)
				{

					if(allOptions.get(i).getText().contains(options))
					{
						valuefound=false;
						action = "Verify Dropdown-->contains "+allOptions.get(i).getText()+"which is not as Expected";
						actionDescription = "Verify Dropdown-->contains "+allOptions.get(i).getText()+"which is not as Expected";

						break;
					}
				}
				if(valuefound.equals(true))
				{
					testStepDetails.logVerificationDetails(drivernew, testCaseParam, action,  startTime, STATUSDONE, element.getText(), options);
				}
				else
				{
					testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);

				}
			}
		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT , action , actionDescription);
			testStepDetails.logExceptionDetails(drivernew, testCaseParam, action, actionDescription, startTime,e);

		}
	}





	public void verifyDropdownValueSelected(WebDriver drivernew, WebElement element,String options, TestCaseParam testCaseParam,PageDetails pageDetails)
	{
		String action = VERIFY_ELEMENT_TEXT;
		String actionDescription = VERIFY_ELEMENT_TEXT;
		LocalDateTime startTime = LocalDateTime.now();


		try
		{
			String actualText=element.getAttribute(TITLE_ATTRIBUTE);


			action = ACTUAL_VALUE_DISPLAYED+actualText+EXPECTED_VALUE_DELIMITER+options;
			actionDescription = ACTUAL_VALUE_DISPLAYED+actualText+"<-->Expected Value-->"+options;

			if(actualText.equals(options))
			{

				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSDONE);
			}
			else
			{
				testStepDetails.logTestStepDetails(drivernew, testCaseParam, action, actionDescription,pageDetails, startTime, STATUSFAIL);
			}



		}
		catch (Exception e)
		{
			logger.error(LOG_FAILED_FORMAT ,action,actionDescription);


		}
	}






}