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

firstName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('JerelCrespoTest', 'yyMMddHHmmss')

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('AutoTest-', 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'('jerelcrespo-automation-', 'yyMMddHHmmss')

//WebUI.openBrowser('https://landing.online.carlow.edu/brand/?utm_source=audience&utm_medium=banner&utm_campaign=QA&uadgroup=uadgrouptest&uAdCampgn=uadcampgntest&utm_term=urefkeywordtest&utm_content=urefmediatypetest&gclid=gclidtest&event=eventTest0&promotion=promoTest1&partnership=partnerTest2&ae=lb1&wbraid=wtest1&gbraid=gtest2')
WebUI.openBrowser(GlobalVariable.BaseUrl + GlobalVariable.BaseUrl)

WebUI.maximizeWindow()

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Program'), FailureHandling.OPTIONAL)) {
    WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 2)
}

WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)

WebUI.setText(findTestObject('Chatbot/LastName'), lastName)

WebUI.setText(findTestObject('Chatbot/FirstName'), firstName)

WebUI.setText(findTestObject('Chatbot/Email'), email)

WebUI.setText(findTestObject('Pages/RFIPage/Phone'), '3214201001')

WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '32822')

WebUI.check(findTestObject('Pages/RFIPage/SMS'))

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(5)

WebUI.closeBrowser()

