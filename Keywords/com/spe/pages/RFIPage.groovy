package com.spe.pages;

import com.spe.TestBase;
import com.spe.util.WriteDataToExcel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import internal.GlobalVariable as GlobalVariable

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RFIPage extends TestBase  {

	@FindBy(xpath = "//select[contains(@id,'degreeLevel')]")
	WebElement selectdegreeLevel;

	@FindBy(xpath = "//select[@data-vv-name='program'] | //select[contains(@id,'programs')]")
	WebElement selectprogram;

	@FindBy(xpath = "//input[contains(@id,'firstName')] | //input[contains(@id,'first-name')]")
	WebElement firstname;

	@FindBy(xpath = "//input[contains(@id,'lastName')] | //input[contains(@id,'last-name')]")
	WebElement lastname;

	@FindBy(xpath = "//input[contains(@id,'email')]")
	WebElement email;

	@FindBy(xpath = "//input[contains(@id,'phone')]")
	WebElement phonenumber;

	@FindBy(xpath = "//input[contains(@id,'zip')]")
	WebElement zipcode;

	@FindBy(xpath = "//select[contains(@id,'stateSelect')]")
	WebElement state;

	@FindBy(xpath = "//button[contains(@class,'form-submit-button')] | //button[contains(@class,'button--submit')]")
	WebElement submitbutton;

	@FindBy(xpath = "//input[contains(@id,'sms')]")
	WebElement smsConsent;

	@FindBy(xpath="//label[contains(@for,'sms')]")
	WebElement smsConsentText;

	@FindBy(linkText="Privacy")
	WebElement privacy;

	@FindBy(linkText="Terms")
	WebElement terms;

	@FindBy(xpath = "//select[contains(@id,'highestDegree')]")
	WebElement highestEducationLevel;

	@FindBy(xpath="//input[contains(@id,'confirmCheckbox')] | //input[contains(@id,'qualifier-checkbox')]")
	WebElement confirmEduQualification;

	@FindBy(xpath="//select[contains(@id,'country')]")
	WebElement country;

	@FindBy(xpath="//div[contains(@id,'program_help')]/div[@class='help-inner']")
	WebElement programValidation;

	@FindBy(xpath="//div[contains(@id,'firstName_help')]/div[@class='help-inner']")
	WebElement firstNameValidation;

	@FindBy(xpath="//div[contains(@id,'lastName_help')]/div[@class='help-inner']")
	WebElement lastNameValidation;

	@FindBy(xpath="//div[contains(@id,'email_help')]/div[@class='help-inner']")
	WebElement emailValidation;

	@FindBy(xpath="//div[contains(@id,'phone_help')]/div[@class='help-inner']")
	WebElement phoneValidation;

	@FindBy(xpath="//button[@id=\"consent_prompt_submit\"]")
	WebElement cookie;

	@FindBy(xpath="//button[contains(@class,'next-button')]")
	WebElement nextButton;

	private String getFirstName;
	private String getLastName;
	private String getSelectedProgram;
	private String getSelectedProgramValue;
	private String getEmail;
	private String getPhoneNum;
	private String getSMSConsent;
	private String getState;
	private String getCountry;

	private Map<String, String> LeadDataMap = new HashMap<String, String>();

	public RFIPage(){
		PageFactory.initElements(driver,this);
	}

	public String getPageTitle(){
		return getTitle();
	}

	public void selectdegreelevel(int index){
		selectDropDownList(selectdegreeLevel,index);
	}

	public void selectProgram(int index){
		selectDropDownList(selectprogram,index);
		getSelectedProgram = getDropDownValue(selectprogram);
		getSelectedProgramValue = getDropDownValueAttribute(selectprogram)
	}

	public String getSelectedProgram(){
		return getDropDownValue(selectprogram);
	}
	public String getSelectedProgramValue(){
		return getDropDownValueAttribute(selectprogram);
	}

	public void selectHighestLevelEducation(){
		selectDropDownList(highestEducationLevel,2);
	}

	public void enterFirstName(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String FirstName = prop.getProperty("firstname") + now.format(formatter);
		setTextAs(firstname,FirstName);
	}

	public void enterLastName(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String LastName = prop.getProperty("lastname") + now.format(formatter);
		setTextAs(lastname,LastName);
	}

	public void enterEmailAddress() throws InterruptedException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		LocalDateTime now = LocalDateTime.now();
		String Email = prop.getProperty("firstname") + now.format(formatter)+ randNum.toString() +"@test.com";
		setTextAs(email,Email);
	}

	public void enterEmailAddress(String emailaddress) throws InterruptedException {
		setTextAs(email,emailaddress);
	}

	public void enterPhoneNumber() throws InterruptedException {
		Random rand = new Random();
		int rand_int1 = rand.nextInt(1000);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
		LocalDateTime now = LocalDateTime.now();
		setTextAs(phonenumber,(prop.getProperty("phone")));
	}

	public void selectCountryState(String value){
		selectDropDownList(country,value);
		getCountry = getDropDownValue(country);
	}

	public void selectCountryUS(){
		selectDropDownList(country,"United States of America");
		selectState();
		getCountry = getDropDownValue(country);
	}

	public void selectState(){
		selectDropDownList(state,1);
		getState = getDropDownValue(state);
	}

	public void enterZipCode(){
		setTextAs(zipcode, prop.getProperty("zip"));
	}

	public void selectSMSConcent(){
		selectCheckBox(smsConsent);
		getSMSConsent = checkBoxSelected(smsConsent);
	}

	public void deSelectSMSConcent(){
		deselectCheckBox(smsConsent);
		getSMSConsent = checkBoxSelected(smsConsent);
	}

	public void selectEduQualificationConfirm(){
		selectCheckBox(confirmEduQualification);
	}

	public String getSMSConsent(){
		return getLabelText(smsConsentText);
	}

	public String getPrivacyLink(){
		return getLink(privacy);
	}

	public String getTermsLink(){
		return getLink(terms);
	}

	public void acceptCookie(){
		clickElement(cookie);
	}

	public Boolean checkCookieBannerAvailable(){
		return checkElementVisible(cookie);
	}

	public String getProgramValidation(){
		return getLabelText(programValidation);
	}
	public String getFirstNameValidation(){
		return getLabelText(firstNameValidation);
	}
	public String getLatNameValidation(){
		return getLabelText(lastNameValidation);
	}
	public String getEmailValidation(){
		return getLabelText(emailValidation);
	}
	public String getPhoneNumberValidation(){
		return getLabelText(phoneValidation);
	}
	public String getZipCodeValidation(){
		return getLabelText(zipcode);
	}
	public String getStateValidation(){
		return getLabelText(state);
	}

	@Keyword
	public void fillStudentDetails() throws InterruptedException {
		//check whether the form is a two-step form
		if(checkElementVisible(nextButton)){
			setStudentDetails();
			nextButton.click();
			setStudentDetails();
		}
		else{
			setStudentDetails();
		}
	}

	public void setStudentDetails() throws InterruptedException {

		if(checkElementVisible(highestEducationLevel)){
			selectHighestLevelEducation();
		}

		if(checkElementVisible(firstname)){
			enterFirstName();
			getFirstName = getText(firstname);
			System.out.println(getText(firstname));
		}

		if(checkElementVisible(lastname)){
			enterLastName();
			getLastName = getText(lastname);
		}

		if(checkElementVisible(email)){
			enterEmailAddress();
			getEmail = getText(email);
		}

		if(checkElementVisible(phonenumber)){
			enterPhoneNumber();
			getPhoneNum = getText(phonenumber);
		}

		if(checkElementVisible(country)){
			selectCountryState(prop.getProperty("country"));
		}

		if(checkElementVisible(zipcode)) {
			enterZipCode();
		}

		if(checkElementVisible(confirmEduQualification)){
			selectEduQualificationConfirm();
		}

	}

	public TYPage submitRFI() throws InterruptedException {
		waitForElementClickable(submitbutton);
		clickElement(submitbutton);

		waitForTitle("Thank You");
		return new TYPage();
	}

	public void selectPrograms(int programNumber) throws InterruptedException {
		if(checkElementVisible(selectprogram)){
			selectProgram(programNumber);
		}
	}

	public void selectDegrees(int degreeNumber) throws InterruptedException {
		if(checkElementVisible(selectdegreeLevel)){
			selectdegreelevel(degreeNumber);
		}
	}

	public int returnDropdownSize(selectdegreeLevel) throws InterruptedException {
		return getDropDownSize(selectprogram);
	}

	public void submitRFIAllProgram(String page, String template)throws InterruptedException{
		//String rfiTemplate=dataSet.get("rfiTemplate");
		String rfiTemplate = template
		if(rfiTemplate.equals("multi-levels")) {

			int numDegreeLevels = getDropDownSize(selectdegreeLevel);
			System.out.println(numDegreeLevels);

			for (int x = 1; x < numDegreeLevels; x++) {
				selectdegreelevel(x);
				Thread.sleep(1000);
				int numPrograms = getDropDownSize(selectprogram);
				System.out.println(numPrograms);

				for (int y = 1; y < numPrograms; y++) {
					selectProgram(y);
					Thread.sleep(1000);
					fillStudentDetails();
					Thread.sleep(1000);
					submitRFI();
					Thread.sleep(8000);

					launchSite(page);
				}
			}
		}
		else if (rfiTemplate.equals("onlineprograms")){
			System.out.println(selectprogram);
			int numPrograms = getDropDownSize(selectprogram);
			System.out.println(numPrograms);

			for(int y=1; y<2; y++){
				selectProgram(y);
				fillStudentDetails();
				submitRFI();
				Thread.sleep(6000);

				launchSite(page);
			}
		}
		else{
			fillStudentDetails();
			submitRFI();
		}
	}


	public void submitRFIWithConsent() {
		RFIPage rfiPage;
		LandingPage landingPage;
		TYPage tyPage;
		WriteDataToExcel writeDataToExcel;
		Map<String,String> leadData;

		rfiPage=new RFIPage();
		writeDataToExcel = new WriteDataToExcel();

		//Remove banners
		if(rfiPage.checkCookieBannerAvailable()){
			rfiPage.acceptCookie();
		}

		//Fill the RFI Form
		rfiPage.selectPrograms();
		rfiPage.fillStudentDetails();
		rfiPage.selectSMSConcent();

		//Add lead data into a Hashmap
		leadData = rfiPage.getLeadData();

		WebUiBuiltInKeywords.takeScreenshot(".\\Include\\Screenshots\\Screenshot.png")

		//Submit RFI
		tyPage=rfiPage.submitRFI();

		//Add UUID to the Hashmap
		leadData.put("program_uuid", tyPage.getUUID());
		writeDataToExcel.WriteRFIData(leadData);

		//Validate "Thank You" page URL
		tyPage.verifyURL();
	}

	public Map<String,String> getLeadData(){
		//Add partner abbrevation
		LeadDataMap.put("abbreviation", GlobalVariable.Schoolid)

		//Add the campaign ("Direct", "audience", "Bucket")
		if(utm){
			LeadDataMap.put("campaign", "audience");
		}
		else if(1 == 2){
			LeadDataMap.put("campaign", "Bucket");
		}
		else{
			LeadDataMap.put("campaign", "Direct Traffic");
		}

		//Add the student details
		LeadDataMap.put("programName", getSelectedProgram);
		LeadDataMap.put("country", getCountry);
		LeadDataMap.put("firstname", getFirstName);
		LeadDataMap.put("lastname", getLastName);
		LeadDataMap.put("email", getEmail);
		LeadDataMap.put("phoneNumber", getPhoneNum);
		LeadDataMap.put("sms", getSMSConsent);
		LeadDataMap.put("state", getState);
		LeadDataMap.put("zip", prop.getProperty("zip"));
		LeadDataMap.put("uuid", getSelectedProgramValue);

		System.out.println(LeadDataMap.get("firstname"));

		return LeadDataMap;
	}

}
