package com.diy.wellnessprogram;

import java.io.IOException;
import java.math.BigInteger;
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

import WellnessProgram.DefaultResponse;
import WellnessProgram.SubmitRegistrationRequest;
import WellnessProgram.WP02Proxy;

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
		String username = ""+request.getParameter("username");
		String password = ""+request.getParameter("password");
		String badge_number = ""+request.getParameter("badge_number");
		String gender = ""+request.getParameter("gender");
		String email = ""+request.getParameter("email");
		String weight = ""+request.getParameter("weight");
		String birth_date = ""+request.getParameter("birth_date");
		String first_name = ""+request.getParameter("first_name");
		String last_name = ""+request.getParameter("last_name");
		//Log(""+strEmail.matches("[a-zA-Z0-9_.]*@[a-zA-Z0-9]*\\.[a-zA-Z]*"));
		ArrayList<String> Errors = new ArrayList<String>();
		if (
			!username.matches("[a-zA-Z0-9_.]*"))
		{
			Errors.add("Incorrect User Name");
		}
		if (
			password.length()<6)
		{
			Errors.add("Too short password");
		}
		if (
			!birth_date.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")
		) {
			Errors.add("Incorrect birth date format (YYYY-MM-DD)");
		}
		if (
			!email.matches("[a-zA-Z0-9_.]*@[a-zA-Z0-9]*\\.[a-zA-Z]*")		
		) {
			Errors.add("Incorrect Email");
		}
		if (
			!weight.matches("[0-9][0-9]*")
		) {
			Errors.add("Incorrect Weight: '"+weight+"'");
		}
		if (
			!first_name.matches("[a-zA-Z0-9 -]*")
		) {
			Errors.add("Incorrect First Name");
		}
		if (
			!last_name.matches("[a-zA-Z0-9 -]*")
		) {
			Errors.add("Incorrect Last Name");
		}
		if (
			!badge_number.matches("[0-9]*")
		) {
			Errors.add("Incorrect Badge Number");
		}
		if (Errors.size()!=0) {
			String[] ErrorsArray = Errors.toArray(new String[Errors.size()]);
			String strErrors = StringUtils.join(ErrorsArray, ",");
			response.sendRedirect("DisplayMessage.jsp?MessageType=ERROR&Message=Incorrect Data: "+strErrors);
		} else {
			int sport_activity = new Integer(request.getParameter("iSportActivity"));
			String strVitexReturn = vrh.submitNewUser(
					username,
					email,
					password,
					badge_number,
					gender,
					weight,
					birth_date,
					sport_activity
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
				String vitex_id = strVitexReturn.substring("SUCCESS:id=".length());
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
				xdbh.setEndpoint(xdbh.getEndpoint().replace("localhost:18080", "localhost:8080"));
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
						+ "<badge_number>"+badge_number+"</badge_number>"
						+ "<first_name>"+first_name+"</first_name>"
						+ "<last_name>"+last_name+"</last_name>"
						+ "<vitex_id>"+vitex_id+"</vitex_id>"
						+ "</member> "
						+ "return insert node $e as first into /members";
				xdbh.runXQuery(strQuery );
				WP02Proxy wp02p = new WP02Proxy();
				SubmitRegistrationRequest parameters = new SubmitRegistrationRequest();
				parameters.setUsername(username);
				parameters.setBirth_date(birth_date);
				parameters.setBadge_number(badge_number);
				parameters.setFirst_name(first_name);
				parameters.setGender(gender);
				parameters.setLast_name(last_name);
				parameters.setLogin(username);
				parameters.setPassword(password);
				parameters.setVitex_id(vitex_id);
				parameters.setWeight(new Float(weight));
				parameters.setEmail_type("NA");
				parameters.setEmail_count(new BigInteger("0"));
				parameters.setEmail(email);
				DefaultResponse dr = wp02p.submitRegistration(parameters);
				String wi = dr.getWorkflowId();
				response.sendRedirect("DisplayMessage.jsp?Message=New user successfully created under Vitex Id '"+vitex_id+"' and xCP registration workflow started ('" + wi + "')");
			}
			// create xCP/DCTM user
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
