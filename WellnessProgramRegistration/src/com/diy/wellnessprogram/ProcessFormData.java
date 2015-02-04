package com.diy.wellnessprogram;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.diy.rest.old.RegistrationRequestSubmitter;

@WebServlet("/ProcessFormData")
public class ProcessFormData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessFormData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Log("doPost");
		// Send data to VITEX REST Helper
		// if new user
			// Send data to XDB / xCP
			// Send welcome email
		// else
			// Send Sorry email
		// end if
	}

	void Log(String msg) {
		System.out.println("ProcesFormData: "+msg);
	}

}
