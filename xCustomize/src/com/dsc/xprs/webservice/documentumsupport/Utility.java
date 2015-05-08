/*    */ package com.dsc.xprs.webservice.documentumsupport;
/*    */ 
/*    */ import com.docscience.xprs.log.LogManager;
/*    */ import com.docscience.xprs.log.Logger;
/*    */ import com.docscience.xprs.resource.ResourceManager;
/*    */ import com.dsc.xprs.documentumsupport.documentservice.AuthoringTool;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jdom.Element;
/*    */ import org.jdom.input.SAXBuilder;
/*    */ 
/*    */ public class Utility
/*    */ {
/* 18 */   private static Logger log = LogManager.getLogger("webservice");
/*    */   private static final String HYPHEN = "-";
/*    */ 
/*    */   public static List<String> parseRequestContext(String requestContext)
/*    */   {
/* 21 */     log.debug("start Utility.parseRequestContext method");
/* 22 */     SAXBuilder builder = new SAXBuilder();
/*    */     try
/*    */     {
/* 25 */       ArrayList requestList = new ArrayList();
/* 26 */       org.jdom.Document doc = builder.build(new ByteArrayInputStream(requestContext.getBytes()));
/*    */ 
/* 28 */       Element root = doc.getRootElement();
/* 29 */       Element cre = root.getChild("Credentials");
/* 30 */       String userLoginName = "";
/* 31 */       String domainName = "";
/* 32 */       String userId = cre.getChild("UserID").getText();
/* 33 */       String[] infoArray = userId.split("\\\\");
/* 34 */       if (infoArray.length == 2) {
/* 35 */         domainName = infoArray[0];
/* 36 */         userLoginName = infoArray[1];
/*    */       } else {
/* 38 */         userLoginName = userId;
/*    */       }
/* 40 */       requestList.add(userLoginName);
/* 41 */       requestList.add(cre.getChild("Password").getText());
/* 42 */       requestList.add(domainName);
/*    */ 
/* 44 */       return requestList;
/*    */     } catch (Exception e) {
/* 46 */       log.error("parse the requestContext " + requestContext + " error.", e);
/*    */ 
/* 48 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getAuthoringTool(String requestContext) {
/* 53 */     log.debug("start Utility.getAuthoringTool method");
/* 54 */     String aut = null;
/* 55 */     SAXBuilder builder = new SAXBuilder();
/*    */     try
/*    */     {
/* 58 */       org.jdom.Document doc = builder.build(new ByteArrayInputStream(requestContext.getBytes()));
/*    */ 
/* 60 */       Element root = doc.getRootElement();
/* 61 */       Element e = root.getChild("ApplicationName");
/* 62 */       if (e == null) {
/* 63 */         throw new RuntimeException("ApplicationName is null.");
/*    */       }
/* 65 */       aut = e.getText();
/*    */     } catch (Exception e) {
/* 67 */       log.error("Get authoring tool from requestContext (" + requestContext + ") error.", e);
/*    */ 
/* 69 */       throw new RuntimeException(e);
/*    */     }
/* 71 */     return aut;
/*    */   }
/*    */ 
/*    */   public static com.dsc.xprs.documentumsupport.documentservice.Document getDocument(String requestContext, byte[] doc, String path, String ver)
/*    */   {
/* 76 */     log.debug("start Utility.getDocument method");
/* 77 */     com.dsc.xprs.documentumsupport.documentservice.Document dt = new com.dsc.xprs.documentumsupport.documentservice.Document();
/* 78 */     dt.setAuthoringTool(AuthoringTool.valueOf(getAuthoringTool(requestContext)));
/*    */ 
/* 80 */     if (doc != null) {
/* 81 */       dt.setContent(doc);
/*    */     }
/* 83 */     dt.setName(path);
/* 84 */     dt.setVersion(ver);
/* 85 */     return dt;
/*    */   }
/*    */ 
/*    */   public static String getErrorMessage(String errorCode) {
/* 89 */     return errorCode + "-" + ResourceManager.getResource(errorCode);
/*    */   }
/*    */ 
/*    */   public static String getErrorMessage(String errorCode, String parameter) {
/* 93 */     return getErrorMessage(errorCode, new String[] { parameter });
/*    */   }
/*    */ 
/*    */   public static String getErrorMessage(String errorCode, String[] params) {
/* 97 */     return errorCode + "-" + ResourceManager.getResource(errorCode, params);
/*    */   }
/*    */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.documentumsupport.Utility
 * JD-Core Version:    0.6.2
 */