package com.diy.charthelper.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

public class ChartHelperTestClient {

	public static void main(String[] args) {
		try {
			String xml = File2String("C:/GIT/WellnessProgramXPression/xDWTemplates/zWeeklyReport/LineChartData.xml");
			XML2ChartHelperProxy x2ch = new XML2ChartHelperProxy();
			x2ch.generateTSChartFromXML(xml);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	static String File2String(String path) {
		String strReturn = "";
		try {
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			byte[] bDoc = new byte[fis.available()];
			fis.read(bDoc);
			strReturn = new String(bDoc);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strReturn;
	}

}
