package TestCases;
import org.testng.annotations.Test;

import Utility.ExcelHelper;

public class Sample {
		
	String testDataPath = "C:\\Users\\njaikumar\\Desktop\\Testing.xlsx";

	@Test
	public void Test() throws Exception {
		
		ExcelHelper.getTestData(testDataPath, "CCW");
		ExcelHelper.getTestDataCellValue("Sc1", "Test1");
	}
}
