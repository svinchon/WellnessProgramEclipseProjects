package com.diy.rest.old;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@WebServlet("/ProcessFormData")
public class ProcessFormDataV1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessFormDataV1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationRequestSubmitter rrs = new RegistrationRequestSubmitter();
		rrs.setAge(""+request.getParameter("age"));
		rrs.setEmail(""+request.getParameter("email"));
		rrs.setFirstName(""+request.getParameter("first_name"));
		rrs.setGender(""+request.getParameter("gender"));
		rrs.setLastName(""+request.getParameter("last_name"));
		rrs.setWeight(""+request.getParameter("weight"));
		String str;
		//try {
			str = rrs.run();
		//} catch (Exception e) {
		//	str = "Connectivity Issue";
		//}
		System.out.println("Response SOAP Message = "+str);
		//response.setContentType("text/xml");
		//response.getWriter().write(str);
		//String strWI = strGetValueFromXML(str, "/SOAP-ENV:Envelope/SOAP-ENV:Body/ns0:defaultResponse/workflowId");
		String strWI = strGetValueFromXML(str, "/Envelope/Body/defaultResponse/workflowId");
		response.sendRedirect("ThankYou.jsp?WorkflowId="+strWI);
	}

	public static String strGetValueFromXML(String strXML, String strXPath) {
		String result="";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new InputSource(new StringReader(strXML))
			);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			if (nodes!=null && nodes.getLength()>0) {
				String strValue=nodes.item(0).getTextContent();
				//System.out.println("XML Helper => Extracted "+strXPath+" value: "+strValue);
				return strValue;
			} else {
				result = "ERROR strGetValueFromXML (xpath returned null or empty)";
				System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
				System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
				System.out.println("Error message : "+"xpath returned null or empty");			}
			nodes = null;
			xpath = null;
			doc = null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
			System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			result = "ERROR: "+e.getMessage();
		}
		return result;
	}

}
