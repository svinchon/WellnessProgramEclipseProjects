package com.diy.hiphelper;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.diy.xmlhelper.XMLHelper;

public class HIPHelper {

	public static void main(String[] args) {
		try {
			Log("<LOG>");
			HIPHelper hiphop = new HIPHelper();
//			Log("<CALL>");
//			hiphop.storeDoc("WP_BATCH_RUN", "SEB_DOC_08", "<root>hello world</root>".getBytes());
//			Log("</CALL>");
			//String[] strDocNames = hiphop.getPatientDocNamesList("WP_BATCH_RUN");
			//String patientId="111111";
			DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd 00:00:00.000000000");
			DateTimeFormatter format2 = DateTimeFormat.forPattern("yyyyMMdd");
			DateTime now = new DateTime();
			now = new DateTime("2015-03-27");
			System.out.println("Previous :" + format.print(now));
			DateTime oneDayAgo = now.minusDays(1);
			System.out.println("Updated :" + format.print(oneDayAgo));
			//String today = new SimpleDateFormat(
			//		"yyyyMMdd",
			//		Locale.US
			//		).format(new Date());
			int iPatientId=111111;
			for (int p = iPatientId;p<iPatientId+20;p++) {
				Log("<CALL>");
				String[] strDocNames = hiphop.getPatientDocNamesList(""+p);
				Log("</CALL>");
				Log("<OUTPUT>");
				//Log("nDocuments:");
				Log("Documents found for patient id "+p);
				for (int i=0;i<strDocNames.length;i++) {
					Log(strDocNames[i]);
				}
				Log("</OUTPUT>");
				//byte[] bDoc = hiphop.getDocContentByDocId("BATCH_1421915624");
				for (int i=0;i<strDocNames.length;i++) {
					Log(format2.print(oneDayAgo));
					if (strDocNames[i].indexOf(format2.print(oneDayAgo)) >=0) {
						String docName = strDocNames[i];
						Log("<CALL>");
						byte[] bDoc = hiphop.getDocContentByDocId(docName);
						Log("Found and retrived "+docName+" that is dated from today for patient id "+p);
						Log("</CALL>");
						Log("<OUTPUT>");
						Log(new String(bDoc));
						Log("</OUTPUT>");
						FileOutputStream fos = new FileOutputStream(
								//"C:/Users/dmadmin/Desktop/VitexData/"
								"T:/Exchange/VitexData/"
								+ docName
								);
						fos.write(bDoc);
						fos.close();					
					}
				}
			}
			Log("</LOG>");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			if (ResourceBundle.getBundle("HIPHelper").getString("DebugGetPatientDocNames").equals("true")) {
				Log("<SOAP_CALL>");
				Log("<MESSAGE>==================== LIST ====================</MESSAGE>");
				Log("<REQUEST>"); Log(strSOAPMessage); Log("</REQUEST>"); 
				Log("<RESPONSE>"); Log(strSOAPResponse.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "")); Log("</RESPONSE>");
				Log("</SOAP_CALL>");
			}
			String strXPath = "/soap:Envelope/soap:Body/ns4:AdhocQueryResponse/ns2:RegistryObjectList/ns2:ExtrinsicObject/ns2:ExternalIdentifier[ns2:Name/ns2:LocalizedString/@value = \"XDSDocumentEntry.uniqueId\"]/@value";
			strDocNames = XMLHelper.strGetValuesFromXML(strSOAPResponse, strXPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDocNames;
	}
	
	// first version of code to retrieve doc
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
				Source sourceContent = new StreamSource(new StringReader(new String((String) (ap).getContent())));
				//.getRawContentBytes())));
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
	
	public byte[] getDocContentByDocId(String strDocId) {
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
			String strSOAPResponse = getSOAPResponseAsString(soapResponse);
			if (ResourceBundle.getBundle("HIPHelper").getString("DebugGetDocContent").equals("true")) {
				Log("<SOAP_CALL>");
				Log("<MESSAGE>==================== RETRIEVE ====================</MESSAGE>");
				Log("<REQUEST>"); Log(strSOAPMessage); Log("</REQUEST>"); 
				Log("<RESPONSE>"); Log(strSOAPResponse.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "")); Log("</RESPONSE>");
				Log("</SOAP_CALL>");				
			}
			Iterator<?> i = soapResponse.getAttachments();
			if (i.hasNext()) {
				AttachmentPart ap = (AttachmentPart)(i.next());
				StreamSource ss = (StreamSource) ap.getContent();
				bReturn = new byte[ss.getInputStream().available()];
				ss.getInputStream().read(bReturn);
				//Log("SOAP Attachement:\n"); Log(new String(bReturn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bReturn;
	}

	public byte[] getDocContentByPatientIdAndDocId(String strPatientId, String strDocId) {
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
				bReturn = ((String)(ap.getContent())).getBytes();
				//bReturn = ap.getRawContentBytes();
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
			//@SuppressWarnings("unused")
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			String strSOAPResponse = getSOAPResponseAsString(soapResponse);
			if (ResourceBundle.getBundle("HIPHelper").getString("DebugStoreDoc").equals("true")) {
				Log("<SOAP_CALL>");
				Log("<MESSAGE>==================== STORE ====================</MESSAGE>");
				Log("<REQUEST>"); Log(strSOAPMessage); Log("</REQUEST>"); 
				Log("<RESPONSE>"); Log(strSOAPResponse.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "")); Log("</RESPONSE>");
				Log("</SOAP_CALL>");				
			}
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
