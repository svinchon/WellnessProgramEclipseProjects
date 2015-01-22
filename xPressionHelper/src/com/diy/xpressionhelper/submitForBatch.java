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


@WebServlet("/submitForBatch")
public class submitForBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public submitForBatch() {
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
			String shortFilename = "submitForBatch_from"+fromIP+"_On_"+ts+".xml";
			String metadata = "<metadata>";
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String paramValue = request.getParameter(paramName);
				metadata += "<"+paramName+">"+paramValue+"</"+paramName+">";
			}
			metadata += "<"+"file_name"+">"+shortFilename+"</"+"file_name"+">";
			metadata += "<"+"request_type"+">"+"submitForBatch"+"</"+"request_type"+">";
			metadata += "</metadata>";
			StringBuilder buffer = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    String line;
		    while ((line = reader.readLine()) != null) { buffer.append(line); }
		    String data = buffer.toString();
			String fileName = "C:/Tmp/"+shortFilename;
			String2File(data, fileName);
			XDBHelperProxy xdbhp = new XDBHelperProxy();
			xdbhp.setEndpoint(xdbhp.getEndpoint().replace("localhost","192.168.3.53"));
			xdbhp.storeDoc(fileName, shortFilename);
			xdbhp.storeStringAsDoc(metadata, shortFilename+"_metadata.xml");
			String strXQ =""
					+ "let $e:=<event><time>"+ts+"</time><type>submitForBatch</type>"+metadata+"</event> "
					+ "return insert node $e as first into /audit_trail";
			xdbhp.runXQuery(strXQ);
			resp = "file written to XDB and "+fileName;
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
