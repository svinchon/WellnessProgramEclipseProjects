package com.diy.xADF;

//import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.io.FileOutputStream;
//import java.io.IOException;





import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/notifyBatchJobEnd")
public class notifyBatchJobEnded extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public notifyBatchJobEnded() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		//response.getWriter().write(new xUtilsWSAPI().strXMLListJobNames());
		System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Request Content Type: "+request.getContentType());
		System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Method: "+request.getMethod());
		if (request.getContentType() != null) {
			byte[] b = new byte[request.getContentLength()];
			request.getInputStream().read(b);
			String strContent = new String(b);
			System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Content: "+strContent);
			ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode rootNode = mapper.readValue(strContent, JsonNode.class);
				String strQueueId = rootNode.get("queueID").asText();
				String strReturnCode = rootNode.get("returnCode").asText();
				System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: QueueId: "+strQueueId);
				System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: ReturnCode: "+strReturnCode);
				bNotifyxCP(strQueueId, strReturnCode);
			} catch (Exception e) {
				System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Cannot read QueueId");
				e.printStackTrace();
			}
		}
	}
    
    public boolean bNotifyxCP(String strQueueId, String strReturnCode) {
    	String url = "http://localhost:7000/bps/http/xPRBatchJobEnd";
    	HttpClient client = new HttpClient();
    	GetMethod method = new GetMethod(url);
    	method.setRequestHeader("Content-type","text/xml; charset=ISO-8859-1");
    	NameValuePair nvp2= new NameValuePair("return_code",strReturnCode);
    	NameValuePair nvp1= new NameValuePair("cid", strQueueId);
    	///NameValuePair nvp3= new NameValuePair("email","email@email.com");
    	//method.setQueryString(new NameValuePair[]{nvp1,nvp2,nvp3});
    	method.setQueryString(new NameValuePair[]{nvp2, nvp1});
    	try{
    		int statusCode = client.executeMethod(method);
    		//if (statusCode == 200) {
    			//System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Notification well received by xCP");   			
    			System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: xCP said:" + method.getResponseBodyAsString());   			
    		//} else {
        //		System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Could not notify xCP");   			
    		//}
			System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Status Code >>> "+statusCode);
    		System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: QueryString >>> "+method.getQueryString());
    		System.out.println("xUtilsWSAPI.NotifyBatchJobEnd: Status Text >>> "+HttpStatus.getStatusText(statusCode));
    		//byte [] res  = method.getResponseBody();
    		//FileOutputStream fos= new FileOutputStream("donepage.html");
    		//fos.write(res);
    		//fos.close();
    		method.releaseConnection();
    		return true;
    	}
    	catch(IOException e) {
      		e.printStackTrace();
      		return false;
    	}
    }

}
