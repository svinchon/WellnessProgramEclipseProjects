package com.diy.rest;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.diy.rest.wellnessprogram.model.User;
//import com.diy.jersey.wellnessprogram.registration.model.User;
//import com.diy.jersey.wellnessprogram.registration.model.user_register_request;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class TestRESTClient {

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
	
	public static void main(String[] args) {
		String strJSONInput = ""
				+ "{"
				+ "'user_register':{"
				+ "'username':'john_doe28',"
				+ "'email':'john+doe28@example.com',"
				+ "'password':'pa$$word',"
				+ "'external_id':'5656866-12',"
				+ "'gender':'female',"
				+ "'weight':75.6667,"
				+ "'birth_date':'1900-01-31',"
				+ "'sports_activity':2"
				+ "}"
				+ "}";
		strJSONInput = strJSONInput.replace("'", "\"").replace("\t", " ");
		try {
			JSONObject jsonObject = new JSONObject(strJSONInput);
			JSONObject jsonObject2 = jsonObject.getJSONObject("user_register");
			Log(jsonObject2.getString("email"));
			ObjectMapper m = new ObjectMapper();
			User user = m.readValue(jsonObject2.toString(), User.class);
			Log(user.getUsername());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//JSONArray jsonArray = jsonObject.getJSONArray("someJsonArray");
		//String value = jsonArray.optJSONObject(i).getString("someJsonValue");
		/*RESTCallResult rcr;
		rcr = CallRESTService(
				"https://128.199.52.160/api/v1/users/",
				"POST",
				strJSONInput,
				"emc_api",
				"brownwallflying"
		);
		rcr = CallRESTService(
				"https://128.199.52.160/api/v1/users/eWisiXfUXL4INp0P6C5XpQ/status",
				"GET",
				"",
				"emc_api",
				"brownwallflying"
		);
		rcr = CallRESTService(
				"https://128.199.52.160/api/v1/users/_all/",
				"GET",
				"",
				"emc_api",
				"brownwallflying"
		);*/

	}
	
	public static RESTCallResult CallRESTService(
			String RESTEndPoint,
			String RESTMethod,
			String strJSONInput,
			String HTTPBasicSecurityLogin,
			String HTTPBasicSecurityPassword

	) {
		if (!strJSONInput.equals("")) {
			Log("JSON Input: "+strJSONInput);
		}
		RESTCallResult rcr = new RESTCallResult();
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
		Log("HTTP Message: " + rcr.strHTTPMessage);
		Log("Result Message: " + rcr.strResultMessage.replaceAll("\n", "").replaceAll("\t", "").replaceAll(">\\s+<", "><").trim());
		Log("success? " + rcr.boolSuccessfull);
		return rcr;
	}
	
	private static void Log(String str) {
		System.out.println("LOG: "+str);
	}

}

