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

String pageUrl = pageUrl
String utmStringOld = utmStringOld
String utmStringNew = utmStringNew

println "pageUrl: " + pageUrl
println "utmStringOld: " + utmStringOld
println "utmStringNew: " + utmStringNew

//Load browser and navigate to site with old utm string
WebUI.openBrowser(pageUrl + utmStringOld)

//Compare cookie values with utm values removing the ? from the beginning
WebUI.callTestCase(findTestCase('Site Standup/Microsite/RFI/UTM/Compare UTMs with Cookie'), ['utmString':utmStringOld.substring(1)])

//Navigate back to page with updated utm string
WebUI.navigateToUrl(pageUrl + utmStringNew)

//Compare cookie values with utm values removing the ? from the beginning
WebUI.callTestCase(findTestCase('Site Standup/Microsite/RFI/UTM/Compare UTMs with Cookie'), ['utmString':utmStringNew.substring(1)])

//Navigate backto page with no utm string
WebUI.navigateToUrl(pageUrl)

//Compare cookie values same as previous values removing the ? from the beginning
WebUI.callTestCase(findTestCase('Site Standup/Microsite/RFI/UTM/Compare UTMs with Cookie'), ['utmString':''])

WebUI.closeBrowser()