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
import com.kms.katalon.core.util.KeywordUtil

//Load browser and navigate to Google
WebUI.navigateToUrl(startingSite)

WebUI.executeJavaScript(String.format("window.location.href = '%s';", destinationSite), null)

compareCookies(expectedValue)

//This is called in the above tests to validate the cookie value with what is passed
def compareCookies(String expectedValue) {
	if (CustomKeywords.'com.spe.Cookie.isCookiePresent'("rfi_referrer")) {
		String cookieValue = CustomKeywords.'com.spe.Cookie.getCookieValue'('rfi_referrer')
		assert cookieValue.contains(expectedValue)
	}
	else if (CustomKeywords.'com.spe.Cookie.isCookiePresent'("tlh_referrer")) {
		String cookieValue = CustomKeywords.'com.spe.Cookie.getCookieValue'('tlh_referrer')
		assert cookieValue.contains(expectedValue)
	}
	else {
		KeywordUtil.markFailed("No UTM Referrer cookie found.")
	}
}