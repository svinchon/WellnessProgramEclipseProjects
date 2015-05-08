package com.bluefish.dfc.test;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.common.IDfId;

public class DfcCrudTest extends Dfc5BaseTest {

    private static String DIR_NAME = "Subdir";
    private static String DIR_PATH = "/Temp/" + DIR_NAME;
    private static String FILE_NAME = "Getting Started with DFC and DQL.txt"; 
    private static String FILE_PATH = DIR_PATH + "/" + FILE_NAME; 
    private static String DOC_AUTHOR = "Steve McMichael";
    
    private IDfFolder folder;
    private IDfDocument document;
    
    public void testSimpleDfc() throws Exception {
        
        initialize();
        
        // tests are order dependent
        
        createFolder();
        createFile();
        linkFileToFolder();
        modifyFile();
        fetchFolderContents();
        queryFiles();
        deleteFile();
        deleteFolder();
        
    }

    private void createFolder() throws Exception {
        log("** Testing folder creation");
        
        folder = (IDfFolder) session.newObject("dm_folder");
        folder.setObjectName(DIR_NAME);
        folder.link("/Temp");
        folder.save();
        
        log("created folder " + folder.getId("r_object_id"));
        assertEquals("unexpected folder path", DIR_PATH, folder.getFolderPath(0));
    }
    
    private void createFile() throws Exception {
        log("** Testing file creation");
        
        document = (IDfDocument) session.newObject("dm_document");
        document.setObjectName(FILE_NAME);
        document.setContentType("crtext");
        document.setFile("E:/clipboard.txt"); // add content to this dm_document
        document.save();
        
        log("created file " + document.getId("r_object_id"));
    }
    
    private void linkFileToFolder() throws Exception {
        log("** Testing file linking to folder");
        
        document.link(DIR_PATH);
        document.save();
        
        log(FILE_PATH);
        assertNotNull("unexpected folder path", session.getObjectByPath( FILE_PATH));
    }
    
    private void modifyFile() throws Exception {
        log("** Testing file modification");
        
        document.checkout();
        int numAuthors = document.getAuthorsCount();
        document.setAuthors(numAuthors, DOC_AUTHOR);
        //doc.checkin(false, "Prevents promotion to CURRENT");
        document.checkin(false, null); // When a null version label is provided,
                                    // DFC automatically gives the new version
                                    // an implicit version label (1.1, 1.2, etc.)
                                    // and the symbolic label "CURRENT". 
    }
    
    private void fetchFolderContents() throws Exception {
        log("** Testing folder contents");

        // (1) Fetch using IDfFolder object
        
        IDfFolder folder = session.getFolderByPath(DIR_PATH);
        assertNotNull("folder is null", folder);
        IDfCollection collection = null;
        IDfDocument doc = null;
        int count = 0;
        try {
            collection = folder.getContents("r_object_id");
            while (collection.next()) {
                count++;
                IDfId id = collection.getId("r_object_id");
                doc = (IDfDocument) session.getObject(id);
                log(id + ":  " + doc.getObjectName());
            }
        } finally {
            // ALWAYS! cleanup your collections
            if (collection != null) {
                collection.close();
            }
        }
        
        assertEquals("wrong number of files in folder", 1, count);
        assertEquals("unexpected doc name", FILE_NAME, doc.getObjectName());
        
        
        // (2) Fetch using DQL folder(..)
        
        String dql = "SELECT r_object_id, object_name from dm_document where folder('"+DIR_PATH+"');";
        
        // Or we can fetch the contents of our folder and all of its subfolders using
        //
        //    folder('/Temp/Subdir', descend)
        //
        // But since we haven't added any subfolders, this will return the same set of dm_documents.
        //
        // String dql = "SELECT r_object_id, object_name from dm_document where folder('"+DIR_PATH+"', descend);";

        IDfQuery query = new DfQuery();
        query.setDQL(dql);
        collection = null;
        String docName = null;
        count = 0;
        try {
            collection = query.execute(session, IDfQuery.DF_READ_QUERY);
            while (collection.next()) {
                count++;
                String id = collection.getString("r_object_id");
                docName = collection.getString("object_name");
                log(id + ":  " + docName);                
            }
        } finally {
            // ALWAYS! cleanup your collections
            if (collection != null) {
                collection.close();
            }
        }
        
        assertEquals("wrong number of files in folder", 1, count);
        assertEquals("unexpected doc name", FILE_NAME, docName);

    }
    
    private void queryFiles() throws Exception {
        log("** Testing file query");
        
        // (1) load by path
        
        IDfDocument doc = (IDfDocument) session.getObjectByPath(FILE_PATH);
        assertNotNull("null doc returned", doc);
        assertEquals("unexpected doc name", FILE_NAME, doc.getObjectName());

        
        // (2) load by query
        
        // NOTE: authors is a "repeating attribute" in Documentum terminology,
        // meaning it is multi-valued. So we need to use the ANY DQL keyword here.
        doc = null;
        String dql = "SELECT r_object_id"
                    + " FROM dm_document"
                    + " WHERE object_name = '" + FILE_NAME + "' AND ANY authors = '" + DOC_AUTHOR + "'";
        IDfQuery query = new DfQuery();
        query.setDQL(dql);
        IDfCollection collection = query.execute(session, IDfQuery.DF_READ_QUERY);
        try {
            assertTrue("query did not return any results", collection.next());
            doc = (IDfDocument) session.getObject(collection.getId("r_object_id"));
        } finally {
            // ALWAYS! cleanup your collections
            if (collection != null) {
                collection.close();
            }
        }
        
        assertNotNull("null doc returned", doc);
        assertEquals("unexpected doc name", FILE_NAME, doc.getObjectName());  
    }
    
    private void deleteFile() throws Exception {
        if (document != null) {
            log("** Testing file deletion");
            document.destroyAllVersions();
        }
    }
    
    private void deleteFolder() throws Exception {
        if (folder != null) {
            log("** Testing folder deletion");
            folder.destroyAllVersions();
        }
    }

    private void initialize() {
        // If something bad happened during the previous run, this will
        // make sure we're back in a good state for this test run.
        try {
            session.getObjectByPath(FILE_PATH).destroy();
        } catch (Exception e) {
            // ignore
        }
        try {
            session.getObjectByPath(DIR_PATH).destroy();
        } catch (Exception e) {
            // ignore
        }
    }
}
