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

def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != "default") {
	url = GlobalVariable.url
	siteType = GlobalVariable.siteType
	formType = GlobalVariable.formType
}

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url], FailureHandling.STOP_ON_FAILURE)

//Enter First Name
WebUI.setText(findTestObject('Pages/RFIPage/FirstName'), firstName)

//Enter Last Name
WebUI.setText(findTestObject('Pages/RFIPage/LastName'), lastName)

//If the form is in two-steps move to next step
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/NextButton'), 5, FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Pages/RFIPage/NextButton'), FailureHandling.STOP_ON_FAILURE)
}

//Incorrect Zip validation
WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '10')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/ZipValidation')).equals(zipValidation)


//Incorrect Zip validation
WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '56200')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(2)

assert WebUI.getText(findTestObject('Pages/RFIPage/ZipValidation')).equals(zipValidation)


//Close the Browser
WebUI.closeBrowser()
