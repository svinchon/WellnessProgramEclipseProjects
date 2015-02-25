package com.xcp.diy.dfchelper;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfLoginInfo;

public class dfcHelperSession
{
	public static IDfSession Session = null;

	public dfcHelperSession(String username, String password, String repository)
	throws DfException
	{
		DfClientX clientx = new DfClientX();
		IDfClient client = clientx.getLocalClient();
		IDfSessionManager mySessMgr = client.newSessionManager();
		IDfLoginInfo logininfo = clientx.getLoginInfo();
		logininfo.setUser(username);
		logininfo.setPassword(password);
		mySessMgr.setIdentity(repository, logininfo);
		Session = mySessMgr.getSession(repository);
	}
}
