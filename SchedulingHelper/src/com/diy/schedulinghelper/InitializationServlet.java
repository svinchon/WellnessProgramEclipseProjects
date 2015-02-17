package com.diy.schedulinghelper;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

@SuppressWarnings("unused")
@WebServlet("/InitializationServlet")
public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private SchedulingHelper sh;
       
//	public ServletContext getServletContext() {
//		HttpServlet servlet = (HttpServlet)(
//				MessageContext
//					.getCurrentContext()
//					.getProperty(HTTPConstants.MC_HTTP_SERVLET)
//		);
//		ServletContext ctx = servlet.getServletContext();
//		return ctx;
//	}

	public InitializationServlet() {
        super();
        Log("******************************************************************************");
        Log("InitializationServlet loaded");
        Log("******************************************************************************");
//		if (
//				true
//				//(Scheduler)(getServletContext().getAttribute("Scheduler"))
//				//==
//				//null
//			) {
//			SchedulerFactory sf = new StdSchedulerFactory();
//			Scheduler my_sched;
//			try {
//				my_sched = sf.getScheduler();
//				//this.getServletContext()
//				this.getServletContext().setAttribute("Scheduler", my_sched);
//			} catch (SchedulerException e) {
//				e.printStackTrace();
//			}
//		}
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
