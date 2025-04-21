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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.webui.driver.DriverFactory

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName
import com.spe.Cookie
import com.spe.salesforce.LeadObject

import internal.GlobalVariable
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

public class WriteDataToCsv {

	String country = "";
	String email = "";
	String firstName = "";
	String lastName = "";
	String phone = "";
	String programName = "";
	String sms = "";
	String uuid = "";
	String zip = "";
	String state = "";
	Map utmMap;

	@Keyword
	public void WriteRow(def leadInfo) throws IOException {
		GetValues()
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime now = LocalDateTime.now();
		String fileLoc = "./Data Files/Leads/Test Leads" + dtf.format(now) + ".csv"
		File f = new File(fileLoc);
		FileWriter fw = new FileWriter(fileLoc, true);
		BufferedWriter bw = new BufferedWriter(fw);

		if (leadInfo['zip'] == "12345"){
			if(leadInfo['state'] == "") {
				leadInfo['state'] ="NY"
			}
			if(leadInfo['country'] == "")
				leadInfo['country'] = "United States"
		}

		//Create row
		String headers = "COUNTRY,EMAIL,FIRSTNAME,LASTNAME,PHONE,PROGRAM,OKTOTEXT,STATE,AE,UTM_CAMPAIGN,UTM_CONTENT,EVENT,GBRAID,GCLID,UTM_MEDIUM,PARTNERSHIP,PROMOTION,UTM_SOURCE,UTM_TERM, UADCAMPGN, UADGROUP, WBRAID,ZIP,URL"
		String record = leadInfo['country'] + "," + leadInfo['email'] + "," + leadInfo['firstName'] + "," + leadInfo['lastName'] + "," +
				leadInfo['phone'] + "," + leadInfo['uuid'] + "," +
				leadInfo['sms'] + "," + leadInfo['state'] + "," + utmMap.get("ae") + "," + utmMap.get("utm_campaign") + "," + utmMap.get("utm_content") + "," +
				utmMap.get("event") + "," + utmMap.get("gbraid") + "," + utmMap.get("gclid") + "," +
				utmMap.get("utm_medium") + "," + utmMap.get("partnership") + "," + utmMap.get("promotion") + "," + utmMap.get("utm_source") + "," +
				utmMap.get("utm_term") + "," + utmMap.get("uadcampgn") + "," + utmMap.get("uadgroup") + "," + utmMap.get("wbraid") + "," + leadInfo['zip'] + "," + leadInfo['url']

		if(CheckHeaderExists(f, headers) == false) {
			bw.write(headers);
			bw.newLine();
		}
		bw.write(record);
		bw.newLine();
		bw.close();
	}

	private GetValues() {

		utmMap = [utm_source:"", utm_medium:"", utm_campaign:"", uadgroup:"", uadcampgn:"", utm_term:"", utm_content:"", gclid:"", event:"", promotion:"", partnership:"", ae:"", wbraid:"", gbraid:""]

		Cookie cookie = new Cookie();
		def utmCookieValues = ""

		if (cookie.isCookiePresent("tlh_qry_string"))
			utmCookieValues = cookie.getCookieValue("tlh_qry_string")
		else if (cookie.isCookiePresent("rfi_query_string"))
			utmCookieValues = cookie.getCookieValue("rfi_query_string")

		String[] params = utmCookieValues.split("&");
		for (String param : params) {
			utmMap.each { key, val ->
				if(param.split("=")[0].toLowerCase().contains(key.toString().toLowerCase())) {
					utmMap.putAt(key, param.split("=")[1])
				}
			}
		}
	}

	private Boolean CheckHeaderExists(File file, String header) {
		try {
			Scanner scanner = new Scanner(file);

			//Read the file line by line...
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if(header) {
					return true;
				}
			}
			return false;
		} catch(FileNotFoundException e) {
			System.out.println(e);
		}
	}

	public WriteSrpRowsToCsvFile(List<LeadObject> srpLeads) {

	}
}
