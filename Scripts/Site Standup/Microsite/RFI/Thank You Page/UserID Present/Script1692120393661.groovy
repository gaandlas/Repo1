import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

KeywordLogger log = new KeywordLogger()

WebUI.delay(5)

WebUI.waitForPageLoad(5)

if(CustomKeywords.'com.spe.Cookie.isCookiePresent'("STYXKEY_jwm_uid")) {
	log.logInfo(CustomKeywords.'com.spe.Cookie.getCookieValue'("STYXKEY_jwm_uid"))
}
else {
	KeywordUtil.markFailed("Could not find cookie 'STYXKEY_jwm_uid'")
}