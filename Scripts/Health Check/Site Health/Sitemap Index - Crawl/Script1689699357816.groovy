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
// The power & efficiency of Selenium!!
WebDriver driver = DriverFactory.getWebDriver()

// As multiple sitemaps are listed, add each of them to a topmost array to navigate through. 
def sitemapIndexList = []
// Instantiating new list of web elements on the page to pull URLs from 
List<WebElement> elements = driver.findElements(By.xpath("//tr/td/a"))

// Iterates through the individual elements in the list above and pulls the href attribute to add into the topmost sitemapIndexList array
for (def element : elements) {
//	println element.getAttribute('href')
//	WebUI.navigateToUrl(element.getAttribute('href'))
	sitemapIndexList.add(element.getAttribute('href'))
}

// Iterates through each sitemap of the index/topmost array
for (def sitemap : sitemapIndexList) {
	WebUI.navigateToUrl(sitemap)
	println 'Current sitemap navigated to: ' + sitemap
	// Creating a sub-array for each of the sitemaps listed in the index/topmost array, to navigate to individual pages
	def pageList = [] 
	// Repopulating the previously instantiated elements List above with a list of URLs/pages to be added to their own temporary sub-array
	pages = driver.findElements(By.xpath("//tr/td/a"))
	for (def page : pages) {
		println 'Page added to sub-array: ' + page.getAttribute('href')
		// Navigating too early?
		// WebUI.navigateToUrl(element.getAttribute('href'))
		// Individual pages added to the subarray, to navigate & verify
		pageList.add(page.getAttribute('href'))
	}
	// Navigates to each page, after they have all been added to the pageList array
	for (int i = 0; i < pageList.size(); i++) {
		println 'Visiting page of sub-array: ' + pageList[i]
		WebUI.navigateToUrl(pageList[i])
		// Defining the currently loaded window URL to compare and ensure no URL redirects ocurred unexpectedly
		def currentUrl = WebUI.getUrl()
		println 'currentUrl defined as: ' + currentUrl
		WebUI.waitForPageLoad(5)
		WebUI.delay(3)
		// Next steps: asserting
		if (pageList[i] != currentUrl) {
			println 'Failed case:\n' + 'Expected pageList: ' + pageList[i] + ', does not match currentUrl: ' + currentUrl
			KeywordUtil.markFailed("Failed case at: " + pageList[i])
		}
		// Would like to implement further refinement: 
		// if (pageList[i] != currentUrl && !currentUrl.contains('brand'|'overview')) 
		// Inspect web elements with a class that contains"error404" etc
		// Wildcard xpath: //*[contains(@class, '404')]
		// Wildcard //*[contains(@class, 'error')] | //*[contains(@class, '404')]
		// Wildcard //*[contains(@class, '404') and contains(@class, 'error')]
	}
}

// Delay for viewing initial testing
WebUI.delay(5)
WebUI.closeBrowser()

















// Would like to implement further refinement:
// if (pageList[i] != currentUrl && !currentUrl.contains('brand'|'overview'))
// Inspect web elements with a class that contains"error404" etc
// Wildcard xpath: //*[contains(@class, '404')]
// Wildcard //*[contains(@class, 'error')] | //*[contains(@class, '404')]
// Wildcard //*[contains(@class, '404') and contains(@class, 'error')]
