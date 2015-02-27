package com.xcp.diy.dfchelper;

import java.util.HashSet;
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
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfWorkflow;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.common.IDfId;

public class dfcHelper extends DfSingleDocbaseModule {
	
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
				while (collection.next()) { ids.add(collection.getId("r_object_id")); }
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
			System.out.println("HealthProgramHelper => AddUserToGroup: "+"BEGIN");
			IDfSession mySession = null;
			DfClientX clientx = new DfClientX();
			System.out.println("HealthProgramHelper => AddUserToGroup: "+"got clientx");
			IDfClient client = clientx.getLocalClient();
			System.out.println("HealthProgramHelper => AddUserToGroup: "+"got client");
			IDfSessionManager mySessMgr = client.newSessionManager();
			System.out.println("HealthProgramHelper => AddUserToGroup: "+"got session mgr");
			IDfLoginInfo logininfo = clientx.getLoginInfo();
			logininfo.setUser(strAdminUserName);
			logininfo.setPassword(strAdminPassword);
			mySessMgr.setIdentity(strRep, logininfo);
			mySession = mySessMgr.getSession(strRep);
			System.out.println("HealthProgramHelper => AddUserToGroup: "+"got session");
			mySession.getGroup(strGroup).addUser(strUser);
			mySession.getGroup(strGroup).save();
			mySessMgr.release(mySession);
			System.out.println("HealthProgramHelper => AddUserToGroup: "+"END");
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
	
	public static void WriteToLog(String str) {
		System.out.println(str);
	}
}
