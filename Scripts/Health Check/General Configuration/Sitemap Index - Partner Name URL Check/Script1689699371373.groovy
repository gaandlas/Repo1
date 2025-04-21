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
import groovy.transform.Field

// Commenting out the global variable RfiUrl which is relevant to the profile variable name and passing in spreadsheet data instead
//def baseUrl = 'https://' + CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.url)
def baseUrl = 'https://' + CustomKeywords.'com.spe.Url.getBaseUrl'(RfiUrl)

// Commenting out the global variable RfiUrl which is relevant to the profile variable name and passing in spreadsheet data instead
//@Field String domainName =  CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.url)
@Field String domainName =  CustomKeywords.'com.spe.Url.getBaseUrl'(RfiUrl)

WebUI.openBrowser(baseUrl + '/sitemap_index.xml')
WebUI.waitForPageLoad(10)
// Getting webdriver to use for finding page elements
WebDriver driver = DriverFactory.getWebDriver()

// Comparing current sitemap index URL to profile's expected base url
def currentUrl = WebUI.getUrl()
if (currentUrl.contains(domainName)) {
	println 'Check: ' + currentUrl + ' contains ' + domainName
	println 'PASS: ' + currentUrl
} else {
	println 'Check: ' + currentUrl + ' does not contain ' + domainName
	KeywordUtil.markFailed('FAIL: Sitemap index URL does not match the expected domain name/partner name')
}

// As multiple sitemaps are listed, add each of them to a topmost array to navigate through. 
def sitemapIndexList = []
// Instantiating new list of web elements on the page to pull URLs from 
List<WebElement> elements = driver.findElements(By.xpath("//tr/td/a"))
// Iterates through the individual elements in the element list above and pulls the href attribute to add into the topmost sitemapIndexList array
for (def element : elements) {
	sitemapIndexList.add(element.getAttribute('href'))
	println 'Latest sitemapIndexList: ' + sitemapIndexList
}

// Iterates through each sitemap of the index/topmost array
for (def sitemap : sitemapIndexList) {
	WebUI.navigateToUrl(sitemap)
	currentUrl = WebUI.getUrl()
	// Ensures the window's current URL matches the respective URL listed in the sitemap index, as well as the partner's domain name
	if (currentUrl.contains(domainName) && currentUrl == sitemap) {
		println 'Check: ' + currentUrl + ' contains ' + domainName + 
		'\nCheck: ' + currentUrl + ' equals ' + sitemap + 
		'\nPASS: ' + sitemap
	} else {
		println 'FAIL: \n Current sitemap: ' + sitemap + '\ncurrentUrl: ' + currentUrl
		KeywordUtil.markFailed("FAIL: " + sitemap + ' (current URL & domain name comparison)')
	}
}

// Delay for viewing initial testing
//WebUI.delay(1)
WebUI.closeBrowser()
