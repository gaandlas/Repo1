import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


KeywordLogger log = new KeywordLogger()

firstName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('JerelCrespoTest', '-yyMMddHHmmss')

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('Katalon-AutoRFI', '-yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'('JerelCrespo-KatalonTest', '-yyMMddHHmmss')

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('highestDegree') : ''
    , ('country') : '', ('state') : '', ('utms') : '', ('phone') : '', ('zip') : '', ('degreeLevel') : '', ('sms') : '', ('url'): '']


WebUI.navigateToUrl(rfiUrl +utm)

WebUI.setViewPortSize(1920, 1080)

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

//Date :9/12/22 -UWF Qualifier
//if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/FormItemQ'), FailureHandling.OPTIONAL)) {
       
	 // WebUI.selectOptionByLabel(findTestObject('Pages/RFIPage/FormItemQ'), 'Yes',false)
	// WebUI.selectOptionByLabel(findTestObject('Pages/RFIPage/FormItemQ'), 'No',false)
 //}

leadInfo['url'] = WebUI.getUrl()

CustomKeywords.'com.spe.util.WriteDataToCsv.WriteRow'(leadInfo)

log.logInfo(leadInfo.toString())

WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))