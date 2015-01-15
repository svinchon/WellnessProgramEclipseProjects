package com.diy.schedulinghelper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InitializationServlet")
public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private SchedulingHelper sh;
       
     public InitializationServlet() {
        super();
//        Log("******************************************************************************");
//        Log("InitializationServlet loaded");
//        Log("******************************************************************************");
//        sh = new SchedulingHelper();
//        sh.startScheduler();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	void Log(String strMessage) {
		System.out.println(strMessage);
	}

}
