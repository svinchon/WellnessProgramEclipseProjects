package com.diy.schedulinghelper;

//import java.io.ByteArrayInputStream;
//import java.io.StringWriter;
//import java.nio.charset.Charset;
//import java.util.ResourceBundle;
//
//import javax.xml.soap.MessageFactory;
//import javax.xml.soap.MimeHeaders;
//import javax.xml.soap.SOAPConnection;
//import javax.xml.soap.SOAPConnectionFactory;
//import javax.xml.soap.SOAPMessage;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.stream.StreamResult;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSCallJob implements Job {

	Logger log = LoggerFactory.getLogger(WSCallJob.class);    

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
//			ResourceBundle rb = ResourceBundle.getBundle("SchedulingHelper");
//			String strURL = rb.getString("EndPoint1");
//			String strSOAPMessage = rb.getString("SOAPMessage1");;
//			MessageFactory factory = MessageFactory.newInstance();
//			SOAPMessage soapRequest = factory.createMessage(
//					new MimeHeaders(),
//					new ByteArrayInputStream(
//							strSOAPMessage.getBytes(Charset.forName("UTF-8"))
//					)
//			);
//			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//			SOAPMessage soapResponse = soapConnection.call(soapRequest, strURL);
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			Source sourceContent = soapResponse.getSOAPPart().getContent();
//			StringWriter sw = new StringWriter();
//			StreamResult xmlOutput=new StreamResult(sw);
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
//			transformer.transform(sourceContent, xmlOutput);
//			log.info(xmlOutput.getWriter().toString());
//			soapConnection.close();
			JobDataMap data = context.getJobDetail().getJobDataMap();
			String strSOAPMessage = data.getString("SOAPMessage");
			log.info("HERE IT IS: "+ strSOAPMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}