/*    */ package com.dsc.xprs.framework.webservice;
/*    */ 
/*    */ import com.docscience.xpression.AuthorizationService;
/*    */ import com.docscience.xpression.DocumentService;
/*    */ import com.docscience.xpression.SecurityService;
/*    */ import com.docscience.xpression.ServiceFactory;
/*    */ import com.docscience.xpression.authorization.Authorization;
/*    */ import com.docscience.xpression.authorization.Authorization.Privilege;
/*    */ import com.docscience.xpression.authorization.AuthorizationException;
/*    */ import com.docscience.xpression.category.Category;
/*    */ import com.docscience.xpression.document.AbstractDocument;
/*    */ import com.dsc.uniarch.util.LocalizedExceptionBuilder;
/*    */ import com.dsc.uniarch.util.Validator;
/*    */ import com.dsc.xprs.framework.securitycontext.RequestContext;
/*    */ import com.dsc.xprs.framework.util.AuthenticationUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultQuickDoc extends QuickDocFacade
/*    */ {
/*    */   public DefaultQuickDoc(RequestContext requestContext)
/*    */   {
/* 21 */     super(requestContext);
/*    */   }
/*    */ 
/*    */   public String[] categoriesForUser()
/*    */   {
/* 26 */     String privilegeAction = "Read";
/* 27 */     String applicationName = this.requestContext.getApplicationName();
/* 28 */     String[] principles = AuthenticationUtil.authenticate(this.requestContext);
/* 29 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*    */ 
/* 31 */     String[] categories = null;
/*    */     try {
/* 33 */       categories = ServiceFactory.getInstance().getAuthorizationService().getAuthorizedCategories(principles, privilegeAction, applicationName);
/*    */     }
/*    */     finally
/*    */     {
/* 39 */       this.securityService.removeSession(sessionId);
/*    */     }
/* 41 */     return categories;
/*    */   }
/* 44 */   public String[] documentsForCategory(String categoryName) { Validator.checkEmptyParameter("categoryName", categoryName);
/* 45 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*    */     String[] documents;
/*    */     try {
/* 49 */       checkReadRightAgainstCategory(categoryName);
/* 50 */       List documentList = this.documentService.getDocumentsForCategory(categoryName);
/*    */ 
/* 52 */       documents = new String[documentList.size()];
/* 53 */       for (int i = 0; i < documentList.size(); i++)
/* 54 */         documents[i] = ((AbstractDocument)documentList.get(i)).getName();
/*    */     }
/*    */     finally {
/* 57 */       this.securityService.removeSession(sessionId);
/*    */     }
/* 59 */     return documents; }
/*    */ 
/*    */   protected void checkReadRightAgainstCategory(String cateName)
/*    */   {
/* 63 */     String[] principles = this.requestContext.getPrinciples();
/* 64 */     String appName = this.requestContext.getApplicationName();
/* 65 */     Authorization authorization = new Authorization(principles, appName);
/* 66 */     authorization.setCategory(cateName);
/* 67 */     List privileges = new ArrayList();
/* 68 */     privileges.add(Authorization.Privilege.Read);
/* 69 */     authorization.setPrivileges(privileges);
/* 70 */     if (!ServiceFactory.getInstance().getAuthorizationService().checkPermission(authorization))
/*    */     {
/* 72 */       throw LocalizedExceptionBuilder.build(AuthorizationException.class, new String[] { principles[0], cateName });
/*    */     }
/*    */   }
/*    */ 
/*    */   protected void checkReadRightAgainstCategory(AbstractDocument doc)
/*    */   {
/* 78 */     Category category = doc.getCategory();
/* 79 */     String categoryName = null;
/* 80 */     if (category != null) {
/* 81 */       categoryName = category.getName();
/* 82 */       checkReadRightAgainstCategory(categoryName);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.framework.webservice.DefaultQuickDoc
 * JD-Core Version:    0.6.2
 */