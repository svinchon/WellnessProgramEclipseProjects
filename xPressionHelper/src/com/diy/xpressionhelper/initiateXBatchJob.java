package com.diy.xpressionhelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diy.xADF.xUtilsWSAPI;
import com.diy.xdb.XDBHelperProxy;


@WebServlet("/initiateXBatchJob")
public class initiateXBatchJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public initiateXBatchJob() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		String JobName = request.getParameter("JobName");
		String DataOrigin = request.getParameter("DataOrigin"); //xquery or file
		String XQuery = ""+request.getParameter("XQuery");;
		String DataFileName = request.getParameter("DataFileName");;
		String ts = generateTimeStamp();
		if (JobName != null && DataOrigin != null) {
			String metadata = "<metadata>";
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String paramValue = request.getParameter(paramName);
				metadata += "<"+paramName+">"+paramValue+"</"+paramName+">";
			}
			metadata += "<"+"file_name"+">"+"</"+"file_name"+">";
			metadata += "<"+"request_type"+">"+"initiateXBatchJob"+"</"+"request_type"+">";
			metadata += "</metadata>";
			XDBHelperProxy xdbhp = new XDBHelperProxy("http://xcp:8080/XDBHelper/services/XDBHelper");
			Log("XDBHelper created with endpoint: "+xdbhp.getEndpoint());
			String strQueueId;
			String strXQ;
			if ((DataOrigin.equals("file") && DataFileName!=null) || DataOrigin.equals("xquery")) {
				String strDataFileFullName;
				strDataFileFullName = ResourceBundle.getBundle("xPressionHelper").getString("TmpFolder")+"/"+"ExtractedForBatch_On_"+ts+".xml";
				if (DataOrigin.equals("file")) {
					/*strXQ = ""
							+ "<users> "
							+ "{ "
							+ "doc('"+DataFileName+"')/users/* "
							+ "} "
							+ "</users>";
					//Log("strXQ="+strXQ);
					String strXML = xdbhp.runXQuery(strXQ);
					String2File(strXML, strDataFileFullName);*/		
				} else {
					String xqfolder = this.getServletContext().getRealPath("/xq");
					strXQ = File2String(xqfolder + "/" + XQuery);
					String strXML = xdbhp.runXQuery(strXQ);
					String2File(strXML, strDataFileFullName);			
				}
				xUtilsWSAPI xu = new xUtilsWSAPI();
				strQueueId = xu.strStartJob(JobName, strDataFileFullName);
				resp = "data extracted and processed"+ " ("+strQueueId+")";
				strXQ = ""
						+ "let $e:=<event><time>"+ts+"</time><type>initiateXBatchJob</type><queue_id>"+strQueueId+"</queue_id>"+metadata+"</event> "
						+ "return insert node $e as first into /audit_trail";
				xdbhp.runXQuery(strXQ);	
			} else {
				resp = "wrong value for DataOrigin";
			}
		} else {
			resp = "missing input parameters";
		}
		resp = "{ \"message\": \""+resp+"\" }";
		response.getWriter().write(resp);
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
	
	String File2String(String strFileName) {
		String s = "";
		try {
			File inFile = new File(strFileName);
			FileInputStream fis = new FileInputStream(inFile);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			 s = new String(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	void Log(String msg) {
		System.out.println("initiateXBatchJob: " + msg);
	}
	
}
