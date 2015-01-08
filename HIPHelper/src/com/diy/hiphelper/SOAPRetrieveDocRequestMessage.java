package com.diy.hiphelper;

import java.io.FileInputStream;

public class SOAPRetrieveDocRequestMessage {
	
	private String strTemplateLocation =
			"D:/xData/Business/Projects/2014/Q3/2014q3-09.WellnessProgram/HIP/SOAPRetrieveDocTemplate.xml";
	private String strXML;
	private String strPatientId;
	private String strDocId;
	private String strDOC_ENTRY_ID;
	
	public static void main(String[] args) {
		//SOAPRetrieveDocRequestMessage m = new SOAPRetrieveDocRequestMessage("PAT01","DOC01");
		SOAPRetrieveDocRequestMessage m = new SOAPRetrieveDocRequestMessage("1.42.20140505220900.6");
		Log(m.getXML());
	}
	
	SOAPRetrieveDocRequestMessage(String strPatientId, String strDocId) {
		this.strPatientId = strPatientId;
		this.strDocId = strDocId;
		this.strDOC_ENTRY_ID = "WP_DOC_ENTRY_ID_"+ this.strPatientId+"_"+ this.strDocId;
	}
	
	SOAPRetrieveDocRequestMessage(String strDocId) {
		this.strDOC_ENTRY_ID = strDocId;
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
		strXML = strXML.replaceAll("--"+"DOC_ENTRY_ID"		+"--",	this.strDOC_ENTRY_ID);
	}
	
	private static void Log(String string) {
		System.out.println(string);
	}

}
