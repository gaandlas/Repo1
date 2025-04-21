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

firstName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('Robtest', 'yyMMddHHmmss')

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('Grajtest', 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'('Robtest', 'yyMMddHHmmss')

WebUI.openBrowser(GlobalVariable.BaseUrl + GlobalVariable.BaseUrl)

CustomKeywords.'com.spe.pages.FillFields.FillFieldsCheckSms'(1, firstName, lastName, email, '7275789865', 'United States', 
    'Florida', '12345')

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(5)

WebUI.closeBrowser()

