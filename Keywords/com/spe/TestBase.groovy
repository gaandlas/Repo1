package com.spe

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class TestBase {
	protected static WebDriver driver;
	protected static Properties prop;
	protected static Boolean utm;
	protected static JSONObject jsonObject;
	protected static HashMap<String, String> dataSet;
	protected String path;
	protected XSSFSheet sheetLP;
	protected XSSFSheet sheetMicro;


	String timeOutMessage="5";

	public TestBase(){
		try {
			path=System.getProperty("user.dir");

			prop = new Properties();
			FileInputStream inputStream = new FileInputStream(".\\Include\\config\\config.properties");
			prop.load(inputStream);

			FileInputStream testdata = new FileInputStream(".\\Include\\config\\TestData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(testdata);
			sheetLP = workbook.getSheet("LP");
			sheetMicro = workbook.getSheet("Microsite");
			Thread.sleep(2000);

			utm=false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void initializeDriver() {
		driver= getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	public void launchSite(String url){
		DriverFactory.getWebDriver().get(url);
	}

	public void launchSite() throws InterruptedException {
		String url=dataSet.get("url");
		String isAffiliate=dataSet.get("isAffiliate");

		if(isAffiliate.equals("true")){
			driver.get(url+prop.getProperty("affiliateString"));
		}
		else if(utm==true){
			driver.get(url+prop.getProperty("utmString"));
		}
		else{
			driver.get(url);
		}
		Thread.sleep(6000);
	}

	protected WebDriver getDriver(){
		return DriverFactory.getWebDriver()
	}

	protected boolean checkElementVisible(WebElement element){
		try{
			element.isDisplayed();
			return true;
		}
		catch (NoSuchElementException e){
			return  false;
		}
	}

	protected String getCurrentURL(){
		return driver.getCurrentUrl();
	}

	protected String getTitle(){
		return driver.getTitle();
	}

	protected String getLabelText(WebElement label){
		return label.getText();
	}

	protected String getLink(WebElement link){
		return link.getAttribute("href");
	}

	protected void selectDropDownList(WebElement dropdownElement, int value){
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(value);
	}

	protected void selectDropDownList(WebElement dropdownElement, String value){
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(value);
	}

	protected int getDropDownSize(WebElement dropdownElement){
		waitForElementClickable(dropdownElement);
		Select dropdown = new Select(dropdownElement);
		int numOfOptions = dropdown.getOptions().size();
		return numOfOptions;
	}

	protected String getDropDownValue(WebElement dropdownElement){
		Select dropdown = new Select(dropdownElement);
		WebElement selectedValue = dropdown.getFirstSelectedOption();
		return selectedValue.getText();
	}
	protected String getDropDownValueAttribute(WebElement dropdownElement){
		Select dropdown = new Select(dropdownElement);
		WebElement selectedValue = dropdown.getFirstSelectedOption();
		return selectedValue.getAttribute("value");
	}
	protected void setTextAs(WebElement element, String text){
		element.sendKeys(text);
	}

	protected String getText(WebElement element){
		return element.getAttribute("value");
	}

	protected void clickElement(WebElement element){
		waitForElementClickable(element);
		element.click();
	}

	protected void selectCheckBox(WebElement checkbox){
		if (!checkbox.isSelected()){
			checkbox.click();
		}
	}

	protected void deselectCheckBox(WebElement checkbox){
		if (checkbox.isSelected()){
			checkbox.click();
		}
	}

	protected String checkBoxSelected(WebElement checkbox){
		if (checkbox.isSelected()){
			return "true";
		}
		else{
			return "false";
		}
	}

	protected void waitForElementClickable(WebElement element){
		WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitForElement(By selector, long timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		} catch (TimeoutException e) {
			throw new IllegalStateException(timeOutMessage);
		}
	}

	protected void waitForTitle(String title) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15);
			wait.until(ExpectedConditions.titleContains(title));
		} catch (TimeoutException e) {
			throw new IllegalStateException(timeOutMessage);
		}
	}

	protected String getSystemDate(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-hh-mm");
		String datetime = ft.format(dNow).toLowerCase();
		return datetime;
	}

	protected JSONObject getTealiumData(List<WebElement> scripts) throws ParseException {
		String scriptText = null;
		for(WebElement element: scripts){
			scriptText = (String) ((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("return arguments[0].innerHTML;", element);
			if(scriptText.contains("utag_data")) {
				break;
			}
		}

		String[] arrOfStr = scriptText.split("=", 2);
		String[] arrofStr2 = arrOfStr[1].split(";",2);
		String utag=arrofStr2[0];

		JSONParser parser=new JSONParser();
		JSONObject tealiumData=(JSONObject) parser.parse(utag);

		return tealiumData;
	}
}
