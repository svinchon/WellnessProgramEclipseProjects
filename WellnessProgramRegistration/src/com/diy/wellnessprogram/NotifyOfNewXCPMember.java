package com.diy.wellnessprogram;

import java.io.IOException;
import java.util.ArrayList;

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
					+ "</member> "
					+ "return insert node $e as first into /members";
			x.runXQuery(xquery );
		}		
	}

}
