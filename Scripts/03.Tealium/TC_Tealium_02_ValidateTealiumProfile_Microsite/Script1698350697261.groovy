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

firstName = GlobalVariable.firstName

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'(GlobalVariable.lastName, 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'(GlobalVariable.lastName, 'yyMMddHHmmss')

phoneNumber = GlobalVariable.phoneNumber

zipcode = GlobalVariable.Zip

country = 'United States of America'

state = GlobalVariable.mailState

smsConsentCheck = true

militaryCheck = true

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

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url,], FailureHandling.STOP_ON_FAILURE)

def utag = WebUI.executeJavaScript('return window.utag_data', null)

//Validate Home page tealium profile ('page_name' different in some site. Hence validating 'page_name' is not null)
assert (utag['page_type']).equals('home')
assert (utag['partner_name']).equals(tealiumProfile)
assert (utag['site_type']).equals('Microsite')
assert !(utag['page_name']).equals('')
assert (utag['is_conversion']) == 0
assert (utag['is_landing_page']) == 0
assert (utag['program_name']).toLowerCase().equals(tealiumProfile + '-brand')
assert (utag['page_category']).equals('Home')
assert (utag['site_section']).equals('Home')

WebUI.click(findTestObject('Pages/Microsite/RfiButton'))

//Select Program
WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)

//Fill RFI Form
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/FillRFIForm'),[('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate,('tealiumProfile') : tealiumProfile,('formType') : formType], FailureHandling.STOP_ON_FAILURE)

//Validate 'Thank You' page tealium profile
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

utag = WebUI.executeJavaScript('return window.utag_data', null)

assert (utag['page_type']).equals('thankyou')
assert (utag['partner_name']).equals(tealiumProfile)
assert (utag['site_type']).equals('Microsite')
assert (utag['page_name']).toLowerCase().contains('thank')
assert (utag['is_conversion']) == 1
assert (utag['is_landing_page']) == 0
assert (utag['program_name']).toString().toLowerCase().contains(tealiumProfile)
//Checking the program name doesn't contain the default partner-brand value
assert !(utag['program_name']).toLowerCase().equals(tealiumProfile + '-brand')
assert (utag['page_category']).equals('Thank You')
assert (utag['site_section']).equals('Thank You')

//Close the Browser
WebUI.closeBrowser()