package reportutilities.excelreport;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;
import java.util.Set;

/**
 * Demonstrates how to create hyperlinks.
 */
public class HyperlinkExample {
	
	static final  Logger logger =LoggerFactory.getLogger(HyperlinkExample.class.getName()); 


    public static void main(String[]args) throws IOException {
 
       try( Workbook wb = new XSSFWorkbook())
       { 
        CreationHelper createHelper = wb.getCreationHelper();

        //cell style for hyperlinks
        //by default hyperlinks are blue and underlined
        CellStyle hLinkStyle = wb.createCellStyle();
        Font hLinkFfont = wb.createFont();
        hLinkFfont.setUnderline(Font.U_SINGLE);
        hLinkFfont.setColor(IndexedColors.BLUE.getIndex());
        hLinkStyle.setFont(hLinkFfont);

        Cell cell;
        Sheet sheet = wb.createSheet("Hyperlinks");
        //URL
        cell = sheet.createRow(0).createCell(0);
        cell.setCellValue("URL Link");

        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress("http://poi.apache.org/");
        cell.setHyperlink(link);
        cell.setCellStyle(hLinkStyle);

        //link to a file in the current directory
        cell = sheet.createRow(1).createCell(0);
        cell.setCellValue("File Link");
        link = createHelper.createHyperlink(HyperlinkType.FILE);
        link.setAddress("C:/Test/TestReport1.jpeg");
        cell.setHyperlink(link);
        cell.setCellStyle(hLinkStyle);

        //e-mail link
        cell = sheet.createRow(2).createCell(0);
        cell.setCellValue("Email Link");
        link = createHelper.createHyperlink(HyperlinkType.EMAIL);
        //note, if subject contains white spaces, make sure they are url-encoded
        link.setAddress("mailto:poi@apache.org?subject=Hyperlinks");
        cell.setHyperlink(link);
        cell.setCellStyle(hLinkStyle);

        //link to a place in this workbook

        //create a target sheet and cell
        Sheet sheet2 = wb.createSheet("Target Sheet");
        sheet2.createRow(0).createCell(0).setCellValue("Target Cell");

        cell = sheet.createRow(3).createCell(0);
        cell.setCellValue("Worksheet Link");
        Hyperlink link2 = createHelper.createHyperlink(HyperlinkType.DOCUMENT);
        link2.setAddress("'Target Sheet'!A1");
        cell.setHyperlink(link2);
        cell.setCellStyle(hLinkStyle);
        
        
         Path path = Paths.get("c:\\Test\\hyperinks.xlsx");
         Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
         Files.setPosixFilePermissions(path, perms);


        FileOutputStream out = new FileOutputStream("c:\\Test\\hyperinks.xlsx");
        wb.write(out);
        out.close();
        

    	}
    	catch(Exception e) 
    	{
    		logger.info(Arrays.toString(e.getStackTrace()));
    	}
    }
}