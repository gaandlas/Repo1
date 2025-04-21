package com.spe.salesforce

import java.text.SimpleDateFormat

import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import com.kms.katalon.core.annotation.Keyword

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.ScanRequest
import software.amazon.awssdk.services.dynamodb.model.ScanResponse
import software.amazon.awssdk.services.ssm.SsmClient
import software.amazon.awssdk.services.ssm.model.GetParameterRequest
import software.amazon.awssdk.services.ssm.model.GetParameterResponse
import software.amazon.awssdk.services.ssm.model.SsmException

public class GenerateToken {

	private static String getAwsProperty(String parameter) {

		Region region = Region.US_EAST_1;
		SsmClient ssmClient = SsmClient.builder()
				.region(region)
				.credentialsProvider(ProfileCredentialsProvider.create("non-prod"))
				.build();

		try {
			GetParameterRequest parameterRequest = GetParameterRequest.builder()
					.name(parameter)
					.build();

			GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);

			return parameterResponse.parameter().value();
		} catch (SsmException e) {
			System.err.println(e.getMessage());
			System.out.println("There was an issue retrieving credentials from AWS. Verify you are logged into the AWS CLI and try again. If problem persists, contact an automation developer.");
			System.exit(1);
		}
	}

	@Keyword
	public static String getKey() {
		Region region = Region.US_EAST_1;
		DynamoDbClient ddb = DynamoDbClient.builder()
				.region(region)
				.credentialsProvider(ProfileCredentialsProvider.create("non-prod"))
				.build();

		List<Map<String, AttributeValue>> results = scanItems(ddb, 'qa-leads-pipeline-salesforce-key');
		SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newestKeyDate = '1900-01-01 01:01:01';
		String key = '';
		for (Map<String, AttributeValue> item : results) {
			Set<String> keys = item.keySet();
			if(simpleformat.parse(item.get('datetime').s()).after(simpleformat.parse(newestKeyDate))) {
				newestKeyDate = item.get('datetime').s();
				key = item.get('access_token').s();
			}
		}
		return key
	}

	private static List<Map<String, AttributeValue>> scanItems( DynamoDbClient ddb, String tableName ) {

		try {
			ScanRequest scanRequest = ScanRequest.builder()
					.tableName(tableName)
					.build();

			ScanResponse response = ddb.scan(scanRequest);
			return response.items();
		} catch (DynamoDbException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static void getDynamoDBItem(DynamoDbClient ddb,String tableName,String key,String keyVal ) {

		HashMap<String,AttributeValue> keyToGet = new HashMap<String,AttributeValue>();

		keyToGet.put(key, AttributeValue.builder()
				.s(keyVal).build());

		GetItemRequest request = GetItemRequest.builder()
				.key(keyToGet)
				.tableName(tableName)
				.build();

		try {
			Map<String,AttributeValue> returnedItem = ddb.getItem(request).item();

			if (returnedItem != null) {
				Set<String> keys = returnedItem.keySet();
				System.out.println("Amazon DynamoDB table attributes: \n");

				for (String key1 : keys) {
					System.out.format("%s: %s\n", key1, returnedItem.get(key1).toString());
				}
			} else {
				System.out.format("No item found with the key %s!\n", key);
			}
		} catch (DynamoDbException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	@Keyword
	public static String generateSalesforceToken() {
		String env = '/leads-pipeline/qa/';
		String credsUrl = getAwsProperty(env + 'CREDS_URL');

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost request = new HttpPost(credsUrl);


			URI uri = new URIBuilder(request.getURI())
					.addParameter("username", getAwsProperty(env + 'CREDS_USERNAME'))
					.addParameter("password", getAwsProperty(env + 'CREDS_PASSWORD'))
					.addParameter("grant_type", getAwsProperty(env + 'CREDS_GRANT_TYPE'))
					.addParameter("client_secret", getAwsProperty(env + 'CREDS_CLIENT_SECRET'))
					.addParameter("client_id", getAwsProperty(env + 'CREDS_CLIENT_ID'))
					.build();

			CloseableHttpResponse response = httpClient.execute(request);

			try {

				// Get HttpResponse Status
				System.out.println(response.getProtocolVersion());              // HTTP/1.1
				System.out.println(response.getStatusLine().getStatusCode());   // 200
				System.out.println(response.getStatusLine().getReasonPhrase()); // OK
				System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// return it as a String
					System.out.println(EntityUtils.toString(entity));
				}

			} finally {
				response.close();
			}

		} finally {
			httpClient.close();
		}
	}
}
