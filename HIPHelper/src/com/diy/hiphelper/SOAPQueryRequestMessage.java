package com.diy.hiphelper;

import java.io.FileInputStream;

public class SOAPQueryRequestMessage {
	
	private String strTemplateLocation =
			"D:/xData/Business/Projects/2014/Q3/2014q3-09.WellnessProgram/HIP/SOAPQueryTemplate.xml";
	private String strXML;
	private String strUniqueId;
	private String strPATIENT_ID_PREFIX = "";//"WP_PATIENT_ID_";
	
	public static void main(String[] args) {
		SOAPQueryRequestMessage m = new SOAPQueryRequestMessage("0007");
		Log(m.getXML());
	}
	
	SOAPQueryRequestMessage(String strUniqueId) {
		this.strUniqueId = strUniqueId;
	}
	
	public String getXML() {
		UpdateXML();
		return this.strXML;
	}
	
	private void UpdateXML() {
		this.strXML = "";
		try {
			FileInputStream fis = new FileInputStream(this.strTemplateLocation);
			byte[] bTemplate = new byte[fis.available()];
			fis.read(bTemplate);
			fis.close();
			this.strXML = new String(bTemplate, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		strXML = strXML.replaceAll(
				"--"+"PATIENT_ID" +"--",
				this.strPATIENT_ID_PREFIX + this.strUniqueId
		);
	}
	
	private static void Log(String string) {
		System.out.println(string);
	}

}
