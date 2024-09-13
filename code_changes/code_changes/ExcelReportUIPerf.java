package reportutilities.uiperfreport;

import testsettings.TestRunSettings;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reportutilities.excelreport.ExcelReportCommon;

import java.io.IOException;
import java.util.*;

public class ExcelReportUIPerf 
{
	static ArrayList<String> header = new ArrayList<>();
	static ArrayList<Integer> headerSize = new ArrayList<>();
	static ExcelReportCommon excelreport = new ExcelReportCommon();
	private static final Logger logger =LoggerFactory.getLogger(ExcelReportUIPerf.class.getName());

	public void initializeColumnHeaders()
	{
		header.add("S.No");
		headerSize.add(10*250);
		header.add("SourcePage");
		headerSize.add(20*500);
		header.add("DestinationPage");
		headerSize.add(20*500);
		header.add("AgerageNavigationTime");
		headerSize.add(20*250);
		header.add("PageLoadTime");
		headerSize.add(20*250);
		header.add("DOMLoadTime");
		headerSize.add(20*250);
		
		
	}
	
	
	
	 public void writeDataToExcel(String filePath) throws IOException {
		 Map<HashMap<String, String>, ArrayList<String>> dataMap=new  LinkedHashMap<>();  
		 dataMap=TestRunSettings.getUiperfdata();
		 List<String> pageLoadtime=new LinkedList<>();
		 pageLoadtime=TestRunSettings.getPageLoadtime();
		 List<String> domLoadtime=new LinkedList<>();
		 domLoadtime=TestRunSettings.getDomLoadtime();
		 try (Workbook workbook = new XSSFWorkbook()) 
	        {
	            Sheet sheet = workbook.createSheet("Perofrmance");
	            	            

	            
	            Row headerrow = sheet.createRow(0);
	            
	            Cell serialNumberCell = headerrow.createCell(0);
	            serialNumberCell.setCellValue("S.No");
	            serialNumberCell.setCellStyle(excelreport.getHeaderCellStyle(workbook));
	            
	            Cell spheaderCell = headerrow.createCell(1);
	            spheaderCell.setCellValue("SourcePage");
	            spheaderCell.setCellStyle(excelreport.getHeaderCellStyle(workbook));
	            
	            Cell dpheaderCell = headerrow.createCell(2);
	            dpheaderCell.setCellValue("DestinationPage");
	            dpheaderCell.setCellStyle(excelreport.getHeaderCellStyle(workbook));
	            
	            Cell timeheaderCell = headerrow.createCell(3);
	            timeheaderCell.setCellValue("AgerageNavigationTime");
	            timeheaderCell.setCellStyle(excelreport.getHeaderCellStyle(workbook));
	            
	            Cell pageloadCell = headerrow.createCell(4);	            
	            pageloadCell.setCellValue("PageLoadTime");
	            pageloadCell.setCellStyle(excelreport.getHeaderCellStyle(workbook));
	            
	            Cell domloadCell = headerrow.createCell(5);
	            domloadCell.setCellValue("DOMLoadTime");
	            domloadCell.setCellStyle(excelreport.getHeaderCellStyle(workbook));
	            
	            Cell screenCounter = headerrow.createCell(6);
	            screenCounter.setCellValue("ScreenOccurances");
	            screenCounter.setCellStyle(excelreport.getHeaderCellStyle(workbook));

	            int rowNum = 1;
	            int sNo=1;
	            int index=0;
	            for (Map.Entry<HashMap<String, String>, ArrayList<String>> entry : dataMap.entrySet()) {
	                Row row = sheet.createRow(rowNum++);

             
	                int arraysize=entry.getValue().size();
	                double avgtime = 0;
	                if(arraysize>0) 
	                {
	             	   avgtime=calculateAverage(entry.getValue());
	                }

	                int cellNum = 0;
        
	                Cell serialCell = row.createCell(cellNum++);
	                serialCell.setCellValue(String.valueOf(sNo++));
	                serialCell.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));
	                for (Map.Entry<String, String> keyEntry : entry.getKey().entrySet())
	                {
	                	Cell keyCell1 = row.createCell(cellNum++);
	                    keyCell1.setCellValue(keyEntry.getKey());
	                    keyCell1.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));
	                    Cell keyCell2 = row.createCell(cellNum++);
	                    keyCell2.setCellValue(keyEntry.getValue());
	                    keyCell2.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));
	                }

	                Cell valuesCell = row.createCell(cellNum++);
	                valuesCell.setCellValue(avgtime);
	                valuesCell.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));
	                
	                String pageloadtime=pageLoadtime.get(index);
	                
	                Cell pltCell = row.createCell(cellNum++);
	                pltCell.setCellValue(pageloadtime);
	                pltCell.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));

	                
	                String domloadtime=domLoadtime.get(index);
	                
	                Cell dltCell = row.createCell(cellNum++);
	                dltCell.setCellValue(domloadtime);
	                dltCell.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));
	                
	                int screenocc=arraysize;
	                
	                Cell scrOcc = row.createCell(cellNum);
	                scrOcc.setCellValue(String.valueOf(screenocc));
	                scrOcc.setCellStyle(excelreport.getGenericCellStyleMiddle(workbook));
	                index++;
	            }
	           excelreport.closeWorkBook(workbook, filePath);

	        }
		 catch (IOException e) 
		 {
			 logger.info(Arrays.toString(e.getStackTrace()));
	        }
	    }
//*[@class='ssp-menuItemDropDownHeader']/p
	 
	 public static double calculateAverage(List<String> numericValues) {
	        if (numericValues == null || numericValues.isEmpty()) {
	            throw new IllegalArgumentException("List is empty or null");
	        }

	        double sum = 0.0;

	        for (String stringValue : numericValues) {
	            try {
	                // Attempt to convert each string value to a double
	                double numericValue = Double.parseDouble(stringValue);
	                sum += numericValue;
	            } catch (NumberFormatException e) {
	                // Handle cases where the string cannot be converted to a double
	            	logger.info(String.format("Skipping non-numeric value: %s", stringValue));
	            }
	        }

	        // Calculate the average
	        return sum / numericValues.size();
	    }
	 
}