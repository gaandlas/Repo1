package com.spe.pages;

import com.spe.TestBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.List;

public class LandingPage extends TestBase{

	@FindBy(xpath = "//meta[@name='robots']")
	WebElement robotsmetatag;

	@FindBy(xpath = "//script")
	List<WebElement> scripts;


	public LandingPage(){
		PageFactory.initElements(driver,this);
	}

	public String getURL(){
		return getCurrentURL();
	}

	public String getRobotsTag(){
		return robotsmetatag.getAttribute("content");
	}

	public String getOptimizelySnippet(){
		String optimizely=null;
		for(WebElement element: scripts){
			String scriptText = element.getAttribute("src");
			if(scriptText.contains("https://cdn.optimizely.com/public/523170811/s/")) {
				optimizely = scriptText;
				break;
			}
		}
		return optimizely;
	}

	public void deleteStyCookie(){
		if(driver.manage().getCookieNamed("STYXKEY_jwm_uid") != null){
			driver.manage().deleteCookieNamed("STYXKEY_jwm_uid");
		}
	}

	public JSONObject getTealiumProfile() throws ParseException, IOException {
		return getTealiumData(scripts);
	}
}
