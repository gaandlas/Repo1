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
import com.kms.katalon.core.configuration.RunConfiguration as RC

firstName = GlobalVariable.firstName

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'(GlobalVariable.lastName, 'yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'(GlobalVariable.lastName, 'yyMMddHHmmss')

//Get execution profile.
def executionProfile = RC.getExecutionProfile()

if (executionProfile != "default") {
	url = GlobalVariable.url
	partnerName = GlobalVariable.partnerName
	tealiumProfile = GlobalVariable.TealiumProfile
}

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email]

//Navigate to the site
WebUI.callTestCase(findTestCase('Test Cases/01.CommonTestSteps/LaunchSite'), [('url') : url], FailureHandling.STOP_ON_FAILURE)

//Click on the Chat icon
WebUI.click(findTestObject('Chatbot/ChatButton'))

//Wait for chat window opens
WebUI.waitForElementPresent(findTestObject('Chatbot/StartChat'), 10)

//For NMHU, email & message has to be entered to start the chat
if(tealiumProfile == 'nmhu') {
	//Fill the details & submit
	WebUI.setText(findTestObject('Chatbot/Email'), email)
	
	WebUI.setText(findTestObject('Chatbot/Message'), 'This is a test')
	
	WebUI.click(findTestObject('Chatbot/Submit'))
	
	//Validate the message
	caseSubmitted= WebUI.getText(findTestObject('Chatbot/CaseSubmitted'))
	assert caseSubmitted.contains("Case submitted")
	
	//Close the chat window
	WebUI.click(findTestObject('Chatbot/Done'))
	
	WebUI.closeBrowser()
	
	return
}

//For other sites, fill firstname, lastname & email & Start Chat
WebUI.setText(findTestObject('Chatbot/FirstName'), firstName)

WebUI.setText(findTestObject('Chatbot/LastName'), lastName)

WebUI.setText(findTestObject('Chatbot/Email'), email)

WebUI.click(findTestObject('Chatbot/StartChat'))

//Wait for Chat starts
WebUI.waitForElementPresent(findTestObject('Chatbot/VerifyChatStarted'), 20)

//Validate
lable_chatStarted= WebUI.getText(findTestObject('Chatbot/ChatbotIntro'))

if(tealiumProfile == 'ltu' || tealiumProfile == 'unisc' || tealiumProfile == 'uob') {
	assert lable_chatStarted.contains("Hey "+ firstName+ "! I\'m "+ partnerName +"\'s online chat assistant.")
}else {
	assert lable_chatStarted.contains("Hey "+ firstName+ "! I\'m "+ partnerName +"\'s virtual chat assistant.")
}

WebUI.click(findTestObject('Chatbot/CloseButton'))

WebUI.click(findTestObject('Chatbot/ConfirmEndChat'))

WebUI.click(findTestObject('Chatbot/CloseChat'))

WebUI.closeBrowser()
