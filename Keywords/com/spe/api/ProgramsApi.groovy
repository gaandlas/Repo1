package com.spe.api
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import com.kms.katalon.util.CryptoUtil

class ProgramsApi {
	@Keyword
	def getProgramsApi(String partnerUuid, String encryptedApiKey, String fileName) {
		String programsApiUrl = "https://programs.qa.edu.help/api/v1/accounts/" + partnerUuid + "/programs"
		CloseableHttpClient httpClient = HttpClients.createDefault();
		getData(programsApiUrl, encryptedApiKey, httpClient)
	}

	def getData(String apiUrl, String encryptedApiKey, CloseableHttpClient httpClient) {

		try {
			HttpGet request = new HttpGet(apiUrl);
			request.addHeader("X-API-KEY", CryptoUtil.decode(CryptoUtil.getDefault(encryptedApiKey)))
			request.addHeader("Content-Type", "application/json")

			CloseableHttpResponse response = httpClient.execute(request);
			try {

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// return it as a String
					String result = EntityUtils.toString(entity);

					JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

					JsonObject links = jsonObject.getAsJsonObject("meta").getAsJsonObject("pagination").getAsJsonObject("links")
					Set<Map.Entry<String, JsonElement>> entries = links.entrySet();
					for(Map.Entry<String, JsonElement> entry: entries)
					{
						if("next" in entry.getKey())
						{
							System.out.println(entry.getValue())
							Thread.sleep(5000)
							getData(entry.getValue().toString(), encryptedApiKey, httpClient)
						}
					}

					//System.out.println(result);
				}
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}
}