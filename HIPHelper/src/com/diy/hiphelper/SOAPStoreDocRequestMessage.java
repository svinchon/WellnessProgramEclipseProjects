package com.diy.hiphelper;

import java.io.FileInputStream;
import java.util.ResourceBundle;

public class SOAPStoreDocRequestMessage {
	
	ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
	String strSOAPTemplatesDirectory = rb.getString("SOAPTemplatesDirectory");
	private String strTemplateLocation = strSOAPTemplatesDirectory + "/SOAPStoreDocTemplate.xml";
	private String strXML;
	private String strPatientId;
	private String strDocId;
	//private String strDOC_OBJ_ID = "WP_DOC_OBJ_ID";
	private String strMIME_TYPE = "application/xml";
	private String strPATIENT_ID_PREFIX = "";//"WP_PATIENT_ID_";
	private String strDOC_ENTRY_ID_PREFIX = "";//WP_DOC_ENTRY_ID";
	private String strSUBM_SET_OBJ_ID = "WP_SUBM_SET_OBJ_ID";
	private String strSUBM_SET_ID= "WP_SUBM_SET_ID";
	private String strASSOC_ID = "WP_ASSOC_ID";
	private String strDOC_ATT_ID = "DOC_ATT_ID";
	private byte[] bDoc = null;
	private String strDOC_BIN_DATA = ""; //<![CDATA[<root>null</root>]]>";
	
	public static void main(String[] args) {
		SOAPStoreDocRequestMessage m = new SOAPStoreDocRequestMessage("PAT01", "DOC01", "<root><field>value</field></root>".getBytes());
		Log(m.getXML());
	}
	
	SOAPStoreDocRequestMessage(String strPatientId, String strDocId, byte [] bDoc) {
		this.strPatientId = strPatientId;
		this.strDocId = strDocId;
		this.bDoc = bDoc;
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
		this.strDOC_BIN_DATA = ""
				//+ "<![CDATA["
				+ new String(this.bDoc).replace("<", "&lt;").replace(">", "&gt;");		//+ "]]>";
		strXML = strXML.replaceAll(
				"--"+"DOC_OBJ_ID"  		+"--",
				//this.strDOC_OBJ_ID		+"_"+ this.strPatientId+"_"+
				this.strDocId
		);
		strXML = strXML.replaceAll("--"+"DOC_BIN_DATA"		+"--",	this.strDOC_BIN_DATA			);
		strXML = strXML.replaceAll("--"+"ASSOC_ID"    		+"--",	this.strASSOC_ID				+"_"+ this.strPatientId+"_"+this.strDocId);
		strXML = strXML.replaceAll("--"+"DOC_ENTRY_ID"		+"--",	this.strDOC_ENTRY_ID_PREFIX		+ this.strDocId);
		strXML = strXML.replaceAll("--"+"MIME_TYPE"			+"--",	this.strMIME_TYPE				);
		strXML = strXML.replaceAll("--"+"PATIENT_ID"		+"--",	this.strPATIENT_ID_PREFIX		+ this.strPatientId);
		strXML = strXML.replaceAll("--"+"SUBM_SET_ID"		+"--",	this.strSUBM_SET_ID				+"_"+ this.strPatientId+"_"+this.strDocId);
		strXML = strXML.replaceAll("--"+"SUBM_SET_OBJ_ID"	+"--",	this.strSUBM_SET_OBJ_ID			+"_"+ this.strPatientId+"_"+this.strDocId);
		strXML = strXML.replaceAll("--"+"DOC_ATT_ID"		+"--",	this.strDOC_ATT_ID				+"_"+ this.strPatientId+"_"+this.strDocId);
	}
	
	private static void Log(String string) {
		System.out.println(string);
	}

}
