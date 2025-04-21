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

//WebUI.openBrowser('')

//WebUI.navigateToUrl('https://online.calvin.edu/')

def baseUrl = CustomKeywords.'com.spe.Url.getBaseUrl'(GlobalVariable.Url)

WebUI.openBrowser('')

WebUI.setViewPortSize(1920, 1080)
WebUI.navigateToUrl(baseUrl)

WebUI.maximizeWindow()

//TestData urls = findTestData('Data Files/RFI URLs/Chatbot')


WebUI.setViewPortSize(1820, 1000)


WebUI.click(findTestObject('Chatbot/NewChat/span_Chat Now'))

WebUI.setText(findTestObject('Chatbot/NewChat/FirstName'), 'swarna')

WebUI.setText(findTestObject('Chatbot/NewChat/LastName'), 'gandla')

WebUI.setText(findTestObject('Chatbot/NewChat/Email'), 'swarna.gandla@test.com')

WebUI.click(findTestObject('Chatbot/NewChat/StartChat'))

WebUI.delay(10)

WebUI.click(findTestObject('Chatbot/NewChat/button_Close'))

WebUI.delay(10)


//WebUI.waitForElementPresent(findTestObject('Object Repository/Chatbot/VerifyChatStarted'), 10)

WebUI.click(findTestObject('Chatbot/NewChat/span_Confirm End Chat'))

WebUI.delay(10)
WebUI.click(findTestObject('Chatbot/NewChat/CloseChat'))
WebUI.delay(10)
WebUI.closeBrowser()

//WebUI.takeAreaScreenshot(null)