import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

def baseUrl = 'https://' + CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.Url)
def executionProfile = RC.getExecutionProfile()

// Navigate to applicable TY page based on LP or Web/Microsite profile - defaults to thank-you
// During the shift from profiles to spreadsheets used during testing, we'll need to update this. 
if (executionProfile.contains('web-')) {
	WebUI.openBrowser(baseUrl + '/thank-you?lp=false')
} else if (executionProfile.contains('lp-')) {
	WebUI.openBrowser(baseUrl + '/lpconfirm-thank-you?lp=true')
} else WebUI.openBrowser(baseUrl + '/thank-you?lp=false') 
WebUI.waitForPageLoad(10)

// Logging for test suite consisting of compiled partner profiles
def currentUrl = WebUI.getUrl()
println 'CURRENT URL: ' + currentUrl

// Javascript object grab
def utag_capture = WebUI.executeJavaScript('return utag_data', null, FailureHandling.CONTINUE_ON_FAILURE)

// Example data
// xpath: //script[contains(.,'utag_data')]/text()
/*
var utag_data = {
	"page_type": "thankyou",
	"partner_name": "car",
	"site_type": "Microsite",
	"page_name": "Thank You",
	"is_conversion": 0,
	"is_landing_page": 0,
	"program_name": "car-brand",
	"site_section": "Thank You",
	"page_category": "Thank You",
	"site_framework": "tux"
};
*/

// Pull only relevant data from utag_capture array and adds to our utag_data variable
def utagDataText = [('page_type') : null, ('partner_name') : null, ('site_type') : null, ('page_name') : null, ('is_conversion') : null, ('is_landing_page') : null, ('program_name') : null, ('site_section') : null, ('page_category') : null, ('site_section') : null, ('site_framework') : null]
TestObject utagDataObj = new TestObject()
utagDataText['page_type'] = utag_capture.page_type
utagDataText['partner_name'] = utag_capture.partner_name
utagDataText['site_type'] = utag_capture.site_type
utagDataText['page_name'] = utag_capture.page_name
utagDataText['is_conversion'] = utag_capture.is_conversion
utagDataText['is_landing_page'] = utag_capture.is_landing_page
utagDataText['programe_name'] = utag_capture.programe_name
utagDataText['site_section'] = utag_capture.site_section
utagDataText['page_category'] = utag_capture.page_category
utagDataText['site_section'] = utag_capture.site_section
utagDataText['site_framework'] = utag_capture.site_framework

// LOGGING SECTION FOR REVIEWING RESULTS
println 'ARRAY FOR UTAG:\n' + utagDataText

// Setting up for differentiating between Microsites and Landing Pages for the following "utag_data" verifications
def profileMC = executionProfile.contains('web-')
def profileLP = executionProfile.contains('lp-')
def utagMC= utagDataText ['site_type'] == 'Microsite'
def utagLP = utagDataText['site_type'] == 'Landing Page'
def isMC = utagDataText['is_landing_page'] == 0
def isLP = utagDataText['is_landing_page'] == 1

// page_type - has been found to be non-standardized between partners, disabling for now
/*
if (utagDataText['page_type'] == 'thankyou') {
	println 'vvvvvvvvvv\n' + '| CASE - page_type:\n' + '| EXPECTED VALUE: thankyou\n' + '| UTAG VALUE: ' + utagDataText['page_type'] + '\n' + '| PASSED\n' + '----------'
} else {
	KeywordUtil.markFailed("\nFAILED CASE AT page_type: \n" +
	"\n| EXPECTED page_type: thankyou"
	"\n| UNEXPECTED VALUE: " + utagDataText['page_type']
	)
}
*/

// partner_name
if (utagDataText['partner_name'] == GlobalVariable.TealiumProfile) {
   println 'vvvvvvvvvv\n' + '| CASE: partner_name\n' + '| EXPECTED TEALIUM PROFILE/PARTNER_NAME: ' + GlobalVariable.TealiumProfile + '\n' + '| UTAG VALUE (CASE SENSITIVE): ' + utagDataText['partner_name'] + '\n' + '| PASSED\n' + '----------'
} else {
   KeywordUtil.markFailed("\nFAILED CASE AT partner_name: " + 
	   "\n| EXPECTED PARTNER_NAME: " + GlobalVariable.TealiumProfile + 
	   "\n| UNEXPECTED VALUE: " + utagDataText['partner_name'])
}

// site_type - checks between microsites and LP
if (profileMC && utagMC) {
   println 'vvvvvvvvvv\n' + '| CASE: site_type\n' + '| EXPECTED VALUE: Microsite\n' + '| UTAG VALUE: ' + utagDataText['site_type'] + '\n' + '| PASSED\n' + '----------'
} else if (profileLP && utagLP) {
   println 'vvvvvvvvvv\n' + '| CASE - site_type: ' + '\n' + '| EXPECTED VALUE: Landing Page' + '\n' + '| VALUE: ' + utagDataText['site_type'] + '\n' + '| PASSED\n' + '----------'
} else {
   KeywordUtil.markFailed("\nFAILED CASE AT SITE_TYPE: \n" + 
	   "\n| UNEXPECTED VALUE: " + utagDataText['site_type']
   )
}

// page_name - this can greatly vary between partners, atm

// is_landing_page - checks between microsites(0) and LP(1)
if (profileMC && utagMC && isMC) {
   println 'vvvvvvvvvv\n' + '| CASE: is_landing_page\n' + '| EXPECTED VALUE: 0\n' + '| UTAG VALUE: ' + utagDataText['is_landing_page'] + '\n' + '| PASSED\n' + '----------'
} else if (profileLP && utagLP && isLP) {
   println 'vvvvvvvvvv\n' + '| CASE - is_landing_page: ' + '\n' + '| EXPECTED VALUE: 1\n' + '| VALUE: ' + utagDataText['is_landing_page'] + '\n' + '| PASSED\n' + '----------'
} else {
   KeywordUtil.markFailed("\nFAILED CASE AT is_landing_page: \n" + 
   "\n| UNEXPECTED VALUE: " + utagDataText['is_landing_page'] + 
   '\n| for a: ' + utagDataText['site_type'] + 
   "\n| FAILED"
   )
}

// program_name - we could possibly create a variation of this test to include everything as well as a form submission, by pulling in one of the RFI Tests - this would dramatically increase the overall time of completion, per page. 

// site_section
if (utagDataText['site_section'] == 'Thank You') {
	println 'vvvvvvvvvv\n' + '| CASE - site_section:\n' + '| EXPECTED VALUE: Thank You\n' + '| UTAG VALUE: ' + utagDataText['site_section'] + '\n' + '| PASSED\n' + '----------'
} else {
	KeywordUtil.markFailed("\nFAILED CASE AT site_section: " +
	"\n| EXPECTED site_section: Thank You" + 
	'\n| UNEXPECTED VALUE: ' + utagDataText['site_section']
	)
}

// page_category - has been found to be non-standardized between partners, disabling for now
/*
if (utagDataText['page_category'] == 'Thank You') {
	println 'vvvvvvvvvv\n' +
    '| CASE - page_category:\n' + 
	'| EXPECTED VALUE: Thank You\n' + 
	'| UTAG VALUE: ' + utagDataText['page_category'] + '\n' + 
	'| PASSED\n' + '----------'
} else {
	KeywordUtil.markFailed("FAILED CASE AT page_category: \n" +
	"\n| EXPECTED page_category: Thank You" +
	"\n| UNEXPECTED VALUE: " + utagDataText['page_category']
	)
}
*/

// site_framework setup - sets expectation for comparison: tux, Drupal or legacy (null/blank)
// this can be cleaned up when it comes to marking as failed
String extractMetaContent(String xpath) {
    String content = null
    // Find the meta element using the given XPath
	WebElement metaElement = null
	try {
		metaElement = DriverFactory.getWebDriver().findElement(By.xpath(xpath))
	} catch (Exception ex) {
		println 'Meta element with XPath ' + xpath + ' not found'
	}
    // Get the value of the "content" attribute and returns a value to compare to the utag object. 
	if (metaElement != null) {
		try {
			content = metaElement.getAttribute("content")
			if (content != null) {
				if (content.contains("tux")) {
					return "tux"
				} else if (content.contains("drupal")) {
					return "drupal"
				} else {
					println 'Meta element with XPath ' + xpath + ' does not contain "tux" or "drupal" in "content" attribute and is likely a Legacy site. Refer to Confluence documentation.'
				}
			} else {
				println 'Meta element with XPath ' + xpath + ' does not have a "content" attribute'
			}
		} catch (Exception ex) {
			println 'Failed to retrieve "content" attribute value from meta element with XPath ' + xpath
		}
	}
	return null
}
String xpath = "/html/head/meta[@name = 'generator']"
String content = extractMetaContent(xpath)
if (content != null) {
    println 'Value of "content" attribute: ' + content
} else {
    println 'Failed to retrieve "content" attribute'
}

// site_framework log - compares the meta tag's content against the utag. 
if (utagDataText['site_framework'] == 'tux' && content == 'tux') {
    println 'vvvvvvvvvv\n' + '| CASE - site_framework:\n' + '| EXPECTED VALUE: ' + content + '\n' + '| UTAG VALUE: ' + utagDataText['site_framework'] + '\n' + '| PASSED\n' + '----------'
} else {
    if (utagDataText['site_framework'] == null && content == 'drupal') {
        println 'vvvvvvvvvv\n' + '| CASE - site_framework:\n' + '| EXPECTED VALUE: ' + content + '\n' + '| UTAG VALUE: ' + utagDataText['site_framework'] + ' (drupal)\n' + '| PASSED\n' + '----------'
    } else {
        if (utagDataText['site_framework'] == 'Legacy' && content == null) {
            println 'vvvvvvvvvv\n' + '| CASE - site_framework:\n' + '| EXPECTED VALUE: ' + 'Legacy' + '\n' + '| UTAG VALUE: ' + utagDataText['site_framework'] + '\n' + '| PASSED\n' + '----------'
        } else {
            KeywordUtil.markFailed("FAILED CASE AT site_framework: \n")
            println  'vvvvvvvvvv\n' + '| CONTENT TYPE: ' + content + '\n' + '| UNEXPECTED VALUE: ' + utagDataText['site_framework']
        }
    }
}

// Delay for viewing initial testing
//WebUI.delay(5)
WebUI.closeBrowser()