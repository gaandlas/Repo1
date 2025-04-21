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


firstName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('JerelCrespoTest', '-yyMMddHHmmss')

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('Katalon-AutoRFI', '-yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'('JerelCrespo-KatalonTest', '-yyMMddHHmmss')

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('highestDegree') : ''
    , ('country') : '', ('state') : '', ('utms') : '', ('phone') : '', ('zip') : '', ('degreeLevel') : '', ('sms') : '', ('url'): '']

if (GlobalVariable.Utm == null) 
    GlobalVariable.Utm = ''
	

if(GlobalVariable.RfiUrl == null)
	rfiUrl = RfiUrl
	else
		rfiUrl = GlobalVariable.Url

WebUI.openBrowser(rfiUrl +utm) 

WebUI.setViewPortSize(337, 667)

if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StickyCtaFooter'), FailureHandling.OPTIONAL)) {
	CustomKeywords.'com.spe.pages.CustomJS.DeleteElement'('tux-c-sticky-cta')
}
if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StickyCtaFooter'), FailureHandling.OPTIONAL)) {
	CustomKeywords.'com.spe.pages.CustomJS.DeleteElement'('osano-cm-window')
}

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
    WebUI.sendKeys(findTestObject('Pages/RFIPage/Phone'), '7274601234')

    (leadInfo['phone']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Phone'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Zip'), FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '12345')

    (leadInfo['zip']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Zip'), 'value')
}

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/NextButton'), FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Pages/RFIPage/NextButton'))

    WebUI.delay(1)

    //Select all dropdowns if available
    //102-106
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
        WebUI.sendKeys(findTestObject('Pages/RFIPage/Phone'), '7274601234')

        (leadInfo['phone']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Phone'), 'value')
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

if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/FormQualifier'), FailureHandling.OPTIONAL)) {
    WebUI.check(findTestObject('Pages/RFIPage/FormQualifier'))
}



//Qualifier Question uwf
//if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/QualifierQuestion'), FailureHandling.OPTIONAL)) {

 //WebUI.selectOptionByLabel(findTestObject('Pages/RFIPage/QualifierQuestion'), 'Yes',false)
// WebUI.selectOptionByLabel(findTestObject('Pages/RFIPage/QualifierQuestion'), 'No',false)
//}


leadInfo['url'] = WebUI.getUrl()

CustomKeywords.'com.spe.util.WriteDataToCsv.WriteRow'(leadInfo)

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))

WebUI.delay(5)

WebUI.closeBrowser()