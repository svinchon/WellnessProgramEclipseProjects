package com.diy.wellnessprogram;

import java.util.ResourceBundle;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VITEXRESTServicesTester {

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
	
	public static void main(String[] args) {
		VITEXRESTHelper rh = new VITEXRESTHelper();
		//RESTCallResult rcr;
		UUID ui = UUID.randomUUID();
		String strGender = "male";
		String strEmail = "john.doe.02@gmail.com";
		String strPassword = "Pa$$word";
		String strUserName= "johndoe02";
		String strBirthDate = "1698-05-08";
		String strWeight = "80";
		int iSportActivity = 2;
		String strExternalId = "085002";
		String strResult;
		strResult = rh.submitNewUser(strUserName, strEmail, strPassword, strExternalId, strGender, strWeight, strBirthDate, iSportActivity);
		Log("WPR / RESTTestClient => "+strResult);
		String strErrorMessage;
		if (strResult.contains("ERROR")) {
			strErrorMessage = "Validation Errors:";
			String strJSONInput = strResult.substring(6);
			try {
				JSONObject jsonObject = new JSONObject(strJSONInput);
				JSONArray jsonArray = jsonObject.getJSONObject("errors").getJSONObject("children").getJSONObject("username").getJSONArray("errors");
				for (int i=0;i<jsonArray.length();i++) {
					strErrorMessage += "\n"+jsonArray.getString(i);
				}
				jsonArray = jsonObject.getJSONObject("errors").getJSONObject("children").getJSONObject("email").getJSONArray("errors");
				for (int i=0;i<jsonArray.length();i++) {
					strErrorMessage += "\n"+jsonArray.getString(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Log("WPR / RESTTestClient => \n"+strErrorMessage);
		}
		strUserName= "johndoe02"+ui;
		strEmail = strUserName+"@gmail.com";
		strExternalId = "085002_"+strUserName;
		strResult = rh.submitNewUser(
				strUserName,
				strEmail,
				strPassword,
				strExternalId,
				strGender,
				strWeight,
				strBirthDate,
				iSportActivity
		);
		Log("WPR / RESTTestClient => "+strResult);
		String strNewUserId=strResult.substring(11);
		Log(strNewUserId);
		strResult = rh.getUserIndex(strNewUserId);
		Log("WPR / RESTTestClient => "+strResult);
		strResult = rh.getUsers("JSON");
		Log("WPR / RESTTestClient => "+strResult);
		strResult = rh.getUsers("XML");
		Log("WPR / RESTTestClient => "+strResult);
		String strXML = strResult.substring(12);
		Log("WPR / RESTTestClient => "+strXML);
		Log("WPR / RESTTestClient => "+VITEXRESTHelper.prettyPrintXML(strXML));
		
		/* object mapper example 
		strJSONOutput = "{\"id\":\"eWisiXfUXL4INp0P6C5XpQ\",\"username\":\"john_doe26\",\"index\":null}";
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(
			strJSONOutput,
			User.class
		);
		Log("------> "+user.getId());
		*/
	}
	
	private static void Log(String str) {
		System.out.println(str);
	}

}