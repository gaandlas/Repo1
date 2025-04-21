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
import com.spe.TestBase as TestBase
import com.spe.dataproviders.DataProviders as DataProviders
import com.spe.pages.LandingPage as LandingPage
import com.spe.pages.Microsite as Microsite
import com.spe.pages.RFIPage as RFIPage
import com.spe.pages.TYPage as TYPage
import com.spe.util.WriteDataToExcel as WriteDataToExcel
import io.qameta.allure.*
import org.json.simple.parser.ParseException as ParseException
import org.openqa.selenium.OutputType as OutputType
import java.io.ByteArrayInputStream as ByteArrayInputStream
import java.io.IOException as IOException
import java.util.HashMap as HashMap
import java.util.Map as Map

WebUI.openBrowser(GlobalVariable.BaseUrl + GlobalVariable.BaseUrl)

WebUI.waitForPageLoad(10)

CustomKeywords.'com.spe.pages.RFIPage.selectPrograms'(1)

CustomKeywords.'com.spe.pages.RFIPage.fillStudentDetails'()

Map<String, String> leadData = CustomKeywords.'com.spe.util.GetLeadData.getLeadData'()

CustomKeywords.'com.spe.util.WriteDataToCsv.WriteRow'(leadData)