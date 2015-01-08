package com.diy.wellnessprogram;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

public class RESTTestClientV2 {

	static {
		HttpsURLConnection.setDefaultHostnameVerifier(
			new HostnameVerifier(){
				public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
					if (hostname.equals("128.199.52.160")) { return true; } 
					return false;
				}
			}
		);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		RESTHelperV2 rh = new RESTHelperV2();
		RESTCallResultV2 rcr;
		String strGender = "male";
		String strEmail = "john.doe.02@gmail.com";
		String strPassword = "Pa$$word";
		String strUserName= "johndoe02";
		String strBirthDate = "1698-05-08";
		String strWeight = "80";
		int iSportActivity = 2;
		String strExternalId = "085002";
		String strResult;
		//strResult = rh.submitNewUser(strUserName, strEmail, strPassword, strExternalId, strGender, strWeight, strBirthDate, iSportActivity);
		//Log("WPR / RESTTestClient => "+strResult);
		strResult = rh.getUserIndex("bM72yorOnhkIsQQlG82Pgw");
		Log("WPR / RESTTestClient => "+strResult);
		strResult = rh.getUsers("JSON");
		Log("WPR / RESTTestClient => "+strResult);
		strResult = rh.getUsers("XML");
		Log("WPR / RESTTestClient => "+strResult);
		//Log("WPR / RESTTestClient => "+RESTHelperV2.prettyPrintXML(strResult));
		
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
		System.out.println("INFO: "+str);
	}

}