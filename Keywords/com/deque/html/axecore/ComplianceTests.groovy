package com.deque.html.axecore

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

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import com.deque.html.axecore.selenium.AxeBuilder
import com.deque.html.axecore.selenium.AxeReporter
import com.deque.html.axecore.results.Results
import com.deque.html.axecore.results.Rule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonElement

import internal.GlobalVariable

public class ComplianceTests {

	@Keyword(keywordObject = "ADA Compliance")
	public getAllResults() {
		WebDriver driver = DriverFactory.getWebDriver()
		AxeBuilder builder = new AxeBuilder();
		Results results = builder.analyze(driver);
		List<Rule> violations = results.getViolations();

		driver.close()

		return results;
	}

	@Keyword(keywordObject = "ADA Compliance")
	public getResultsWithTags(List<String> tags) {
		WebDriver driver = DriverFactory.getWebDriver()
		AxeBuilder builder = new AxeBuilder();
		builder.withTags(tags);
		Results results = builder.analyze(driver);
		List<Rule> violations = results.getViolations();

		driver.close()

		return results;
	}

	@Keyword(keywordObject = "ADA Compliance")
	public getResultsWithOnlyRules(List<String> rules) {
		WebDriver driver = DriverFactory.getWebDriver()
		AxeBuilder builder = new AxeBuilder();
		builder.withOnlyRules(rules);
		Results results = builder.analyze(driver);
		List<Rule> violations = results.getViolations();

		driver.close()

		return results;
	}

	@Keyword(keywordObject = "ADA Compliance")
	public getViolationsFromResults(Results results) {
		List<Rule> violations = results.getViolations();
		return violations;
	}

	@Keyword(keywordObject = "ADA Compliance")
	public writeResultsToJsonFile(Results results, String reportFileName) {
		JsonParser jsonParser = new JsonParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		AxeReporter.writeResultsToJsonFile(reportFileName, results);
		JsonElement jsonElement = jsonParser.parse(new FileReader(reportFileName + ".json"));
		String prettyJson = gson.toJson(jsonElement);
		AxeReporter.writeResultsToTextFile(reportFileName, prettyJson);
	}
}
