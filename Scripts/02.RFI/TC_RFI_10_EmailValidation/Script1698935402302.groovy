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
 * Email Validation
 * 
 ***/

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	url = GlobalVariable.Url
	siteType = GlobalVariable.siteType
	formType = GlobalVariable.formType
}

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	url = GlobalVariable.Url
	siteType = GlobalVariable.siteType
}

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url], FailureHandling.STOP_ON_FAILURE)

//For Microsite - Navigate to RFI page
if (siteType == 'Microsite') {
	WebUI.click(findTestObject('Pages/Microsite/RfiButton'))
}

if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/TlhForm'), 4, FailureHandling.OPTIONAL)) {
	emailValidation = 'The Email field must be a valid email'
} else {
	emailValidation = 'Invalid email address'
}

//Invalid Email (Email without "@" sign)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'speleadsgmail.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals(emailValidation)



//Invalid Email (Email with two @ signs)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'spe@leads@gmail.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals(emailValidation)



//Invalid Email (Email without domain)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'speleads@com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals(emailValidation)



//Invalid Email (Email with invalid domain)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'speleads@abc.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals('Invalid email address')



//Invalid Email (Email without top level domain)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'speleads@gmail')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals(emailValidation)



//Invalid Email (Email with invalid top level domain)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'speleads@gmail.abc')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals('Invalid email address')


//Invalid Email (Please try submitting again)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'greylisted@example.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals('Please try submitting again')



//Invalid Email (Check for typos)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'speleads@gmail.com.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/EmailValidation')).equals('Check for typos')



//Valid Email (Valid email with characters & numbers)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'gabriela.salas.17@gmail.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

WebUI.verifyElementNotPresent(findTestObject('Pages/RFIPage/EmailValidation'), 0)



//Valid Email (Valid email with @test.com)
WebUI.setText(findTestObject('Pages/RFIPage/Email'), 'spe.leads-123_spes@test.com')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

WebUI.verifyElementNotPresent(findTestObject('Pages/RFIPage/EmailValidation'), 0)


//Close the Browser
WebUI.closeBrowser()

