package com.spe

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint





import com.kms.katalon.core.annotation.Keyword












import org.openqa.selenium.WebDriver



import com.kms.katalon.core.webui.driver.DriverFactory



public class Cookie {

	@Keyword
	public String getCookieValue(String cookieName) {
		WebDriver driver = DriverFactory.getWebDriver()
		return driver.manage().getCookieNamed(cookieName).getValue()
	}

	@Keyword
	public void addCookieValue() {
		WebDriver driver = DriverFactory.getWebDriver()
		println("cookie tlh: " + driver.manage().getCookieNamed("tlh_referrer").getValue())
		//driver.manage().deleteCookieNamed("tlh_referrer")
		try {
			driver.manage().addCookie(new Cookie("tlh_referrer", "google.com"))
		} catch(Exception e) {
			println("exception "+ e)
		}
		println("tlh cookie: "+ driver.manage().getCookieNamed("tlh_referrer").getValue())

	}


	@Keyword
	public void deleteCookie(String cookieName) {
		WebDriver driver = DriverFactory.getWebDriver()
		driver.manage().deleteCookieNamed(cookieName)
	}

	@Keyword
	public Set<Cookie> getAllCookies() {
		WebDriver driver = DriverFactory.getWebDriver()
		return driver.manage().getCookies()
	}

	@Keyword
	public boolean isCookiePresent(String cookieName) {
		WebDriver driver = DriverFactory.getWebDriver()
		return !driver.manage().getCookieNamed(cookieName).is(null)
	}
}
