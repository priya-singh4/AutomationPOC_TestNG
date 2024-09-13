package commonutilities.utilities;

import com.github.javafaker.Faker;

import commonutilities.common.PoiReadExcel;
import commonutilities.common.RetreiveProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

        private static final String TEST_SCRIPT_PREFIX = "TestScript::";
        private static final String TEST_DATA_PREFIX ="TestData";
    private static final String TEST_ITERATION_PREFIX ="Iteration";

    private static final String TEST_TS_PREFIX ="TestScript";
    public static final Random r = new Random();


        private static final Logger logger = LoggerFactory.getLogger(Util.class);

        public enum Mode {
            ALPHA, ALPHANUMERIC, NUMERIC
        }

        public static String getRootPath() {
            return Paths.get("").toAbsolutePath().toString();
        }

        public Util() {
            // Default constructor
        }


        public Map<String, ArrayList<String>> getDictionaryFromPOI(String path, String sheetName, String clause) {

                ArrayList<String> whereClause = new ArrayList<>();
                whereClause.add(clause);
                return PoiReadExcel.fetchWithCondition(path, sheetName, whereClause);

        }

        public Map<String, String> getTestData(String tdPath, String testDataSheet, String testCase, Integer currentIteration) {

                List<String> testDataSheets = Arrays.asList(testDataSheet.split(";"));
                Map<String, String> testData = new HashMap<>();

                for (String td : testDataSheets) {
                    String testDataPath = String.format("%s%s.csv", tdPath, td);

                    ArrayList<String> whereClause = new ArrayList<>();
                    whereClause.add(TEST_SCRIPT_PREFIX + testCase);
                    whereClause.add(TEST_ITERATION_PREFIX + currentIteration);
                    Map<String, ArrayList<String>> result = PoiReadExcel.fetchWithCondition(testDataPath, TEST_DATA_PREFIX, whereClause);

                    for (Map.Entry<String, ArrayList<String>> entry : result.entrySet()) {
                        testData.put(entry.getKey(), entry.getValue().get(0));
                    }
                }
                return testData;

        }

        public static String getCurrentDate() {
                LocalDateTime today = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                String date = today.format(format);
                return date.replace(":", "_").replace(" ", "_").replace(".", "_").replace("-", "_");

        }

        public static String getCurrentTime() {

                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.sss");
                String result = dateFormat.format(Calendar.getInstance().getTime());
                return result.replace(":", "_").replace(" ", "_").replace(".", "_");

        }






        public Map<String, String> getORElements(String orPath, String orFileNames) {
            Map<String, String> objRep = new HashMap<>();
            List<String> orFiles = Arrays.asList(orFileNames.split(";"));

            for (String orFile : orFiles) {
                try {
                    String propFile = String.format("%s%s.txt", orPath, orFile);
                    RetreiveProperties rp = new RetreiveProperties();
                    Map<String, String> tempObjRep = rp.getProperties(propFile);

                    for (Map.Entry<String, String> entry : tempObjRep.entrySet()) {
                        objRep.put(entry.getKey(), entry.getValue());
                    }
                } catch (Exception e) {
                    logger.error("Error occurred while getting OR elements: {}", e.getMessage(), e);
                }
            }
            return objRep;
        }

        public String getObjectFromObjectMap(String key, String scenario, String homePath) {
            try {
                String propFile = String.format("%s/test/resources/ObjectRepository/%s.txt", homePath, scenario);
                RetreiveProperties rp = new RetreiveProperties();
                Map<String, String> objRep = rp.getProperties(propFile);
                return objRep.getOrDefault(key, null);
            } catch (Exception e) {
                logger.error("Error occurred while getting object from object map: {}", e.getMessage(), e);
                return null;
            }
        }


        public Map<String, String> getObjectFromCommonRep(String homePath) {
            Map<String, String> objRep = new HashMap<>();
            try {
                String propFile = homePath + "/test/resources/ObjectRepository/Common.txt";
                RetreiveProperties rp = new RetreiveProperties();
                objRep = rp.getProperties(propFile);
            } catch (FileNotFoundException e) {
                logger.error("File not found: {}", e.getMessage());
            } catch (IOException e) {
                logger.error("IO Exception: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Exception: {}", e.getMessage(), e);
            }
            return objRep;
        }

        public String getDataValueForAppiumTC(String colName, String testCase, String dataPath, Integer currentIteration) {
            String dataValue = "";

                ArrayList<String> whereClause = new ArrayList<>();
                whereClause.add(TEST_SCRIPT_PREFIX + testCase);
                whereClause.add(TEST_ITERATION_PREFIX + currentIteration);
                Map<String, ArrayList<String>> result = PoiReadExcel.fetchWithCondition(dataPath, TEST_DATA_PREFIX, whereClause);

                for (int i = 0; i < result.get(TEST_TS_PREFIX).size(); i++) {
                    if (testCase.equals(result.get(TEST_TS_PREFIX).get(i)) && currentIteration.equals(Integer.parseInt(result.get(TEST_ITERATION_PREFIX).get(i)))) {
                        dataValue = result.get(colName).get(i);
                        break;
                    }
                }

            return dataValue;
        }

        public String generateRandomString(int length, Mode mode) {
            StringBuilder buffer = new StringBuilder();
            String characters;
            switch (mode) {
                case ALPHA : 
                	characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                	break;
                case ALPHANUMERIC :
                	characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                	break;
                case NUMERIC:
                	characters = "1234567890";
                	break;
                	default:
                		throw new IllegalArgumentException();
            }
            for (int i = 0; i < length; i++) {
                int index = r.nextInt(characters.length());
                buffer.append(characters.charAt(index));
            }
            return buffer.toString();
        }

        public String createDateFolder(String homePath) {

                File dir = new File(homePath + "/Results");
                if (!dir.exists()) dir.mkdirs();

                String date = "Run_" + Util.getCurrentDate();
                String resultsFolder = homePath + "/Results/" + date;
                dir = new File(resultsFolder);
                if (!dir.exists()) dir.mkdirs();

                return resultsFolder;

        }

        public String createDirectory(String directoryPath, String directoryName) {

                String directoryFullPath = directoryPath + File.separator + directoryName;
                File dir = new File(directoryFullPath);
                if (!dir.exists()) dir.mkdirs();

                return directoryFullPath;

        }

        public String generateFirstName() {
            return new Faker().name().firstName();
        }

        public String generateLastName() {
            return new Faker().name().lastName();
        }

        public String getRandom(String value) {
            if (value.toLowerCase().contains("string")) {
                return generateRandomString(r.nextInt(16) + 5, Mode.ALPHA);
            } else if (value.toLowerCase().contains("name")) {
                return generateFirstName();
            } else if (value.toLowerCase().contains("dob")) {
                return getDateOfBirth(value);
            } else if (value.toLowerCase().contains("ssn")) {
                return getRandomSSN();
            } else if (value.toLowerCase().contains("ssn_dash")) {
                return getRandomSSN("-");
            } else if (value.toLowerCase().contains("psuedossn")) {
                return getRandomSSN();
            } else if (value.toLowerCase().contains("psuedossn_dash")) {
                return getRandomSSN("-");
            } else if (value.toLowerCase().contains("individualid")) {
                return generateRandomString(9, Mode.NUMERIC);
            } else if (value.toLowerCase().contains("number")) {
                int length = value.split(";").length > 1 ? Integer.parseInt(value.split(";")[1]) : 10;
                return generateRandomString(length, Mode.NUMERIC);
            } else if (value.toLowerCase().contains("alphanum")) {
                return generateRandomString(30, Mode.ALPHANUMERIC);
            } else if (value.toLowerCase().contains("uuid") || value.toLowerCase().contains("guid")) {
                return UUID.randomUUID().toString();
            }
            return value;
        }

        public String getDateOfBirth(String dataValue, String... dateTimeFormat) {
            String dateFormat = dateTimeFormat.length == 0 ? "MM/dd/yyyy" : dateTimeFormat[0];

                String[] randomDob = dataValue.split(";");
                int days ; 
                switch (randomDob.length) {
                case 2 : 
                	days= (Integer.parseInt(randomDob[1]) * 365) + (Integer.parseInt(randomDob[1]) / 4);
                	break;
                case 3 : 
                	days = (Integer.parseInt(randomDob[1]) * 365) + (Integer.parseInt(randomDob[2]) * 30) + (Integer.parseInt(randomDob[1]) / 4);
                	break;
                case 4 :
                	days = (Integer.parseInt(randomDob[1]) * 365) + (Integer.parseInt(randomDob[2]) * 30) + (Integer.parseInt(randomDob[3]));
                	break;
                default : 
                	days = 0;
                	break;

                }
                LocalDate localDate = LocalDate.now().minusDays((long)r.nextInt(days) + 1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                return localDate.format(formatter);

        }

        public String getRandomSSN(String... delimiter) {
            if (delimiter.length == 0) {
                delimiter = new String[]{""};
            }
            Integer iThree = getRandomNumber(132, 921);
            Integer iTwo = getRandomNumber(12, 83);
            Integer iFour = getRandomNumber(1423, 9211);
            return String.join(delimiter[0], iThree.toString(), iTwo.toString(), iFour.toString());
        }

        public String getRandomPsuedoSSN(String... delimiter) {
            if (delimiter.length == 0) {
                delimiter = new String[]{""};
            }
            Integer iThree = getRandomNumber(911, 988);
            Integer iTwo = getRandomNumber(12, 83);
            Integer iFour = getRandomNumber(1423, 9211);
            return String.join(delimiter[0], iThree.toString(), iTwo.toString(), iFour.toString());
        }

        public int getRandomNumber(int min, int max) {
            return r.nextInt(max - min) + min;
        }

    public Map<String, String> getTestData(String tdPath, String testDataSheet, String testCase, Integer currentIteration, String... defaultTestDataFormat) throws InterruptedException {
        if (defaultTestDataFormat.length == 0) {
            defaultTestDataFormat = new String[]{".csv"};
        }

        ArrayList<String> testDataSheets = new ArrayList<>(List.of(testDataSheet.split(";")));
        HashMap<String, String> testData = new HashMap<>();

        for (String td : testDataSheets) {
            String testDataPath = tdPath + td + defaultTestDataFormat[0];
            ArrayList<String> whereClause = new ArrayList<>();
            whereClause.add(TEST_SCRIPT_PREFIX + testCase);
            whereClause.add("Iteration::" + currentIteration);
            Map<String, ArrayList<String>> result = PoiReadExcel.fetchWithCondition(testDataPath, TEST_DATA_PREFIX, whereClause);

            if (result.isEmpty()) {
                logger.error("Blank column in Test Data - There is no data in the column for Iteration {} of test case {}", currentIteration, testCase);
            }

            for (Map.Entry<String, ArrayList<String>> entry : result.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().get(0);
                if (value.toLowerCase().trim().startsWith("random_")) {
                    value = getRandom(value);
                    Thread.sleep(500); // Consider reducing or removing this
                    logger.info("Random Value Added in {} = {}", key, value);
                }
                testData.put(key, value);
            }
        }
        return testData;
    }

    public Properties loadProperties(String filepath) {

        Properties prop = new Properties();

        try (FileInputStream propsInput = new FileInputStream(filepath)) {
            prop.load(propsInput);
        } catch (Exception e) {
        	logger.info(Arrays.toString(e.getStackTrace()));
        }

        return prop;

    }

    public Map<String, ArrayList<String>> getScreenTCData(String screenName, String testCaseName, String testDataLocation, String testDataMappingFileName, String testDataMappingSheetName, String iteration) {

        ArrayList<String> whereClauseTestData = new ArrayList<>();
        whereClauseTestData.add("ScreenName::" + screenName);
        Map<String, ArrayList<String>> result = PoiReadExcel.fetchWithCondition(testDataMappingFileName, testDataMappingSheetName, whereClauseTestData);

        ArrayList<String> whereClauseTestData2 = new ArrayList<>();
        whereClauseTestData2.add("TestCase::" + testCaseName);
        whereClauseTestData2.add("Iteration::" + iteration);
        return PoiReadExcel.fetchWithCondition(testDataLocation + "\\" + result.get("TestDataFileName").get(0), result.get("TestDataSheetName").get(0), whereClauseTestData2);


    }


    }