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
 * Submit a lead with Current Status = High School Graduation & related fields
 ***/

firstName = GlobalVariable.firstName

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'(GlobalVariable.lastName, 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'(GlobalVariable.lastName, 'yyMMddHHmmss')

phoneNumber = GlobalVariable.phoneNumber

zip = GlobalVariable.Zip

country = 'United States of America'

state = GlobalVariable.mailState

smsConsentCheck = true

militaryCheck = false

city = 'Los Angeles'

streetAddress = 'Cherokee Ave'

graduationYear = '2024'

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	url = GlobalVariable.url
	siteType = GlobalVariable.siteType
	isAffiliate = GlobalVariable.isAffiliate
}

//Save information to write to csv file later
//def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('order_id') : '', ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate, ('tealiumProfile') : tealiumProfile, ('formType') : formType]

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url + GlobalVariable.uwacAffiliate], FailureHandling.STOP_ON_FAILURE)

//Select Current Status as 'High School Student'
WebUI.selectOptionByIndex(findTestObject('Pages/UWACRFIForm/Status'), 1)

//For affiliate pages enter Highest Level Of Education
if (isAffiliate == 'TRUE') {
	//Select Highest Level Of Education
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/HighestDegree'), 2)
}

//Enter First Name
WebUI.setText(findTestObject('Pages/RFIPage/FirstName'), firstName)

//Enter Last Name
WebUI.setText(findTestObject('Pages/RFIPage/LastName'), lastName)

//Enter Email
WebUI.setText(findTestObject('Pages/RFIPage/Email'), email)

//Select US Flag
WebUI.click(findTestObject('Pages/RFIPage/PhoneCountryCodeFlag'))
WebUI.click(findTestObject('Pages/RFIPage/selectUS'))

//Enter Phone Number
WebUI.setText(findTestObject('Pages/RFIPage/Phone'), phoneNumber)

//Enter Country/State
WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/Country'), country, false)
WebUI.selectOptionByValue(findTestObject('Pages/RFIPage/State'), state, false)

// Enter Street Address
WebUI.setText(findTestObject('Pages/UWACRFIForm/Street'), streetAddress)

//Enter City
WebUI.setText(findTestObject('Pages/UWACRFIForm/City'), city)

//Enter Zipcode
WebUI.setText(findTestObject('Pages/RFIPage/Zip'), zip)

//Enter Anticipated Major
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/Program'), 5, FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 2)
}

//Enter High School Graduation Year
WebUI.setText(findTestObject('Pages/UWACRFIForm/Graduation'), graduationYear)

//Select SMS Consent
WebUI.check(findTestObject('Pages/RFIPage/SMS'))

//Submit the lead
WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(10)

//Validate Thank You Page
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

//Add Program UUID & Order ID to the report
//leadInfo['uuid'] = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
//leadInfo['order_id'] = WebUI.executeJavaScript('return utag_data.order_id', null, FailureHandling.OPTIONAL)

//Close the Browser
WebUI.closeBrowser()


