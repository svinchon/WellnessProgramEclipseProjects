package com.diy.hiphelper;

import java.io.FileInputStream;
import java.util.ResourceBundle;

public class SOAPQueryRequestMessage {
	
	ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
	String strSOAPTemplatesDirectory = rb.getString("SOAPTemplatesDirectory");
	private String strTemplateLocation = strSOAPTemplatesDirectory + "/SOAPQueryTemplate.xml";
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
