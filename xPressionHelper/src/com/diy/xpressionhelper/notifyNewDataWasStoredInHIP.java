package com.diy.xpressionhelper;

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


@WebServlet("/notifyNewDataWasStoredInHIP")
public class notifyNewDataWasStoredInHIP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public notifyNewDataWasStoredInHIP() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		String ts = generateTimeStamp();
		String fromIP = (String)request.getRemoteHost().replace(":", "-");
		String shortFilename = "notifyNewDataWasStoredInHIP_from"+fromIP+"_On_"+ts+".xml";
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
		XDBHelperProxy xdbhp = new XDBHelperProxy();
		xdbhp.setEndpoint(xdbhp.getEndpoint().replace("localhost","192.168.3.53"));
		String strXQ =""
				+ "let $e:=<event><time>"+ts+"</time><type>submitForBatch</type>"+metadata+"</event> "
				+ "return insert node $e as first into /audit_trail";
		xdbhp.runXQuery(strXQ);
		strXQ =""
				+ "let $e:=<notification><time>"+ts+"</time><type>submitForBatch</type>"+metadata+"</notification> "
				+ "return insert node $e as first into /notifications";
		xdbhp.runXQuery(strXQ);
		resp = "notification received";
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
