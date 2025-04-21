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
import org.junit.Assert as Assert

/*
Validate that the porgram drop down not available in programmatic LP RFI forms & smartrfi cookie setup with program uuid
*/

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
	formType = GlobalVariable.formType
	tealiumProfile = GlobalVariable.tealiumProfile
	sigleProgramURL = GlobalVariable.sigleProgramURL
}

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('order_id') : '', ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate, ('tealiumProfile') : tealiumProfile, ('formType') : formType]

URL parsedUrl = new URL(url)
String domain = parsedUrl.getHost()

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : domain + sigleProgramURL,], FailureHandling.STOP_ON_FAILURE)

//Validate Program Drop down not available
WebUI.verifyElementNotPresent(findTestObject('Pages/RFIPage/Program'), 0)

//Validate smartrfi cookie value is not null
WebDriver driver = DriverFactory.getWebDriver()
def uuid = driver.manage().getCookieNamed('smart_rfi_uuid').getValue()

assert !(uuid.equals(''))

//Fill RFI Form
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/FillRFIForm'),[('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate,('tealiumProfile') : tealiumProfile,('formType') : formType], FailureHandling.STOP_ON_FAILURE)

//Validate Thank You Page
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

//Add Program UUID & Order ID to the report
leadInfo['uuid'] = uuid
leadInfo['order_id'] = WebUI.executeJavaScript('return utag_data.order_id', null, FailureHandling.OPTIONAL)

//Close the Browser
WebUI.closeBrowser()