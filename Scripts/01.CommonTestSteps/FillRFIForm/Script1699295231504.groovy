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

//Enter First Name
WebUI.setText(findTestObject('Pages/RFIPage/FirstName'), firstName, FailureHandling.OPTIONAL)

//Enter Last Name
WebUI.setText(findTestObject('Pages/RFIPage/LastName'), lastName, FailureHandling.OPTIONAL)

//Enter Email
WebUI.setText(findTestObject('Pages/RFIPage/Email'), email, FailureHandling.OPTIONAL)

//Select US Flag
WebUI.click(findTestObject('Pages/RFIPage/PhoneCountryCodeFlag'), FailureHandling.OPTIONAL)
WebUI.click(findTestObject('Pages/RFIPage/selectUS'), FailureHandling.OPTIONAL)

//Enter phone number
WebUI.setText(findTestObject('Pages/RFIPage/Phone'), phone, FailureHandling.OPTIONAL)

//Select Highest Level Of Education (Only available in 'La Trobe University' sites)
if (tealiumProfile == 'ltu' || tealiumProfile == 'unisc'){
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/HighestDegree'), 2, FailureHandling.OPTIONAL)
}

//For affiliate pages enter Highest Level Of Education, Country/State. For other sites, enter Zipcode or Country/State depend on the form type.
if (isAffiliate) {
	
	//Select Highest Level Of Education
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/HighestDegree'), 2, FailureHandling.OPTIONAL)

	//Enter Country/State
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/Country'), country, false, FailureHandling.OPTIONAL)
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/State'), state, false, FailureHandling.OPTIONAL)
	
}else if (formType == 'Domestic - Zipcode'){
	//Enter Zipcode
	WebUI.setText(findTestObject('Pages/RFIPage/Zip'), zip, FailureHandling.OPTIONAL)
	
}else {
	//Enter Country/State
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/Country'), country, false, FailureHandling.OPTIONAL)
		
	if(country == 'United States of America') {
		WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/State'), state,false, FailureHandling.OPTIONAL)
	}
}

//If Form Qualifier/Program Qualifier exsist, select checkbox
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/ConfirmEduQualification'), 4, FailureHandling.OPTIONAL)) {
	WebUI.check(findTestObject('Pages/RFIPage/ConfirmEduQualification'), FailureHandling.OPTIONAL)
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
		WebUI.check(findTestObject('Pages/RFIPage/MilitaryAffiliated'), FailureHandling.OPTIONAL)
	}
}

//Submit the lead
WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(15)


