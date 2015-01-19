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

import com.diy.xdb.XDBHelperProxy;


@WebServlet("/submitForAccumulation")
public class submitForAccumulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public submitForAccumulation() {
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
			String shortFilename = "submitForAccumulation_from"+fromIP+"_On_"+ts+".xml";
//			String priority = (String)request.getHeader("Priority");
//			String template = (String)request.getHeader("Template");
//			String outputProfile = (String)request.getHeader("OutputProfile");
			String priority = (String)request.getParameter("Priority");
			String template = (String)request.getParameter("Template");
			String outputProfile = (String)request.getParameter("OutputProfile");
			Log(template);
			String metadata = ""
					+ "<metadata>"
					+ "<priority>"
					+ priority
					+ "</priority>"
					+ "<template>"
					+ template
					+ "</template>"
					+ "<output_profile>"
					+ outputProfile
					+ "</output_profile>"
					+ "</metadata>";
			metadata = "<metadata>";
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String paramValue = request.getParameter(paramName);
				metadata += "<"+paramName+">"+paramValue+"</"+paramName+">";
			}
			metadata += "<"+"file_name"+">"+shortFilename+"</"+"file_name"+">";
			metadata += "<"+"request_type"+">"+"submitForAccumulation"+"</"+"request_type"+">";
			metadata += "</metadata>";
			Log(metadata);
			StringBuilder buffer = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        buffer.append(line);
		    }
		    String data = buffer.toString();
			Log(data);
			String fileName = "C:/Tmp/"+shortFilename;
			String2File(data, fileName);
			XDBHelperProxy xdbhp = new XDBHelperProxy();
			Log(xdbhp.getEndpoint());
			xdbhp.setEndpoint(xdbhp.getEndpoint().replace("xpression","192.168.3.53"));
			Log(xdbhp.getEndpoint());
			Log("fn="+fileName);
			Log("sfn="+shortFilename);
			xdbhp.storeDoc(fileName, shortFilename);
			xdbhp.storeStringAsDoc(metadata, shortFilename+"_metadata.xml");
			resp = "file written to "+fileName;
		} else {
			resp="input has to be XML";
		}
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
