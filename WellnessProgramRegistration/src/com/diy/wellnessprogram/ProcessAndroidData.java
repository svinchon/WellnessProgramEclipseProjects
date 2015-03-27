package com.diy.wellnessprogram;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import com.diy.xdb.XDBHelperProxy;

public class ProcessAndroidData {
	public String uploadNewData(String str) {
		String ret = "begin";
		try {
			Log("New Data: "+str+"");
			XDBHelperProxy xdbh;
			xdbh =  new XDBHelperProxy();
			Log(xdbh.getEndpoint());
			xdbh.setEndpoint("http://xpression:18080/XDBHelper/services/XDBHelper");
			xdbh.storeStringAsDoc(str, "FromMobileApp_"+this.generateTimeStamp()+".xml", "FromMobileApp");
			ret = "got data: " +str;
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = "ERROR";
		}
		return ret;
	}
	
	String generateTimeStamp() {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-DD@HH-mm-ss-SSS", Locale.US);
		//SimpleDateFormat DateFormat = new SimpleDateFormat("ddHmsSSS", Locale.US);
		Date d = new Date();
		return DateFormat.format(d);
	}
	
	void Log(String str) {
		System.out.println(str);
	}
}
