
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import java.util.List

import com.deque.html.axecore.results.Results



def static "com.spe.pages.RFIPage.fillStudentDetails"() {
    (new com.spe.pages.RFIPage()).fillStudentDetails()
}


def static "com.spe.encryption.KatalonEncryption.DecryptText"(
    	String encryptedText	) {
    (new com.spe.encryption.KatalonEncryption()).DecryptText(
        	encryptedText)
}


def static "com.spe.encryption.KatalonEncryption.EncryptText"(
    	String originalText	) {
    (new com.spe.encryption.KatalonEncryption()).EncryptText(
        	originalText)
}


def static "com.spe.pages.Cookies.GetCookieValue"(
    	String key	) {
    (new com.spe.pages.Cookies()).GetCookieValue(
        	key)
}


def static "com.spe.util.GenerateExecutionProfile.createProfile"() {
    (new com.spe.util.GenerateExecutionProfile()).createProfile()
}


def static "com.spe.util.WriteDataToCsv.WriteRow"(
    	Object leadInfo	) {
    (new com.spe.util.WriteDataToCsv()).WriteRow(
        	leadInfo)
}


def static "com.spe.util.Parser.parseTsvFile"(
    	String fileLocation	
     , 	int column	) {
    (new com.spe.util.Parser()).parseTsvFile(
        	fileLocation
         , 	column)
}


def static "com.spe.pages.CustomJS.DeleteElement"(
    	String className	) {
    (new com.spe.pages.CustomJS()).DeleteElement(
        	className)
}


def static "com.spe.pages.FillFields.FillFieldsCheckSms"(
    	int programNum	
     , 	String firstName	
     , 	String lastName	
     , 	String email	
     , 	String phone	
     , 	String country	
     , 	String state	
     , 	String zip	) {
    (new com.spe.pages.FillFields()).FillFieldsCheckSms(
        	programNum
         , 	firstName
         , 	lastName
         , 	email
         , 	phone
         , 	country
         , 	state
         , 	zip)
}


def static "com.spe.pages.FillFields.FillFieldsUnCheckSms"(
    	int programNum	
     , 	String firstName	
     , 	String lastName	
     , 	String email	
     , 	String phone	
     , 	String country	
     , 	String state	
     , 	String zip	) {
    (new com.spe.pages.FillFields()).FillFieldsUnCheckSms(
        	programNum
         , 	firstName
         , 	lastName
         , 	email
         , 	phone
         , 	country
         , 	state
         , 	zip)
}


def static "com.spe.salesforce.GenerateToken.getKey"() {
    (new com.spe.salesforce.GenerateToken()).getKey()
}


def static "com.spe.salesforce.GenerateToken.generateSalesforceToken"() {
    (new com.spe.salesforce.GenerateToken()).generateSalesforceToken()
}


def static "com.spe.Cookie.getCookieValue"(
    	String cookieName	) {
    (new com.spe.Cookie()).getCookieValue(
        	cookieName)
}


def static "com.spe.Cookie.addCookieValue"() {
    (new com.spe.Cookie()).addCookieValue()
}


def static "com.spe.Cookie.deleteCookie"(
    	String cookieName	) {
    (new com.spe.Cookie()).deleteCookie(
        	cookieName)
}


def static "com.spe.Cookie.getAllCookies"() {
    (new com.spe.Cookie()).getAllCookies()
}


def static "com.spe.Cookie.isCookiePresent"(
    	String cookieName	) {
    (new com.spe.Cookie()).isCookiePresent(
        	cookieName)
}


def static "com.spe.salesforce.FindRecords.getOpportunityId"(
    	String email	
     , 	String accessKey	) {
    (new com.spe.salesforce.FindRecords()).getOpportunityId(
        	email
         , 	accessKey)
}


def static "com.spe.salesforce.FindRecords.validateCsvByEmail"(
    	String file	
     , 	String outputFile	) {
    (new com.spe.salesforce.FindRecords()).validateCsvByEmail(
        	file
         , 	outputFile)
}


def static "com.spe.util.AppendText.AppendDate"(
    	String start	
     , 	String format	) {
    (new com.spe.util.AppendText()).AppendDate(
        	start
         , 	format)
}


def static "com.spe.util.AppendText.AppendRandomWithDate"(
    	String start	
     , 	String format	) {
    (new com.spe.util.AppendText()).AppendRandomWithDate(
        	start
         , 	format)
}


def static "com.spe.util.AppendText.AppendDateEmailFormat"(
    	String start	
     , 	String format	) {
    (new com.spe.util.AppendText()).AppendDateEmailFormat(
        	start
         , 	format)
}


def static "com.spe.util.AppendText.AppendRandomWithDateEmailFormat"(
    	String start	
     , 	String format	) {
    (new com.spe.util.AppendText()).AppendRandomWithDateEmailFormat(
        	start
         , 	format)
}


def static "com.spe.util.GetLeadData.getLeadData"() {
    (new com.spe.util.GetLeadData()).getLeadData()
}


def static "com.deque.html.axecore.ComplianceTests.getAllResults"() {
    (new com.deque.html.axecore.ComplianceTests()).getAllResults()
}


def static "com.deque.html.axecore.ComplianceTests.getResultsWithTags"(
    	java.util.List<String> tags	) {
    (new com.deque.html.axecore.ComplianceTests()).getResultsWithTags(
        	tags)
}


def static "com.deque.html.axecore.ComplianceTests.getResultsWithOnlyRules"(
    	java.util.List<String> rules	) {
    (new com.deque.html.axecore.ComplianceTests()).getResultsWithOnlyRules(
        	rules)
}


def static "com.deque.html.axecore.ComplianceTests.getViolationsFromResults"(
    	Results results	) {
    (new com.deque.html.axecore.ComplianceTests()).getViolationsFromResults(
        	results)
}


def static "com.deque.html.axecore.ComplianceTests.writeResultsToJsonFile"(
    	Results results	
     , 	String reportFileName	) {
    (new com.deque.html.axecore.ComplianceTests()).writeResultsToJsonFile(
        	results
         , 	reportFileName)
}


def static "com.spe.util.Dropdowns.returnTextOfSelectedOption"(
    	String objectPath	) {
    (new com.spe.util.Dropdowns()).returnTextOfSelectedOption(
        	objectPath)
}


def static "com.spe.Url.getBaseUrl"(
    	String url	) {
    (new com.spe.Url()).getBaseUrl(
        	url)
}


def static "com.spe.api.ProgramsApi.getProgramsApi"(
    	String partnerUuid	
     , 	String encryptedApiKey	
     , 	String fileName	) {
    (new com.spe.api.ProgramsApi()).getProgramsApi(
        	partnerUuid
         , 	encryptedApiKey
         , 	fileName)
}


def static "com.spe.aws.S3.downloadFile"(
    	String bucketName	
     , 	String keyName	
     , 	String destination	) {
    (new com.spe.aws.S3()).downloadFile(
        	bucketName
         , 	keyName
         , 	destination)
}
