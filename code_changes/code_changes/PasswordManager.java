package commonutilities.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;

/**
 * @author hakkanolla
 * Implementation for integrating AWS Secrets Manager and retrieve secrets.
 */
public class PasswordManager {
	private static final Logger logger = LoggerFactory.getLogger(PasswordManager.class);

	private static JSONObject json = new JSONObject();
	
	public static void main(String[] args) {
		try {
			PasswordManager.loadAWSSecretPasswords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(Arrays.toString(e.getStackTrace()));
		}
	}
	
	public static void loadAWSSecretPasswords() throws IOException {
	    String secretName = "/cares/qa-automation/test";
	    Region region = Region.of("us-west-2");
	    String roleArn = "arn:aws:iam::501473456222:role/QATools.Automation";
	    String roleSessionName="test-ZYy5g0";
	    
	    
	    Path currentRelativePath = Paths.get(""); 
		String prjPath=currentRelativePath.toAbsolutePath().toString();
		String configFilePath = Paths.get(prjPath, "credentialsAWS.properties").toString();
		File configFile = new File(configFilePath);

		if (!configFile.exists()) {
			throw new IOException("Config file not found: " + configFile.getAbsolutePath());
		}

		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream(configFile)) {
			prop.load(fis);
		}

//	    AwsBasicCredentials credentials = AwsBasicCredentials.
//	    		create(prop.getProperty("credentialsAWS","AWSaccessKeyId"),
//	    				prop.getProperty("credentialsAWS","AWSaccessKeySecret"));
//	    
	    AwsBasicCredentials credentials = AwsBasicCredentials.
	    		create("","");
	    
	    StsClient stsClient = StsClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
	    
	    
	    AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .roleSessionName(roleSessionName)
                .build();
	    

	    AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
	    Credentials myCreds = roleResponse.credentials();
        String key = myCreds.accessKeyId();
        String secKey = myCreds.secretAccessKey();
        String secToken = myCreds.sessionToken();
        
	    // Create a Secrets Manager client
	    SecretsManagerClient client = SecretsManagerClient.builder()
	    		.credentialsProvider(
                        StaticCredentialsProvider.create(AwsSessionCredentials.create(key, secKey, secToken)))
	            .region(region)
	            .build();

	    GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
	            .secretId(secretName)
	            .build();

	    
	    
	    GetSecretValueResponse getSecretValueResponse;

	    try {
	        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	    } catch (Exception e) {
	        // For a list of exceptions thrown, see
	        // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
	        throw e;
	    }

	    String secret = getSecretValueResponse.secretString();
	    try {
			json = new JSONObject(secret);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.info(Arrays.toString(e.getStackTrace()));
		}
	}
	
	public String returnPassword(String userKey){
		String password =null;
		if(Objects.nonNull(userKey)) {
			try {
				password = json.getString(userKey);
			} catch (Exception e) {
				logger.info(Arrays.toString(e.getStackTrace()));
			}
			
			if(Objects.nonNull(password)) {
				return password;
			} else {
				throw new NullPointerException("Exception occurred while retreiving password for user"+ userKey);
			}
		} else {
			throw new NullPointerException("Exception occurred while retreiving password");
		}
}
}