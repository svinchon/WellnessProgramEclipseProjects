package com.diy.hiphelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.diy.xmlhelper.XMLHelper;

public class HIPHelper {

	public static void main(String[] args) {
		HIPHelper hiphop = new HIPHelper();
		//hiphop.storeDoc("PAT01", "DOC08", "<root>hello world</root>".getBytes());
		String[] strDocNames = hiphop.getPatientDocNamesList("PAT01");
		for (int i=0;i<strDocNames.length;i++) {
			Log(strDocNames[i]);
		}
		//hiphop.getRetrieveDocForPatient("PAT01", "DOC04");
		//Log(new String(hiphop.getDocContent("PAT01", "DOC08")));
		Log(new String(hiphop.getDocContent("WP_DOC_ENTRY_ID_PAT01_DOC08")));
	}

	public String[] getPatientDocNamesList(String strPatientId) {
		String[] strDocNames = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
			String strURL = rb.getString("iti18url");
			String strSOAPMessage = new SOAPQueryRequestMessage(strPatientId).getXML();
			SOAPMessage soapRequest = getSoapMessageFromString(strSOAPMessage);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			String strSOAPResponse = getSOAPResponseAsString(soapResponse);
			//Log("SOAP Request:\n"); Log(strSOAPMessage); Log("SOAP Response:\n"); Log(strSOAPResponse);
			String strXPath = "/soap:Envelope/soap:Body/ns4:AdhocQueryResponse/ns2:RegistryObjectList/ns2:ExtrinsicObject/ns2:ExternalIdentifier[ns2:Name/ns2:LocalizedString/@value = \"XDSDocumentEntry.uniqueId\"]/@value";
			strDocNames = XMLHelper.strGetValuesFromXML(strSOAPResponse, strXPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDocNames;
	}
	
	public String getPatientDocsXML(String strPatientId, String strDocId) {
		String strReturn = "";
		try {
			ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
			String strURL = rb.getString("iti43url");
			String strSOAPMessage = new SOAPRetrieveDocRequestMessage(strPatientId, strDocId).getXML();
			SOAPMessage soapRequest = getSoapMessageFromString(strSOAPMessage);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			//String strSOAPResponse = getSOAPResponseAsString(soapResponse); Log("SOAP Request:\n"); Log(strSOAPMessage); Log("SOAP Response:\n"); Log(strSOAPResponse);
			Iterator<?> i = soapResponse.getAttachments();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			while (i.hasNext()) {
				Log("Attachement:\n");
				AttachmentPart ap = (AttachmentPart)(i.next());
				Source sourceContent = new StreamSource(new StringReader(new String((ap).getRawContentBytes())));
				StringWriter sw = new StringWriter();
				StreamResult xmlOutput=new StreamResult(sw);
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			    transformer.transform(sourceContent, xmlOutput);						
			    strReturn = xmlOutput.getWriter().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;
	}
	
	public byte[] getDocContent(String strDocId) {
		byte[] bReturn = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
			String strURL = rb.getString("iti43url");
			String strSOAPMessage = new SOAPRetrieveDocRequestMessage(strDocId).getXML();
			SOAPMessage soapRequest = getSoapMessageFromString(strSOAPMessage);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			//String strSOAPResponse = getSOAPResponseAsString(soapResponse); Log("SOAP Request:\n"); Log(strSOAPMessage); Log("SOAP Response:\n"); Log(strSOAPResponse);
			Iterator<?> i = soapResponse.getAttachments();
			if (i.hasNext()) {
				AttachmentPart ap = (AttachmentPart)(i.next());
				bReturn = ap.getRawContentBytes();
				//Log("SOAP Attachement:\n"); Log(new String(bReturn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bReturn;
	}

	public byte[] getDocContent(String strPatientId, String strDocId) {
		byte[] bReturn = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
			String strURL = rb.getString("iti43url");
			String strSOAPMessage = new SOAPRetrieveDocRequestMessage(strPatientId, strDocId).getXML();
			SOAPMessage soapRequest = getSoapMessageFromString(strSOAPMessage);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			//String strSOAPResponse = getSOAPResponseAsString(soapResponse); Log("SOAP Request:\n"); Log(strSOAPMessage); Log("SOAP Response:\n"); Log(strSOAPResponse);
			Iterator<?> i = soapResponse.getAttachments();
			if (i.hasNext()) {
				AttachmentPart ap = (AttachmentPart)(i.next());
				bReturn = ap.getRawContentBytes();
				//Log("SOAP Attachement:\n"); Log(new String(bReturn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bReturn;
	}

	public void storeDoc(String strPatientId, String strDocId, byte[] bDoc) {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
			String strURL = rb.getString("iti41url");
			String strSOAPMessage = new SOAPStoreDocRequestMessage(strPatientId, strDocId, bDoc).getXML();
			SOAPMessage soapRequest = getSoapMessageFromString(strSOAPMessage);
			AttachmentPart  attachmentPart = soapRequest.createAttachmentPart();
			String contentType = "application/xml";
			attachmentPart.setContent(new String(bDoc), contentType);
			attachmentPart.setContentId("DOC_ATT_ID"+"_"+strPatientId+"_"+strDocId);
			soapRequest.addAttachmentPart(attachmentPart);
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			@SuppressWarnings("unused")
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			//String strSOAPResponse = getSOAPResponseAsString(soapResponse); Log("SOAP Request:\n"); Log(strSOAPMessage); Log("SOAP Response:\n"); Log(strSOAPResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
		return message;
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
	
	private static void Log(String string) {
		System.out.println(string);
	}
	
}
