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
import org.openqa.selenium.*
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil

def baseUrl = 'https://' + CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.Url)

WebUI.openBrowser(baseUrl + '/sitemap_index.xml')
WebUI.waitForPageLoad(10)
// Selenium 
WebDriver driver = DriverFactory.getWebDriver()

// As multiple sitemaps are listed, add each of them to a topmost array to navigate through. 
def sitemapIndexList = []
// Instantiating new list of web elements on the page to pull URLs from 
List<WebElement> elements = driver.findElements(By.xpath("//tr/td/a"))

// Iterates through the individual elements in the list above and pulls the href attribute to add into the topmost sitemapIndexList array
for (def element : elements) {
	sitemapIndexList.add(element.getAttribute('href'))
}

// Iterates through each sitemap of the index/topmost array
for (def sitemap : sitemapIndexList) {
	WebUI.navigateToUrl(sitemap)
	println 'CURRENT SITEMAP NAVIGATED INTO: ' + sitemap
	// Creating a sub-array for each of the sitemaps listed in the index/topmost array, to navigate to individual pages
	def pageList = [] 
	// Repopulating the previously instantiated elements List above with a list of URLs/pages to be added to their own temporary sub-array
	pages = driver.findElements(By.xpath("//tr/td/a"))
	for (def page : pages) {
		println 'PAGE ADDED TO SUB-ARRAY: ' + page.getAttribute('href')
		// WebUI.navigateToUrl(element.getAttribute('href'))
		// Individual pages added to the subarray, to navigate & verify
		pageList.add(page.getAttribute('href'))
	}
	// Navigates to each page, after they have all been added to the pageList array
	for (int i = 0; i < pageList.size(); i++) {
		println 'VISITING PAGE OF SUB-ARRAY: ' + pageList[i]
		WebUI.navigateToUrl(pageList[i])
		// Defining the currently loaded window URL to compare and ensure no URL redirects ocurred unexpectedly
		def currentUrl = WebUI.getUrl()
		println 'currentUrl DEFINED AS: ' + currentUrl
		WebUI.waitForPageLoad(5)
		// Logs smart_rfi_uuid cookie on single program LP, that it stays between microsite pages w/o forms, and to verify cookie is updated on applicable new smart rfi pages
		if (CustomKeywords.'com.spe.Cookie.isCookiePresent'('smart_rfi_uuid')) {
			def uuid = driver.manage().getCookieNamed("smart_rfi_uuid").getValue()
			println 'BACKGROUND SMART_RFI_UUID COOKIE VALUE: ' + uuid
		}
		WebUI.delay(3)
		// Checking if the page has a form - TLH/Freya
		if (driver.findElements(By.xpath(findTestObject('Object Repository/Pages/RFIPage/Forms').getSelectorCollection().get(SelectorMethod.XPATH))).size() == 0) {
			// if not, lets us know and moves on // (can be expanded onto, in case we need to test a page that should have a form)
			println 'NO FORM LOCATED ON: ' + currentUrl
		}
		// if so, compare uuid of page's smart_rfi_uuid cookie against the form's selected program
		if (driver.findElements(By.xpath(findTestObject('Object Repository/Pages/RFIPage/Program').getSelectorCollection().get(SelectorMethod.XPATH))).size() >= 1) {
			println 'FORM LOCATED ON: ' + currentUrl
			if (CustomKeywords.'com.spe.Cookie.isCookiePresent'('smart_rfi_uuid')) {
				def uuid = driver.manage().getCookieNamed("smart_rfi_uuid").getValue()
				System.out.println((('SMART_RFI_UUID: ' + uuid) + ', VS FORM SELECTION: ') + WebUI.getAttribute(findTestObject('Object Repository/Pages/RFIPage/Program'), 'value'))
				// If the comparison is false, it will log to console and continue testing the rest of the site if applicable
				WebUI.verifyOptionSelectedByValue(findTestObject('Object Repository/Pages/RFIPage/Program'), uuid, false, 10, FailureHandling.CONTINUE_ON_FAILURE)
			}
		}
		println 'MOVING TO NEXT PAGE'
	}
}
// delay, for viewing initial testing, can be removed to optimize later on
WebUI.delay(5)
// glhf
WebUI.closeBrowser()