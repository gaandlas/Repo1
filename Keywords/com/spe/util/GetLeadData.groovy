package com.spe.util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.spe.Cookie

import internal.GlobalVariable

public class GetLeadData {

	@Keyword
	public Map<String, String> getLeadData() {
		Map<String,String> leadDataMap = new HashMap<String, String>();
		Cookie cookie = new Cookie();
		//Add partner abbreviation
		leadDataMap.put("abbreviation", GlobalVariable.SchoolName)
		def utm = cookie.getCookieValue("tlh_qry_string")

		//Add the campaign ("Direct", "audience", "Bucket")
		if(utm.contains("utm_source=audience")){
			leadDataMap.put("campaign", "audience");
		}
		else{
			leadDataMap.put("campaign", "Direct Traffic");
		}

		//Add the student details
		if(WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/Country'), 1, FailureHandling.OPTIONAL))
			leadDataMap.put("country", WebUI.getText(findTestObject('Pages/RFIPage/Country')));
		if(WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/State'), 1, FailureHandling.OPTIONAL))
			leadDataMap.put("state", WebUI.getText(findTestObject('Pages/RFIPage/State')));
		if(WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/Zip'), 1, FailureHandling.OPTIONAL))
			leadDataMap.put("zip", WebUI.getText(findTestObject('Pages/RFIPage/Zip')));
		leadDataMap.put("programName", WebUI.getText(findTestObject('Pages/RFIPage/Program')));
		leadDataMap.put("firstname", WebUI.getText(findTestObject('Chatbot/FirstName')));
		leadDataMap.put("lastname", WebUI.getText(findTestObject('Chatbot/LastName')));
		leadDataMap.put("email", WebUI.getText(findTestObject('Chatbot/Email')));
		leadDataMap.put("phoneNumber", WebUI.getText(findTestObject('Pages/RFIPage/Phone')));
		leadDataMap.put("sms", WebUI.verifyElementChecked(findTestObject('Pages/RFIPage/SMS'), 1));
		leadDataMap.put("uuid", WebUI.getAttribute(findTestObject('Pages/RFIPage/Program'), 'value'));



		return leadDataMap;
	}
}
