package com.diy.xdb;

import java.io.File;
import java.util.Date;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import com.xhive.XhiveDriverFactory;
import com.xhive.core.interfaces.XhiveDatabaseIf;
import com.xhive.core.interfaces.XhiveDriverIf;
import com.xhive.core.interfaces.XhiveSessionIf;
import com.xhive.dom.interfaces.XhiveDocumentIf;
import com.xhive.dom.interfaces.XhiveLibraryChildIf;
//import com.xhive.dom.interfaces.XhiveLibraryChildIf;
import com.xhive.dom.interfaces.XhiveLibraryIf;
import com.xhive.query.interfaces.XhiveXQueryResultIf;
import com.xhive.query.interfaces.XhiveXQueryValueIf;
import com.xhive.util.interfaces.IterableIterator;

public class XDBHelper {

	public XDBHelper() {
		
	}
	
	public static void main(String[] args) {
		XDBHelper h = new XDBHelper();
		h.Test();
		/*h.storeDoc(
			"C:/tmp/submitForBatch_from0-0-0-0-0-0-0-1_On_2015-01-16@12-00-17-591.xml",
			"TEST"
		);*/
		/*String strXQ =""
				+ "let $e:=<event><time>"+"</time><type>submitForBatch</type>"+"</event> "
				+ "return insert node $e as first into /audit_trail";
		
		h.Log(h.runXQuery(strXQ));*/
	}
	
	public String Test() {
		String strReturn = "NA";
		ResourceBundle rb = ResourceBundle.getBundle("XDBHelper");
		String databaseName = rb.getString("DatabaseName");//"xData";
		String administratorName = rb.getString("AdministratorName");//"Administrator";
		String administratorPassword = rb.getString("AdministratorPassword");//"demo.demo";
		XhiveDriverIf driver = XhiveDriverFactory.getDriver("xhive://"+ rb.getString("XDBHost")+":"+ rb.getString("XDBPort"));//localhost:1235");
		driver.init();
		XhiveSessionIf session = driver.createSession();
		try {
			session.connect(administratorName, administratorPassword, databaseName);
			session.begin();
			XhiveDatabaseIf xData = session.getDatabase();
			XhiveLibraryIf rootLibrary = xData.getRoot();
			XhiveLibraryIf l = (XhiveLibraryIf)rootLibrary.get("xPressionHelper");//Child
			//IterableIterator<XhiveLibraryChildIf> ii = rootLibrary.getChildren();
			LSParser builder = l.createLSParser();
			LSInput lsi = l.createLSInput();
			lsi.setStringData("<root>ggg</root>");
			Document firstDocument = builder.parse(lsi);
			String firstDocumentName = "test.xml";
			if (l.nameExists(firstDocumentName)) {
				Document docRetrievedByName = (Document)l.get( firstDocumentName );
				l.removeChild(docRetrievedByName);				
			}
			l.appendChild(firstDocument);
			((XhiveDocumentIf)firstDocument).setName(firstDocumentName);
			((XhiveDocumentIf)firstDocument).getMetadata().put("DateStored", new Date().toString());
			session.commit();
			strReturn = "OK";
		} catch (Exception e) {
			System.out.println("StoreDocuments sample failed: ");
			e.printStackTrace();
			strReturn = "KO: " + e.getMessage();
		} finally {
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return strReturn;
	}
	public String storeDoc(String strFileName, String strDocumentName, String strLibrary) {
		String strReturn = "NA";
		ResourceBundle rb = ResourceBundle.getBundle("XDBHelper");
		String databaseName = rb.getString("DatabaseName");//"xData";
		String administratorName = rb.getString("AdministratorName");//"Administrator";
		String administratorPassword = rb.getString("AdministratorPassword");//"demo.demo";
		XhiveDriverIf driver = XhiveDriverFactory.getDriver("xhive://"+ rb.getString("XDBHost")+":"+ rb.getString("XDBPort"));//localhost:1235");
		driver.init();
		XhiveSessionIf session = driver.createSession();
		try {
			session.connect(administratorName, administratorPassword, databaseName);
			session.begin();
			XhiveDatabaseIf xData = session.getDatabase();
			XhiveLibraryIf rootLibrary = (XhiveLibraryIf)((XhiveLibraryIf)xData.getRoot().get("xPressionHelper")).get(strLibrary);
			LSParser builder = rootLibrary.createLSParser();
			//Log(strFileName);
			Document firstDocument = builder.parseURI(new File(strFileName).toURI().toString());
			String firstDocumentName = strDocumentName;
			if (rootLibrary.nameExists(firstDocumentName)) {
				Document docRetrievedByName = (Document)rootLibrary.get( strDocumentName );
				rootLibrary.removeChild(docRetrievedByName);				
			}
			rootLibrary.appendChild(firstDocument);
			((XhiveDocumentIf)firstDocument).setName(firstDocumentName);
			((XhiveDocumentIf)firstDocument).getMetadata().put("FileName", strFileName);
			((XhiveDocumentIf)firstDocument).getMetadata().put("DateStored", new Date().toString());
			session.commit();
			strReturn = "OK";
		} catch (Exception e) {
			System.out.println("StoreDocuments sample failed: ");
			e.printStackTrace();
			strReturn = "KO: " + e.getMessage();
		} finally {
			// disconnect and remove the session
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return strReturn;
	}

	public String storeStringAsDoc(String string, String strDocumentName, String strLibrary) {
		String strReturn = "NA";
		ResourceBundle rb = ResourceBundle.getBundle("XDBHelper");
		String databaseName = rb.getString("DatabaseName");//"xData";
		String administratorName = rb.getString("AdministratorName");//"Administrator";
		String administratorPassword = rb.getString("AdministratorPassword");//"demo.demo";
		XhiveDriverIf driver = XhiveDriverFactory.getDriver("xhive://"+ rb.getString("XDBHost")+":"+ rb.getString("XDBPort"));//localhost:1235");
		driver.init();
		XhiveSessionIf session = driver.createSession();
		try {
			session.connect(administratorName, administratorPassword, databaseName);
			session.begin();
			XhiveDatabaseIf xData = session.getDatabase();
			XhiveLibraryIf rootLibrary = (XhiveLibraryIf)((XhiveLibraryIf)xData.getRoot().get("xPressionHelper")).get(strLibrary);
			LSParser builder = rootLibrary.createLSParser();
			LSInput lsi = rootLibrary.createLSInput();
			lsi.setStringData(string);
			Document firstDocument = builder.parse(lsi);
			String firstDocumentName = strDocumentName;
			if (rootLibrary.nameExists(firstDocumentName)) {
				Document docRetrievedByName = (Document)rootLibrary.get( strDocumentName );
				rootLibrary.removeChild(docRetrievedByName);				
			}
			rootLibrary.appendChild(firstDocument);
			((XhiveDocumentIf)firstDocument).setName(firstDocumentName);
			((XhiveDocumentIf)firstDocument).getMetadata().put("DateStored", new Date().toString());
			session.commit();
			strReturn = "OK";
		} catch (Exception e) {
			System.out.println("StoreDocuments sample failed: ");
			e.printStackTrace();
			strReturn = "KO: " + e.getMessage();
		} finally {
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return strReturn;
	}

	public String removeDoc(String strDocumentName, String strLibrary) {  
		ResourceBundle rb = ResourceBundle.getBundle("XDBHelper");
		String databaseName = rb.getString("DatabaseName");//"xData";
		String administratorName = rb.getString("AdministratorName");//"Administrator";
		String administratorPassword = rb.getString("AdministratorPassword");//"demo.demo";
		XhiveDriverIf driver = XhiveDriverFactory.getDriver("xhive://"+ rb.getString("XDBHost")+":"+ rb.getString("XDBPort"));//localhost:1235");
		driver.init();
		XhiveSessionIf session = driver.createSession();
		try {
			session.connect(administratorName, administratorPassword, databaseName);
			session.begin();
			XhiveDatabaseIf xData = session.getDatabase();
			XhiveLibraryIf rootLibrary = (XhiveLibraryIf)((XhiveLibraryIf)xData.getRoot().get("xPressionHelper")).get(strLibrary);
			Document docRetrievedByName = (Document)rootLibrary.get( strDocumentName );
			rootLibrary.removeChild(docRetrievedByName);
			session.commit();
		} catch (Exception e) {
			System.out.println("StoreDocuments sample failed: ");
			e.printStackTrace();
		} finally {
			// disconnect and remove the session
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return "OK";
	}

	public String runXQuery(String strQuery) {
		String strReturn = "";
		ResourceBundle rb = ResourceBundle.getBundle("XDBHelper");
		String databaseName = rb.getString("DatabaseName");//"xData";
		String administratorName = rb.getString("AdministratorName");//"Administrator";
		String administratorPassword = rb.getString("AdministratorPassword");//"demo.demo";
		XhiveDriverIf driver = XhiveDriverFactory.getDriver("xhive://"+ rb.getString("XDBHost")+":"+ rb.getString("XDBPort"));//localhost:1235");
		driver.init();
		XhiveSessionIf session = driver.createSession();
		try {
			// open a connection to the database
			session.connect(administratorName, administratorPassword, databaseName);
			// begin the transaction
			session.begin();
			// get a handle to the database
			XhiveDatabaseIf xData = session.getDatabase();
			// get a handle to the root library
			XhiveLibraryIf rootLibrary = xData.getRoot();
			//XhiveLibraryIf myLibrary;
			//= xData.ge.get("xPressionHelper");
			// Create a query (find all the short chapter titles)
			/*String theQuery = 
				"<xData>{for $i in document('/')/xData/xTransaction "
				//+ "where string-length($i) lt 16 \n"
				+ "return $i}</xData>";*/
			// Execute the query (place the results in the new document)
			System.out.println("#running query:\n" + strQuery);
			XhiveXQueryResultIf result = rootLibrary.executeXQuery(strQuery); //rootLibrary
			//XhiveXQueryResultIf result = myLibrary.executeXQuery(strQuery); //rootLibrary
			// Process the results
			while (result.hasNext()) {
				// Get the next value from the result sequence
				XhiveXQueryValueIf value = result.next();
				// Print this value
				//System.out.println(value.toString());
				Node n = (Node)value;
				LSSerializer writer = rootLibrary.createLSSerializer(); 
				writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); 
				strReturn = writer.writeToString(n);
				//.writeToURI(n, new File(strFileName).toURI().toString());
				//String output = writer.writeToString(n);
				//System.out.println(output);
			}
			session.commit();
		} catch (Exception e) {
			System.err.println("XQuery sample failed: ");
			e.printStackTrace();
			strReturn = "ERRRO";
		} finally {
			// disconnect and remove the session
			if (session.isOpen()) {
				session.rollback();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
			driver.close();
		}
		return strReturn;
	}

	void Log(String str) {
		System.out.println(str);
	}
	
}