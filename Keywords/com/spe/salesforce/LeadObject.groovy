package com.spe.salesforce

import com.opencsv.bean.CsvBindByNames;
import com.opencsv.bean.CsvBindByName;

public class LeadObject {

	@CsvBindByName(column = "firstName")
	private String firstName;

	@CsvBindByName(column = "lastName")
	private String lastName;

	@CsvBindByName(column = "email")
	private String email;

	@CsvBindByName(column = "phone")
	private String phone;

	@CsvBindByName(column = "country")
	private String country;

	@CsvBindByName(column = "zip")
	private String zip;

	@CsvBindByName(column = "state")
	private String state;

	@CsvBindByName(column = "utm_medium")
	private String utm_medium;

	@CsvBindByName(column = "utm_campaign")
	private String utm_campaign;

	@CsvBindByName(column = "utm_content")
	private String utm_content;

	@CsvBindByName(column = "referrer")
	private String referrer;

	@CsvBindByName(column = "utm_term")
	private String utm_term;

	@CsvBindByName(column = "utm_source")
	private String utm_source;

	@CsvBindByName(column = "gaUaId")
	private String gaUaId;

	@CsvBindByName(column = "gaClientId")
	private String gaClientId;

	@CsvBindByName(column = "program")
	private String program;

	@CsvBindByName(column = "accountId")
	private String accountId;

	@CsvBindByName(column = "uadgroup")
	private String uadgroup;

	@CsvBindByName(column = "uAdCampgn")
	private String uAdCampgn;

	@CsvBindByName(column = "okToText")
	private String okToText;

	@CsvBindByName(column = "gaUserId")
	private String gaUserId;

	@CsvBindByName(column = "gclid")
	private String gclid;

	@CsvBindByName(column = "formSource")
	private String formSource;

	@CsvBindByName(column = "tId")
	private String tId;

	@CsvBindByName(column = "event")
	private String event;

	@CsvBindByName(column = "partnership")
	private String partnership;

	@CsvBindByName(column = "promotion")
	private String promotion;

	@CsvBindByName(column = "webSchedulerStatus")
	private String webSchedulerStatus;

	@CsvBindByName(column = "isLandingPage")
	private String isLandingPage;

	@CsvBindByName(column = "ae")
	private String ae;

	@CsvBindByName(column = "wbraid")
	private String wbraid;

	@CsvBindByName(column = "gbraid")
	private String gbraid;

	@CsvBindByName(column = "militaryAffiliated")
	private String militaryAffiliated;

	@CsvBindByName(column = "termOfEntry")
	private String termOfEntry;

	@CsvBindByName(column = "yearOfEntry")
	private String yearOfEntry;

	@CsvBindByName(column = "prospectType")
	private String prospectType;

	@CsvBindByName(column = "parentGuardianFirstName")
	private String parentGuardianFirstName;

	@CsvBindByName(column = "parentGuardianLastName")
	private String parentGuardianLastName;

	@CsvBindByName(column = "parentGuardianPhone")
	private String parentGuardianPhone;

	@CsvBindByName(column = "parentGuardianEmail")
	private String parentGuardianEmail;

	@CsvBindByName(column = "city")
	private String city;

	@CsvBindByName(column = "streetAddress")
	private String streetAddress;

	@CsvBindByName(column = "anticipatedHighSchoolGraduationYear")
	private String anticipatedHighSchoolGraduationYear;

	@CsvBindByName(column = "institutions")
	private String institutions;

	@CsvBindByName(column = "interestTimeframe")
	private String interestTimeframe;

	@CsvBindByName(column = "shortCourseSelection")
	private String shortCourseSelection;

	@CsvBindByName(column = "fbcid")
	private String fbcid;

	@CsvBindByName(column = "highestDegree")
	private String highestDegree;

	@CsvBindByName(column = "leadSource")
	private String leadSource;
	@CsvBindByName(column = "okToEmail")
	private String okToEmail;

	@CsvBindByName(column = "Validated")
	private String validated;

	@CsvBindByName(column = "Results")
	private String results;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUtm_medium() {
		return utm_medium;
	}

	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}

	public String getUtm_campaign() {
		return utm_campaign;
	}

	public void setUtm_campaign(String utm_campaign) {
		this.utm_campaign = utm_campaign;
	}

	public String getUtm_content() {
		return utm_content;
	}

	public void setUtm_content(String utm_content) {
		this.utm_content = utm_content;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getUtm_term() {
		return utm_term;
	}

	public void setUtm_term(String utm_term) {
		this.utm_term = utm_term;
	}

	public String getUtm_source() {
		return utm_source;
	}

	public void setUtm_source(String utm_source) {
		this.utm_source = utm_source;
	}

	public String getGaUaId() {
		return gaUaId;
	}

	public void setGaUaId(String gaUaId) {
		this.gaUaId = gaUaId;
	}

	public String getGaClientId() {
		return gaClientId;
	}

	public void setGaClientId(String gaClientId) {
		this.gaClientId = gaClientId;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUadgroup() {
		return uadgroup;
	}

	public void setUadgroup(String uadgroup) {
		this.uadgroup = uadgroup;
	}

	public String getuAdCampgn() {
		return uAdCampgn;
	}

	public void setuAdCampgn(String uAdCampgn) {
		this.uAdCampgn = uAdCampgn;
	}

	public String getOkToText() {
		return okToText;
	}

	public void setOkToText(String okToText) {
		this.okToText = okToText;
	}

	public String getGaUserId() {
		return gaUserId;
	}

	public void setGaUserId(String gaUserId) {
		this.gaUserId = gaUserId;
	}

	public String getGclid() {
		return gclid;
	}

	public void setGclid(String gclid) {
		this.gclid = gclid;
	}

	public String getFormSource() {
		return formSource;
	}

	public void setFormSource(String formSource) {
		this.formSource = formSource;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getPartnership() {
		return partnership;
	}

	public void setPartnership(String partnership) {
		this.partnership = partnership;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getWebSchedulerStatus() {
		return webSchedulerStatus;
	}

	public void setWebSchedulerStatus(String webSchedulerStatus) {
		this.webSchedulerStatus = webSchedulerStatus;
	}

	public String getIsLandingPage() {
		return isLandingPage;
	}

	public void setIsLandingPage(String isLandingPage) {
		this.isLandingPage = isLandingPage;
	}

	public String getAe() {
		return ae;
	}

	public void setAe(String ae) {
		this.ae = ae;
	}

	public String getWbraid() {
		return wbraid;
	}

	public void setWbraid(String wbraid) {
		this.wbraid = wbraid;
	}

	public String getGbraid() {
		return gbraid;
	}

	public void setGbraid(String gbraid) {
		this.gbraid = gbraid;
	}

	public String getMilitaryAffiliated() {
		return militaryAffiliated;
	}

	public void setMilitaryAffiliated(String militaryAffiliated) {
		this.militaryAffiliated = militaryAffiliated;
	}

	public String getTermOfEntry() {
		return termOfEntry;
	}

	public void setTermOfEntry(String termOfEntry) {
		this.termOfEntry = termOfEntry;
	}

	public String getYearOfEntry() {
		return yearOfEntry;
	}

	public void setYearOfEntry(String yearOfEntry) {
		this.yearOfEntry = yearOfEntry;
	}

	public String getProspectType() {
		return prospectType;
	}

	public void setProspectType(String prospectType) {
		this.prospectType = prospectType;
	}

	public String getParentGuardianFirstName() {
		return parentGuardianFirstName;
	}

	public void setParentGuardianFirstName(String parentGuardianFirstName) {
		this.parentGuardianFirstName = parentGuardianFirstName;
	}

	public String getParentGuardianLastName() {
		return parentGuardianLastName;
	}

	public void setParentGuardianLastName(String parentGuardianLastName) {
		this.parentGuardianLastName = parentGuardianLastName;
	}

	public String getParentGuardianPhone() {
		return parentGuardianPhone;
	}

	public void setParentGuardianPhone(String parentGuardianPhone) {
		this.parentGuardianPhone = parentGuardianPhone;
	}

	public String getParentGuardianEmail() {
		return parentGuardianEmail;
	}

	public void setParentGuardianEmail(String parentGuardianEmail) {
		this.parentGuardianEmail = parentGuardianEmail;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAnticipatedHighSchoolGraduationYear() {
		return anticipatedHighSchoolGraduationYear;
	}

	public void setAnticipatedHighSchoolGraduationYear(String anticipatedHighSchoolGraduationYear) {
		this.anticipatedHighSchoolGraduationYear = anticipatedHighSchoolGraduationYear;
	}

	public String getInstitutions() {
		return institutions;
	}

	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}

	public String getInterestTimeframe() {
		return interestTimeframe;
	}

	public void setInterestTimeframe(String interestTimeframe) {
		this.interestTimeframe = interestTimeframe;
	}

	public String getShortCourseSelection() {
		return shortCourseSelection;
	}

	public void setShortCourseSelection(String shortCourseSelection) {
		this.shortCourseSelection = shortCourseSelection;
	}

	public String getFbcid() {
		return fbcid;
	}

	public void setFbcid(String fbcid) {
		this.fbcid = fbcid;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}


	public String getOkToEmail() {
		return okToEmail;
	}

	public void setOkToEmail(String okToEmail) {
		this.okToEmail = okToEmail;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}
}