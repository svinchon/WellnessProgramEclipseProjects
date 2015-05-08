package com.xcp.diy.dfchelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.DfAuthenticationException;
import com.documentum.fc.client.DfIdentityException;
import com.documentum.fc.client.DfPrincipalException;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.DfServiceException;
import com.documentum.fc.client.DfSingleDocbaseModule;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfWorkflow;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.common.IDfId;

public class dfcHelper extends DfSingleDocbaseModule {

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public String Test(String FolderName) throws DfException {
		try {
			System.out.println("DFC Helper => ListContent("+FolderName+") : "+"BEGIN");
			// get session
			IDfSession mySession = null;
			if (dfcHelperSession.Session == null) {
				// getSession inherited from DfSingleDocbaseModule
				mySession = getSession();
			} else {
				mySession = dfcHelperSession.Session;
			}
			System.out.println("DFC Helper => Got session");
			// test
			String myStrId;
			myStrId = "090d8b668000f122";
			myStrId = "090d8b668000cdcf";
			String qualification = "dm_document where r_object_id = '" + myStrId + "'"; 
			IDfSysObject myObject =  (IDfSysObject)mySession.getObjectByQualification(qualification );
			IDfDocument myDoc = (IDfDocument)myObject;
			//IDfFolder myFol = myDoc.getPath(0);
			IDfSysObject myObj2 = (IDfSysObject)mySession.getObject(myDoc.getFolderId(0));
			if (myObj2.getTypeName().equals("dm_folder")) {
				IDfFolder myFol = (IDfFolder)mySession.getObject(myDoc.getFolderId(0));
				Log("Folder:"+myFol);
				for (int z=0;z<myFol.getAttrCount();z++) {
					//Log(""+myFol.getAttr(z).getName()+"="+myFol.getValue(myFol.getAttr(z).getName()));
				}
				//Log("Path:"+myFol.getValue("r_folder_path"));
				Log(
						((IDfFolder)mySession.getObject(((IDfDocument)mySession.getObjectByQualification("dm_document where r_object_id = '090d8b668000cdcf'")).getFolderId(0))).getValue("r_folder_path").toString()
						+"/"+
						((IDfDocument)mySession.getObjectByQualification("dm_document where r_object_id = '090d8b668000cdcf'")).getObjectName()
				);
			}
			//
			IDfCollection collection;
			// navigate folder structure
			collection = null;
			IDfDocument doc = null;
			IDfFolder fol = null;
			int count = 0;
			try {
				IDfFolder folder = mySession.getFolderByPath("/Temp");
				collection = folder.getContents("r_object_id");
				while (collection.next()) {
					count++;
					IDfId id = collection.getId("r_object_id");
					IDfSysObject object	= (IDfSysObject) mySession.getObject(id);
					if (object.getTypeName().equals("dm_folder")) {
						fol = (IDfFolder)object;
						//Log("Folder: " + fol.getObjectName());
					} else if (object.getTypeName().equals("dm_document")){
						doc = (IDfDocument)object;						
						//Log("Document: " + doc.getObjectName());
					}
				}
			} finally {
				if (collection != null) { collection.close(); }
			}
			// use dql
			Set<IDfId> ids = new HashSet();
			collection = null;
			String DQL = "SELECT r_object_id, r_object_type FROM dm_sysobject ENABLE (FETCH_ALL_RESULTS 10)";
			try {
				collection = new DfQuery(DQL).execute(mySession, 0);
				while (collection.next()) {
					IDfId id = collection.getId("r_object_id");
					IDfSysObject object	= (IDfSysObject) mySession.getObject(id);
					Log("DQL: "+object.getTypeName());
				}
			} finally {
				if ((collection != null) && (collection.getStateEx() != 2)) { collection.close(); }
			}
			for (IDfId workflowId : ids) {
				IDfWorkflow dfWorkflow = (IDfWorkflow)mySession.getObject(workflowId);
				WriteToLog(dfWorkflow.getObjectName());
				dfWorkflow.abort();
				dfWorkflow.destroy();
			}				
			System.out.println("DFC Helper => ListContent("+FolderName+"): "+"END");
		} catch (DfIdentityException e) {
			e.printStackTrace();
		} catch (DfAuthenticationException e) {
			e.printStackTrace();
		} catch (DfPrincipalException e) {
			e.printStackTrace();
		} catch (DfServiceException e) {
			e.printStackTrace();
		} catch (DfException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String GetFullPathFromId(String id) {
		String path = null;
		System.out.println("DFC Helper => GetFullPathFromId("+id+") : "+"BEGIN");
		try {
			IDfSession mySession = null;
			// getSession inherited from DfSingleDocbaseModule
			if (dfcHelperSession.Session == null) { mySession = getSession(); } else { mySession = dfcHelperSession.Session; }
			String qualification = "dm_document where r_object_id = '" + id + "'"; 
			IDfSysObject myDocObject =  (IDfSysObject)mySession.getObjectByQualification(qualification);
			IDfDocument myDoc = (IDfDocument)myDocObject;
			IDfSysObject myFolSysObject = (IDfSysObject)mySession.getObject(myDoc.getFolderId(0));
			if (myFolSysObject.getTypeName().equals("dm_folder")) {
				IDfFolder myFol = (IDfFolder)mySession.getObject(myDoc.getFolderId(0));
				path = myFol.getValue("r_folder_path") + "/" + myDoc.getObjectName();
				myFol = null;
			}
			myFolSysObject = null;
			myDoc = null;
			myDocObject = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DFC Helper => GetFullPathFromId("+id+") : "+"END");
		return path;
	}
	
	public String KillAllProcess(
			String strProcessName
			)
					throws DfException
	{
		try {
			System.out.println("DFC Helper => KillAllProcess("+strProcessName+") : "+"BEGIN");
			Boolean allVersions = true;
			IDfSession mySession = null;
			if (dfcHelperSession.Session == null) {
				mySession = getSession();
			} else {
				mySession = dfcHelperSession.Session;
			}
			System.out.println("DFC Helper => Got session");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Set<IDfId> ids = new HashSet();
			IDfCollection collection = null;
			try {
				StringBuffer dql = new StringBuffer("SELECT r_object_id FROM dm_workflow WHERE process_id IN (SELECT r_object_id FROM dm_process ");
				if (allVersions) { dql.append("(ALL) "); }
				dql.append("WHERE object_name='").append(strProcessName).append("') AND r_runtime_state=1");
				collection = new DfQuery(dql.toString()).execute(mySession, 0);
				while (collection.next()) {
					ids.add(collection.getId("r_object_id"));
				}
			} finally {
				if ((collection != null) && (collection.getStateEx() != 2)) { collection.close(); }
			}
			for (IDfId workflowId : ids) {
				IDfWorkflow dfWorkflow = (IDfWorkflow)mySession.getObject(workflowId);
				WriteToLog(dfWorkflow.getObjectName());
				dfWorkflow.abort();
				dfWorkflow.destroy();
			}
			System.out.println("DFC Helper => KillAllProcess ("+strProcessName+"): "+"END");
		} catch (DfIdentityException e) {
			e.printStackTrace();
		} catch (DfAuthenticationException e) {
			e.printStackTrace();
		} catch (DfPrincipalException e) {
			e.printStackTrace();
		} catch (DfServiceException e) {
			e.printStackTrace();
		} catch (DfException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String AddUserToGroup(
			String strRep,
			String strAdminUserName,
			String strAdminPassword,
			String strUser,
			String strGroup
			) 
					throws DfException
	{
		try {
			Log("AddUserToGroup: "+"BEGIN");
			IDfSession mySession = null;
			DfClientX clientx = new DfClientX();
			Log("AddUserToGroup: "+"got clientx");
			IDfClient client = clientx.getLocalClient();
			Log("AddUserToGroup: "+"got client");
			IDfSessionManager mySessMgr = client.newSessionManager();
			Log("AddUserToGroup: "+"got session mgr");
			IDfLoginInfo logininfo = clientx.getLoginInfo();
			logininfo.setUser(strAdminUserName);
			logininfo.setPassword(strAdminPassword);
			mySessMgr.setIdentity(strRep, logininfo);
			mySession = mySessMgr.getSession(strRep);
			Log("AddUserToGroup: "+"got session");
			mySession.getGroup(strGroup).addUser(strUser);
			mySession.getGroup(strGroup).save();
			mySessMgr.release(mySession);
			Log("AddUserToGroup: "+"END");
		} catch (DfIdentityException e) {
			e.printStackTrace();
		} catch (DfAuthenticationException e) {
			e.printStackTrace();
		} catch (DfPrincipalException e) {
			e.printStackTrace();
		} catch (DfServiceException e) {
			e.printStackTrace();
		} catch (DfException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	static void Log(String str) {
		System.out.println(
				new SimpleDateFormat(
						"HH:mm:ss:SSS ",
						Locale.US
						).format(new Date())
						+"DFCHelper => "
						+ str
				);
	}

	public static void WriteToLog(String str) {
		System.out.println(str);
	}
}
