package com.diy.wellnessprogram;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.diy.xdb.XDBHelperProxy;

//import org.apache.axis.utils.StringUtils;

@WebServlet("/ProcessFormData")
public class ProcessFormData extends HttpServlet {
	
	static {
		HttpsURLConnection.setDefaultHostnameVerifier(
			new HostnameVerifier(){
				public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
					if (hostname.equals(ResourceBundle.getBundle("WellnessProgram").getString("VitexServerHost"))) { return true; } 
					return false;
				}
			}
		);
	}
	
	private static final long serialVersionUID = 1L;
       
    public ProcessFormData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Log("doPost");
		VITEXRESTHelper vrh = new VITEXRESTHelper();
		String strUserName = ""+request.getParameter("username");
		String strPassword = ""+request.getParameter("password");
		String strExternalId = ""+request.getParameter("badge_number");
		String strGender = ""+request.getParameter("gender");
		String strEmail = ""+request.getParameter("email");
		String strWeight = ""+request.getParameter("weight");
		String strBirthDate = ""+request.getParameter("birth_date");
		String strFirstName = ""+request.getParameter("first_name");
		String strLastName = ""+request.getParameter("last_name");
		//Log(""+strEmail.matches("[a-zA-Z0-9_.]*@[a-zA-Z0-9]*\\.[a-zA-Z]*"));
		ArrayList<String> Errors = new ArrayList<String>();
		if (
			!strUserName.matches("[a-zA-Z0-9_.]*"))
		{
			Errors.add("Incorrect User Name");
		}
		if (
			strPassword.length()<6)
		{
			Errors.add("Too short password");
		}
		if (
			!strBirthDate.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")
		) {
			Errors.add("Incorrect birth date format (YYYY-MM-DD)");
		}
		if (
			!strEmail.matches("[a-zA-Z0-9_.]*@[a-zA-Z0-9]*\\.[a-zA-Z]*")		
		) {
			Errors.add("Incorrect Email");
		}
		if (
			!strWeight.matches("[0-9][0-9]*")
		) {
			Errors.add("Incorrect Weight: '"+strWeight+"'");
		}
		if (
			!strFirstName.matches("[a-zA-Z0-9 -]*")
		) {
			Errors.add("Incorrect First Name");
		}
		if (
			!strLastName.matches("[a-zA-Z0-9 -]*")
		) {
			Errors.add("Incorrect Last Name");
		}
		if (
			strExternalId.matches("[0-9]*")
		) {
			Errors.add("Incorrect Badge Number");
		}
		if (Errors.size()!=0) {
			String[] ErrorsArray = Errors.toArray(new String[Errors.size()]);
			String strErrors = StringUtils.join(ErrorsArray, ",");
			response.sendRedirect("DisplayMessage.jsp?MessageType=ERROR&Message=Incorrect Data: "+strErrors);
		} else {
			int iSportActivity = new Integer(request.getParameter("iSportActivity"));
			String strVitexReturn = vrh.submitNewUser(
					strUserName,
					strEmail,
					strPassword,
					strExternalId,
					strGender,
					strWeight,
					strBirthDate,
					iSportActivity
			);
			if (strVitexReturn.indexOf("ERROR")>=0) {
				Log(strVitexReturn);
				String strRegEx = "\\{\"errors\":\\[\"([^}]*)\"\\]\\}";
				//\{"errors":\[([^\}]*)\]\}
				Log(strRegEx);
				Errors = new ArrayList<String>();
				Matcher m = Pattern	.compile(strRegEx)
									.matcher(strVitexReturn);
				while (m.find()) {
					Errors.add(m.group(1).replace("\\\"", "\""));
					Log(m.group(1));
				}				
				String[] ErrorsArray = Errors.toArray(new String[Errors.size()]);
				String strErrors = StringUtils.join(ErrorsArray, "  ");
				response.sendRedirect("DisplayMessage.jsp?MessageType=ERROR&Message="+strErrors);
			} else {
				String strVitexId = strVitexReturn.substring("SUCCESS:id=".length());
				response.sendRedirect("DisplayMessage.jsp?Message=New user successfully created under Vitex Id '"+strVitexId+"'");
				String ts = generateTimeStamp();
				String metadata = "<metadata>";
				Enumeration<String> parameterNames = request.getParameterNames();
				while (parameterNames.hasMoreElements()) {
					String paramName = parameterNames.nextElement();
					String paramValue = request.getParameter(paramName);
					metadata += "<"+paramName+">"+paramValue+"</"+paramName+">";
				}
				metadata += "<"+"request_type"+">"+"registerNewUser"+"</"+"request_type"+">";
				metadata += "</metadata>";
				XDBHelperProxy xdbh = new XDBHelperProxy();
				String strQuery;
				strQuery= ""
					+ "let $e:="
					+ "<event>"
					+ "<time>"+ts+"</time>"
					+ "<type>registerNewUser</type>"
					+ metadata
					+ "</event> "
					+ "return insert node $e as first into /audit_trail";
				xdbh.runXQuery(strQuery );
				strQuery= ""
						+ "let $e:="
						+ "<member>"
						+ "<badge_number>"+strExternalId+"</badge_number>"
						+ "<first_name>"+strFirstName+"</first_name>"
						+ "<last_name>"+strLastName+"</last_name>"
						+ "<vitex_id>"+strVitexId+"</vitex_id>"
						+ "</member> "
						+ "return insert node $e as first into /members";
				xdbh.runXQuery(strQuery );				
			}

			// Send welcome email
			// else
				// Send Sorry email
			// end if			
		}

	}

	String generateTimeStamp() {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-DD@HH-mm-ss-SSS", Locale.US);
		Date d = new Date();
		return DateFormat.format(d);
	}
	
	void Log(String msg) {
		System.out.println("ProcesFormData: "+msg);
	}

}
