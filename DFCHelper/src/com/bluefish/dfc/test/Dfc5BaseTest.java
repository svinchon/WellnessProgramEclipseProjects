package com.bluefish.dfc.test;

import junit.framework.TestCase;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;

public class Dfc5BaseTest extends TestCase {

    private static final String DOCBASE = "DOCBASE";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private IDfSessionManager sessionMgr = null;
    protected IDfSession session = null;

    protected void setUp() throws Exception {
    	super.setUp();
    
    	IDfClient client = DfClient.getLocalClient();
    	sessionMgr = client.newSessionManager();
    
    	// Setup login details.
    	IDfLoginInfo login = new DfLoginInfo();
    	login.setUser(USERNAME);
    	login.setPassword(PASSWORD);
    	login.setDomain(null);
    	sessionMgr.setIdentity(DOCBASE, login);
    
    	session = sessionMgr.newSession(DOCBASE);
    }

    protected void tearDown() throws Exception {
    	super.tearDown();
    	if (session != null) {
    		sessionMgr.release(session);
    	}
    }

    protected void log(String message) {
        System.out.println(message);
    }

}
