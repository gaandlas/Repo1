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

//Remove the "https://" from the URL & concatenate with the facebook post link
strippedUrl = url.replaceFirst("https://", "")
URLwithReferrer = "https://l.facebook.com/l.php?u=https%3A%2F%2F"+ strippedUrl + "%3Ffbclid%3DIwAR3NpT2bEBBUDuYDUrtfVauUIXJXE7opyBptv_payDYVYIclkoLiufx4i7c&h=AT2nHCxFLglEEyzbKQwEZaFYefpc9NeoBUcZ3KeG2gjazTIIn8X2awzMvi0aXpR72Selzcp8K3KMtzV9VVf46sXN4xFVkv3pFHaJIwPvgNQU4kdA1PfEjdSkBGJjo4z_HJyx&__tn__=-UK-R&c[0]=AT3l8wDkgytShCQlOBB-ZzxdEv3NdVmzTR889pYKrDdqoPhzs5GdYKszCPuvFBSDC8DJFPbWwmY_-p1XMni10Jl6G4VMaHFkE0dr1BctjBX44UfG0TAINNcTEjSnPgDV358FIH2m2PMwU632qWz76Epxbp8el1r-2Q6Z2Mpthv6N3A"

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : URLwithReferrer], FailureHandling.STOP_ON_FAILURE)

//For Microsite - Navigate to RFI page
if (siteType == 'Microsite') {
	WebUI.click(findTestObject('Pages/Microsite/RfiButton'))
}

//Validate qry_string cookire value is not null. Validate fbclid cookie value.
WebDriver driver = DriverFactory.getWebDriver()

def referrer = driver.manage().getCookieNamed('freya_referrer').getValue()
assert referrer.equals('https://l.facebook.com/')

def fbclid = driver.manage().getCookieNamed('_fbc').getValue()
assert fbclid.equals('IwAR3NpT2bEBBUDuYDUrtfVauUIXJXE7opyBptv_payDYVYIclkoLiufx4i7c')

//Select Program
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/Program'), 5, FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)
}

//Fill RFI Form
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/FillRFIForm'),[('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('country') : country, ('state') : state, ('phone') : phoneNumber, ('zip') : zipcode, ('smsConsentCheck') : smsConsentCheck, ('militaryCheck') : militaryCheck, ('isAffiliate') : isAffiliate,('tealiumProfile') : tealiumProfile,('formType') : formType], FailureHandling.STOP_ON_FAILURE)

//Validate Thank You Page
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/ValidateThankYouPage'), [('siteType') : siteType,], FailureHandling.STOP_ON_FAILURE)

//Add Program UUID & Order ID to the report
//leadInfo['uuid'] = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
//leadInfo['order_id'] = WebUI.executeJavaScript('return utag_data.order_id', null, FailureHandling.OPTIONAL)

//Close the Browser
WebUI.closeBrowser()
