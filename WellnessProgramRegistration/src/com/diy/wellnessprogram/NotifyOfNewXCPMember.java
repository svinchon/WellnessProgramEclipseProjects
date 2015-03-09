package com.diy.wellnessprogram;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.diy.xdb.XDBHelperProxy;

@WebServlet("/notifyOfNewXCPMember")
public class NotifyOfNewXCPMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NotifyOfNewXCPMember() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Log("START");
		String badgeNumber = ""+request.getParameter("badge_number");
		String firstName = ""+request.getParameter("first_name");
		String lastName =  ""+request.getParameter("last_name");
		String vitexId =  ""+request.getParameter("vitex_id");
		ArrayList<String> errors = new ArrayList<String>();
		if (!firstName.matches("[a-zA-Z0-9 -]*")) { errors.add("Incorrect First Name"); }
		if (!lastName.matches("[a-zA-Z0-9 -]*")) { errors.add("Incorrect Last Name"); }
		if (!badgeNumber.matches("[0-9]*")) { errors.add("Incorrect Badge Number"); }
		if (errors.size()!=0) {
			String[] errorsArray = errors.toArray(new String[errors.size()]);
			String errorString = StringUtils.join(errorsArray, ",");
			Log("END");
			response.getWriter().write("{ "+ errorString + " }");
		} else {
			XDBHelperProxy x = new XDBHelperProxy(
					"http://xpression:18080/XDBHelper/services/XDBHelper"
					);
			String xquery= ""
					+ "let $e:="
					+ "<member>"
					+ "<badge_number>"+badgeNumber+"</badge_number>"
					+ "<first_name>"+firstName+"</first_name>"
					+ "<last_name>"+lastName+"</last_name>"
					+ "<vitex_id>"+vitexId+"</vitex_id>"
					+ "<enrollment_date>"+new SimpleDateFormat("yyyy-MM-dd ",Locale.US).format(new Date())+"</enrollment_date>"
					+ "<team>Italy</team>"
					+ "</member> "
					+ "return insert node $e as first into /members"
					;
			x.runXQuery(xquery );
			Log("END (happy)");
		}		
	}

	static void Log(String str) {
		System.out.println(
				new SimpleDateFormat(
						"HH:mm:ss:SSS ",
						Locale.US
						).format(new Date())
				+"NotifyOfNewXCPMember => "
				+ str
				);
	}
	
}
