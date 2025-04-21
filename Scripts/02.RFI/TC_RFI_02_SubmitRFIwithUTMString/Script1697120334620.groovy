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
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

/***
 * Submit RFI with UTM string + with SMS Consent check not selected + For international partners Country other than 'United State of America'(if fields available) + Military checkbox not selected (if field available).
 * 'tlh_qry_string' , '_fbc' , 'STYXKEY_jwm_uid' cookie values are validated.
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
	url = GlobalVariable.url
	siteType = GlobalVariable.siteType
	tealiumProfile = GlobalVariable.tealiumProfile
	formType = GlobalVariable.formType
}

//Save information to write to csv file later
//def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('order_id') : '', ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate, ('tealiumProfile') : tealiumProfile, ('formType') : formType]

//Add UTM string to the end of the URL & Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url + GlobalVariable.utm ], FailureHandling.STOP_ON_FAILURE)

// Deletes sticky footers which prevents certain clicks and submission during mobile and headless RFI
if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StickyCtaFooter'), FailureHandling.OPTIONAL)) {
	CustomKeywords.'com.spe.pages.CustomJS.DeleteElement'('tux-c-sticky-cta')
}
if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StickyCtaFooter'), FailureHandling.OPTIONAL)) {
	CustomKeywords.'com.spe.pages.CustomJS.DeleteElement'('osano-cm-window')
}

//For Microsite - Navigate to RFI page
if (siteType == 'Microsite') {
	WebUI.click(findTestObject('Pages/Microsite/RfiButton'))
}

//Validate qry_string cookire value is not null. Validate fbclid cookie value.
WebDriver driver = DriverFactory.getWebDriver()

def qry_string = driver.manage().getCookieNamed('freya_qry_string').getValue()
assert !(qry_string.equals(''))

def fbclid = driver.manage().getCookieNamed('_fbc').getValue()
assert fbclid.contains(GlobalVariable.fbclid)

//Select Program
String utagExists = WebUI.executeJavaScript('return utag_data.program_uuid;', null, FailureHandling.OPTIONAL)	
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Program'), FailureHandling.OPTIONAL)) {
    WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)
    leadInfo['uuid'] = WebUI.getAttribute(findTestObject('Pages/RFIPage/Program'), 'value')
} else if (leadInfo['uuid'] == '' || leadInfo['uuid'] == null) {
    if (utagExists){
        leadInfo['uuid'] = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
    } else {
        leadInfo['uuid'] = WebUI.executeJavaScript('return defaultProgram', null, FailureHandling.OPTIONAL)
    }
}

//Fill RFI Form
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/FillRFIForm'),[('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate,('tealiumProfile') : tealiumProfile,('formType') : formType], FailureHandling.STOP_ON_FAILURE)

//Validate Thank You Page
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

//Validate userID cookie value
//def userID = driver.manage().getCookieNamed('STYXKEY_jwm_uid').getValue()
//assert !(userID.equals(''))

//Add Program UUID & Order ID to the report
//leadInfo['uuid'] = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
//leadInfo['order_id'] = WebUI.executeJavaScript('return utag_data.order_id', null, FailureHandling.OPTIONAL)

// Writing to the CSV...
leadInfo['url'] = WebUI.getUrl()
CustomKeywords.'com.spe.util.WriteDataToCsv.WriteRow'(leadInfo)

//Close the Browser
WebUI.closeBrowser()

