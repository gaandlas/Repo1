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

def utm_test = 'utm_source=test_source'

def utm_second_test = 'utm_second_source=test_second_source'

WebUI.openBrowser('https://online.campbellsville.edu/?' + utm_test)

def links = WebUI.getAllLinksOnCurrentPage(false, null)

WebUI.navigateToUrl(links.get(0))

assert CustomKeywords.'com.spe.Cookie.getCookieValue'('tlh_qry_string').equals(utm_test)

WebUI.navigateToUrl('https://online.campbellsville.edu/?' + utm_second_test)

assert CustomKeywords.'com.spe.Cookie.getCookieValue'('tlh_qry_string').equals(utm_second_test)

