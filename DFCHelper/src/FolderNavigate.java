/**
 * 
 * Utility class that navigates repository tree
 * and generates XML representation.  
 * 
 * Parameters:
 *           DOCBASE USER PASS FOLDERPATH
 * Example Parameters:
 *           mydocbase dmadmin dmpass /Temp
 *
 * Example compilation command:
 * javac -classpath c:\progra~1\documentum\shared\dfc.jar;c:\documentum\config;. FolderNavigate.java
 *
 * Example run command:
 * java -classpath c:\progra~1\documentum\shared\dfc.jar;c:\documentum\config;. FolderNavigate mydocbase dmadmin dmpass /Temp
 *
 * @author Fabian Lee
 * 
 */

import com.documentum.com.*;
import com.documentum.fc.client.*;
import com.documentum.fc.common.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class FolderNavigate {

	public static void main(String args[]) {
		if (args.length < 4) {
			System.out.println("Usage: DOCBASE USER PASS FOLDERPATH");
			System.exit(1);
		}
		FolderNavigate nav = new FolderNavigate(args[0], args[1], args[2], args[3]);
	}

	public FolderNavigate(String docbaseName, String userName, String password,     String folderPath) {
		IDfSessionManager sMgr = null;
		IDfSession session = null;
		try {
			// establish session
			sMgr = createSessionManager(docbaseName, userName, password);
			session = sMgr.getSession(docbaseName);
			// call function to generate XML content
			StringBuffer xmlsb = new StringBuffer();
			navigate(xmlsb, folderPath, "", session);
			String2File(xmlsb.toString(),"");
		} catch (DfException exc) {
			exc.printStackTrace();
		} finally {
			if (sMgr != null && session != null)
				sMgr.release(session);
		}
	} // constructor

	/**
	 * Recursive function for generating map of folder structure and documents
	 * 
	 * @param rootPath absolute path to repository cabinet or folder (i.e. "/Temp/myfolder")
	 * @param indent string containing indentation tabs
	 * @param session repository session
	 * 
	 * @throws DfException
	 */
	public void navigate(
			StringBuffer xmlsb,
			String rootPath,
			String indent,
			IDfSession session
		)
		throws DfException {
		// Folder representation of absolute path
		IDfFolder root = session.getFolderByPath(rootPath);
		// These rows are being stored first because
		// if you make your recursive call within the 
		// loop, you will quickly run out of collections
		List rows = new ArrayList();
		IDfCollection coll = null;
		try {
			// get all objects in folder
			coll = root.getContents("");
			while (coll.next()) {
				rows.add(coll.getTypedObject());
			}
		} finally {
			if (coll != null)
				coll.close();
		}
		// write beginning XML element
		String relementName = resolveElementName(
				root.getString("r_object_id"),
				root.getString("r_object_type")
		);
		xmlsb.append(     
				createStartElement(
						indent,
						relementName,
						root.getString("r_object_id"),
						root.getString("object_name")
				)
		);
		// iterate through results of folder
		for (int i = 0; i < rows.size(); i++) {
			IDfTypedObject row = (IDfTypedObject) rows.get(i);
			String id = row.getString("r_object_id");
			// if cabinet or folder, recursively navigate contents
			if (id.startsWith("0c") || id.startsWith("0b")) {                    
				navigate(xmlsb, rootPath + "/" + row.getString("object_name"), indent + "\t", session);
			}else {
				// resolve element name
				String elementName = resolveElementName(id,row.getString("r_object_type"));
				// write object XML element
				xmlsb.append(     
						createObjectElement(
								indent + "\t",
								elementName,
								row.getString("r_object_id"),
								row.getString("object_name")
						)
				);
				Log("AC:" + row.getAttrCount());
				for (int z=0;z<row.getAttrCount();z++) {
					Log("A:"+row.getAttr(z).getName());
					Log("V:"+row.getValue(row.getAttr(z).getName()));
				}
				
			}

		} // results list

		// write matching end XML element
		xmlsb.append(
				createEndElement(
						indent,
						relementName
				)
		);

	} // navigate

	// resolves a DCTM type to a name
	public String resolveElementName(String id,String type) {
		// resolve element name
		String elementName = "";
		if (id.startsWith("0c")) {
			elementName = "cabinet";
		}else if(id.startsWith("0b")) {
			elementName = "folder";
		}else if(id.startsWith("09")) {
			elementName = "document";
		}else {
			elementName = type;
		}
		return elementName;
	}

	// starting XML element
	public String createStartElement(String indent,String elementName,String id,String name) {
		StringBuffer sbuf = new StringBuffer(indent);
		sbuf.append("<").append(elementName).append(" id=\"").append(id).append("\" ");
		sbuf.append("name=\"").append(name).append("\">");
		return sbuf.toString();
	}

	// closing XML element
	public String createEndElement(String indent,String elementName) {
		StringBuffer sbuf = new StringBuffer(indent);
		sbuf.append("</").append(elementName).append(">");
		return sbuf.toString();
	}

	// Self-contained object XML element
	public String createObjectElement(String indent,String elementName,String id,String name) {
		StringBuffer sbuf = new StringBuffer(indent);
		sbuf.append("<").append(elementName).append(" id=\"").append(id).append("\" ");
		sbuf.append("name=\"").append(name).append("\" />");
		return sbuf.toString();
	}
	
	public void String2File(String str, String path) {
		try {
			FileOutputStream fos = new FileOutputStream(new File("C:/Users/dmadmin/Desktop/FolderNavigate.xml"));
			fos.write(str.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Log(String str) {
		System.out.println(str);
	}
	
	// boiler plate session manager code
	public IDfSessionManager createSessionManager(
			String docbase,
			String user,
			String pass
		) throws DfException {
		//create Client objects
		IDfClientX clientx = new DfClientX();
		IDfClient client = clientx.getLocalClient();
		//create a Session Manager object
		IDfSessionManager sMgr = client.newSessionManager();
		//create an IDfLoginInfo object named loginInfoObj
		IDfLoginInfo loginInfoObj = clientx.getLoginInfo();
		loginInfoObj.setUser(user);
		loginInfoObj.setPassword(pass);
		loginInfoObj.setDomain(null);
		//bind the Session Manager to the login info
		sMgr.setIdentity(docbase, loginInfoObj);
		return sMgr;
	} // createSessionManager

} // FolderNavigate