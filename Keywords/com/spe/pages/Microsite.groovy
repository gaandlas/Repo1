package com.spe.pages;

import com.spe.TestBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class Microsite extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Request Info')]| //a[contains(text(),'Get Started')]")
	WebElement rfiButton;

	@FindBy(xpath = "//a[contains(text(),'Programs')] | //a[contains(text(),'Online Degrees')]")
	WebElement programList;

	@FindBy(xpath = "//a[contains(@class,'card')]")
	WebElement program;

	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement search;

	@FindBy(xpath = "//input[contains(@id,'search')]")
	WebElement searchSubmit;

	@FindBy(xpath = "//a[contains(text(),'Admission')]")
	WebElement content;

	@FindBy(xpath = "//script")
	List<WebElement> scripts;

	public Microsite(){
		PageFactory.initElements(driver,this);
	}

	public void navigateToRFIPage(){
		clickElement(rfiButton);
	}

	public void navigateToProgramList(){
		clickElement(programList);
	}

	public void navigateToProgramPage(){
		clickElement(program);
	}

	public void navigateToAdmissionPage(){
		clickElement(content);
	}

	public void enterSearchText(){
		setTextAs(search,"test");
	}

	public void clickSearchButton(){
		clickElement(searchSubmit);
	}



	public JSONObject getTealiumProfile() throws ParseException, IOException {
		return getTealiumData(scripts);
	}

	public void deleteStyCookie(){
		if(driver.manage().getCookieNamed("STYXKEY_jwm_uid") != null){
			driver.manage().deleteCookieNamed("STYXKEY_jwm_uid");
		}
	}
}
