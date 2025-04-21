package com.spe.salesforce

import java.util.concurrent.TimeUnit

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.kms.katalon.core.annotation.Keyword
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class FindRecords {

	@Keyword
	public String getOpportunityId(String email, String accessKey) {
		String selectObjects = "ConvertedOpportunityId"
		String soqlQuery = String.format("https://wes-srp.my.salesforce.com/services/data/v55.0/query/?q=SELECT+" + selectObjects + "+FROM+Lead+where+Email+=+'%s'", email);

		return sendQueryToSalesforce(soqlQuery, accessKey)
	}

	@Keyword
	public boolean validateCsvByEmail(String file, String outputFile) {
		String csvFile = file;
		String srpFile = outputFile;
		//Header order: Id, First Name, Last Name, Email, Program, Validated, Results
		String emails = "";
		int count = 0;
		//SOQL query max character limit is 100,000. The query currently has around 1100 in it, if the emails are around 50 characters, you can have a max limit of around 1900 emails
		int maxSearchCount = 500;
		String token = GenerateToken.getKey();

		List<LeadObject> csvLeads = new CsvToBeanBuilder(new FileReader(csvFile)).withType(LeadObject.class).build().parse();

		List<LeadObject> srpLeads = new ArrayList<LeadObject>();

		for(LeadObject lead : csvLeads) {
			System.out.println(lead.email);
			if(lead.validated == null)
			{
				lead.validated = "";
			}
			if(lead.validated.toLowerCase().contains("true"))
			{
				break;
			}
		}

		for(LeadObject lead : csvLeads)
		{
			System.out.println(lead.email);
			System.out.println(lead.country);
			if(lead.validated.toLowerCase().contains("true"))
			{
				continue;
			}
			if(count > maxSearchCount) {
				break;
			}
			if(lead.country != null) {
				if(!lead.country.replaceAll("\\s", "").isEmpty()) {
					lead.country = IsUsBased(lead.country);
				}
			}
			if(lead.validated != null && lead.validated.toLowerCase() != "true")
			{
				emails += String.format("'%s',", lead.email);
				count++;
			}
		}

		emails = removeLastComma(emails);
		System.out.println(emails)

		String oppSelectObjects = "SRP_Mailing_Country__c,+Email__c,+SRP_Student_First_Name__c,+SRP_Student_Last_Name__c,+SRP_Mobile_Phone__c,+RFI_Program_Code__c,+SRP_OK_To_text__c,+SRP_Mailing_State__c,+Account_Executive__c,+DeltakSRP__LP_UREFPROMOTION__c,+DeltakSRP__LP_UREF_MEDIATYPE__c,+" +
				"Event_Id__c,+GBRAID__c,+Gclid__c,+DeltakSRP__Source__c,+Partnership_ID__c,+Promotion_ID__c,+DeltakSRP__Campaign_Name__c,+DeltakSRP__LP_UREFKEYWORD__c,+DeltakSRP__LP_UADCAMPAIGN__c,+DeltakSRP__LP_UADGROUP__c,+WBRAID__c,+Mailing_Zip__c"

		String leadSelectObjects = "FirstName,+LastName,+Email,+MobilePhone,+Country,+PostalCode,+State,+Source__c,+DeltakSRP__LP_UREFPROMOTION__c,+DeltakSRP__LP_UREURL__c,+DeltakSRP__LP_UREFKEYWORD__c,+RFI_Campaign_Code__c,+GATRACKID__c,+GACLIENTID__c,+DeltakSRP__AcademicProgram__c,+" +
				"DeltakSRP__AcademicInstitution__c,+DeltakSRP__LP_UADGROUP__c,+DeltakSRP__LP_UADCAMPAIGN__c,+DeltakSRPSMS__Ok_To_Text__c,+GAUSERID__c,+Gclid__c,+Form_Source__c,+Allocadia_ID__c,+Event_ID__c,+Partnership_ID__c,+" +
				"Promotion_ID__c,+DeltakSRP__WebScheduler_Status__c,+Company,+AE_Initials__c,+WBRAID__c,+GBRAID__c,+Affiliated_with_U_S_Military__c,+DeltakSRP__Term__c,+Expected_Year_Of_Entry__c,+SRP_Inquiry_Type__c,+Parent_Guardian_First_Name__c,+" +
				"Parent_Guardian_Last_Name__c,+DeltakSRP__LP_UREF_MEDIATYPE__c,+Parent_Guardian_Phone__c,+Parent_Guardian_Email__c,+City,+Street,+Anticipated_High_School_Graduation_Year__c,+EBP_Institutions_of_Interest__c,+DeltakSRP__Interest_Timeframe__c,+Notes__c,+fbc_id__c,+Partner_CRM_Opportunity_ID__c,+" +
				"Highest_Degree_Earned__c,+LeadSource,+HasOptedOutOfEmail"

		String soqlQuery = String.format("https://wes-srp.my.salesforce.com/services/data/v55.0/query/?q=SELECT+" + leadSelectObjects + "+FROM+Lead+where+Email+IN+(%s)", emails);

		String SrpResponse = sendQueryToSalesforce(soqlQuery, token);
		if(!emails.isEmpty()) {
			srpLeads = processResponse(SrpResponse, csvFile);
			System.out.println(SrpResponse);
			System.out.println(srpLeads);
			compareCsvToSrp(csvLeads, srpLeads, csvFile);
			UpdateCsvFile(srpLeads, srpFile);
		}
		return true;
	}

	//Returns Salesforce data based on the query
	String sendQueryToSalesforce(String query, String token)
	{
		System.out.println("Querying Salesforce...");
		String result = "";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			HttpGet request = new HttpGet(query);

			// add request headers
			request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

			CloseableHttpResponse response = httpClient.execute(request);

			try {

				// Get HttpResponse Status
				System.out.println(response.getProtocolVersion());              // HTTP/1.1
				System.out.println(response.getStatusLine().getStatusCode());   // 200
				System.out.println(response.getStatusLine().getReasonPhrase()); // OK
				System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// return it as a String
					result = EntityUtils.toString(entity);
				}

			} finally {
				response.close();
			}

		} finally {
			httpClient.close();
		}
		return result;
	}

	private String removeLastComma(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	private List<LeadObject> processResponse(String response, String csvFile)
	{
		System.out.println("Processing Salesforce Response...");

		List<LeadObject> srpLeads = new ArrayList<LeadObject>();

		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		List<LeadObject> csvLeads = new CsvToBeanBuilder(new FileReader(csvFile)).withType(LeadObject.class).build().parse();

		JsonArray records = jsonObject.getAsJsonArray("records");
		/*
		 for (JsonElement record : records)
		 {
		 LeadObject srpLead = new LeadObject();
		 srpLead.country = Objects.toString(((JsonObject) record).get("SRP_Mailing_Country__c"), "");
		 srpLead.email = Objects.toString(((JsonObject) record).get("Email__c"), "");
		 srpLead.firstName = Objects.toString(((JsonObject) record).get("SRP_Student_First_Name__c"), "");
		 srpLead.lastName = Objects.toString(((JsonObject) record).get("SRP_Student_Last_Name__c"), "");
		 srpLead.phone = Objects.toString(((JsonObject) record).get("SRP_Mobile_Phone__c"), "");
		 srpLead.programUuid = Objects.toString(((JsonObject) record).get("RFI_Program_Code__c"), "");
		 srpLead.sms = Objects.toString(((JsonObject) record).get("SRP_OK_To_text__c"), "");
		 srpLead.state = Objects.toString(((JsonObject) record).get("SRP_Mailing_State__c"), "");
		 srpLead.utm_ae = Objects.toString(((JsonObject) record).get("Account_Executive__c"), "");
		 srpLead.utm_campaign = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UREFPROMOTION__c"), "");
		 srpLead.utm_content = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UREF_MEDIATYPE__c"), "");
		 srpLead.utm_event = Objects.toString(((JsonObject) record).get("Event_Id__c"), "");
		 srpLead.utm_gbraid = Objects.toString(((JsonObject) record).get("GBRAID__c"), "");
		 srpLead.utm_gclid = Objects.toString(((JsonObject) record).get("Gclid__c"), "");
		 srpLead.utm_medium = Objects.toString(((JsonObject) record).get("DeltakSRP__Source__c"), "");
		 srpLead.utm_partnership = Objects.toString(((JsonObject) record).get("Partnership_ID__c"), "");
		 srpLead.utm_promotion = Objects.toString(((JsonObject) record).get("Promotion_ID__c"), "");
		 srpLead.utm_source = Objects.toString(((JsonObject) record).get("DeltakSRP__Campaign_Name__c"), "");
		 srpLead.utm_term = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UREFKEYWORD__c"), "");
		 srpLead.utm_uadcampgn = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UADCAMPAIGN__c"), "");
		 srpLead.utm_uadgroup = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UADGROUP__c"), "");
		 srpLead.utm_wbraid = Objects.toString(((JsonObject) record).get("WBRAID__c"), "");
		 srpLead.zip = Objects.toString(((JsonObject) record).get("Mailing_Zip__c"), "");
		 srpLeads.add(srpLead);
		 }
		 */



		for (JsonElement record : records)
		{
			LeadObject srpLead = new LeadObject();
			srpLead.firstName = Objects.toString(((JsonObject) record).get("FirstName"), "").replace('"', '');
			srpLead.lastName = Objects.toString(((JsonObject) record).get("LastName"), "").replace('"', '');
			srpLead.email = Objects.toString(((JsonObject) record).get("Email"), "").replace('"', '');
			srpLead.phone = Objects.toString(((JsonObject) record).get("MobilePhone"), "").replace('"', '');
			srpLead.country = Objects.toString(((JsonObject) record).get("Country"), "").replace('"', '');
			if(srpLead.country != null) {
				if(!srpLead.country.replaceAll("\\s", "").isEmpty()) {
					srpLead.country = IsUsBased(srpLead.country)
				}
			}
			srpLead.zip = Objects.toString(((JsonObject) record).get("PostalCode"), "").replace('"', '');
			srpLead.state = Objects.toString(((JsonObject) record).get("State"), "").replace('"', '');
			srpLead.utm_medium = Objects.toString(((JsonObject) record).get("Source__c"), "").replace('"', '');
			srpLead.utm_campaign = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UREFPROMOTION__c"), "").replace('"', '');
			srpLead.utm_content = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UREF_MEDIATYPE__c"), "").replace('"', '');
			srpLead.referrer = Objects.toString(((JsonObject) record).get("DeltakSRP_LP_UREURL__c"), "").replace('"', '');
			srpLead.utm_term = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UREFKEYWORD__c"), "").replace('"', '');
			srpLead.utm_source = Objects.toString(((JsonObject) record).get("RFI_Campaign_Code__c"), "").replace('"', '');
			srpLead.gaUaId = Objects.toString(((JsonObject) record).get("GATRACKID__c"), "").replace('"', '');
			srpLead.gaClientId = Objects.toString(((JsonObject) record).get("GACLIENTID__c"), "").replace('"', '');
			srpLead.program = Objects.toString(((JsonObject) record).get("DeltakSRP__AcademicProgram__c"), "").replace('"', '');
			srpLead.accountId = Objects.toString(((JsonObject) record).get("DeltakSRP__AcademicInstitution__c"), "").replace('"', '');
			srpLead.uadgroup = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UADGROUP__c"), "").replace('"', '');
			srpLead.uAdCampgn = Objects.toString(((JsonObject) record).get("DeltakSRP__LP_UADCAMPAIGN__c"), "").replace('"', '');
			srpLead.okToText = Objects.toString(((JsonObject) record).get("DeltakSRPSMS__Ok_To_Text__c"), "").replace('"', '');
			srpLead.gaUserId = Objects.toString(((JsonObject) record).get("GAUSERID__c"), "").replace('"', '');
			srpLead.gclid = Objects.toString(((JsonObject) record).get("Gclid__c"), "").replace('"', '');
			srpLead.formSource = Objects.toString(((JsonObject) record).get("Form_Source__c"), "").replace('"', '');
			srpLead.tId = Objects.toString(((JsonObject) record).get("Allocadia_ID__c"), "").replace('"', '');
			srpLead.event = Objects.toString(((JsonObject) record).get("Event_ID__c"), "").replace('"', '');
			srpLead.partnership = Objects.toString(((JsonObject) record).get("Partnership_ID__c"), "").replace('"', '');
			srpLead.webSchedulerStatus = Objects.toString(((JsonObject) record).get("DeltakSRP__WebScheduler_Status__c"), "").replace('"', '');
			srpLead.isLandingPage = Objects.toString(((JsonObject) record).get("IsLandingPage"), "").replace('"', '');
			srpLead.ae = Objects.toString(((JsonObject) record).get("AE_Initials__c"), "").replace('"', '');
			srpLead.wbraid = Objects.toString(((JsonObject) record).get("WBRAID__c"), "").replace('"', '');
			srpLead.gbraid = Objects.toString(((JsonObject) record).get("GBRAID__c"), "").replace('"', '');
			srpLead.militaryAffiliated = Objects.toString(((JsonObject) record).get("Affiliated_with_U_S_Military__c"), "").replace('"', '');
			srpLead.termOfEntry = Objects.toString(((JsonObject) record).get("DeltakSRP__Term__c"), "").replace('"', '');
			srpLead.prospectType = Objects.toString(((JsonObject) record).get("SRP_Inquiry_Type__c"), "").replace('"', '');
			srpLead.parentGuardianFirstName = Objects.toString(((JsonObject) record).get("Parent_Guardian_First_Name__c"), "").replace('"', '');
			srpLead.parentGuardianLastName = Objects.toString(((JsonObject) record).get("Parent_Guardian_Last_Name__c"), "").replace('"', '');
			srpLead.parentGuardianPhone = Objects.toString(((JsonObject) record).get("Parent_Guardian_Phone__c"), "").replace('"', '');
			srpLead.parentGuardianEmail = Objects.toString(((JsonObject) record).get("Parent_Guardian_Email__c"), "").replace('"', '');
			srpLead.city = Objects.toString(((JsonObject) record).get("City"), "").replace('"', '');
			srpLead.streetAddress = Objects.toString(((JsonObject) record).get("Street"), "").replace('"', '');
			srpLead.anticipatedHighSchoolGraduationYear = Objects.toString(((JsonObject) record).get("Anticipated_High_School_Graduation_Year__c"), "").replace('"', '');
			srpLead.institutions = Objects.toString(((JsonObject) record).get("EBP_Institutions_of_Interest"), "").replace('"', '');
			srpLead.interestTimeframe = Objects.toString(((JsonObject) record).get("DeltakSRP__Interest_Timeframe__c"), "").replace('"', '');
			srpLead.shortCourseSelection = Objects.toString(((JsonObject) record).get("Notes__c"), "").replace('"', '');
			srpLead.fbcid = Objects.toString(((JsonObject) record).get("fbc_id__c"), "").replace('"', '');
			srpLead.highestDegree = Objects.toString(((JsonObject) record).get("Highest_Degree_Earned__c"), "").replace('"', '');
			srpLead.leadSource = Objects.toString(((JsonObject) record).get("LeadSource"), "").replace('"', '');
			srpLead.okToEmail = Objects.toString(((JsonObject) record).get("HasOptedOutOfEmail"), "").replace('"', '');
			srpLead.promotion = Objects.toString(((JsonObject) record).get("Promotion_ID__c"), "").replace('"', '');
			srpLead.yearOfEntry = Objects.toString(((JsonObject) record).get("Expected_Year_of_Entry__c"), "").replace('"', '');

			srpLeads.add(srpLead);
		}

		return srpLeads;
	}

	private void compareCsvToSrp(List<LeadObject> csvLeads, List<LeadObject> srpLeads, String csvFile)
	{
		System.out.println("Comparing Csv to SRP...");

		for(LeadObject csvLead : csvLeads)
		{
			if(csvLead.validated.toLowerCase().contains('true'))
				break
			boolean passed = null;
			for(LeadObject srpLead : srpLeads)
			{
				if(csvLead.email.toLowerCase().replaceAll("\\s", "").replaceAll('"', "").equals(srpLead.email.toLowerCase().replaceAll("\\s", "").replaceAll('"', "")))
				{
					passed = true;
					csvLead.properties.each { csvField, csvValue ->
						srpLead.properties.each { srpField, srpValue ->
							if(csvField.toString().equalsIgnoreCase(srpField.toString()) && !csvField.toString().equalsIgnoreCase("results") && !csvField.toString().equalsIgnoreCase("validated"))
							{
								if(csvValue != null && !csvValue.toString().replaceAll("\\s", "").isEmpty())
								{
									System.out.println(csvField + ":" + csvValue + "=" + srpField + ":" + srpValue);
									if(srpValue != null)
									{
										if(!srpValue.toString().replaceAll("\\s", "").replaceAll('"', "").toLowerCase().contains(csvValue.toString().replaceAll("\\s", "").replaceAll('"', "").toLowerCase()))
										{
											passed = false;
											csvLead.results += " " + csvValue.toString() + " doesn't match " + srpValue.toString() + " at " + csvField.toString() + "; ";
										}
										else if(!csvValue.toString().isEmpty() && srpValue.toString().isEmpty())
										{
											passed = false;
											csvLead.results += "SRP's " srpField.toString() + " is blank and csv value is not" + " at " + csvField.toString() + "; ";
										}
									}
									if(srpValue == null)
									{
										System.out.println("Null vs not null");
										passed = false;
										csvLead.results += "SRP's " srpField.toString() + " is null and csv value is not at " + csvField.toString();
									}
								}
							}
						}
					}
				}
			}

			if(csvLead.results == null) {
				csvLead.results = ""
			}

			if(passed == null)
				csvLead.results = "FAILED - Lead not found in SRP";
			else if(passed)
				csvLead.results = "PASSED";
			else {
				if(csvLead.results.isEmpty()) {
					csvLead.results = "Could not find lead in SRP"
				}
				csvLead.results = "FAILED - " + csvLead.results;
			}
			csvLead.validated = "TRUE";

			csvLeads.set(csvLeads.indexOf(csvLead), csvLead);
		}

		UpdateCsvFile(csvLeads, csvFile);
	}

	private UpdateCsvFile(List<LeadObject> csvLeads, String file)
	{
		System.out.println("Writing to file");

		try {
			FileWriter writer = new FileWriter(file);
			ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
			mappingStrategy.setType(LeadObject.class);

			// Creating StatefulBeanToCsv object
			StatefulBeanToCsvBuilder<LeadObject> builder= new StatefulBeanToCsvBuilder(writer);
			StatefulBeanToCsv beanWriter = builder.build();

			// Write list to StatefulBeanToCsv object
			beanWriter.write(csvLeads);

			// closing the writer object
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private IsUsBased(String country) {
		if (country.toLowerCase().trim() == 'us' || country.toLowerCase().trim() == 'united states' || country.toLowerCase().trim() == 'united states of america')
			return 'United States'
		else
			return country
	}
}
