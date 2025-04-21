<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>QA Leads Pipeline Endpoint</description>
   <name>QA Leads Pipeline</name>
   <tag></tag>
   <elementGuidId>c42b1095-bf46-44a4-804b-1b69a6e12ea8</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n\t\&quot;firstName\&quot;: ${firstName},\n\t\&quot;lastName\&quot;: \&quot;Grajtest42122\&quot;,\n\t\&quot;email\&quot;: \&quot;Robtest1Grajtest42122@test.com\&quot;,\n\t\&quot;city\&quot; : \&quot;Louisville\&quot;,\n\t\&quot;state\&quot;: \&quot;KY\&quot;,\n\t\&quot;zip\&quot;: \&quot;40202\&quot;,\n\t\&quot;phone\&quot;: \&quot;502-555-5555\&quot;,\n\t\&quot;accountId\&quot;: \&quot;e682e0da-78ce-457c-9b92-8add0c64520f\&quot;,\n\t\&quot;program\&quot;: \&quot;a72aad03-7946-25bd-80aa-6c61e3836a19\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://rfi.qa.edu.help/v1/leads/sites</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'Robtest42122'</defaultValue>
      <description></description>
      <id>8933019c-75f4-4d08-bd51-22454277a4ca</id>
      <masked>false</masked>
      <name>firstName</name>
   </variables>
   <variables>
      <defaultValue>'Grajtest42122'</defaultValue>
      <description></description>
      <id>2f44b579-5f90-481b-8c49-2e55cab1e2dd</id>
      <masked>false</masked>
      <name>lastName</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>3b030d3d-a46d-47b1-8c10-6a4d002c0e60</id>
      <masked>false</masked>
      <name>program</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>3dbbf694-261e-4638-86c9-d21c06123bf3</id>
      <masked>false</masked>
      <name>accountId</name>
   </variables>
   <variables>
      <defaultValue>'Robtest1Grajtest42122@test.com'</defaultValue>
      <description></description>
      <id>523e6b57-ab65-4a00-b953-714be735fc32</id>
      <masked>false</masked>
      <name>email</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
