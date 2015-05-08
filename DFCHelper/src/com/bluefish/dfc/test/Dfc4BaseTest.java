package com.bluefish.dfc.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfLoginInfo;

/**
 * Migrates ACL Template Instances by resolving the parent ACL Template
 * and Alias Set of the ACL instance and setting it on the target system object for
 * the ACL Template Instance.
 * 
 * For a complete explanation, see {@link #migrateAclTemplateInstances()}.
 */
public abstract class Dfc4BaseTest extends TestCase {

    private static final String DOCBASE = "bhp_int_a";
    private static final String USERNAME = "dmadmin";
    private static final String PASSWORD = "imndlf8r";

    private IDfClient client = null;
    private IDfSession session = null;
    
    protected void setUp() throws Exception {
        IDfLoginInfo login = new DfLoginInfo();
        login.setUser(USERNAME);
        login.setPassword(PASSWORD);
        login.setDomain(null);

        client = DfClient.getLocalClient();
        session = client.newSession(DOCBASE, login);
    }

    protected void tearDown() throws Exception {
        if (session != null) {
            session.disconnect();
        }
    }
    
    protected IDfSession getSession() throws DfException {
        if (session == null) {
            throw new IllegalStateException("session has not been connected");
        }
        return session;
    }
    
    protected String resolveUsername(IDfSession session, String username) throws DfException {
        if (username.equals(session.getDocbaseOwnerName())) {
            return "dm_dbo";
        }
        return username;
    }

    protected IDfPersistentObject retrieveObject(IDfSession session, String type, String whereClause) throws Exception {
        ArrayList obj = retrieveObjectIds(session, type, whereClause);
        if (obj.isEmpty()){
            throw new IllegalStateException("no objects found");
        } else if (obj.size() > 1) {
            throw new IllegalStateException("more than one object found");
        }
        return (IDfPersistentObject) session.getObjectWithType((IDfId)obj.get(0), type, null);
    }
        
    protected ArrayList retrieveObjectIds(IDfSession session, String type, String whereClause) throws Exception {
        ArrayList ids = new ArrayList();
        
        String dql = "SELECT r_object_id "
            + " FROM " + type
            + (whereClause != null ? " WHERE " + whereClause : "");

        IDfQuery query = new DfQuery();
        query.setDQL(dql);
        IDfCollection collection = query.execute(session, IDfQuery.DF_READ_QUERY);
        try {
            while (collection.next()) {
                ids.add(collection.getId("r_object_id"));
            }
        } finally {
            if (collection != null) {
                collection.close();
            }
        }
        
        return ids;
    }
    
}
