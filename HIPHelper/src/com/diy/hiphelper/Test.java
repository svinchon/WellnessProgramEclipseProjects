package com.diy.hiphelper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Iterator;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Test {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		try {
			
			// QUERY
			String strQueryURL = "http://ec2-54-195-241-25.eu-west-1.compute.amazonaws.com/registry/xds-iti18";
			String strQuerySOAPMessageTemplate = ""
					+ "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:a=\"http://www.w3.org/2005/08/addressing\">\n"
					+ "	<soap:Header/>\n"
					+ "	<soap:Body>\n"
					+ "		<query:AdhocQueryRequest xmlns:query=\"urn:oasis:names:tc:ebxml-regrep:xsd:query:3.0\" xmlns=\"urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0\">\n"
					+ "			<query:ResponseOption returnComposedObjects=\"true\" returnType=\"LeafClass\"/>\n"
					+ "			<AdhocQuery id=\"urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d\">\n"
					+ "				<Slot name=\"$XDSDocumentEntryPatientId\">\n"
					+ "					<ValueList>\n"
					+ "						<Value>'{{PATIENT_ID}}'</Value>\n"
					+ "					</ValueList>\n"
					+ "				</Slot>\n"
					+ "				<Slot name=\"$XDSDocumentEntryStatus\">\n"
					+ "					<ValueList>\n"
					+ "						<Value>('urn:oasis:names:tc:ebxml-regrep:StatusType:Approved')</Value>\n"
					+ "					</ValueList>\n"
					+ "				</Slot>\n"
					+ "			</AdhocQuery>\n"
					+ "		</query:AdhocQueryRequest>\n"
					+ "	</soap:Body>\n"
					+ "</soap:Envelope>";
			String strPatientId = "100000103^^^&amp;1.3.6.1.4.1.21367.2005.3.7&amp;ISO";
			//String strQuerySOAPMessage = strQuerySOAPMessageTemplate.replaceAll("{{PATIENT_ID}}", strPatientId);
			
			// RETRIEVE
			String strRetrieveURL = "http://ec2-54-195-241-25.eu-west-1.compute.amazonaws.com/cs-repository/xds-iti43";
			String strRetrieveSOAPMessageTemplate = ""
					+ "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:urn=\"urn:ihe:iti:xds-b:2007\">\n"
					+ "	<soap:Header/>\n"
					+ "	<soap:Body>\n"
					+ "		<RetrieveDocumentSetRequest\n"
					+ "		xsi:schemaLocation=\"urn:ihe:iti:xds-b:2007 file:/Users/bill/ihe/Frameworks/ITI-4/XDS.b/schema/IHE/XDS.b_DocumentRepository.xsd\"\n"
					+ "		xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
					+ " 		xmlns=\"urn:ihe:iti:xds-b:2007\">\n"
					+ "			<DocumentRequest>\n"
					+ "				<RepositoryUniqueId>1.3.6.1.4.1.21367.13.40.81</RepositoryUniqueId>\n"
					+ "				<DocumentUniqueId>{{DOCUMENT_UNIQUE_ID}}</DocumentUniqueId>\n"
					+ "			</DocumentRequest>\n"
					+ "		</RetrieveDocumentSetRequest>\n"
					+ "	</soap:Body>\n"
					+ "</soap:Envelope>";
			String strDocumentUniqueId ="1.42.20140505220900.6";
			//String strRetrieveSOAPMessage = strRetrieveSOAPMessageTemplate.replaceAll("{{DOCUMENT_UNIQUE_ID}}", strDocumentUniqueId);

			// STORE
			String strStoreURL = "http://ec2-54-195-241-25.eu-west-1.compute.amazonaws.com/cs-repository/xds-iti41";
			String strStoreSOAPMessageTemplate = ""
					+ "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n"
					+ "	<soap:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">\n"
					+ "		<wsa:To>http://ec2-54-195-241-25.eu-west-1.compute.amazonaws.com/cs-repository/xds-iti41</wsa:To>\n"
					+ "		<wsa:MessageID>urn:uuid:817cda23-ec18-4cfa-afb4-b2628cacd85d</wsa:MessageID>\n"
					+ "		<wsa:Action>urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b</wsa:Action>\n"
					+ "	</soap:Header>\n"
					+ "	<soap:Body>\n"
					+ "		<xdsb:ProvideAndRegisterDocumentSetRequest\n"
					+ "		xmlns:xdsb=\"urn:ihe:iti:xds-b:2007\"\n"
					+ "		xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
					+ "		xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
					+ "			<lcm:SubmitObjectsRequest xmlns:lcm=\"urn:oasis:names:tc:ebxml-regrep:xsd:lcm:3.0\">\n"
					+ "				<rim:RegistryObjectList xmlns:rim=\"urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0\">\n"
					+ "					<!--XDSSubmissionSet-->\n"
					+ "					<rim:ObjectRef id=\"urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd\"/>\n" // cs cl10
					//+ "					<rim:ObjectRef id=\"urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d\"/>\n"+ // ??
					+ "					<rim:ObjectRef id=\"urn:uuid:aa543740-bdda-424e-8c96-df4873be8500\"/>\n" // cs cl09
					+ "					<rim:ObjectRef id=\"urn:uuid:6b5aea1a-874d-4603-a4bc-96a0a7b38446\"/>\n" // patient id?
					+ "					<rim:ObjectRef id=\"urn:uuid:554ac39e-e3fe-47fe-b233-965d2a147832\"/>\n" // source id?
					+ "					<rim:ObjectRef id=\"urn:uuid:96fdda7c-d067-4183-912e-bf5ee74998a8\"/>\n" // unique id?
					+ "					<rim:ObjectRef id=\"urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1\"/>\n" // needed for pdf?
					+ "					<rim:ObjectRef id=\"urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d\"/>\n" // classification scheme?
					+ "					<rim:ObjectRef id=\"urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a\"/>\n" // cs cl02
					+ "					<rim:ObjectRef id=\"urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f\"/>\n" // cs cl07
					+ "					<rim:ObjectRef id=\"urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4\"/>\n" // cs cl08
					+ "					<rim:ObjectRef id=\"urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d\"/>\n" // cs cl04
					+ "					<rim:ObjectRef id=\"urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1\"/>\n" // cs cl05
					+ "					<rim:ObjectRef id=\"urn:uuid:f0306f51-975f-434e-a61c-c59651d33983\"/>\n" // cs cl03 classification scheme?
					+ "					<rim:ObjectRef id=\"urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427\"/>\n" // patient id?
					+ "					<rim:ObjectRef id=\"urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab\"/>\n" // unique id
					//+ "					<rim:ExtrinsicObject id=\"Document_4\" mimeType=\"application/pdf\" objectType=\"urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1\">\n"
					+ "					<rim:ExtrinsicObject id=\"Document_SEB\" mimeType=\"application/pdf\" objectType=\"urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1\">\n"
					+ "						<rim:Slot name=\"creationTime\">\n"
					+ "							<rim:ValueList><rim:Value>20141204000000</rim:Value></rim:ValueList>\n" // adjusted.  correct?
					+ "						</rim:Slot>\n"
					+ "					<rim:Slot name=\"languageCode\">\n"
					+ "						<rim:ValueList>\n"
					+ "							<rim:Value>it-IT</rim:Value>\n" // en-US?
					+ "						</rim:ValueList>\n"
					+ "					</rim:Slot>\n"
					+ "					<rim:Slot name=\"sourcePatientId\">\n"
					+ "						<rim:ValueList>\n"
					//+ "							<rim:Value>MAVOCI^^^&amp;1.3.6.1.4.1.21367.13.20.1000&amp;ISO</rim:Value>\n"
					+ "							<rim:Value>100000103^^^&amp;1.3.6.1.4.1.21367.2005.3.7&amp;ISO</rim:Value>\n" // adjusted. correct?  how to determine value?
					+ "						</rim:ValueList>\n"
					+ "					</rim:Slot>\n"
					+ "					<rim:Slot name=\"repositoryUniqueId\">\n"
					+ "						<rim:ValueList>\n"
					//+ "							<rim:Value>home.com.id.x1v1.test.evo.3.1</rim:Value>\n"
					+ "							<rim:Value>1.3.6.1.4.1.21367.13.40.81</rim:Value>\n" // adjusted.  correct?  how to determine value?
					+ "						</rim:ValueList>\n"
					+ "					</rim:Slot>\n"
					//+ "					<rim:Classification id=\"cl01\" classificationScheme=\"urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d\" classifiedObject=\"Document_4\" nodeRepresentation=\"\">\n"
					+ "					<rim:Classification id=\"cl01\" classificationScheme=\"urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"\">\n"
					+ "						<rim:Slot name=\"authorPerson\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>TEST</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Slot name=\"legalAuthenticator\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>String</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "					</rim:Classification>\n"
					//+ "					<rim:Classification id=\"cl02\" classificationScheme=\"urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a\" classifiedObject=\"Document_4\" nodeRepresentation=\"DOC_001\">\n"
					+ "					<rim:Classification id=\"cl02\" classificationScheme=\"urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"DOC_001\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>POSTECOM classCodes</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					//+ "							<rim:LocalizedString value=\"Referto Laboratorio Analisi\"/>\n"
					+ "							<rim:LocalizedString value=\"WellnessProgram\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
					//+ "					<rim:Classification id=\"cl03\" classificationScheme=\"urn:uuid:f0306f51-975f-434e-a61c-c59651d33983\" classifiedObject=\"Document_4\" nodeRepresentation=\"28574-2\">\n"
					+ "					<rim:Classification id=\"cl03\" classificationScheme=\"urn:uuid:f0306f51-975f-434e-a61c-c59651d33983\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"28574-2\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>LOINC</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"Discharge Note\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
					//+ "					<rim:Classification id=\"cl04\" classificationScheme=\"urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d\" classifiedObject=\"Document_4\" nodeRepresentation=\"PDF\">\n"
					+ "					<rim:Classification id=\"cl04\" classificationScheme=\"urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"PDF\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>Connect-a-thon formatCodes</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"PDF\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
					//+ "					<rim:Classification id=\"cl05\" classificationScheme=\"urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1\" classifiedObject=\"Document_4\" nodeRepresentation=\"ASSETTOORGANIZZATIVO_001\">\n"
					+ "					<rim:Classification id=\"cl05\" classificationScheme=\"urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"ASSETTOORGANIZZATIVO_001\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>POSTECOM healthcareFacilityTypeCodes</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"Presidio Ospedaliero\" xml:lang=\"it-IT\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
					//+ "					<rim:Classification id=\"cl06\" classificationScheme=\"urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead\" classifiedObject=\"Document_4\" nodeRepresentation=\"394802001\">\n"
					+ "					<rim:Classification id=\"cl06\" classificationScheme=\"urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"394802001\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>2.16.840.1.113883.6.96</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"General Medicine\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
					//+ "					<rim:Classification id=\"cl07\" classificationScheme=\"urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f\" classifiedObject=\"Document_4\" nodeRepresentation=\"N\">\n"
					+ "					<rim:Classification id=\"cl07\" classificationScheme=\"urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"N\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>2.16.840.1.113883.5.25</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"Normal\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
//					+ "					<rim:Classification id=\"cl08\" classificationScheme=\"urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4\" classifiedObject=\"Document_4\" nodeRepresentation=\"AZIONECLINICA_001\">\n"
					+ "					<rim:Classification id=\"cl08\" classificationScheme=\"urn:uuid:2c6b8cb7-8b2a-4051-b291-b1ae6a575ef4\" classifiedObject=\"Document_SEB\" nodeRepresentation=\"AZIONECLINICA_001\">\n"
					+ "						<rim:Slot name=\"codingScheme\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>POSTECOM eventCodeLists</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"Ricovero Ordinario\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:Classification>\n"
					+ "					<rim:ExternalIdentifier\n"
					+ "						id=\"urn:uuid:8130bc39-d2f3-41c4-85f9-37115680bb28\""
					//+ "						registryObject=\"Document_4\""
					+ "						registryObject=\"Document_SEB\""
					+ "						identificationScheme=\"urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427\""
					//+ "						value=\"MAVOCI^^^&amp;1.3.6.1.4.1.21367.13.20.1000&amp;ISO\">\n"
					+ "						value=\"100000103^^^&amp;1.3.6.1.4.1.21367.2005.3.7&amp;ISO\">\n" // adjusted. correct?  how to determine value?
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"XDSDocumentEntry.patientId\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:ExternalIdentifier>\n"
					//+ "					<rim:ExternalIdentifier id=\"urn:uuid:6aaf7279-3eca-405a-91df-9bb31e49490a\" registryObject=\"Document_4\" identificationScheme=\"urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab\" value=\"00012\">\n"
					+ "					<rim:ExternalIdentifier "
					+ "						id=\"urn:uuid:6aaf7279-3eca-405a-91df-9bb31e49490a\""
					+ "						registryObject=\"Document_SEB\""
					+ "						identificationScheme=\"urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab\""
					+ "						value=\"99999\">\n" // 00012
					+ "						<rim:Name>\n"
					+ "							<rim:LocalizedString value=\"XDSDocumentEntry.uniqueId\"/>\n"
					+ "						</rim:Name>\n"
					+ "					</rim:ExternalIdentifier>\n"
					+ "					</rim:ExtrinsicObject>\n"
					//+ "					<rim:RegistryPackage id=\"SubmissionSet_4\">\n"
					+ "					<rim:RegistryPackage id=\"WP_RP_1\">\n"
					+ "						<rim:Slot name=\"submissionTime\">\n"
					+ "							<rim:ValueList>\n"
					+ "								<rim:Value>20130422173045</rim:Value>\n"
					+ "							</rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "						<rim:Classification id=\"cl09\" classificationScheme=\"urn:uuid:aa543740-bdda-424e-8c96-df4873be8500\" classifiedObject=\"SubmissionSet_4\" nodeRepresentation=\"Communication\">\n"
					+ "							<rim:Slot name=\"codingScheme\">\n"
					+ "								<rim:ValueList>\n"
					+ "									<rim:Value>Connect-a-thon contentTypeCodes</rim:Value>\n"
					+ "								</rim:ValueList>\n"
					+ "							</rim:Slot>\n"
					+ "							<rim:Name>\n"
					+ "								<rim:LocalizedString value=\"Communication\"/>\n"
					+ "							</rim:Name>\n"
					+ "						</rim:Classification>\n"
					+ "						<rim:ExternalIdentifier id=\"urn:uuid:85eba638-4b79-4f43-8501-962b0e27231f\" registryObject=\"SubmissionSet_4\" identificationScheme=\"urn:uuid:6b5aea1a-874d-4603-a4bc-96a0a7b38446\" value=\"100000103^^^&amp;1.3.6.1.4.1.21367.2005.3.7&amp;ISO\">\n" // adjusted
					+ "							<rim:Name>\n"
					+ "								<rim:LocalizedString value=\"XDSSubmissionSet.patientId\"/>\n"
					+ "							</rim:Name>\n"
					+ "						</rim:ExternalIdentifier>\n"
					+ "						<rim:ExternalIdentifier "
					+ "							id=\"urn:uuid:6367a0ad-e5c5-4b43-8237-0f16b26cb91a\""
					+ "							registryObject=\"SubmissionSet_4\""
					+ "							identificationScheme=\"urn:uuid:96fdda7c-d067-4183-912e-bf5ee74998a8\""
					+ "							value=\"subUniqueId\">\n"
					+ "							<rim:Name>\n"
					+ "								<rim:LocalizedString value=\"XDSSubmissionSet.uniqueId\"/>\n"
					+ "							</rim:Name>\n"
					+ "						</rim:ExternalIdentifier>\n"
					+ "						<rim:ExternalIdentifier id=\"urn:uuid:f115d760-9183-4c8a-a433-e84b0410f663\" registryObject=\"SubmissionSet_4\" identificationScheme=\"urn:uuid:554ac39e-e3fe-47fe-b233-965d2a147832\" value=\"1.3.6.1.4.1.21367.2009.1.2.196\">\n"
					+ "							<rim:Name>\n"
					+ "								<rim:LocalizedString value=\"XDSSubmissionSet.sourceId\"/>\n"
					+ "							</rim:Name>\n"
					+ "						</rim:ExternalIdentifier>\n"
					+ "					</rim:RegistryPackage>\n"
					+ "					<rim:Classification id=\"cl10\" classifiedObject=\"SubmissionSet_4\" classificationNode=\"urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd\"/>\n"
					//+ "					<rim:Association id=\"assoc1\" associationType=\"urn:oasis:names:tc:ebxml-regrep:AssociationType:HasMember\" sourceObject=\"SubmissionSet_4\" targetObject=\"Document_4\" objectType=\"urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:Association\">\n"
					+ "					<rim:Association id=\"assoc1\" associationType=\"urn:oasis:names:tc:ebxml-regrep:AssociationType:HasMember\" sourceObject=\"SubmissionSet_4\" targetObject=\"Document_SEB\" objectType=\"urn:oasis:names:tc:ebxml-regrep:ObjectType:RegistryObject:Association\">\n"
					+ "						<rim:Slot name=\"SubmissionSetStatus\">\n"
					+ "							<rim:ValueList><rim:Value>Original</rim:Value></rim:ValueList>\n"
					+ "						</rim:Slot>\n"
					+ "					</rim:Association>\n"
					+ "				</rim:RegistryObjectList>\n"
					+ "			</lcm:SubmitObjectsRequest>\n"
					//+ "			<xdsb:Document id=\"Document_4\">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48P3htbC1zdHlsZXNoZWV0IHR5cGU9InRleHQveHNsIiBocmVmPSJodHRwOi8vd3gxdjEtbGhhLmxvY2FsZG9tYWluL3hzbC9wcmVzY3JpemlvbmkueHNsIj8+PENsaW5pY2FsRG9jdW1lbnQgeG1sbnM9InVybjpobDctb3JnOnYzIiB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1haW5zdGFuY2UiPjxyZWFsbUNvZGUgY29kZT0iSVQiIC8+PHR5cGVJZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4xLjMiIGV4dGVuc2lvbj0iUE9DRF9IRDAwMDA0MCIgLz48dGVtcGxhdGVJZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuMTAuMS4yIiAvPjxpZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNC4zLjgiIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9Ik1pbmlzdGVybyBFY29ub21pYSBlIEZpbmFuemUiIGV4dGVuc2lvbj0iMDUwQTAwMDAwMDM5OTM3IiAvPjxjb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMSIgY29kZVN5c3RlbU5hbWU9IkxPSU5DIiBjb2RlU3lzdGVtVmVyc2lvbj0iMi4xOSIgY29kZT0iNTc4MzItOCIgZGlzcGxheU5hbWU9IlByZXNjcmlwdGlvbiBmb3IgZGlhZ25vc3RpYyBvciBzcGVjaWFsaXN0IGNhcmUiPjx0cmFuc2xhdGlvbiBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNS4yLjEiIGNvZGVTeXN0ZW1OYW1lPSJDbGFzc2lmaWNhemlvbmUgUHJlc2NyaXppb25lIiBjb2RlU3lzdGVtVmVyc2lvbj0iMS4wIiBjb2RlPSJQUkVTQ19TUEVDIiBkaXNwbGF5TmFtZT0iUHJlc2NyaXppb25lIHNwZWNpYWxpc3RpY2EiPjxxdWFsaWZpZXI+PG5hbWUgY29kZVN5c3RlbT0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjUuMi4xIiBjb2RlU3lzdGVtTmFtZT0iQ2xhc3NpZmljYXppb25lIFByZXNjcml6aW9uZSIgY29kZVN5c3RlbVZlcnNpb249IjEuMCIgY29kZT0iVEkiIGRpc3BsYXlOYW1lPSJUaXBvbG9naWEgaW50ZXN0YXppb25lIiAvPjx2YWx1ZSBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNS4yLjEiIGNvZGVTeXN0ZW1OYW1lPSJDbGFzc2lmaWNhemlvbmUgUHJlc2NyaXppb25lIiBjb2RlU3lzdGVtVmVyc2lvbj0iMS4wIiBjb2RlPSIwNTAiIGRpc3BsYXlOYW1lPSJSaWNldHRhIFNTTiAtIFJlZ2lvbmUgVmVuZXRvIiAvPjwvcXVhbGlmaWVyPjwvdHJhbnNsYXRpb24+PC9jb2RlPjx0aXRsZT5QcmVzY3JpemlvbmU8L3RpdGxlPjxlZmZlY3RpdmVUaW1lIHZhbHVlPSIyMDEzMDIxMzExNTAxMyswMTAwIiAvPjxjb25maWRlbnRpYWxpdHlDb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjUuMjUiIGNvZGVTeXN0ZW1OYW1lPSJDb25maWRlbnRpYWxpdHkiIGNvZGVTeXN0ZW1WZXJzaW9uPSIxIiBjb2RlPSJOIiAvPjxsYW5ndWFnZUNvZGUgY29kZT0iaXRhLUlUQSIgLz48c2V0SWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjQuMy44IiBhc3NpZ25pbmdBdXRob3JpdHlOYW1lPSJNaW5pc3Rlcm8gRWNvbm9taWEgZSBGaW5hbnplIiBleHRlbnNpb249IjA1MEEwMDAwMDAzOTkzNyIgLz48dmVyc2lvbk51bWJlciB2YWx1ZT0iMSIgLz48cmVjb3JkVGFyZ2V0PjxwYXRpZW50Um9sZT48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjIuNTAuNC4xIiBleHRlbnNpb249IjAwODc1MzQ4MDciIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9IlJlZ2lvbmUgVmVuZXRvIiAvPjxpZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNC4zLjIiIGV4dGVuc2lvbj0iUE5DR1JHOTNCMTlBNDg4VCIgYXNzaWduaW5nQXV0aG9yaXR5TmFtZT0iTWluaXN0ZXJvIEVjb25vbWlhIGUgRmluYW56ZSIgLz48YWRkciB1c2U9IkhQIj48aG91c2VOdW1iZXI+MTI8L2hvdXNlTnVtYmVyPjxzdHJlZXROYW1lPlZJQSBESSBTT1RUTzwvc3RyZWV0TmFtZT48Y2l0eT5QSU5FVE88L2NpdHk+PHBvc3RhbENvZGU+NjQwMjU8L3Bvc3RhbENvZGU+PC9hZGRyPjxwYXRpZW50PjxuYW1lPjxmYW1pbHk+UEFOSUNPPC9mYW1pbHk+PGdpdmVuPkdJT1JHSU88L2dpdmVuPjwvbmFtZT48YWRtaW5pc3RyYXRpdmVHZW5kZXJDb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjUuMSIgY29kZT0iTSIgLz48YmlydGhUaW1lIHZhbHVlPSIxOTkzMDIxOSIgLz48YmlydGhwbGFjZT48cGxhY2U+PGFkZHI+PGNpdHk+QVRSSTwvY2l0eT48Y291bnR5PjwvY291bnR5PjwvYWRkcj48L3BsYWNlPjwvYmlydGhwbGFjZT48L3BhdGllbnQ+PC9wYXRpZW50Um9sZT48L3JlY29yZFRhcmdldD48YXV0aG9yPjx0aW1lIHZhbHVlPSIyMDEzMDIxMzExNTAxMyswMTAwIiAvPjxhc3NpZ25lZEF1dGhvcj48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjUwLjQuMi4xIiBleHRlbnNpb249IjEzNTciIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9IlJlZ2lvbmUgVmVuZXRvIiAvPjxpZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNC4zLjIiIGV4dGVuc2lvbj0iTkxEU1JHNjVCMjNENjEyVCIgYXNzaWduaW5nQXV0aG9yaXR5TmFtZT0iTWluaXN0ZXJvIEVjb25vbWlhIGUgRmluYW56ZSIgLz48L2Fzc2lnbmVkQXV0aG9yPjwvYXV0aG9yPjxjdXN0b2RpYW4+PGFzc2lnbmVkQ3VzdG9kaWFuPjxyZXByZXNlbnRlZEN1c3RvZGlhbk9yZ2FuaXphdGlvbj48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjQuMS4xIiBleHRlbnNpb249IjA1MDEwMSIgYXNzaWduaW5nQXV0aG9yaXR5TmFtZT0iTWluaXN0ZXJvIGRlbGxhIFNhbHV0ZSIgLz48L3JlcHJlc2VudGVkQ3VzdG9kaWFuT3JnYW5pemF0aW9uPjwvYXNzaWduZWRDdXN0b2RpYW4+PC9jdXN0b2RpYW4+PGxlZ2FsQXV0aGVudGljYXRvcj48dGltZSB2YWx1ZT0iMjAxMzAyMTMxMTUwMTMrMDEwMCIgLz48c2lnbmF0dXJlQ29kZSBjb2RlPSJTIiAvPjxhc3NpZ25lZEVudGl0eT48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjQuMy4yIiBleHRlbnNpb249Ik5MRFNSRzY1QjIzRDYxMlQiIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9Ik1pbmlzdGVybyBFY29ub21pYSBlIEZpbmFuemUiIC8+PC9hc3NpZ25lZEVudGl0eT48L2xlZ2FsQXV0aGVudGljYXRvcj48cmVsYXRlZERvY3VtZW50IHR5cGVDb2RlPSJYRlJNIj48cGFyZW50RG9jdW1lbnQgY2xhc3NDb2RlPSJET0NDTElOIiBtb29kQ29kZT0iRVZOIj48aWQgbnVsbEZsYXZvcj0iTkEiIC8+PC9wYXJlbnREb2N1bWVudD48L3JlbGF0ZWREb2N1bWVudD48Y29tcG9uZW50PjxzdHJ1Y3R1cmVkQm9keSBtb29kQ29kZT0iRVZOIiBjbGFzc0NvZGU9IkRPQ0JPRFkiPjxjb21wb25lbnQ+PHNlY3Rpb24+PGNvZGUgY29kZT0iNTc4MjctOCIgZGlzcGxheU5hbWU9IlJlYXNvbiBmb3IgY28tcGF5bWVudCBleGVtcHRpb24iIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMSIgY29kZVN5c3RlbU5hbWU9IkxPSU5DIiBjb2RlU3lzdGVtVmVyc2lvbj0iMi4xOSIgLz48dGl0bGU+RXNlbnppb25pPC90aXRsZT48dGV4dD48Y29udGVudCBJRD0iZXNlbnpfMSI+TmVzc3VuYSBlc2VuemlvbmU8L2NvbnRlbnQ+PC90ZXh0PjxlbnRyeT48YWN0IGNsYXNzQ29kZT0iQUNUIiBtb29kQ29kZT0iRVZOIj48Y29kZSBjb2RlU3lzdGVtTmFtZT0iVGFiZWxsYSBlc2VuemlvbmkiIGNvZGU9Ik5FIiBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNS4yLjIiPjxvcmlnaW5hbFRleHQ+PHJlZmVyZW5jZSB2YWx1ZT0iI2VzZW56XzEiIC8+PC9vcmlnaW5hbFRleHQ+PC9jb2RlPjwvYWN0PjwvZW50cnk+PC9zZWN0aW9uPjwvY29tcG9uZW50Pjxjb21wb25lbnQ+PHNlY3Rpb24+PGNvZGUgY29kZT0iNTc4MjgtNiIgZGlzcGxheU5hbWU9IlByZXNjcmlwdGlvbnMiIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMSIgY29kZVN5c3RlbU5hbWU9IkxPSU5DIiBjb2RlU3lzdGVtVmVyc2lvbj0iMi4xOSIgLz48dGl0bGU+UHJlc2NyaXppb25pPC90aXRsZT48dGV4dD48bGlzdCBJRD0iUlFPIj48Y2FwdGlvbj5QcmVzdGF6aW9uaSByaWNoaWVzdGU8L2NhcHRpb24+PGl0ZW0+PGNvbnRlbnQgSUQ9InByZXN0XzEiPlRBQyBBRERPTUUgQ09NUExFVE8gK0NPTlRSQVNUTzwvY29udGVudD48L2l0ZW0+PC9saXN0PjwvdGV4dD48ZW50cnk+PG9ic2VydmF0aW9uIGNsYXNzQ29kZT0iT0JTIiBtb29kQ29kZT0iUlFPIj48Y29kZSBjb2RlPSI4OC4wMS42IiBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuMi41MC42LjExIiBjb2RlU3lzdGVtTmFtZT0iRGl6aW9uYXJpbyByZWdpb25hbGUgZGVsbGUgcHJlc3Rhemlvbmkgc2FuaXRhcmllIiBjb2RlU3lzdGVtVmVyc2lvbj0iVUsiPjxvcmlnaW5hbFRleHQ+PHJlZmVyZW5jZSB2YWx1ZT0iI3ByZXN0XzEiIC8+PC9vcmlnaW5hbFRleHQ+PC9jb2RlPjxyZXBlYXROdW1iZXIgdmFsdWU9IjEiIC8+PGVudHJ5UmVsYXRpb25zaGlwIHR5cGVDb2RlPSJSU09OIj48b2JzZXJ2YXRpb24gY2xhc3NDb2RlPSJPQlMiIG1vb2RDb2RlPSJFVk4iPjxpZCByb290PSIxLjIuMy40IiBleHRlbnNpb249ImltcF8xIiAvPjxjb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMTAzIiBjb2RlU3lzdGVtTmFtZT0iSUNELTlDTSIgY29kZT0iNzk5LjkiIC8+PC9vYnNlcnZhdGlvbj48L2VudHJ5UmVsYXRpb25zaGlwPjwvb2JzZXJ2YXRpb24+PC9lbnRyeT48L3NlY3Rpb24+PC9jb21wb25lbnQ+PC9zdHJ1Y3R1cmVkQm9keT48L2NvbXBvbmVudD48L0NsaW5pY2FsRG9jdW1lbnQ+</xdsb:Document>\n"
					+ "			<xdsb:Document id=\"Document_SEB\">PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48P3htbC1zdHlsZXNoZWV0IHR5cGU9InRleHQveHNsIiBocmVmPSJodHRwOi8vd3gxdjEtbGhhLmxvY2FsZG9tYWluL3hzbC9wcmVzY3JpemlvbmkueHNsIj8+PENsaW5pY2FsRG9jdW1lbnQgeG1sbnM9InVybjpobDctb3JnOnYzIiB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1haW5zdGFuY2UiPjxyZWFsbUNvZGUgY29kZT0iSVQiIC8+PHR5cGVJZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4xLjMiIGV4dGVuc2lvbj0iUE9DRF9IRDAwMDA0MCIgLz48dGVtcGxhdGVJZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuMTAuMS4yIiAvPjxpZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNC4zLjgiIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9Ik1pbmlzdGVybyBFY29ub21pYSBlIEZpbmFuemUiIGV4dGVuc2lvbj0iMDUwQTAwMDAwMDM5OTM3IiAvPjxjb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMSIgY29kZVN5c3RlbU5hbWU9IkxPSU5DIiBjb2RlU3lzdGVtVmVyc2lvbj0iMi4xOSIgY29kZT0iNTc4MzItOCIgZGlzcGxheU5hbWU9IlByZXNjcmlwdGlvbiBmb3IgZGlhZ25vc3RpYyBvciBzcGVjaWFsaXN0IGNhcmUiPjx0cmFuc2xhdGlvbiBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNS4yLjEiIGNvZGVTeXN0ZW1OYW1lPSJDbGFzc2lmaWNhemlvbmUgUHJlc2NyaXppb25lIiBjb2RlU3lzdGVtVmVyc2lvbj0iMS4wIiBjb2RlPSJQUkVTQ19TUEVDIiBkaXNwbGF5TmFtZT0iUHJlc2NyaXppb25lIHNwZWNpYWxpc3RpY2EiPjxxdWFsaWZpZXI+PG5hbWUgY29kZVN5c3RlbT0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjUuMi4xIiBjb2RlU3lzdGVtTmFtZT0iQ2xhc3NpZmljYXppb25lIFByZXNjcml6aW9uZSIgY29kZVN5c3RlbVZlcnNpb249IjEuMCIgY29kZT0iVEkiIGRpc3BsYXlOYW1lPSJUaXBvbG9naWEgaW50ZXN0YXppb25lIiAvPjx2YWx1ZSBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNS4yLjEiIGNvZGVTeXN0ZW1OYW1lPSJDbGFzc2lmaWNhemlvbmUgUHJlc2NyaXppb25lIiBjb2RlU3lzdGVtVmVyc2lvbj0iMS4wIiBjb2RlPSIwNTAiIGRpc3BsYXlOYW1lPSJSaWNldHRhIFNTTiAtIFJlZ2lvbmUgVmVuZXRvIiAvPjwvcXVhbGlmaWVyPjwvdHJhbnNsYXRpb24+PC9jb2RlPjx0aXRsZT5QcmVzY3JpemlvbmU8L3RpdGxlPjxlZmZlY3RpdmVUaW1lIHZhbHVlPSIyMDEzMDIxMzExNTAxMyswMTAwIiAvPjxjb25maWRlbnRpYWxpdHlDb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjUuMjUiIGNvZGVTeXN0ZW1OYW1lPSJDb25maWRlbnRpYWxpdHkiIGNvZGVTeXN0ZW1WZXJzaW9uPSIxIiBjb2RlPSJOIiAvPjxsYW5ndWFnZUNvZGUgY29kZT0iaXRhLUlUQSIgLz48c2V0SWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjQuMy44IiBhc3NpZ25pbmdBdXRob3JpdHlOYW1lPSJNaW5pc3Rlcm8gRWNvbm9taWEgZSBGaW5hbnplIiBleHRlbnNpb249IjA1MEEwMDAwMDAzOTkzNyIgLz48dmVyc2lvbk51bWJlciB2YWx1ZT0iMSIgLz48cmVjb3JkVGFyZ2V0PjxwYXRpZW50Um9sZT48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjIuNTAuNC4xIiBleHRlbnNpb249IjAwODc1MzQ4MDciIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9IlJlZ2lvbmUgVmVuZXRvIiAvPjxpZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNC4zLjIiIGV4dGVuc2lvbj0iUE5DR1JHOTNCMTlBNDg4VCIgYXNzaWduaW5nQXV0aG9yaXR5TmFtZT0iTWluaXN0ZXJvIEVjb25vbWlhIGUgRmluYW56ZSIgLz48YWRkciB1c2U9IkhQIj48aG91c2VOdW1iZXI+MTI8L2hvdXNlTnVtYmVyPjxzdHJlZXROYW1lPlZJQSBESSBTT1RUTzwvc3RyZWV0TmFtZT48Y2l0eT5QSU5FVE88L2NpdHk+PHBvc3RhbENvZGU+NjQwMjU8L3Bvc3RhbENvZGU+PC9hZGRyPjxwYXRpZW50PjxuYW1lPjxmYW1pbHk+UEFOSUNPPC9mYW1pbHk+PGdpdmVuPkdJT1JHSU88L2dpdmVuPjwvbmFtZT48YWRtaW5pc3RyYXRpdmVHZW5kZXJDb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjUuMSIgY29kZT0iTSIgLz48YmlydGhUaW1lIHZhbHVlPSIxOTkzMDIxOSIgLz48YmlydGhwbGFjZT48cGxhY2U+PGFkZHI+PGNpdHk+QVRSSTwvY2l0eT48Y291bnR5PjwvY291bnR5PjwvYWRkcj48L3BsYWNlPjwvYmlydGhwbGFjZT48L3BhdGllbnQ+PC9wYXRpZW50Um9sZT48L3JlY29yZFRhcmdldD48YXV0aG9yPjx0aW1lIHZhbHVlPSIyMDEzMDIxMzExNTAxMyswMTAwIiAvPjxhc3NpZ25lZEF1dGhvcj48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjUwLjQuMi4xIiBleHRlbnNpb249IjEzNTciIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9IlJlZ2lvbmUgVmVuZXRvIiAvPjxpZCByb290PSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNC4zLjIiIGV4dGVuc2lvbj0iTkxEU1JHNjVCMjNENjEyVCIgYXNzaWduaW5nQXV0aG9yaXR5TmFtZT0iTWluaXN0ZXJvIEVjb25vbWlhIGUgRmluYW56ZSIgLz48L2Fzc2lnbmVkQXV0aG9yPjwvYXV0aG9yPjxjdXN0b2RpYW4+PGFzc2lnbmVkQ3VzdG9kaWFuPjxyZXByZXNlbnRlZEN1c3RvZGlhbk9yZ2FuaXphdGlvbj48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjQuMS4xIiBleHRlbnNpb249IjA1MDEwMSIgYXNzaWduaW5nQXV0aG9yaXR5TmFtZT0iTWluaXN0ZXJvIGRlbGxhIFNhbHV0ZSIgLz48L3JlcHJlc2VudGVkQ3VzdG9kaWFuT3JnYW5pemF0aW9uPjwvYXNzaWduZWRDdXN0b2RpYW4+PC9jdXN0b2RpYW4+PGxlZ2FsQXV0aGVudGljYXRvcj48dGltZSB2YWx1ZT0iMjAxMzAyMTMxMTUwMTMrMDEwMCIgLz48c2lnbmF0dXJlQ29kZSBjb2RlPSJTIiAvPjxhc3NpZ25lZEVudGl0eT48aWQgcm9vdD0iMi4xNi44NDAuMS4xMTM4ODMuMi45LjQuMy4yIiBleHRlbnNpb249Ik5MRFNSRzY1QjIzRDYxMlQiIGFzc2lnbmluZ0F1dGhvcml0eU5hbWU9Ik1pbmlzdGVybyBFY29ub21pYSBlIEZpbmFuemUiIC8+PC9hc3NpZ25lZEVudGl0eT48L2xlZ2FsQXV0aGVudGljYXRvcj48cmVsYXRlZERvY3VtZW50IHR5cGVDb2RlPSJYRlJNIj48cGFyZW50RG9jdW1lbnQgY2xhc3NDb2RlPSJET0NDTElOIiBtb29kQ29kZT0iRVZOIj48aWQgbnVsbEZsYXZvcj0iTkEiIC8+PC9wYXJlbnREb2N1bWVudD48L3JlbGF0ZWREb2N1bWVudD48Y29tcG9uZW50PjxzdHJ1Y3R1cmVkQm9keSBtb29kQ29kZT0iRVZOIiBjbGFzc0NvZGU9IkRPQ0JPRFkiPjxjb21wb25lbnQ+PHNlY3Rpb24+PGNvZGUgY29kZT0iNTc4MjctOCIgZGlzcGxheU5hbWU9IlJlYXNvbiBmb3IgY28tcGF5bWVudCBleGVtcHRpb24iIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMSIgY29kZVN5c3RlbU5hbWU9IkxPSU5DIiBjb2RlU3lzdGVtVmVyc2lvbj0iMi4xOSIgLz48dGl0bGU+RXNlbnppb25pPC90aXRsZT48dGV4dD48Y29udGVudCBJRD0iZXNlbnpfMSI+TmVzc3VuYSBlc2VuemlvbmU8L2NvbnRlbnQ+PC90ZXh0PjxlbnRyeT48YWN0IGNsYXNzQ29kZT0iQUNUIiBtb29kQ29kZT0iRVZOIj48Y29kZSBjb2RlU3lzdGVtTmFtZT0iVGFiZWxsYSBlc2VuemlvbmkiIGNvZGU9Ik5FIiBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuNS4yLjIiPjxvcmlnaW5hbFRleHQ+PHJlZmVyZW5jZSB2YWx1ZT0iI2VzZW56XzEiIC8+PC9vcmlnaW5hbFRleHQ+PC9jb2RlPjwvYWN0PjwvZW50cnk+PC9zZWN0aW9uPjwvY29tcG9uZW50Pjxjb21wb25lbnQ+PHNlY3Rpb24+PGNvZGUgY29kZT0iNTc4MjgtNiIgZGlzcGxheU5hbWU9IlByZXNjcmlwdGlvbnMiIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMSIgY29kZVN5c3RlbU5hbWU9IkxPSU5DIiBjb2RlU3lzdGVtVmVyc2lvbj0iMi4xOSIgLz48dGl0bGU+UHJlc2NyaXppb25pPC90aXRsZT48dGV4dD48bGlzdCBJRD0iUlFPIj48Y2FwdGlvbj5QcmVzdGF6aW9uaSByaWNoaWVzdGU8L2NhcHRpb24+PGl0ZW0+PGNvbnRlbnQgSUQ9InByZXN0XzEiPlRBQyBBRERPTUUgQ09NUExFVE8gK0NPTlRSQVNUTzwvY29udGVudD48L2l0ZW0+PC9saXN0PjwvdGV4dD48ZW50cnk+PG9ic2VydmF0aW9uIGNsYXNzQ29kZT0iT0JTIiBtb29kQ29kZT0iUlFPIj48Y29kZSBjb2RlPSI4OC4wMS42IiBjb2RlU3lzdGVtPSIyLjE2Ljg0MC4xLjExMzg4My4yLjkuMi41MC42LjExIiBjb2RlU3lzdGVtTmFtZT0iRGl6aW9uYXJpbyByZWdpb25hbGUgZGVsbGUgcHJlc3Rhemlvbmkgc2FuaXRhcmllIiBjb2RlU3lzdGVtVmVyc2lvbj0iVUsiPjxvcmlnaW5hbFRleHQ+PHJlZmVyZW5jZSB2YWx1ZT0iI3ByZXN0XzEiIC8+PC9vcmlnaW5hbFRleHQ+PC9jb2RlPjxyZXBlYXROdW1iZXIgdmFsdWU9IjEiIC8+PGVudHJ5UmVsYXRpb25zaGlwIHR5cGVDb2RlPSJSU09OIj48b2JzZXJ2YXRpb24gY2xhc3NDb2RlPSJPQlMiIG1vb2RDb2RlPSJFVk4iPjxpZCByb290PSIxLjIuMy40IiBleHRlbnNpb249ImltcF8xIiAvPjxjb2RlIGNvZGVTeXN0ZW09IjIuMTYuODQwLjEuMTEzODgzLjYuMTAzIiBjb2RlU3lzdGVtTmFtZT0iSUNELTlDTSIgY29kZT0iNzk5LjkiIC8+PC9vYnNlcnZhdGlvbj48L2VudHJ5UmVsYXRpb25zaGlwPjwvb2JzZXJ2YXRpb24+PC9lbnRyeT48L3NlY3Rpb24+PC9jb21wb25lbnQ+PC9zdHJ1Y3R1cmVkQm9keT48L2NvbXBvbmVudD48L0NsaW5pY2FsRG9jdW1lbnQ+</xdsb:Document>\n"
					+ "		</xdsb:ProvideAndRegisterDocumentSetRequest>\n"
					+ "	</soap:Body>\n"
					+ "</soap:Envelope>";
			String strStoreSOAPMessage = strStoreSOAPMessageTemplate;
			
			// SOAP CALL
			String url;
			String strSOAPMessage;

			//strSOAPMessage = strQuerySOAPMessage;
			//url = strQueryURL;
			strSOAPMessage = strStoreSOAPMessage;
			url = strStoreURL;

			SOAPMessage soapRequest = getSoapMessageFromString(strSOAPMessage);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			soapConnection.close();

			// Soap Messages
			System.out.println(strSOAPMessage);
			System.out.println("Response:\n"+getSOAPResponseAsString(soapResponse));

			// Soap Attachments
			Iterator i = soapResponse.getAttachments();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			while (i.hasNext()) {
				System.out.println("Attachement:\n");
				AttachmentPart ap = (AttachmentPart)(i.next());
				//StreamSource ss;
				//StringReader sr;
				Source sourceContent = new StreamSource(
						new StringReader(
								new String(((String)((ap).getContent())))
						)
				);
				StringWriter sw = new StringWriter();
				StreamResult xmlOutput=new StreamResult(sw);
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
		        transformer.transform(sourceContent, xmlOutput);						
		        System.out.println(xmlOutput.getWriter().toString());
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
	}

	// another way to build a Soap Message
	@SuppressWarnings("unused")
	private SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		String serverURI = "HealthProgram";
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("heal", serverURI);
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("SubmitRegistrationRequest", "heal");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("first_name");
		//soapBodyElem1.addTextNode(firstName);
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("weight");
		//soapBodyElem2.addTextNode(weight);
		SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("email");
		//soapBodyElem4.addTextNode(email);
		SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("age");
		soapBodyElem5.addTextNode("42");
		SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("last_name");
		//soapBodyElem6.addTextNode(lastName);
		SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("gender");
		//soapBodyElem7.addTextNode(gender);
		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI +"/"+serverURI+"/"+"SubmitRegistration");
		soapMessage.saveChanges();
		System.out.print("Request SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();
		return soapMessage;
	}

	private static String getSOAPResponseAsString(SOAPMessage soapResponse) throws SOAPException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		StringWriter sw = new StringWriter();
		StreamResult xmlOutput=new StreamResult(sw);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        transformer.transform(sourceContent, xmlOutput);
        return xmlOutput.getWriter().toString();
	}

}
