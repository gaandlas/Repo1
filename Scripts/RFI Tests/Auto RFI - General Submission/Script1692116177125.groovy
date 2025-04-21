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
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil

firstName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('JerelCrespoTest', '-yyMMddHHmmss')

lastName = CustomKeywords.'com.spe.util.AppendText.AppendDate'('Katalon-AutoRFI', '-yyMMddHHmmss')

email = CustomKeywords.'com.spe.util.AppendText.AppendDateEmailFormat'('JerelCrespo-KatalonTest', '-yyMMddHHmmss')

//Save information to write to csv file later
def leadInfo = [('firstName') : firstName, ('lastName') : lastName, ('email') : email, ('uuid') : '', ('highestDegree') : ''
    , ('country') : '', ('state') : '', ('utms') : '', ('phone') : '', ('zip') : '', ('degreeLevel') : '', ('sms') : '', ('url'): '']

if (GlobalVariable.Utm == null) 
    GlobalVariable.Utm = ''

if(GlobalVariable.url == null)
	rfiUrl = url
	else
		rfiUrl = GlobalVariable.url

WebUI.openBrowser(rfiUrl + utm)
// JS to select the indexed values required for affiliate forms
// Flexibly stores the index of the selection, since not all Country dropdowns are the same size on affiliate pages, apparently
String jsSelectIndex = """
        var select = arguments[0];
        var desiredOptionLabel = arguments[1];
        for (var i = 0; i < select.options.length; i++) {
            if (select.options[i].text === desiredOptionLabel) {
                select.selectedIndex = i;
                var event = new Event('change', { bubbles: true });
                select.dispatchEvent(event);
                break;
            }
        }
    """
// Can refactor to accept variable input from a spreadsheet to resize the window (i.e. view: desktop, mobile, headless) 
//WebUI.setViewPortSize(337, 667)

// Deletes sticky footers which prevents certain clicks and submission during mobile and headless RFI
if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StickyCtaFooter'), FailureHandling.OPTIONAL)) {
	CustomKeywords.'com.spe.pages.CustomJS.DeleteElement'('tux-c-sticky-cta')
}
if(WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/StickyCtaFooter'), FailureHandling.OPTIONAL)) {
	CustomKeywords.'com.spe.pages.CustomJS.DeleteElement'('osano-cm-window')
}

// Select all dropdowns if available
// Highest Level of Education
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/HighestDegree'), FailureHandling.OPTIONAL)) {
	String desiredOptionLabel = "Bachelor's Degree" // Affiliate necessity - this value is a safe bet for Sparkroom programs
	TestObject dropdownObject = findTestObject('Pages/RFIPage/HighestDegree')
	// Runs JS to find the desired option, stores the index in the list and selects the option
	WebElement dropdownElement = WebUI.findWebElement(dropdownObject)
	WebUI.executeJavaScript(jsSelectIndex, Arrays.asList(dropdownElement, desiredOptionLabel))
	
	(leadInfo['highestDegree']) = WebUI.getAttribute(dropdownObject, 'value')
}
// Program
String utagExists = WebUI.executeJavaScript('return utag_data.program_uuid;', null, FailureHandling.OPTIONAL)	
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Program'), FailureHandling.OPTIONAL)) {
    WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)
    leadInfo['uuid'] = WebUI.getAttribute(findTestObject('Pages/RFIPage/Program'), 'value')
} else if (leadInfo['uuid'] == '' || leadInfo['uuid'] == null) {
    if (utagExists){
        leadInfo['uuid'] = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
    } else {
        leadInfo['uuid'] = WebUI.executeJavaScript('return defaultProgram', null, FailureHandling.OPTIONAL)
    }
}

// Country
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Country'), FailureHandling.OPTIONAL)) {
	String desiredOptionLabel = "United States of America" // Affiliate necessity - this value is required by Sparkroom
	TestObject dropdownObject = findTestObject('Pages/RFIPage/Country')
	// Runs JS to find the desired option, stores the index in the list and selects the option
	WebElement dropdownElement = WebUI.findWebElement(dropdownObject)
	WebUI.executeJavaScript(jsSelectIndex, Arrays.asList(dropdownElement, desiredOptionLabel))
	
	(leadInfo['country']) = WebUI.getAttribute(dropdownObject, 'value')
}
// State
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/State'), FailureHandling.OPTIONAL)) {
    WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/State'), 2)

    (leadInfo['state']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/State'), 'value')
}

//Fill all text fields if on page
// First Name
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/FirstName'), FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Pages/RFIPage/FirstName'), firstName)
}
// Last Name
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/LastName'), FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Pages/RFIPage/LastName'), lastName)
}
// Email
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Email'), FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Pages/RFIPage/Email'), email)
}
// Phone Number
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Phone'), FailureHandling.OPTIONAL)) {
    WebUI.sendKeys(findTestObject('Pages/RFIPage/Phone'), '3214201001')

    (leadInfo['phone']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Phone'), 'value')
}
// Zipcode
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Zip'), FailureHandling.OPTIONAL)) {
    WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '12345')

    (leadInfo['zip']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Zip'), 'value')
}

// Looks for next button, in the case of a multi-step form 
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/NextButton'), FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Pages/RFIPage/NextButton'))
    WebUI.delay(1)
    //Select all dropdowns if available
    // Highest Level of Education
//	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/HighestDegree'), FailureHandling.OPTIONAL)) {
//	    String desiredOptionLabel = "Bachelor's Degree"
//	    TestObject dropdownObject = findTestObject('Pages/RFIPage/HighestDegree')
//	    try {
//	        selectOptionByLabel(dropdownObject, desiredOptionLabel)
//	    } catch (Exception e) {
//	        int fallbackIndex = 2
//	        WebUI.selectOptionByIndex(dropdownObject, fallbackIndex)
//	    }
//	
//	    (leadInfo['highestDegree']) = WebUI.getAttribute(dropdownObject, 'value')
//	}
	
	// Program
	println "utagVerify: " + utagExists
	println "leadInfo: " + leadInfo['uuid']
		
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Program'), FailureHandling.OPTIONAL)) {
		WebUI.selectOptionByIndex(findTestObject('Pages/RFIPage/Program'), 1)
		leadInfo['uuid'] = WebUI.getAttribute(findTestObject('Pages/RFIPage/Program'), 'value')
		println "program dropdown exists. program_uuid: " + leadInfo['uuid']
	}
	if ((utagExists) && (((leadInfo['uuid']) == '') || ((leadInfo['uuid']) == null))) {
		leadInfo['uuid'] = WebUI.executeJavaScript('return utag_data.program_uuid', null, FailureHandling.OPTIONAL)
		println "utag exists. program_uuid: " + leadInfo['uuid']
	} else if ((!utagExists) && (((leadInfo['uuid']) == '') || ((leadInfo['uuid']) == null))) {
		leadInfo['uuid'] = WebUI.executeJavaScript('return defaultProgram', null, FailureHandling.OPTIONAL)
		println "defaultProgram configured. value: " + leadInfo['uuid']
	} else {
		KeywordUtil.markFailed('Failed at adding program UUID')
	}
	
    // Country
//	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Country'), FailureHandling.OPTIONAL)) {
//		String desiredOptionLabel = "United States of America"
//		TestObject dropdownObject = findTestObject('Pages/RFIPage/Country')
//		try {
//			selectOptionByLabel(dropdownObject, desiredOptionLabel)
//		} catch (Exception e) {
//			int fallbackIndex = 238 // The index of the USA: 238
//			WebUI.selectOptionByIndex(dropdownObject, fallbackIndex)
//		}
//	
//		(leadInfo['country']) = WebUI.getAttribute(dropdownObject, 'value')
//	}
    // Fill all text fields if on page
	// First Name
    if (WebUI.verifyElementVisible(findTestObject('Chatbot/FirstName'), FailureHandling.OPTIONAL)) {
        WebUI.setText(findTestObject('Chatbot/FirstName'), firstName)
    }
    // Last Name
    if (WebUI.verifyElementVisible(findTestObject('Chatbot/LastName'), FailureHandling.OPTIONAL)) {
        WebUI.setText(findTestObject('Chatbot/LastName'), lastName)
    }
	// Email
	if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Email'), FailureHandling.OPTIONAL)) {
		WebUI.setText(findTestObject('Pages/RFIPage/Email'), email)
	}
    // Phone Number
    if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Phone'), FailureHandling.OPTIONAL)) {
        WebUI.sendKeys(findTestObject('Pages/RFIPage/Phone'), '3214201001')

        (leadInfo['phone']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Phone'), 'value')
    }
    // Zipcode
    if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/Zip'), FailureHandling.OPTIONAL)) {
        WebUI.setText(findTestObject('Pages/RFIPage/Zip'), '12345')

        (leadInfo['zip']) = WebUI.getAttribute(findTestObject('Pages/RFIPage/Zip'), 'value')
    }
}

// SMS Checkbox
if (WebUI.verifyElementPresent(findTestObject('Pages/RFIPage/SMS'), 1, FailureHandling.OPTIONAL)) {
    if (WebUI.verifyElementChecked(findTestObject('Pages/RFIPage/SMS'), 1, FailureHandling.OPTIONAL)) {
        (leadInfo['sms']) = 'true'
    } else {
        (leadInfo['sms']) = 'false'
    }
}

// Form Qualifier Check - may need tweaking for non-standardized forms
if (WebUI.verifyElementVisible(findTestObject('Pages/RFIPage/FormQualifier'), FailureHandling.OPTIONAL)) {
    WebUI.check(findTestObject('Pages/RFIPage/FormQualifier'))
}

// Collecting final portion to add to leadInfo report before submission
leadInfo['url'] = WebUI.getUrl()
CustomKeywords.'com.spe.util.WriteDataToCsv.WriteRow'(leadInfo)

// Lead Submission & delay, to wait for it to process and redirect to the thank you page
WebUI.click(findTestObject('Pages/RFIPage/SubmitButton'))
WebUI.delay(1)

WebUI.closeBrowser()