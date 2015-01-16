package com.diy.xmlhelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

	public static String strUpdateNodeInXML(String strXML, String strXPath, String strValue) {
		String strReturn = "nothing done";
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(strXPath, doc, XPathConstants.NODESET);
			for (int idx = 0; idx < nodes.getLength(); idx++) {
			    nodes.item(idx).setTextContent(strValue);
			}
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			StringWriter s = new StringWriter();
			xformer.transform(new DOMSource(doc), new StreamResult(s));
			xformer = null;
			doc = null;
			strReturn = s.toString();
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(strXML);
			strReturn = "Error during strUpdateNodeInXMLFile";
		} 
		System.out.println("XMLHelper => strUpdateNodeInXML: "+strReturn);
		return strReturn;
	}

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
			NamespaceContext ns = new NamespaceContext() {
				
				@SuppressWarnings("rawtypes")
				@Override
				public Iterator getPrefixes(String namespaceURI) {
					return null;
				}
				
				@Override
				public String getPrefix(String namespaceURI) {
					ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
					HashMap<String, String> m1 = new HashMap<String, String>();
					HashMap<String, String> m2 = new HashMap<String, String>();
					String namespaces = rb.getString("namespaces");
					String[] namespace = namespaces.split(";");
					for (int i=0;i<namespace.length;i++) {
						String[] ns = namespace[i].split(" ");
						m1.put(ns[0], ns[1]);
						m2.put(ns[1], ns[0]);
					}
					return (String)m2.get(namespaceURI);
				}
				
				@Override
				public String getNamespaceURI(String prefix) {
					ResourceBundle rb = ResourceBundle.getBundle("HIPHelper");
					HashMap<String, String> m1 = new HashMap<String, String>();
					HashMap<String, String> m2 = new HashMap<String, String>();
					String namespaces = rb.getString("namespaces");
					String[] namespace = namespaces.split(";");
					for (int i=0;i<namespace.length;i++) {
						String[] ns = namespace[i].split(" ");
						m1.put(ns[0], ns[1]);
						m2.put(ns[1], ns[0]);
					}
					return (String)m1.get(prefix);				}
			};
			xpath.setNamespaceContext(ns);
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
			System.out.println("Input data: "+strXML.replaceAll("[\n\t]",""));
			System.out.println("Input xquery: "+strXPath.replaceAll("[\n\t]",""));
			System.out.println("Error message : "+e.getMessage());
			result = new String[1];
			result[0] = "ERROR: "+e.getMessage();
			e.printStackTrace();
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

}
