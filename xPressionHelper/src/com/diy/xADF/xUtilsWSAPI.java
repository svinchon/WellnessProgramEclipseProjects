package com.diy.xADF;

import java.io.File;
import java.io.StringReader;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;

public class xUtilsWSAPI {
	
	ResourceBundle rb = ResourceBundle.getBundle("xPressionHelper");
	String xPressionHost = rb.getString("xPressionHost");
	String xPressionPort = rb.getString("xPressionPort");
	String xPressionUser = rb.getString("xPressionUser");
	String xPressionPassword = rb.getString("xPressionPassword");
	String uriGetJobNames = "http://"+xPressionHost+":"+xPressionPort+"/xFramework/restful/services/jobs/all/list/names";
	String uriStartJobXML = "http://"+xPressionHost+":"+xPressionPort+"/xFramework/restful/services/job/start/xml";
	String uriGetJobStatus = "http://"+xPressionHost+":"+xPressionPort+"/xFramework/restful/services/job/status";
	String uriStartJob = "http://"+xPressionHost+":"+xPressionPort+"/xFramework/restful/services/job/start";
	String uriGetJobQueue = "http://"+xPressionHost+":"+xPressionPort+"/xFramework/restful/services/jobs/queue/all";
	
	String strRC = "" +
			"<XServicesRequest>\n"+
			"	<RequestContext function=\"General.security\">\n"+
			"		<Credentials method=\"UserID and Password\">\n"+
			"		<UserID>"+xPressionUser+"</UserID>\n"+
			"		<Password>"+xPressionPassword+"</Password>\n"+
			"		</Credentials>\n"+
			"		<ApplicationName>xPression Batch</ApplicationName>\n"+
			"	</RequestContext>\n"+
			"	<RequestContext function=\"Job.start\">\n"+
			"		<Job version=\"2.0\" name=\"{JOB_NAME}\">\n"+
			"			<JobOption>-o {JOB_INPUT_FILE}</JobOption>\n"+
			"		</Job>\n"+
			"	</RequestContext>\n"+
			"	<RequestContext function=\"Job.status\">\n"+
			"		<Item ref=\"queueID\">{JOB_ID}</Item>\n"+
			"		<ReturnInfo>statistics with details</ReturnInfo>\n"+
			"	</RequestContext>\n" +
			"	<RequestContext function=\"Job.queue.all\">\n"+
			"		<Filter>\n"+
			"			<Status>{JOB_STATUS_LIST}</Status>\n"+
			"		</Filter>\n"+
			"	</RequestContext>\n"+
			"</XServicesRequest>";
	
	public String strTimeStampFile(String strFile) {
		try {
			long i = System.currentTimeMillis();
			File ff = new File("C:/xADF/Temp/"+strFile);
			String strExt = ff.getPath().substring(ff.getPath().lastIndexOf("."));
			File ft = new File("C:/xADF/Temp/"+strFile+"_"+i+strExt);
			ff.renameTo(ft);
			System.out.println("xUtilsWSAPI.strTimeStampFile: "+ff.getPath()+"->"+ft.getPath());
			return ft.getPath();
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";			
		}
	}
	
	public String strSendMSG(String strMessage, String strDestination) {
		try {
			System.out.println("xUtilsWSAPI.strSendMSG: "+strMessage);
			return "SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";			
		}
	}
	
	public String strStartJob(String strJobName, String strJobInputFile) {
		strRC = strRC.replace("{JOB_NAME}", strJobName);
		strRC = strRC.replace("{JOB_INPUT_FILE}", strJobInputFile);
		//System.out.println(strRC);
		WebResource wr;
		String strResponse;
		String uri;
		Client c = Client.create();
		c.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		uri = uriStartJob;
		wr = c.resource(uri);
		strResponse = wr.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).entity(strRC).post(String.class); 
		//System.out.println(strResponse);
		System.out.println("xUtilsWSAPI.strStartJob: QueueId="+strResponse);
		return strResponse;
	}
	
	public String strJobStatus(String strJobId) {
		strRC = strRC.replace("{JOB_ID}", strJobId);
		System.out.println(strRC);
		WebResource wr;
		String strResponse;
		String uri;
		Client c = Client.create();
		c.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		uri = uriGetJobStatus;
		wr = c.resource(uri);
		strResponse = wr.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).entity(strRC).post(String.class); 
		System.out.println("xUtilsWSAPI.strJobStatus: Return="+strResponse);
		return strResponse;
	}

	public String strXMLListJobNames() {
		strRC = strRC.replace("{JOB_STATUS_LIST}", "-1,0,1");
		System.out.println(strRC);
		String strResponse = null;
		try {
			WebResource wr;
			String uri;
			Client c = Client.create();
			c.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
			uri = uriGetJobQueue;
			wr = c.resource(uri);
			strResponse = wr.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).entity(strRC).post(String.class); 
			///System.out.println("XML File : "+strReturn);
			System.out.println("xUtilsWSAPI.strXMLListJobNames: Return="+strResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResponse;
	}
	
	public String[] strLisJobNames() {
		strRC = strRC.replace("{JOB_STATUS_LIST}", "-1,0,1,2,3");
		//System.out.println(strRC);
		String[] strReturn = null;
		try {
			WebResource wr;
			String strResponse;
			String uri;
			Client c = Client.create();
			c.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
			uri = uriGetJobQueue;
			wr = c.resource(uri);
			strResponse = wr.type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).entity(strRC).post(String.class); 
			System.out.println("xUtilsWSAPI.strXMLListJobNames: Return="+strResponse);
			//System.out.println("XML File : "+strResponse);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(strResponse)));
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			//XPathExpression expr = xpath.compile("/Response/JOB/RunID");
			XPathExpression expr = xpath.compile("/Response/JOB/RunID");
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			strReturn = new String[nl.getLength()];
			for(int i=0;i<nl.getLength();i++) {
				strReturn[i] = nl.item(i).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;
	}
 }