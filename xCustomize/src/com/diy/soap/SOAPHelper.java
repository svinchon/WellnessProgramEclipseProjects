package com.diy.soap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import com.diy.html.HTMLHelperProxy;

public class SOAPHelper {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			FileInputStream fis1 = new FileInputStream(
				new File(
					"C:/GIT/WellnessProgramXPression/xDWTemplates/MonthlyReport.html"
				)
			);
			byte[] bHTMLIn = new byte[fis1.available()];
			fis1.read(bHTMLIn);
			FileOutputStream fos = new FileOutputStream(
				new File(
					"C:/GIT/WellnessProgramXPression/xDWTemplates/xMonthlyReport02Final2.html"
				)
			);
			String strSOAPIn = ""
					+ "<soapenv:Envelope "
					+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
					+ "xmlns:html=\"http://html.diy.com\""
					+ ">"
					+ "<soapenv:Header/>"
					+ "<soapenv:Body>"
					+ "<html:EmbedImages>"
					+ "<html:strHTML>"
					//+ "cid:824151026447"
					+ "<![CDATA["
					+ new String(bHTMLIn)
					+ "]]>"
					+ "</html:strHTML>"
					+ "</html:EmbedImages>"
					+ "</soapenv:Body>"
					+ "</soapenv:Envelope>";
			//System.out.println(strSOAPIn);
			/*
			byte[] bHTMLOut = getValueFromXML(
					new SOAPHelper().call(
						//"http://localhost:18080/HTMLHelper/services/HTMLHelper?wsdl",
						"http://localhost:18080/HTMLHelper/services/HTMLHelper",
						strSOAPIn
					),
					"/soapenv:Envelope/soapenv:Body/EmbedImagesResponse/EmbedImagesReturn"
				)
				.getBytes();
			fos.write(bHTMLOut);*/
			HTMLHelperProxy hhp;
			hhp = new HTMLHelperProxy();
			String HTMLOut = hhp.embedImages(new String(bHTMLIn));
			fos.write(HTMLOut.getBytes());
			System.out.println(HTMLOut);
			fos.close();
			fis1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String call(String strURL, String strRequestXML) {
		String strReturn = null;
		try {
			// turn string into soap input
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage soapRequest = factory.createMessage(
					new MimeHeaders(),
					new ByteArrayInputStream(
							strRequestXML.getBytes(Charset.forName("UTF-8"))
					)
			);
			//soapRequest.getSOAPHeader()
			//http://html.diy.com/HTMLHelper/bEmbedImagesRequest
			MimeHeaders headers =soapRequest.getMimeHeaders();
			headers.addHeader("SOAPAction", "http://html.diy.com/HTMLHelper/bEmbedImagesRequest");
			// create connection and call method
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
			soapConnection.close();
			// turn output into string
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			Source sourceContent = soapResponse.getSOAPPart().getContent();
			StringWriter sw = new StringWriter();
			StreamResult xmlOutput=new StreamResult(sw);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			transformer.transform(sourceContent, xmlOutput);
			strReturn = xmlOutput.getWriter().toString();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return strReturn;
	}

}
