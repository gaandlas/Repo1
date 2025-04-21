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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration as RC

/***
 * Validate SMS COnsent copy, checkbox & Disclaimer Text. Validate Terms, Privacy, Disclaimer Links
 ***/

firstName = GlobalVariable.firstName

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'(GlobalVariable.lastName, 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'(GlobalVariable.lastName, 'yyMMddHHmmss')

phoneNumber = GlobalVariable.phoneNumber

zipcode = GlobalVariable.Zip

country = GlobalVariable.Country

state = GlobalVariable.mailState

smsConsentCheck = false

militaryCheck = false

isAffiliate = false

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	partnerName = GlobalVariable.Partner
	url = GlobalVariable.url
	siteType = GlobalVariable.siteType
	partnerAcronym = GlobalVariable.tealiumProfile
	formType = GlobalVariable.formType
}

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('order_id') : '', ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate, ('partnerAcronym') : partnerAcronym, ('formType') : formType]

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url + GlobalVariable.utm ], FailureHandling.STOP_ON_FAILURE)


//For Microsite - Navigate to RFI page
if (siteType == 'Microsite') {
	WebUI.click(findTestObject('Pages/Microsite/RfiButton'))
}

if (formType == 'Domestic - Zipcode' || formType == 'Domestic') {
	
	//Validate SMS consent checkbox is selected by default
	WebUI.verifyElementChecked(findTestObject('Pages/RFIPage/SMS'), 0)
	
	//Validate SMS consent copy
	smsConsent = WebUI.getText(findTestObject('Pages/RFIPage/SmsConsentText'))
	assert smsConsent.equals(('I authorize ' + partnerName) + ' and its representatives to contact me via SMS. I am providing my consent by leaving the opt-in checked. Message and data rates may apply. Terms.')
	
	//Validate Terms link
	WebUI.click(findTestObject('Pages/RFIPage/Terms'))
	WebUI.switchToWindowIndex(1)
	terms_url = WebUI.getUrl()
	
	assert terms_url.equals(GlobalVariable.termsLinkUS)
	
	WebUI.switchToWindowIndex(0)
	
	//Validate Disclaimer text 
	disclaimer = WebUI.getText(findTestObject('Pages/RFIPage/DisclaimerText'))
	assert disclaimer.equals(partnerName+' has engaged Wiley University Services to help support your education journey. Wiley will contact you shortly in response to your request for information. Learn more about Wiley. Privacy Policy.')
	
	//Validate Privacy Policy link
	WebUI.click(findTestObject('Pages/RFIPage/Privacy'))
	WebUI.switchToWindowIndex(2)
	privacy_url = WebUI.getUrl()
		
	assert privacy_url.equals(GlobalVariable.privacyLinkUS)	
	
	WebUI.switchToWindowIndex(0)
	
	//Validate Disclaimer text link
	WebUI.click(findTestObject('Pages/RFIPage/DisclaimerLink'))
	WebUI.switchToWindowIndex(3)
	disclaimer_url = WebUI.getUrl()
		
	assert disclaimer_url.equals(GlobalVariable.disclaimerLink)
	
	
} else {
	//Validate SMS consent checkbox is not selected by default
	WebUI.verifyElementNotChecked(findTestObject('Pages/RFIPage/SMS'), 0)
	
	//Validate SMS consent copy
	smsConsent = WebUI.getText(findTestObject('Pages/RFIPage/SmsConsentText'))
	assert smsConsent.equals(('I authorise ' + partnerName) + ' and its representatives to contact me via SMS. Message and data rates may apply. Terms.')
	
	//Validate Terms link
	WebUI.click(findTestObject('Pages/RFIPage/Terms'))
	WebUI.switchToWindowIndex(1)
	terms_url = WebUI.getUrl()
	
	assert terms_url.equals(GlobalVariable.termLinkInt)
	
	WebUI.switchToWindowIndex(0)
	
	//Validate Privacy Text copy
	smsConsent = WebUI.getText(findTestObject('Pages/RFIPage/PrivacyText'))
	assert smsConsent.equals('By submitting your information, you confirm you have read the Privacy Policy')
		
	//Validate Privacy Policy link
	WebUI.click(findTestObject('Pages/RFIPage/Privacy'))
	WebUI.switchToWindowIndex(2)
	privacy_url = WebUI.getUrl()
		
	assert privacy_url.equals(GlobalVariable.privacyLinkInt)

}

//Close the Browser
WebUI.closeBrowser()





