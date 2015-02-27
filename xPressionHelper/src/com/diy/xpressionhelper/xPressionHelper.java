package com.diy.xpressionhelper;


import java.io.File;
import java.io.FilenameFilter;

import java.util.ResourceBundle;

import com.diy.QuickDoc.QuickDocPortTypeProxy;

import net.lingala.zip4j.core.ZipFile;

public class xPressionHelper {
	
	public String strUploadFile(
		byte[] bFileData,
		String strLocation,
		String strFileName
	) {
		System.out.println("xDocxa - START");
		String strReturn = "nothing done";
		ResourceBundle rb = ResourceBundle.getBundle("xDocxa");
		if(strLocation == null || strLocation.equals("")) {
			strLocation = rb.getString("DefaultTargetDirectory");
		}
		try {
			System.out.println("xDocxa - Before write");
			System.out.println("xDocxa - File size: "+bFileData.length);
			xPressionHelperUtils.vByte2File(bFileData, strLocation+"/"+strFileName);
			System.out.println("xDocxa - After write");
			strReturn = "File sucessfully written to "+strLocation+"/"+strFileName;
		} catch (Exception e) {
			e.printStackTrace();
			strReturn = "Error writting File to "+strLocation+"/"+strFileName;
		}
		System.out.println("xDocxa - END");
		return strReturn;
	}
	
	public byte[] bProcessZIPAndReturnPDF(
		byte[] bFileData,
		String strTemplateName,
		String strOutputProfileName,
		String strIdentifiantUnique
	) {
		String strReturn = "nothing done";
		byte[] bReturn = strReturn.getBytes();
		System.out.println("xDocxa - START");
		ResourceBundle rb = ResourceBundle.getBundle("xDocxa");
		String strLocation = rb.getString("ProcessZIPRootDirectory");
		String strxPressionHost = rb.getString("xPressionHost");
		String strxPressionPort = rb.getString("xPressionPort");
		if (strOutputProfileName==null || strOutputProfileName.isEmpty()) strOutputProfileName="PDF to Caller";
		if (strIdentifiantUnique==null || strIdentifiantUnique.isEmpty()) strIdentifiantUnique=xPressionHelperUtils.strGenerateUniqueIdentifier();
		try {
			System.out.println("xDocxa - Before write");
			System.out.println("xDocxa - File size: "+bFileData.length);
			String strZIPPath=strLocation+"/"+strIdentifiantUnique+".zip";
			xPressionHelperUtils.vByte2File(bFileData, strZIPPath);
			System.out.println("xDocxa - After write");
			strReturn = "- ZIP written to "+strZIPPath;
			ZipFile zipFile = new ZipFile(strLocation+"/"+strIdentifiantUnique+".zip");
			String strDirPath=strLocation+"/"+strIdentifiantUnique;
			zipFile.extractAll(strDirPath);
			strReturn += "\n"+"- ZIP extracted to "+strDirPath;
			File fZipFile = new File(strLocation+"/"+strIdentifiantUnique+".zip");
			fZipFile.delete();
			strReturn += "\n"+"- ZIP deleted";
			File fDir = new File(strLocation+"/"+strIdentifiantUnique);
			File[] fXMLs = fDir.listFiles(new FilenameFilter() { 
			    public boolean accept(File dir, String filename) { 
			        return filename.endsWith(".xml"); 
			    }
			});
			String strXMLFile = fXMLs[0].getAbsolutePath();
			System.out.println("xDocxa - Found XML in ZIP: "+strXMLFile);
			strReturn += "\n"+"- XML found at "+strXMLFile;
			xPressionHelperUtils.strUpdateNodeInXMLFile(
					strXMLFile,
					"/root/document/requestInfo/uniqueIdentifier",
					strIdentifiantUnique
			);
			xPressionHelperUtils.strUpdateNodeInXMLFile(
					strXMLFile,
					"/root/document/requestInfo/distibutionParameter5",
					strLocation + "/" + strIdentifiantUnique
			);
			strReturn += "\n"+"- XML adjusted";
			QuickDocPortTypeProxy qdp = new QuickDocPortTypeProxy();
			String strEndPoint = qdp.getEndpoint();
			strEndPoint = strEndPoint.replaceFirst("localhost", strxPressionHost);
			strEndPoint = strEndPoint.replaceFirst("18080", strxPressionPort);
			System.out.println(strEndPoint);
			strReturn += "\n"+"- EndPoint adjusted: "+strEndPoint;
			qdp.setEndpoint(strEndPoint);
			String requestContext = "<RequestContext><Credentials method=\"UserID and Password\"><UserID>xDesigner</UserID><Password>Pa55word</Password></Credentials><ApplicationName>xPression DevKit</ApplicationName></RequestContext>";
			String customerData = new String(xPressionHelperUtils.bFile2Byte(strXMLFile));
			//String outputProfileName = "PDF to Caller";
			//String documentName = "ForturamaXWord_Template";
			bReturn = qdp.publishAndReturnDocument(
				requestContext,
				strTemplateName,
				customerData,
				strOutputProfileName
			);
		} catch (Exception e) {
			//e.printStackTrace();
			strReturn = "<ERROR>\nError processing "+strIdentifiantUnique+ " (bProcessZIPAndReturnPDF)"+
					"\n---------------\nWork Done Before Failure:\n"+strReturn+
					"\n---------------\nException Stack Trace\n: "+e.getMessage()+
					"\n</ERROR>";
			bReturn = (strReturn).getBytes();
		}
		System.out.println("xDocxa - END");
		return bReturn;
	}

	public String strProcessZIPAndDistributeAsynchrounously(
		byte[] bFileData,
		String strTemplateName,
		String strOutputProfileName,
		String strIdentifiantUnique
	) {
		String strReturn = "nothing done";
		System.out.println("xDocxa - START");
		ResourceBundle rb = ResourceBundle.getBundle("xDocxa");
		String strLocation = rb.getString("ProcessZIPRootDirectory");
		String strxPressionHost = rb.getString("xPressionHost");
		String strxPressionPort = rb.getString("xPressionPort");
		if (strOutputProfileName==null || strOutputProfileName.isEmpty()) strOutputProfileName="PDF to Caller";
		if (strIdentifiantUnique==null || strIdentifiantUnique.isEmpty()) strIdentifiantUnique=xPressionHelperUtils.strGenerateUniqueIdentifier();
		try {
			System.out.println("xDocxa - Before write");
			System.out.println("xDocxa - File size: "+bFileData.length);
			String strZIPPath=strLocation+"/"+strIdentifiantUnique+".zip";
			xPressionHelperUtils.vByte2File(bFileData, strZIPPath);
			System.out.println("xDocxa - After write");
			strReturn = "- ZIP written to "+strZIPPath;
			ZipFile zipFile = new ZipFile(strLocation+"/"+strIdentifiantUnique+".zip");
			String strDirPath=strLocation+"/"+strIdentifiantUnique;
			zipFile.extractAll(strDirPath);
			strReturn += "\n"+"- ZIP extracted to "+strDirPath;
			File fZipFile = new File(strLocation+"/"+strIdentifiantUnique+".zip");
			fZipFile.delete();
			strReturn += "\n"+"- ZIP deleted";
			File fDir = new File(strLocation+"/"+strIdentifiantUnique);
			File[] fXMLs = fDir.listFiles(new FilenameFilter() { 
			    public boolean accept(File dir, String filename) { 
			        return filename.endsWith(".xml"); 
			    }
			});
			String strXMLFile = fXMLs[0].getAbsolutePath();
			System.out.println("xDocxa - Found XML in ZIP: "+strXMLFile);
			strReturn += "\n"+"- XML found at "+strXMLFile;
			xPressionHelperUtils.strUpdateNodeInXMLFile(
					strXMLFile,
					"/root/document/requestInfo/uniqueIdentifier",
					strIdentifiantUnique
			);
			xPressionHelperUtils.strUpdateNodeInXMLFile(
					strXMLFile,
					"/root/document/requestInfo/distibutionParameter5",
					strLocation + "/" + strIdentifiantUnique
			);
			strReturn += "\n"+"- XML adjusted";
			QuickDocPortTypeProxy qdp = new QuickDocPortTypeProxy();
			String strEndPoint = qdp.getEndpoint();
			strEndPoint = strEndPoint.replaceFirst("localhost", strxPressionHost);
			strEndPoint = strEndPoint.replaceFirst("18080", strxPressionPort);
			System.out.println(strEndPoint);
			strReturn += "\n"+"- EndPoint adjusted: "+strEndPoint;
			qdp.setEndpoint(strEndPoint);
			String requestContext = "<RequestContext><Credentials method=\"UserID and Password\"><UserID>xDesigner</UserID><Password>Pa55word</Password></Credentials><ApplicationName>xPression DevKit</ApplicationName></RequestContext>";
			String customerData = new String(xPressionHelperUtils.bFile2Byte(strXMLFile));
			//String outputProfileName = "PDF to Caller";
			//String documentName = "ForturamaXWord_Template";
			strReturn += "\n"+qdp.publishDocument(
				requestContext,
				strTemplateName,
				customerData,
				strOutputProfileName
			);
		} catch (Exception e) {
			//e.printStackTrace();
			strReturn = "<ERROR>"+
					"\nError processing "+strIdentifiantUnique+" (strProcessZIPAndDistributeAsynchrounously)"+
					"\n---------------\nWork Done Before Failure:\n"+strReturn+
					"\n---------------\nException Stack Trace\n: "+e.getMessage()+
					"\n</ERROR>";
		}
		System.out.println("xDocxa - END");
		return strReturn;
	}

	public String strPublishDocument(
			String strTemplateName,
			String strData,
			String strOutputProfileName
		) {
			String strReturn = "nothing done";
			//System.out.println("xPressionHelper => START");
			ResourceBundle rb = ResourceBundle.getBundle("xPressionHelper");
			String strxPressionHost = rb.getString("xPressionHost");
			String strxPressionPort = rb.getString("xPressionPort");
			String strSimulatexPression = rb.getString("SimulatexPression");
			if (strOutputProfileName==null || strOutputProfileName.isEmpty()) strOutputProfileName="PDF to File";
			try {
				QuickDocPortTypeProxy qdp = new QuickDocPortTypeProxy();
				String strEndPoint = qdp.getEndpoint();
				strEndPoint = strEndPoint.replaceFirst("localhost", strxPressionHost);
				strEndPoint = strEndPoint.replaceFirst("18080", strxPressionPort);
				//System.out.println("xPressionHelper => "+strEndPoint);
				strReturn += "\n"+"- EndPoint adjusted: "+strEndPoint;
				qdp.setEndpoint(strEndPoint);
				String requestContext = "<RequestContext><Credentials method=\"UserID and Password\"><UserID>xDesigner</UserID><Password>Pa55word</Password></Credentials><ApplicationName>xPression DevKit</ApplicationName></RequestContext>";
				if (!strSimulatexPression.equals("true")) {
					strReturn += "\n"+qdp.publishDocument(
						requestContext,
						strTemplateName,
					    strData,
					    strOutputProfileName
					);
				} else {
					Log("Simulating xPression!");
					Log("Data: "+ strData);
				}
			} catch (Exception e) {
				strReturn = ""+
						"<ERROR>"+
						e.getMessage()+
						"\n</ERROR>";
				Log("ERROR talking to xVM");
			}
			//System.out.println("xPressionHelper => END");
			return strReturn;
		}
	
	private void Log(String str) {
		System.out.println("xPressionHelper => "+str);
	}
}

