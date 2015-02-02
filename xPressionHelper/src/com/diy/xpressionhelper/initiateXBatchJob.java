package com.diy.xpressionhelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diy.QuickDoc.QuickDocPortTypeProxy;
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
		String XQuery = request.getParameter("XQuery");;
		String DataFileName = request.getParameter("DataFileName");;
		String ts = generateTimeStamp();
		if (JobName != null && DataOrigin != null) {
			if (DataOrigin.equals("file")) {
			} else if (DataOrigin.equals("xquery")) {
				String strXQ =""
						+ "<files>"
						+ "{"
						+ "for $e in doc('AuditTrail.xml')/audit_trail/event "
						+ "where $e/type = \"submitForBatch\" and not ($e[processed]) "
						+ "return $e/metadata/file_name "
						+ "}"
						+ "</files>";
				XDBHelperProxy xdbhp = new XDBHelperProxy();
				String strXML = xdbhp.runXQuery(strXQ);
				String2File(strXML, "C:/tmp/"+"ExtractedForBatch"+ts+".xml");
			} else {
				resp = "wrong value for DataOrigin";
			}
		} else {
			resp = "missing input parameters";
		}
		xUtilsWSAPI xu = new xUtilsWSAPI();
		//xu.strStartJob(JobName, DataFileName);
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
	
}
