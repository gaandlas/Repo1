package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object firstName
     
    /**
     * <p></p>
     */
    public static Object lastName
     
    /**
     * <p></p>
     */
    public static Object phoneNumber
     
    /**
     * <p></p>
     */
    public static Object Zip
     
    /**
     * <p></p>
     */
    public static Object mailState
     
    /**
     * <p></p>
     */
    public static Object Country
     
    /**
     * <p></p>
     */
    public static Object termsLinkUS
     
    /**
     * <p></p>
     */
    public static Object privacyLinkUS
     
    /**
     * <p></p>
     */
    public static Object termLinkInt
     
    /**
     * <p></p>
     */
    public static Object privacyLinkInt
     
    /**
     * <p></p>
     */
    public static Object disclaimerLink
     
    /**
     * <p></p>
     */
    public static Object utm
     
    /**
     * <p></p>
     */
    public static Object utm2
     
    /**
     * <p></p>
     */
    public static Object utmAffiliate
     
    /**
     * <p></p>
     */
    public static Object fbclid
     
    /**
     * <p></p>
     */
    public static Object token
     
    /**
     * <p></p>
     */
    public static Object uwacAffiliate
     
    /**
     * <p></p>
     */
    public static Object url
     
    /**
     * <p></p>
     */
    public static Object siteType
     
    /**
     * <p></p>
     */
    public static Object formType
     
    /**
     * <p></p>
     */
    public static Object tealiumProfile
     
    /**
     * <p></p>
     */
    public static Object sigleProgramURL
     
    /**
     * <p></p>
     */
    public static Object partnerName
     
    /**
     * <p></p>
     */
    public static Object RfiUrl
     
    /**
     * <p></p>
     */
    public static Object Utm
     
    /**
     * <p></p>
     */
    public static Object BaseUrl
     
    /**
     * <p></p>
     */
    public static Object IsLanding
     
    /**
     * <p></p>
     */
    public static Object IsInternational
     
    /**
     * <p></p>
     */
    public static Object SchoolName
     
    /**
     * <p></p>
     */
    public static Object SmsSchoolName
     
    /**
     * <p></p>
     */
    public static Object PostId
     
    /**
     * <p></p>
     */
    public static Object TealiumProfile
     
    /**
     * <p></p>
     */
    public static Object Pod
     
    /**
     * <p></p>
     */
    public static Object Acronym
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters(), selectedVariables)
    
            firstName = selectedVariables['firstName']
            lastName = selectedVariables['lastName']
            phoneNumber = selectedVariables['phoneNumber']
            Zip = selectedVariables['Zip']
            mailState = selectedVariables['mailState']
            Country = selectedVariables['Country']
            termsLinkUS = selectedVariables['termsLinkUS']
            privacyLinkUS = selectedVariables['privacyLinkUS']
            termLinkInt = selectedVariables['termLinkInt']
            privacyLinkInt = selectedVariables['privacyLinkInt']
            disclaimerLink = selectedVariables['disclaimerLink']
            utm = selectedVariables['utm']
            utm2 = selectedVariables['utm2']
            utmAffiliate = selectedVariables['utmAffiliate']
            fbclid = selectedVariables['fbclid']
            token = selectedVariables['token']
            uwacAffiliate = selectedVariables['uwacAffiliate']
            url = selectedVariables['url']
            siteType = selectedVariables['siteType']
            formType = selectedVariables['formType']
            tealiumProfile = selectedVariables['tealiumProfile']
            sigleProgramURL = selectedVariables['sigleProgramURL']
            partnerName = selectedVariables['partnerName']
            RfiUrl = selectedVariables['RfiUrl']
            Utm = selectedVariables['Utm']
            BaseUrl = selectedVariables['BaseUrl']
            IsLanding = selectedVariables['IsLanding']
            IsInternational = selectedVariables['IsInternational']
            SchoolName = selectedVariables['SchoolName']
            SmsSchoolName = selectedVariables['SmsSchoolName']
            PostId = selectedVariables['PostId']
            TealiumProfile = selectedVariables['TealiumProfile']
            Pod = selectedVariables['Pod']
            Acronym = selectedVariables['Acronym']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
