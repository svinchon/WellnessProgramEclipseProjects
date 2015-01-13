package com.diy.HTTPS;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/*
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
*/

public class Test01 {

	public static void main(String[] args) {
		try {
			//System.setProperty("javax.net.ssl.trustStore", "/usr/lib/jvm/jdk1.6.0_32/jre/lib/security/cacerts");
			//System.setProperty("javax.net.ssl.trustStore", "C:/Program Files/Java/jre7/lib/security/cacerts");
			////System.setProperty("javax.net.ssl.trustStore", "C:/Program Files/Java/jdk1.7.0_25/jre/lib/security/jssecacerts");
			////System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
			////Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			//TrustStore..
			////char[] passphrase = "changeit".toCharArray(); //password
			////KeyStore keystore = KeyStore.getInstance("JKS");
			//KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			////keystore.load(new FileInputStream("C:/Program Files/Java/jre7/lib/security/cacerts"), passphrase); //path
			//TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509"); //instance
			////TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			////tmf.init(keystore);
			////SSLContext context = SSLContext.getInstance("TLS");
			////TrustManager[] trustManagers = tmf.getTrustManagers();
			////context.init(null, trustManagers, null);
			//context.init(null, null, null);
			////SSLSocketFactory sf = context.getSocketFactory();
			//URL url = new URL("https://www.google.fr");
			URL url = new URL("https://xpression45.iigfrance.com/WellnessProgramHelper/RESTTest");
			HttpsURLConnection httpsCon = (HttpsURLConnection) url.openConnection();
			////httpsCon.setSSLSocketFactory(sf);
			httpsCon.setRequestMethod("GET");
			////
			System.out.println("\nResponse Message is " + httpsCon.getResponseMessage());
			InputStream inStrm = httpsCon.getInputStream();
			System.out.println("\nContent at " + url);
			int ch;
			while (((ch = inStrm.read()) != -1)){
				System.out.print((char) ch);
			}
			inStrm.close();
			/*
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, null, null);
			SSLSocketFactory sf = new SSLSocketFactory(
				sslcontext,
				SSLSocketFactory.STRICT_HOSTNAME_VERIFIER
			);
			Scheme https = new Scheme("https", 443, sf);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
