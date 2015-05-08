/*    */ package com.dsc.xprs.webservice.documentumsupport;
/*    */ 
/*    */ import com.docscience.xpression.document.AbstractDocument;
/*    */ import com.docscience.xpression.document.DocumentURL;
/*    */ import com.dsc.uniarch.util.Validator;
/*    */ import com.dsc.xprs.documentumsupport.documentservice.DocumentManager;
/*    */ import com.dsc.xprs.documentumsupport.documentservice.IDocumentManager;
/*    */ import com.dsc.xprs.framework.securitycontext.RequestContext;
/*    */ import com.dsc.xprs.framework.webservice.QuickDocFacade;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DocumentumQuickDoc extends QuickDocFacade
/*    */ {
/*    */   public DocumentumQuickDoc(RequestContext requestContext)
/*    */   {
/* 16 */     super(requestContext);
/*    */   }
/*    */ 
/*    */   public String[] categoriesForUser()
/*    */   {
/* 21 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public String[] documentsForCategory(String categoryName)
/*    */   {
/* 27 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   protected void checkReadRightAgainstCategory(AbstractDocument doc)
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void checkReadRightAgainstCategory(String cateName)
/*    */   {
/*    */   }
/*    */ 
/*    */   public String[][] versionsForDocumentWithWorkflowStatus(String documentURL)
/*    */   {
/* 43 */     Validator.checkEmptyParameter("documentURL", documentURL);
/* 44 */     DocumentURL url = new DocumentURL(documentURL);
/* 45 */     String location = url.getLocation();
/* 46 */     String packageName = url.getPackageNameWithExtension();
/* 47 */     IDocumentManager dm = new DocumentManager();
/* 48 */     List versions = dm.versionsForDocument(location, packageName);
/* 49 */     String[][] result = new String[versions.size()][2];
/* 50 */     for (int i = 0; i < versions.size(); i++) {
/* 51 */       result[i][0] = ((String)versions.get(i));
/* 52 */       result[i][1] = null;
/*    */     }
/* 54 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.documentumsupport.DocumentumQuickDoc
 * JD-Core Version:    0.6.2
 */