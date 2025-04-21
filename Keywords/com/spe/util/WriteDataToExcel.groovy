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

import internal.GlobalVariable

import com.spe.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Map;

public class WriteDataToExcel extends TestBase {

	File file = new File(".\\Data Files\\Leads\\leadData.xlsx");

	public void WriteRFIData(Map<String,String> submittedLeads) throws IOException, InterruptedException {
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet sheet;
		//        if(workbook.getNumberOfSheets() == 0){
		//           sheet = workbook.createSheet("leads");
		//        }else{
		//           sheet = workbook.getSheet("leads");
		//        }

		sheet = workbook.getSheet("LeadsData");

		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);

		if(rowCount == 1){
			rowCount = 2;
		}else{
			rowCount = rowCount + 1;
		}
		System.out.println("row count: " + rowCount);
		Thread.sleep(2000);
		Cell email = sheet.createRow(rowCount).createCell(0);
		email.setCellValue(submittedLeads.get("email"));

		Cell fnameCell = sheet.getRow(rowCount).createCell(1);
		fnameCell.setCellValue(submittedLeads.get("firstname"));

		Cell lnameCell = sheet.getRow(rowCount).createCell(2);
		lnameCell.setCellValue(submittedLeads.get("lastname"));

		Cell abbrevation = sheet.getRow(rowCount).createCell(3);
		abbrevation.setCellValue(submittedLeads.get("abbrevation"));

		Cell program_uuid = sheet.getRow(rowCount).createCell(4);
		program_uuid.setCellValue(submittedLeads.get("program_uuid"));

		Cell phoneNumber = sheet.getRow(rowCount).createCell(5);
		phoneNumber.setCellValue(submittedLeads.get("phoneNumber"));

		Cell sms = sheet.getRow(rowCount).createCell(6);
		sms.setCellValue(submittedLeads.get("sms"));

		Cell country = sheet.getRow(rowCount).createCell(7);
		country.setCellValue("");

		Cell state = sheet.getRow(rowCount).createCell(8);
		state.setCellValue(""); //state for the ZIP set

		Cell zip = sheet.getRow(rowCount).createCell(9);
		state.setCellValue("12345"); //state for the ZIP set

		Cell campaign = sheet.getRow(rowCount).createCell(10);
		campaign.setCellValue(submittedLeads.get("campaign"));

		Cell programName = sheet.getRow(rowCount).createCell(11);
		programName.setCellValue(submittedLeads.get("programName"));



		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();
	}

	public void removeSheetData() throws IOException, InterruptedException {

		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Thread.sleep(5000);
		workbook.removeSheetAt(0);

		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();


	}

	public String getCellText(int cellnum, Row cellRow){
		String cellData = "";

		Cell cell = cellRow.getCell(cellnum);
		if(cell != null){
			cellData = cell.toString();
		}
		return cellData;
	}
}
