package com.diy.highcharts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;

import net.sf.saxon.xqj.SaxonXQDataSource;

import com.diy.xdb.XDBHelperProxy;

@WebServlet("/HighChartsHelperDisplay")
public class HighChartsHelperDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HighChartsHelperDisplay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String metadata = "<metadata>";
//		Enumeration<String> parameterNames = request.getParameterNames();
//		while (parameterNames.hasMoreElements()) {
//			String paramName = parameterNames.nextElement();
//			String paramValue = request.getParameter(paramName);
//			metadata += "<"+paramName+">"+paramValue+"</"+paramName+">";
//		}
		String visu_xq = ""+request.getParameter("xq");
		String data_xq = ""+request.getParameter("data_xq");
		if (visu_xq.equals("null")) {visu_xq="GaugeAndTS1";}
		if (data_xq.equals("null")) {data_xq="HRStats2";}
		String xqfolder;
		xqfolder = "C:/Users/dmadmin/git/WellnessProgramEclipseProjects/HighCharts/WebContent/xq";
		xqfolder = "C:/GIT/WellnessProgramXDB/XQ/HighCharts";
		String data_xquery = File2String(xqfolder+"/"+data_xq+".xq");
		String index_threshold = ""+request.getParameter("index_threshold");
		if (!index_threshold.equals("null")) {
			data_xquery = data_xquery.replace(
					"let $threshold := 350",
					"let $threshold := "+index_threshold
					);
		}
		String days_above_required = ""+request.getParameter("days_above_required");
		if (!days_above_required.equals("null")) {
			data_xquery = data_xquery.replace(
					"let $needed_days := 5",
					"let $needed_days := "+days_above_required
					);
		}
		XDBHelperProxy xdhp = new XDBHelperProxy(
			"http://xpression:18080/XDBHelper/services/XDBHelper"
		);
		String xml = xdhp.runXQueryReadOnly(data_xquery)
		//		.replaceAll("[\\n\\r\\t]*", "")
		//		.replaceAll("> *<", "><");
				;
		xdhp = null;
		String visu_xquery = File2String(xqfolder+"/" +visu_xq+ ".xq");
		Log("visu xq: "+visu_xq);
		Log("data_xq: "+data_xq);
		xml = xml
				.replaceAll("\n", " ")
				.replaceAll("> *<", "><")
		;
		Log("xml: " + xml);
		String html = strRunXQuerySaxon(xml, visu_xquery);
		response.getWriter().write(html);
	}

	public String strRunXQuerySaxon(String strInputAsString, String strXQUERYAsString) {
		String strResult = "ERROR";
		try {
			//String strInputAsString = "<root><item index='1'>item A</item><item index='2'>item B</item></root>";
			//String strXQUERYAsString = "declare variable $doc external;<xdata>{for $i in $doc/root/item return $i}</xdata>";			
			ByteArrayInputStream bais = new ByteArrayInputStream(strInputAsString.getBytes());
			SaxonXQDataSource ds = new SaxonXQDataSource();
			XQConnection con = ds.getConnection();
			XQStaticContext ctx = con.getStaticContext();
			ctx.setBindingMode(XQConstants.BINDING_MODE_DEFERRED);
			con.setStaticContext(ctx);
			XQPreparedExpression expr = con.prepareExpression(strXQUERYAsString);
			expr.bindDocument(new QName("doc"), bais, null, null);
			XQSequence result = expr.executeQuery();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			result.writeSequence(baos, null);
			strResult = baos.toString("UTF-8");
			result.close();
			expr.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log("strRunXQuerySaxon: Input data: "+strInputAsString.replaceAll("[\r\n\t]+","").replaceAll("> *<", "><"));
			Log("strRunXQuerySaxon: Input xquery: "+strXQUERYAsString.replaceAll("[\r\n\t]+"," "));
			Log("strRunXQuerySaxon: Error message : "+e.getMessage());
			strResult = "strRunXQuerySaxon: ERROR";//: "+e.getMessage();
		}
		return strResult;
    }

	static String File2String(String path) {
		String strReturn = "";
		try {
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			byte[] bDoc = new byte[fis.available()];
			fis.read(bDoc);
			strReturn = new String(bDoc);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strReturn;
	}
	
	static void Log(String str) {
		//"yyyy-MM-dd@HH-mm-ss-SSS"
		System.out.println(new SimpleDateFormat("HH:mm:ss:SSS ", Locale.US).format(new Date())+"HighChartsHelper => " + str);
	}
	
}
