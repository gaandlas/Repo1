import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/**
 * 
 * Two step forms used in Legacy sites has two different form structures. This script written to cover these 2 strucutes.
 *        1. First Step: First Name/Last Name | Second Step: Email/Phone Number/Zip
 *        2. First Step: First Name/Last Name/Email | Second Step: Phone Number/Zip
 */

//Enter First Name
WebUI.setText(findTestObject('Pages/RFIPage/FirstName'), firstName)

//Enter Last Name
WebUI.setText(findTestObject('Pages/RFIPage/LastName'), lastName)

//Enter Email
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Email'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/Email'), email)
}

WebUI.click(findTestObject('Pages/RFIPage/NextButton'))

//Enter Email
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Email'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/Email'), email)
}

//Select US Flag
WebUI.click(findTestObject('Pages/RFIPage/PhoneCountryCodeFlag'))
WebUI.click(findTestObject('Pages/RFIPage/selectUS'))

//Enter phone number
WebUI.setText(findTestObject('Pages/RFIPage/Phone'), phone)

//For affiliate pages enter Highest Level Of Education, Country/State. For other sites, enter Zipcode or Country/State depend on the form type.
if (isAffiliate) {
	
	//Select Highest Level Of Education
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/HighestDegree'), 2)

	//Enter Country/State
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/Country'), country, false)
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/State'), state, false)
	
}else if (formType == 'Domestic - Zipcode'){
	//Enter Zipcode
	WebUI.setText(findTestObject('Pages/RFIPage/Zip'), zip)
	
}else {
	//Enter Country/State
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/Country'), country, false)
		
	if(country == 'United States of America') {
		WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/State'), state,false)
	}
}

//If Form Qualifier/Program Qualifier exsist, select checkbox
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/ConfirmEduQualification'), 4, FailureHandling.OPTIONAL)) {
	WebUI.check(findTestObject('Pages/RFIPage/ConfirmEduQualification'))
}

//SMS Consent Check
if (smsConsentCheck) {
	if(!WebUI.verifyElementChecked(findTestObject('Pages/RFIPage/SMS'), 1, FailureHandling.OPTIONAL)){
        WebUI.check(findTestObject('Pages/RFIPage/SMS'))
	}
} else {
    if (WebUI.verifyElementChecked(findTestObject('Pages/RFIPage/SMS'), 1, FailureHandling.OPTIONAL)) {
        WebUI.uncheck(findTestObject('Pages/RFIPage/SMS'))
    }
}

//MilitaryAffiliated check
if(WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/MilitaryAffiliated'),4, FailureHandling.OPTIONAL)) {
	if(militaryCheck) {
		WebUI.check(findTestObject('Pages/RFIPage/MilitaryAffiliated'))
	}
}

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(15)


