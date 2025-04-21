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

firstName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('SwarnaTest', '-yyMMddHHmmss')

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('GTest', '-yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'('JerelCrespo-FreyaTest', '-yyMMddHHmmss')

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('highestDegree') : ''
	, ('country') : '', ('state') : '', ('utms') : '', ('phone') : '', ('zip') : '', ('degreeLevel') : '', ('sms') : '']

if (GlobalVariable.Utm == null)
	GlobalVariable.Utm = ''

if(GlobalVariable.Url == null)
	rfiUrl = RfiUrl
	else
		rfiUrl = GlobalVariable.Url

WebUI.openBrowser('')

	WebUI.navigateToUrl('https://info.uwa.edu/campus-degree-form/')

WebUI.setViewPortSize(1920, 1080)

//Select all dropdowns if available


if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Country'), FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Country'), 2)

	(leadInfo['country']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Country'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/State'), FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/State'), 2)

	(leadInfo['state']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/State'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Status'), FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Status'), 2)
	
	(leadInfo['status']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Status'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Major'), FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Major'), 2)
	
	(leadInfo['major']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Major'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StartTerm'), FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/StartTerm'), 2)
	
	(leadInfo['startterm']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/StartTerm'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StartYear'), FailureHandling.OPTIONAL)) {
	WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/StartYear'), 2)
	
	(leadInfo['startyear']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/StartYear'), 'value')
}

//Fill all text fields if on page
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/FirstName'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/FirstName'), firstName)
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/LastName'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/LastName'), lastName)
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Email'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/Email'), email)
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Phone'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/Phone'), '7274601234')

	(leadInfo['phone']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Phone'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Zip'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '12345')

	(leadInfo['zip']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Zip'), 'value')
}

// City code 
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/City'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/City'), 'MT')

	(leadInfo['City']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/City'), 'value')
}


if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Street'), FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Pages/RFIPage/Street'), '123 Ev')

	(leadInfo['Street']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Street'), 'value')
}


	
WebUI.delay(5)

	// High school graduation year
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Graduation'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Pages/RFIPage/Graduation'), '2022')
	
		(leadInfo['Graduation']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Graduation'), 'value')
	}



if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/NextButton'), FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Pages/RFIPage/NextButton'))

	WebUI.delay(1)

	//Select all dropdowns if available
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/DegreeLevel'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/DegreeLevel'), 2)

		(leadInfo['degreeLevel']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/DegreeLevel'), 'value')
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Program'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)

		(leadInfo['uuid']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Program'), 'value')
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/HighestDegree'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/HighestDegree'), 2)

		(leadInfo['highestDegree']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/HighestDegree'), 'value')
	}
	
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Country'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Country'), 2)

		(leadInfo['country']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Country'), 'value')
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/State'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/State'), 2)

		(leadInfo['state']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/State'), 'value')
	}
	
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Anticipated'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Anticipated'), 2)

		(leadInfo['Anticipated']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Anticipated'), 'value')
	}
	
	//Fill all text fields if on page
	if (WebUI.verifyElementVisible(findTestObject('Chatbot/FirstName'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Chatbot/FirstName'), firstName)
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Chatbot/LastName'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Chatbot/LastName'), lastName)
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Chatbot/Email'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Chatbot/Email'), email)
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Phone'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Pages/RFIPage/Phone'), '7274601234')

		(leadInfo['phone']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Phone'), 'value')
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Graduation'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Graduation'), 2022)

		(leadInfo['Graduation']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Graduation'), 'value')
	}
	
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Street'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Pages/RFIPage/Street'), '123 Ev')
	
		(leadInfo['Street']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Street'), 'value')
	}
	
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Zip'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '12345')

		(leadInfo['zip']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Zip'), 'value')
	}
}



if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/SMS'), 1, FailureHandling.OPTIONAL)) {
	if (WebUI.verifyElementChecked(findTestObject('Pages/RFIPage/SMS'), 1, FailureHandling.OPTIONAL)) {
		(leadInfo['sms']) = 'true'
	} else {
		(leadInfo['sms']) = 'false'
	}
}

if (((leadInfo['uuid']) == '') || ((leadInfo['uuid']) == null)) {
	(leadInfo['uuid']) = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
}




CustomKeywords.'com.spe.util.WriteDataToCsv.WriteRow'(leadInfo)

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))
WebUI.delay(5)



