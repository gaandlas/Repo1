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

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

//If site profile selected, then set variables with values in the profile. Or test execute with test data in spreadsheets.
if (executionProfile != 'default') {
    url = GlobalVariable.url
	siteType = GlobalVariable.siteType
}

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForPageLoad(2)

//For Microsite - Navigate to RFI page
if (siteType == 'Microsite') {
	WebUI.click(findTestObject('Pages/Microsite/RfiButton'))
}

//verify autoptimize is loading
WebUI.verifyElementPresent(findTestObject('wp_optimization/autoptimize_css'), 0)

WebUI.verifyElementPresent(findTestObject('wp_optimization/autoptimize_js'), 0)

//assert the following scripts/links doesnt contain defer attibute
assert WebUI.getAttribute(findTestObject('wp_optimization/optimizely'), 'defer').equals(null)

//run only for sites that use osano
//assert WebUI.getAttribute(findTestObject('wp_optimization/osano.js'), 'defer').equals(null)

assert WebUI.getAttribute(findTestObject('wp_optimization/jquery.min'), 'defer').equals(null)

assert WebUI.getAttribute(findTestObject('wp_optimization/cookie.js'), 'defer').equals(null)

assert WebUI.getAttribute(findTestObject('wp_optimization/freya_essential.js'), 'defer').equals(null)

//the freya-form-manager.umd.min.js is only available in pages that contain RFI
assert WebUI.getAttribute(findTestObject('wp_optimization/freya-form-manager.umd.min.js'), 'defer').equals(null)

WebUI.closeBrowser()

