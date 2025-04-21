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

WebUI.openBrowser('')

WebUI.navigateToUrl(loginPage)

WebUI.waitForPageLoad(5)

WebUI.click(findTestObject('Pages/Azure Login/AzureLoginButton'))

WebUI.waitForElementVisible(findTestObject('Pages/Azure Login/SignInField'), 5)

WebUI.setText(findTestObject('Pages/Azure Login/SignInField'), GlobalVariable.username)

WebUI.waitForElementVisible(findTestObject('Pages/Azure Login/Next Submit Button'), 5)

WebUI.click(findTestObject('Pages/Azure Login/Next Submit Button'))

WebUI.waitForElementVisible(findTestObject('Pages/Azure Login/PasswordField'), 5)

WebUI.setText(findTestObject('Pages/Azure Login/PasswordField'), GlobalVariable.password)

WebUI.waitForElementVisible(findTestObject('Pages/Azure Login/Next Submit Button'), 5)

WebUI.click(findTestObject('Pages/Azure Login/Next Submit Button'))

WebUI.delay(4)

assert WebUI.getText(findTestObject('Pages/Azure Login/BodyText')).contains('Authenticator')

WebUI.closeBrowser()