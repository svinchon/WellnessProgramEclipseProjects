package com.diy.xmlhelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import javax.xml.xquery.XQStaticContext;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLHelper {

	public static String strGetValueFromXML(String strXML, String strXPath) {
		String result="";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new InputSource(new StringReader(strXML))
			);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			if (nodes!=null && nodes.getLength()>0) {
				String strValue=nodes.item(0).getTextContent();
				//System.out.println("XML Helper => Extracted "+strXPath+" value: "+strValue);
				Log(strXPath + ": " + strValue);
				return strValue;
			} else {
				result = "ERROR strGetValueFromXML (xpath returned null or empty)";
				System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
				System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
				System.out.println("Error message : "+"xpath returned null or empty");			}
			nodes = null;
			xpath = null;
			doc = null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
			System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			result = "ERROR: "+e.getMessage();
		}
		return result;
	}

	public static String[] strGetValuesFromXML(String strXML, String strXPath) {
		String[] result= null;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				new InputSource(new StringReader(strXML))
			);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			if (nodes!=null && nodes.getLength()>0) {
				int iArraySize = nodes.getLength();
				result = new String[iArraySize];
				for (int iNode=0;iNode<iArraySize;iNode++) {
					result[iNode] = nodes.item(iNode).getTextContent();					
				}
			} else {
				result = new String[1];
				result[0] = "ERROR strGetValueFromXML (xpath returned null or empty)";
				System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
				System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
				System.out.println("Error message : "+"xpath returned null or empty");
			}
			nodes = null;
			xpath = null;
			doc = null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
			System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			result = new String[1];
			result[0] = "ERROR: "+e.getMessage();
		}
		return result;
	}
	
	public static String strRunXQuery(String strInputAsString, String strXQUERYAsString) {
		String strResult = "ERROR";
		try {
			//String strInputAsString = "<root><item index='1'>item A</item><item index='2'>item B</item></root>";
			//String strXQUERYAsString = "declare variable $doc external;<xdata>{for $i in $doc/root/item return $i}</xdata>";			
			ByteArrayInputStream bais = new ByteArrayInputStream(strInputAsString.getBytes());
			SaxonXQDataSource ds = new SaxonXQDataSource();
			XQConnection con = ds.getConnection();
			XQStaticContext ctx = con.getStaticContext();
			ctx.setBindingMode(XQConstants.BINDING_MODE_DEFERRED);
			con.setStaticContext(ctx);
			XQPreparedExpression expr = con.prepareExpression(strXQUERYAsString);
			expr.bindDocument(new QName("doc"), bais, null, null);
			XQSequence result = expr.executeQuery();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			result.writeSequence(baos, null);
			strResult = baos.toString("UTF-8");
			result.close();
			expr.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Input data: "+strInputAsString.replaceAll("[\n\t]",""));
			System.out.println("Input xquery: "+strXQUERYAsString.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			strResult = "ERROR: "+e.getMessage();
		}
		return strResult;
    }
	
	static void Log(String str) {
		System.out.println("XMLHelper => "+str);
	}
}
