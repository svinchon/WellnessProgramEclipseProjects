package com.diy.xpressionhelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class xPressionHelperUtils {

	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static void vByte2File(byte[] bData, String strFileName) throws Exception {
		
		File outFile = new File(strFileName);
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
			System.out.println("xDocxa - Directory Created");
		}
		FileOutputStream fos = new FileOutputStream(outFile);
		fos.write(bData);
		System.out.println("xDocxa - File written");
		fos.close();
	}

	public static byte[] bFile2Byte(String strFileName) throws Exception {
		File inFile = new File(strFileName);
		FileInputStream fis = new FileInputStream(inFile);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();
		return b;
	}
	
	public static String strUpdateNodeInXMLFile(String strFileName, String strXPath, String strValue) {
		String strReturn = "nothing done";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(strFileName));
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			for (int idx = 0; idx < nodes.getLength(); idx++) {
			    nodes.item(idx).setTextContent(strValue);
			}
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(new DOMSource(doc), new StreamResult(new File(strFileName)));
			xformer = null;
			doc = null;
			strReturn = "strUpdateNodeInXMLFile sucessfull";
		} catch (Exception e) {
			e.printStackTrace();
			strReturn = "Error during strUpdateNodeInXMLFile";
		} 
		return strReturn;
	}
	
	public static String strGenerateUniqueIdentifier() {
		return UUID.randomUUID().toString();
	}
}

