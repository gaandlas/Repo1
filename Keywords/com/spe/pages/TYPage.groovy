package com.spe.pages;

import com.spe.TestBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class TYPage extends TestBase {

	@FindBy(xpath = "//script")
	List<WebElement> scripts;

	public TYPage(){
		PageFactory.initElements(driver,this);
	}

	public String getTitle(){
		return driver.getTitle();
	}

	public String getURL(){
		return driver.getCurrentUrl();
	}

	public void getStyCookie(){
		System.out.println("STY..Cookie: " + driver.manage().getCookieNamed("STYXKEY_jwm_uid"));
		//driver.manage().getCookieNamed("STYXKEY_jwm_uid");
	}

	public JSONObject getTYTealiumProfile() throws ParseException {
		return getTealiumData(scripts);
	}

	public void getTealiumValues() throws ParseException {
		JSONObject tealium=getTealiumData(scripts);
		System.out.println(tealium);
	}

	public String getUUID() throws ParseException {
		JSONObject tealium = getTealiumData(scripts);
		return (String) tealium.get("program_uuid");
	}

	public void verifyURL(){
		assert(getURL().contains("/thank-you"));
	}
}
