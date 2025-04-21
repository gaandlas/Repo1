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
import com.kms.katalon.core.configuration.RunConfiguration as RC

/***
 * Submit a lead from affiliate pages + with SMS Consent check not selected + Military checkbox not selected (if field available)
 *
 * ***/

firstName = GlobalVariable.firstName

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'(GlobalVariable.lastName, 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'(GlobalVariable.lastName, 'yyMMddHHmmss')

phoneNumber = GlobalVariable.phoneNumber

zipcode = GlobalVariable.Zip

country = 'United States of America'

state = GlobalVariable.mailState

smsConsentCheck = false

militaryCheck = false

isAffiliate = true

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	url = GlobalVariable.url
	siteType = GlobalVariable.siteType
	tealiumProfile = GlobalVariable.tealiumProfile
	formType = GlobalVariable.formType
}

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('order_id') : '', ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate, ('tealiumProfile') : tealiumProfile, ('formType') : formType]

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url + GlobalVariable.utmAffiliate ], FailureHandling.STOP_ON_FAILURE)

//Select Program
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/Program'), 5, FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)
}

//Fill RFI Form
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/FillRFIForm'),[('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate,('tealiumProfile') : tealiumProfile,('formType') : formType], FailureHandling.STOP_ON_FAILURE)

//Validate Thank You Page
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

//Close the Browser
WebUI.closeBrowser()

