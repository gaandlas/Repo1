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
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil

def baseUrl = 'https://' + CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.Url)

WebUI.openBrowser(baseUrl + '/robots.txt')
WebUI.waitForPageLoad(10)

def robotsText = [('User-agent') : null, ('Disallow') : null, ('Allow') : null, ('Sitemap') : null]
TestObject robotsObj = new TestObject()

// Selecting and holding the string on the robots.txt page
robotsObj.setSelectorValue(SelectorMethod.XPATH,"//body/pre")
robotsObj.setSelectorMethod(SelectorMethod.XPATH)
// Splits the string of text in the robots.txt page to be stored into an object
def lines = WebUI.getText(robotsObj).split('\n')
// Iterates through the lines in the robotsObj object, storing the value in the robotsText array
for (def line : lines)
{
	if(line.contains("User-agent") || line.contains("User-Agent"))
	{
		robotsText['User-agent'] = line.split(": ")[1]
	}
	else if(line.contains("Disallow"))
	{
		robotsText['Disallow'] = line.split(": ")[1]
	}
	else if(line.contains("Allow"))
	{
		robotsText['Allow'] = line.split(": ")[1]
	}
	else if(line.contains("Sitemap"))
	{
		robotsText['Sitemap'] = line.split(": ")[1]
	}
}
 println 'robotsText arr: \n' + robotsText

// Expected User-agent: *
if (robotsText['User-agent'] == '*') {
	println "User-agent: " + robotsText['User-agent'] + "\n" + 'Passed' 
} else {
	KeywordUtil.markFailed("Failed case at " + "User-agent: \n")
	println 'expected User-agent: *' + '\n'
	println 'unexpected value: ' + robotsText['User-agent']
}

// Expected Disallow: /wp-admin/
if (robotsText['Disallow'] == '/wp-admin/') {
	println "Disallow: " + robotsText['Disallow'] + "\n" + 'Passed'
} else {
	KeywordUtil.markFailed("Failed case at " + "Disallow")
	println 'expected Disallow: /wp-admin/' + '\n'
	println 'unexpected value: ' + robotsText['Disallow']
}

// Expected Allow: /wp-admin/admin-ajax.php
if (robotsText['Allow'] == '/wp-admin/admin-ajax.php') {
	println "Allow: " + robotsText['Allow'] + "\n" + 'Passed'
} else {
	KeywordUtil.markFailed("Failed case at " + "Allow")
}

// Expected Sitemap: domain/sitemap_index.xml
// Clean up later with switch case
if (robotsText['Sitemap'] == baseUrl + '/sitemap_index.xml') {
	println "Sitemap: " + robotsText['Sitemap'] + "\n" + 'Passed'
} else if (robotsText['Sitemap'] == baseUrl + '/sitemap.xml') {
	println "Sitemap: " + robotsText['Sitemap'] + "\n" + 'Passed'
} else if (baseUrl.contains("landing")) { 
	println 'Landing Page without a sitemap: ' + robotsText['Sitemap']
} else { 
	println 'expected Sitemap: ' + baseUrl + '/sitemap_index.xml' + ' ( or ) ' + baseUrl + '/sitemap.xml'
	println 'unexpected value: ' + robotsText['Sitemap']
	KeywordUtil.markFailed("Failed case at " + "Sitemap")
}



WebUI.delay(5)

WebUI.closeBrowser()
