package commonutilities.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RetreiveProperties
{
	
	private static final Logger logger = LoggerFactory.getLogger(RetreiveProperties.class);
    public Map<String, String> getProperties(String path) throws IOException{
        try {
            String fileData = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            fileData = fileData.replace("\r", "");

            Map<String, String> properties = new HashMap<>(); 
            String[] kvp;
            String[] records = fileData.split("\n");

            for (String rec : records) {
                if (!rec.trim().startsWith("//") && rec.contains("=")) {
                    kvp = rec.split("=");
                    if (properties.containsKey(kvp[0].trim())) {
                        properties.put(kvp[0].trim(), kvp[1].trim());
                    }
                }
            }
            return properties;

        } catch (FileNotFoundException e) {
        	logger.info(e.getMessage());
            logger.info(Arrays.toString(e.getStackTrace()));
           return Collections.emptyMap();
        }
    }

}