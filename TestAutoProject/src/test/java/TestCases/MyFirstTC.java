package TestCases;


import java.io.IOException;

import org.junit.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import Utility.WebDriverHelpers;

public class MyFirstTC {

	@BeforeClass
	public static void startTest() {

	}


	@Test
	public static void Registration() throws IOException {  
		try {

			WebDriverHelpers.SelectDriver();

			//code to be added here

			WebDriverHelpers.quitDriver();


		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@AfterClass
	public static void endTest(){

	}

}
		