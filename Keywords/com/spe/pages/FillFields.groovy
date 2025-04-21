package com.spe.pages

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

import internal.GlobalVariable

public class FillFields {

	@Keyword
	public void FillFieldsCheckSms(int programNum, String firstName, String lastName, String email, String phone, String country, String state, String zip) {
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/NextButton'), FailureHandling.OPTIONAL)) {
			FillAllFields(programNum, firstName, lastName, email, phone, country, state, zip)
			WebUI.click(findTestObject('Pages/RFIPage/NextButton'))
			FillAllFields(programNum, firstName, lastName, email, phone, country, state, zip)
		}
		else {
			FillAllFields(programNum, firstName, lastName, email, phone, country, state, zip)
		}
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/SMS'), FailureHandling.OPTIONAL)) {
			WebUI.check(findTestObject('Pages/RFIPage/SMS'))
		}
	}

	@Keyword
	public void FillFieldsUnCheckSms(int programNum, String firstName, String lastName, String email, String phone, String country, String state, String zip) {
		FillAllFields(programNum, firstName, lastName, email, phone, country, state, zip)
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/SMS'), FailureHandling.OPTIONAL)) {
			WebUI.uncheck(findTestObject('Pages/RFIPage/SMS'))
		}
	}

	private void FillAllFields(int programNum, String firstName, String lastName, String email, String phone, String country, String state, String zip) {
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Program'), FailureHandling.OPTIONAL)) {
			WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), programNum)
		}
		if(WebUI.verifyElementVisible(findTestObject('Chatbot/ChatFirstName'), FailureHandling.OPTIONAL)) {
			WebUI.setText(findTestObject('Chatbot/ChatFirstName'), firstName)
		}
		if(WebUI.verifyElementVisible(findTestObject('Chatbot/LastName'), FailureHandling.OPTIONAL)) {
			WebUI.setText(findTestObject('Chatbot/LastName'), lastName)
		}
		if(WebUI.verifyElementVisible(findTestObject('Chatbot/Email'), FailureHandling.OPTIONAL)) {
			WebUI.setText(findTestObject('Chatbot/Email'), email)
		}
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Phone'), FailureHandling.OPTIONAL)) {
			WebUI.setText(findTestObject('Pages/RFIPage/Phone'), phone)
		}
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Zip'), FailureHandling.OPTIONAL)) {
			WebUI.setText(findTestObject('Pages/RFIPage/Zip'), zip)
		}
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Country'), FailureHandling.OPTIONAL)) {
			WebUI.selectOptionByLabel(findTestObject('Pages/RFIPage/Country'), country, false)
		}
		if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/State'), FailureHandling.OPTIONAL)) {
			WebUI.selectOptionByLabel(findTestObject('Pages/RFIPage/State'), state, false)
		}
	}
}
