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
 * Validate that when user submit a lead without entering mandatory fields error messages are triggering
 * 
 ***/

//Get execution profile.

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


def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	url = GlobalVariable.url
	siteType = GlobalVariable.siteType
	tealiumProfile = GlobalVariable.tealiumProfile
	formType = GlobalVariable.formType
}

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url], FailureHandling.STOP_ON_FAILURE)

//For Microsite - Navigate to RFI page
if (siteType == 'Microsite') {
	WebUI.click(findTestObject('Pages/Microsite/RfiButton'))
}

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/TlhForm'), 4, FailureHandling.OPTIONAL)) {
	programValidation = 'The Select a Program field is required'
	
	firstNameValidation = 'The First Name field is required'

	lastNameValidation = 'The Last Name field is required'

	phoneValidation = 'is not a valid Phone Number.'

	emailValidation = 'The Email field must be a valid email'

	countryValidation = 'The Select Your Country field is required'

	stateValidation = 'The Select State of Residence field is required'

	zipValidation = 'The Zip field must be numeric and may contain decimal points'

} else {
	programValidation = 'This field is required'
	
	firstNameValidation = 'This field is required'

	lastNameValidation = 'This field is required'

	phoneValidation = 'This field is required'

	emailValidation = 'This field is required'

	countryValidation = 'This field is required'

	stateValidation = 'This field is required'

	zipValidation = 'This field is required'
}

//rogram Validation
assert WebUI.getText(findTestObject('Pages/RFIPage/FirstNameValidation')).equals(firstNameValidation)

//First Name Validation
assert WebUI.getText(findTestObject('Pages/RFIPage/FirstNameValidation')).equals(firstNameValidation)

//Last Name Validation
assert WebUI.getText(findTestObject('Pages/RFIPage/LastNameValidation')).equals(lastNameValidation)

//Email Validation
assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals(emailValidation)

//Phone number Validation
assert WebUI.getText(findTestObject('Pages/RFIPage/PhoneValidation')).equals(phoneValidation)

if (formType == 'Domestic - Zipcode') {
	
	//Zip validation Validation
	assert WebUI.getText(findTestObject('Pages/RFIPage/ZipValidation')).equals(zipValidation)
	
}else if(formType == 'Domestic'){
	//State Validation
	assert WebUI.getText(findTestObject('Pages/RFIPage/StateValidation')).equals(stateValidation)
		
}else {
	//Country Validation
	assert WebUI.getText(findTestObject('Pages/RFIPage/CountryValidation')).equals(countryValidation)
		
	//Select the Country as 'United State' and Validate State field
	WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/Country'), 'United States of America', false)
	WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))
	assert WebUI.getText(findTestObject('Pages/RFIPage/StateValidation')).equals(stateValidation)
}

//If Form Qualifier/Program Qualifier exsist, select checkbox
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/ConfirmEduQualification'), 4, FailureHandling.OPTIONAL)) {
	WebUI.check(findTestObject('Pages/RFIPage/ConfirmEduQualification'))
}

//Fill RFI Form
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/FillRFIForm'),[('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate,('tealiumProfile') : tealiumProfile,('formType') : formType], FailureHandling.STOP_ON_FAILURE)

//Validate Thank You Page
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

//Close the Browser
WebUI.closeBrowser()