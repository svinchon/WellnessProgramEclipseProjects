package com.diy.wellnessprogram;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ResourceBundle;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;

public class RESTHelperV2 {

	public RESTCallResultV2 CallRESTService(
			String RESTEndPoint,
			String RESTMethod,
			String strJSONInput,
			String HTTPBasicSecurityLogin,
			String HTTPBasicSecurityPassword

	) {
		if (!strJSONInput.equals("")) {
			Log("WPR / RESTHelperV2 / CallRESTService => JSON Input: "+strJSONInput);
		}
		RESTCallResultV2 rcr = new RESTCallResultV2();
		rcr.boolSuccessfull = null;
		DefaultClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE
		);
		Client client = Client.create(clientConfig);
		client.addFilter(
				new HTTPBasicAuthFilter(
						HTTPBasicSecurityLogin,
						HTTPBasicSecurityPassword
				)
		);
		WebResource webResource = client.resource(RESTEndPoint);
		ClientResponse response = null;
		if (RESTMethod.equals("POST")) {
			response = webResource
					.accept("application/json")
					.type("application/json")
					.post(ClientResponse.class, strJSONInput);
		} else {
			response = webResource.
					accept("application/json")
					.type("application/json")
					.get(ClientResponse.class);
		}
		rcr.strHTTPMessage = response.toString();
		if (
				response.getStatus() != 200
				&
				response.getStatus() != 201
				&
				response.getStatus() != 404
		)
		{ rcr.boolSuccessfull = false; } else { rcr.boolSuccessfull = true; }
		rcr.strResultMessage = response.getEntity(String.class);
		response = null;
		webResource = null;
		client = null;
		clientConfig = null;
		Log("WPR / RESTHelperV2 / CallRESTService => HTTP Message: " + rcr.strHTTPMessage);
		Log("WPR / RESTHelperV2 / CallRESTService => Result Message: " + rcr.strResultMessage.replaceAll("\n", "").replaceAll("\t", "").replaceAll(">\\s+<", "><").trim());
		Log("WPR / RESTHelperV2 / CallRESTService => success? " + rcr.boolSuccessfull);
		return rcr;
	}
	
	public String submitNewUser(
			String strUserName,
			String strEmail,
			String strPassword,
			String strExternalId,
			String strGender,
			String strWeight,
			String strBirthDate,
			int iSportActivity
	) {
		String strResult = "ERROR";
		try {
			String strVitexServerHost = ResourceBundle.getBundle("WellnessProgram").getString("VitexServerHost");		
			RESTHelperV2 rh = new RESTHelperV2();
			String strJSONInput = ""
					+ "{"
					+ "'user_register':{"
					+ "'username':'"+strUserName+"',"
					+ "'email':'"+strEmail+"',"
					+ "'password':'"+strPassword+"',"
					+ "'external_id':'"+strExternalId+"'," 
					+ "'gender':'"+strGender+"',"
					+ "'weight':"+strWeight+","
					+ "'birth_date':'"+strBirthDate+"'," 
					+ "'sports_activity':"+iSportActivity
					+ "}"
					+ "}";
			strJSONInput = strJSONInput.replace("'", "\"").replace("\t", " ");
			RESTCallResultV2 rcr;
			rcr = rh.CallRESTService(
					"https://"+strVitexServerHost+"/api/v1/users/",
					"POST",
					strJSONInput,
					"emc_api",
					"brownwallflying"
			);
			if (rcr.boolSuccessfull) {
				JSONObject obj = new JSONObject(rcr.strResultMessage); 
				String id = obj.getJSONObject("user").getString("id");
				strResult = "SUCCESS:id="+id;
			} else {
				strResult = "ERROR";
				Log("WPR / RestHelperV2 / submitNewUser => "+rcr.strResultMessage);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return strResult;
	}

	public String getUserIndex(
			String strId
	) {
		String strResult = "ERROR";
		try {
			String strVitexServerHost = ResourceBundle.getBundle("WellnessProgram").getString("VitexServerHost");		
			RESTHelperV2 rh = new RESTHelperV2();
			RESTCallResultV2 rcr;
			rcr = rh.CallRESTService(
					"https://"+strVitexServerHost+"/api/v1/users/"+strId+"/status",
					"GET",
					"",
					"emc_api",
					"brownwallflying"
			);
			if (rcr.boolSuccessfull) {
				JSONObject obj = new JSONObject(rcr.strResultMessage); 
				String index = obj.getJSONObject("user").getString("index");
				strResult = "SUCCESS:index="+index;
			} else {
				strResult = "ERROR";
				Log("WPR / RestHelperV2 / getUserIndex => "+rcr.strResultMessage);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return strResult;
	}

	public String getUsers(String strFormat) {
		String strResult = "ERROR";
		try {
			String strVitexServerHost = ResourceBundle.getBundle("WellnessProgram").getString("VitexServerHost");		
			RESTHelperV2 rh = new RESTHelperV2();
			RESTCallResultV2 rcr;
			rcr = rh.CallRESTService(
					"https://"+strVitexServerHost+"/api/v1/users/_all/",
					"GET",
					"",
					"emc_api",
					"brownwallflying"
			);
			if (rcr.boolSuccessfull) {
				//JSONObject obj = new JSONObject(rcr.strResultMessage); 
				//String index = obj.getJSONObject("user").getString("index");
				if (strFormat.equals("JSON")) {
					strResult = "SUCCESS:msg="+rcr.strResultMessage;		
				} else {
					JSONObject obj = new JSONObject(rcr.strResultMessage);
					String strXML = "<users>"+XML.toString(obj).replace("users", "user")+"</users>";
					strResult = "SUCCESS:xml="+strXML;
					//Log("WPR / RestHelperV2 / getUsers => \n"+prettyPrintXML(strXML));					
				}
			} else {
				strResult = "ERROR";
				Log("WPR / RestHelperV2 / getUsers => "+rcr.strResultMessage);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return strResult;
	}

	public static String prettyPrintXML(String str) {
		String strReturn = "";
		try {
			Source sourceContent = new StreamSource(new StringReader(str));
			StringWriter sw = new StringWriter();
			StreamResult xmlOutput=new StreamResult(sw);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			transformer.transform(sourceContent, xmlOutput);						
			strReturn = xmlOutput.getWriter().toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	    return strReturn;
	}
	
	private static void Log(String str) {
		System.out.println("LOG: "+str);
	}
	
}
