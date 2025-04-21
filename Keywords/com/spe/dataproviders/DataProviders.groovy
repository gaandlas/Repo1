package com.spe.dataproviders

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

import internal.GlobalVariable

import com.spe.TestBase;
import com.spe.util.WriteDataToExcel;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DataProviders extends TestBase {
	Object[] data;
	int i;
	WriteDataToExcel writeDataToExcel;

	@DataProvider(name="rfitestdata")
	public Object[] getRfiDataFromExcel() throws IOException, InterruptedException {

		data = new Object[sheetLP.getLastRowNum()+sheetMicro.getLastRowNum()];
		i=0;

		readLPData();
		readMicrositeData();

		return data;
	}

	@DataProvider(name="MicrositeData")
	public Object[] getDataFromExcelForMicrosite() throws IOException, InterruptedException {

		data = new Object[sheetMicro.getLastRowNum()];
		i=0;

		readMicrositeData();

		return data;
	}

	@DataProvider(name="LPData")
	public Object[] getDataFromExcelForLP() throws IOException, InterruptedException {

		data = new Object[sheetLP.getLastRowNum()];
		i=0;

		readLPData();

		return data;
	}

	public void readLPData(){
		WriteDataToExcel writeDataToExcel = new WriteDataToExcel();

		Iterator<Row> iteratorLP = sheetLP.rowIterator();
		iteratorLP.next();

		while (iteratorLP.hasNext()) {
			Row row = iteratorLP.next();
			Map<String, String> map = new HashMap<String, String>();

			if(prop.getProperty("environment").equals("staging"))
				map.put("url", writeDataToExcel.getCellText(2, row));
			else
				map.put("url", writeDataToExcel.getCellText(3, row));

			map.put("partnerNname", writeDataToExcel.getCellText(0, row));
			map.put("siteType", writeDataToExcel.getCellText(1, row));
			map.put("isAffiliate", writeDataToExcel.getCellText(4, row));
			map.put("privacyLink", writeDataToExcel.getCellText(5, row));
			map.put("termsLink", writeDataToExcel.getCellText(6, row));
			map.put("smsConsentText", writeDataToExcel.getCellText(7, row));
			map.put("optimizelySnippet", writeDataToExcel.getCellText(8, row));

			data[i]=map;
			i++;
		}
	}

	public void readMicrositeData(){
		WriteDataToExcel writeDataToExcel = new WriteDataToExcel();

		Iterator<Row> iteratorMicro = sheetMicro.rowIterator();
		iteratorMicro.next();

		while (iteratorMicro.hasNext()) {
			Row row = iteratorMicro.next();
			Map<String, String> map = new HashMap<String, String>();

			if(prop.getProperty("environment").equals("staging"))
				map.put("url", writeDataToExcel.getCellText(2, row));
			else
				map.put("url", writeDataToExcel.getCellText(3, row));

			map.put("partnerNname", writeDataToExcel.getCellText(0, row));
			map.put("siteType", writeDataToExcel.getCellText(1, row));
			map.put("isAffiliate", writeDataToExcel.getCellText(4, row));
			map.put("privacyLink", writeDataToExcel.getCellText(5, row));
			map.put("termsLink", writeDataToExcel.getCellText(6, row));
			map.put("smsConsentText", writeDataToExcel.getCellText(7, row));
			map.put("optimizelySnippet", writeDataToExcel.getCellText(8, row));

			data[i]=map;
			i++;
		}
	}
}
