import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.firefox.FirefoxOptions
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

FirefoxOptions firefoxOptions = new FirefoxOptions().addPreference("network.cookie.cookieBehavior", 2)

RunConfiguration.setWebDriverPreferencesProperty('moz:firefoxOptions', firefoxOptions)

WebUI.openBrowser('')
WebUI.deleteAllCookies()

WebUI.navigateToUrl(landingPageRfi)

assert WebUI.verifyElementVisible(findTestObject('Object Repository/Pages/RFIPage/Program'))
assert WebUI.getNumberOfTotalOption(findTestObject('Object Repository/Pages/RFIPage/Program')) > 1