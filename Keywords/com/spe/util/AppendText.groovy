package com.spe.util

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

import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import internal.GlobalVariable


public class AppendText {

	@Keyword
	public String AppendDate(String start, String format) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		String newString = start + now.format(formatter);
		return newString;
	}

	@Keyword
	public String AppendRandomWithDate(String start, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		LocalDateTime now = LocalDateTime.now();
		String newString = start + now.format(formatter)+ randNum.toString();
		return newString;
	}

	@Keyword
	public String AppendDateEmailFormat(String start, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		String newString = start + now.format(formatter) +"@test.com";
		return newString;
	}

	@Keyword
	public String AppendRandomWithDateEmailFormat(String start, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		LocalDateTime now = LocalDateTime.now();
		String newString = start + now.format(formatter)+ randNum.toString() +"@test.com";
		return newString;
	}
}
