package pdfvalidation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import testsettings.TestRunSettings;
import constants.PrjConstants;
import reportutilities.common.ReportCommon;
import reportutilities.extentmodel.PageDetails;
import reportutilities.model.TestCaseParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commonutilities.utilities.Util;

public class WritePDFtoTxtUtility {

	private static final Logger logger = LoggerFactory.getLogger(WritePDFtoTxtUtility.class.getName());
	ReportCommon exceptionDetails = new ReportCommon();


	public WritePDFtoTxtUtility() {
		// Load properties and initialize TestRunSettings if needed
		try {
			Util util = new Util();
			Properties prop = new Properties();
			Path currentRelativePath = Paths.get("");
			String prjPath = currentRelativePath.toAbsolutePath().toString();
			prop = util.loadProperties(prjPath + PrjConstants.CONFIGFILE);
			TestRunSettings.setArtifactsPath(prop.getProperty("ArtifactsPath"));
		} catch (Exception e) {
			logger.info(Arrays.toString(e.getStackTrace()));
		}
	}

	public void writePdfToTextFile(TestCaseParam testCaseParam, PageDetails pageDetails,String pdfFileName, String sourceTextFile, String sourceTextFileLocation) throws IOException {

		String pdfSourceFile = pdfFileName;
		LocalDateTime startTime= LocalDateTime.now();

		try {
			new StringBuilder();
			File file1 = new File(pdfSourceFile);
			PDDocument document = PDDocument.load(file1);
			int totalPageCount = getPageCount(document);

			for (int i = 1; i <= totalPageCount; i++) {
				String pageContent = readPdfContent(document, i, totalPageCount - (totalPageCount - i));
				File directory = new File(sourceTextFileLocation);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				try(BufferedWriter out = new BufferedWriter(new FileWriter(sourceTextFile + i + ".txt", StandardCharsets.UTF_8)))
				{
					out.write(pageContent);
					
				}
				

				logger.info("Source file Page saved as TXT {} ", i);

			}

			document.close();
		} catch (Exception e) {
			logger.info(Arrays.toString(e.getStackTrace()));
			logger.error("Unable to read source file:{} ", pdfFileName);
			exceptionDetails.logExceptionDetails(TestRunSettings.getDriver(), testCaseParam, pageDetails.getPageActionName(), pageDetails.getPageActionDescription(), startTime, e);
		}
	}

	private int getPageCount(PDDocument doc) {
		return doc.getNumberOfPages();
	}

	private String readPdfContent(PDDocument document, int firstPage, int lastPage) throws IOException {
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setStartPage(firstPage);
		pdfStripper.setEndPage(lastPage);
		return pdfStripper.getText(document);
	}
}