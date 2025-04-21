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

def cookieName1 = "tlh_qry_string"
def cookieName2 = "rfi_query_string"
def cookieName3 = "freya_qry_string"

if (CustomKeywords.'com.spe.Cookie.isCookiePresent'("tlh_qry_string") || CustomKeywords.'com.spe.Cookie.isCookiePresent'("rfi_query_string") || CustomKeywords.'com.spe.Cookie.isCookiePresent'("freya_qry_string")) {
	// Determine the cookie name dynamically
    def dynamicCookieName
    if (CustomKeywords.'com.spe.Cookie.isCookiePresent'(cookieName1)) {
        dynamicCookieName = cookieName1
    } else if (CustomKeywords.'com.spe.Cookie.isCookiePresent'(cookieName2)) {
        dynamicCookieName = cookieName2
    } else {
        dynamicCookieName = cookieName3
    }
	def utmCookieValues = CustomKeywords.'com.spe.Cookie.getCookieValue'(dynamicCookieName)
	
	mapString(utmString)
	mapCookies(utmCookieValues)
	println("string:" + stringMap)
	println("cookie: "+ cookieMap)
	assert stringMap == cookieMap
	cookieMap.clear()
	stringMap.clear()
} else {
	KeywordUtil.markFailed("No UTM Query String cookie found.")
}

void mapCookies(String utmMapValues) {
	String[] params = utmMapValues.split("&");
	println("params:" + params)
	for (String param : params) {
		cookieMap.each { key, val ->
			if(param.split("=")[0].toLowerCase().trim().contains(key.toString().toLowerCase().trim())) {
				cookieMap.putAt(key, param.split("=")[1])
			}
		}
	}
}

void mapString(String utmMapValues) {
	String[] params = utmMapValues.split("&");
	println("params:" + params)
	for (String param : params) {
		stringMap.each { key, val ->
			if(param.split("=")[0].toLowerCase().trim().contains(key.toString().toLowerCase().trim())) {
				stringMap.putAt(key, param.split("=")[1])
			}
		}
	}
}