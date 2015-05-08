package com.diy.highcharts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HighChartsHelper")
public class HighChartsHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HighChartsHelper() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String visu_xq = ""+request.getParameter("xq");
		String data_xq = ""+request.getParameter("data_xq");
		String index_threshold = ""+request.getParameter("index_threshold");
		String days_above_required = ""+request.getParameter("days_above_required");
		//Enum e = request.getParameterNames()
		String params = ""
				+"?xq="+visu_xq
				+"&data_xq="+data_xq
				+"&data_xq="+data_xq
				+"&index_threshold="+index_threshold
				+"&days_above_required="+days_above_required;
		response.setContentType("text/html");
		response.getWriter().write(""
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv='refresh' content='0; url=http://xcp:8080/HighCharts/HighChartsHelperDisplay"+params+"' />"
				+ "</head>"
				+ "<body bgcolor='white'>"
				+ "<center>"
				+ "<img src='http://xcp:8080/HighCharts/Images/Wait1.gif'/>"
				+ "</center>"
				+ "</body>"
				+ "</html>"
				+ "");
		//response.sendRedirect("http://xcp:8080/HighCharts/HighChartsHelperDisplay");
	}

}
