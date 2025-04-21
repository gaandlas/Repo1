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

import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.*

//Get base Url from the RFI url
def baseUrl = CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.BaseUrl)

WebUI.openBrowser('')

WebUI.setViewPortSize(1920, 1080)

//Navigate to the sitemap
WebUI.navigateToUrl(baseUrl + '/sitemap.xml')
WebUI.waitForPageLoad(10)

//Look for program page in sitemap. Add to here if the program page doesn't include the program page
if (WebUI.verifyElementVisible(findTestObject('Smart RFI/Program Resource Sitemap Link'), FailureHandling.STOP_ON_FAILURE)) {
    WebUI.click(findTestObject('Smart RFI/Program Resource Sitemap Link'))
	WebUI.waitForPageLoad(10)
}

//WebDriver is a Selenium library. This attaches the current browser that Katalon is using to the Selenium library
WebDriver driver = DriverFactory.getWebDriver()

//Returns a list of all elements that match the xpath. From here we can iterate through all of them or check a specific one.
List<WebElement> programPages = new ArrayList<WebElement>()
programPages = driver.findElements(By.xpath('//*[@id="sitemap"]/tbody/tr/td/a'))

programPages[1].click()

WebUI.waitForPageLoad(10)

//Get smart rfi cookie value and store it in uuid variable
def uuid = driver.manage().getCookieNamed("smart_rfi_uuid").getValue()

//Navigate to the RFI page and compare the uuid we got from the cookie to the preselected option on the RFI
WebUI.navigateToUrl(GlobalVariable.BaseUrl)
System.out.println((('Comparing ' + uuid) + ' to ') + WebUI.getAttribute(findTestObject('Object Repository/Pages/RFIPage/Program'), 'value'))

WebUI.verifyOptionSelectedByValue(findTestObject('Object Repository/Pages/RFIPage/Program'), uuid, false, 10, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()