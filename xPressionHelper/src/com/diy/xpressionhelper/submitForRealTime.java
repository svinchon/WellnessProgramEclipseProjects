package com.diy.xpressionhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;

import com.diy.QuickDoc.QuickDocPortTypeProxy;
import com.diy.xdb.XDBHelperProxy;


@WebServlet("/submitForRealTime")
public class submitForRealTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public submitForRealTime() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		String rct = (String)request.getContentType();
		if (rct.equals("application/xml")) {
			String ts = generateTimeStamp();
			String fromIP = (String)request.getRemoteHost().replace(":", "-");
			String shortFilename = "submitForRealTime_from"+fromIP+"_On_"+ts+".xml";
			//String priority = (String)request.getParameter("Priority");
			String template = (String)request.getParameter("Template");
			String outputProfile = (String)request.getParameter("OutputProfile");
			if (template.equals(null) | outputProfile.equals(null)) {
				resp = "Missing request parameters";
			} else {
				String metadata = "<metadata>";
				Enumeration<String> parameterNames = request.getParameterNames();
				while (parameterNames.hasMoreElements()) {
					String paramName = parameterNames.nextElement();
					String paramValue = request.getParameter(paramName);
					metadata += "<"+paramName+">"+paramValue+"</"+paramName+">";
				}
				metadata += "<"+"file_name"+">"+shortFilename+"</"+"file_name"+">";
				metadata += "<"+"request_type"+">"+"submitForRealTime"+"</"+"request_type"+">";
				metadata += "</metadata>";
				StringBuilder buffer = new StringBuilder();
			    BufferedReader reader = request.getReader();
			    String line;
			    while ((line = reader.readLine()) != null) { buffer.append(line); }
			    String data = buffer.toString();
				String fileName = "C:/Tmp/"+shortFilename;
				String2File(data, fileName);
				XDBHelperProxy xdbhp = new XDBHelperProxy();
				xdbhp.setEndpoint(xdbhp.getEndpoint().replace("xpression","192.168.3.53"));
				xdbhp.storeDoc(fileName, shortFilename);
				xdbhp.storeStringAsDoc(metadata, shortFilename+"_metadata.xml");
				String strXQ =""
						+ "let $e:=<event><time>"+ts+"</time><type>submitForRealTime</type>"+metadata+"</event> "
						+ "return insert node $e as first into /audit_trail";
				xdbhp.runXQuery(strXQ);
				QuickDocPortTypeProxy p = new QuickDocPortTypeProxy();
				p.setEndpoint(p.getEndpoint().replace("localhost:18080", "localhost:8080"));	
				String documentName = (request.getParameter("Template").equals("--AUTO--")) ? "MonthlyReport.xdrwv" : request.getParameter("Template");
				String outputProfileName = (request.getParameter("OutputProfile").equals("--AUTO--")) ? "PDF to File" : request.getParameter("OutputProfile");
				String requestContext = "<RequestContext><Credentials method=\"UserID and Password\"><UserID>xDesigner</UserID><Password>Pa55word</Password></Credentials><ApplicationName>xPression DevKit</ApplicationName></RequestContext>";
				try {
					resp = p.publishDocument(
							requestContext,
							documentName,
							data,
							outputProfileName
					);
				} catch (AxisFault e) {
					resp = e.getFaultString();
				}
			}
		} else {
			resp="input has to be XML";
		}
		resp = "{ \"message\": \""+resp+"\" }";
		response.getWriter().write(resp);
	}

	void Log(String str) {
		System.out.println(str);
	}
	
	String generateTimeStamp() {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-DD@HH-mm-ss-SSS", Locale.US);
		Date d = new Date();
		return DateFormat.format(d);
	}
	
	void String2File(String str, String path) {
		try {
			File f = new File(path);
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(str.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
