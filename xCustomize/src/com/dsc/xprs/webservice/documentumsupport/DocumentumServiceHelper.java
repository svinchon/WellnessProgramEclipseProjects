/*     */ package com.dsc.xprs.webservice.documentumsupport;
/*     */ 
/*     */ import com.docscience.xprs.exception.AuthenticationException;
/*     */ import com.docscience.xprs.log.LogManager;
/*     */ import com.docscience.xprs.log.Logger;
/*     */ import com.dsc.uniarch.cr.common.CREJBHelper;
/*     */ import com.dsc.uniarch.cr.ejb.LicenseControllerSL;
/*     */ import com.dsc.xprs.documentumsupport.sessionmanagement.DocumentumConfigException;
/*     */ import com.dsc.xprs.documentumsupport.sessionmanagement.SecurityService;
/*     */ import com.dsc.xprs.framework.securitycontext.RequestContext;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.input.SAXBuilder;
/*     */ 
/*     */ public class DocumentumServiceHelper
/*     */ {
/*     */   public static final int COMPONENTID_DE = 15;
/*     */   private static final String DFC_AUTHENTICATE_FAILURE_NO_ROLE = "DFC_AUTHENTICATE_FAILURE_NO_ROLE";
/*     */   private static final String DFC_DOCBROKER_REQUEST_FAILED = "DFC_DOCBROKER_REQUEST_FAILED";
/*  24 */   private static Logger log = LogManager.getLogger("webservice");
/*     */ 
/*     */   public static boolean licenseSupportsDE()
/*     */   {
/*     */     try {
/*  29 */       LicenseControllerSL bean = CREJBHelper.getLicenseControllerSL();
/*  30 */       return bean.checkComponentLicense(15) == 1;
/*     */     } catch (Exception e) {
/*  32 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isDEConfigured() {
/*     */     try {
/*  38 */       return SecurityService.getInstance().getRepository() != null; } catch (DocumentumConfigException e) {
/*     */     }
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   public static String authenticate(String request, String role)
/*     */   {
/*  45 */     log.debug("start Authentication.authenticate method");
/*     */     try {
/*  47 */       List requestList = parseRequestContext(request);
/*  48 */       if ("xpression_designer".equals(role)) {
/*  49 */         SecurityService.getInstance().loginAsDesigner((String)requestList.get(0), (String)requestList.get(1), (String)requestList.get(2));
/*     */       }
/*  52 */       else if ("xpression_dashboard".equals(role)) {
/*  53 */         SecurityService.getInstance().loginAsDashboard((String)requestList.get(0), (String)requestList.get(1), (String)requestList.get(2));
/*     */       }
/*  56 */       else if ("xpression_framework".equals(role)) {
/*  57 */         SecurityService.getInstance().loginAsFramework((String)requestList.get(0), (String)requestList.get(1), (String)requestList.get(2));
/*     */       }
/*     */ 
/*  61 */       return "5000";
/*     */     } catch (AuthenticationException e) {
/*  63 */       log.error("Authentication error", e);
/*  64 */       if ("DFC_AUTHENTICATE_FAILURE_NO_ROLE".equals(e.getErrorCode()))
/*  65 */         return "5002";
/*  66 */       if ("DFC_DOCBROKER_REQUEST_FAILED".equals(e.getErrorCode()))
/*  67 */         return "5010";
/*     */     }
/*  69 */     return "5001";
/*     */   }
/*     */ 
/*     */   private static List<String> parseRequestContext(String requestContext)
/*     */   {
/*  74 */     SAXBuilder builder = new SAXBuilder();
/*     */     try
/*     */     {
/*  77 */       ArrayList requestList = new ArrayList();
/*  78 */       Document doc = builder.build(new ByteArrayInputStream(requestContext.getBytes()));
/*     */ 
/*  80 */       Element root = doc.getRootElement();
/*  81 */       Element cre = root.getChild("Credentials");
/*  82 */       String userLoginName = "";
/*  83 */       String domainName = "";
/*  84 */       String userId = cre.getChild("UserID").getText();
/*  85 */       String[] infoArray = userId.split("\\\\");
/*  86 */       if (infoArray.length == 2) {
/*  87 */         domainName = infoArray[0];
/*  88 */         userLoginName = infoArray[1];
/*     */       } else {
/*  90 */         userLoginName = userId;
/*     */       }
/*  92 */       requestList.add(userLoginName);
/*  93 */       requestList.add(cre.getChild("Password").getText());
/*  94 */       requestList.add(domainName);
/*     */ 
/*  96 */       return requestList;
/*     */     } catch (Exception e) {
/*  98 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   static String getAuthoringTool(String requestContext)
/*     */   {
/* 104 */     String aut = null;
/* 105 */     SAXBuilder builder = new SAXBuilder();
/*     */     try
/*     */     {
/* 108 */       Document doc = builder.build(new ByteArrayInputStream(requestContext.getBytes()));
/*     */ 
/* 110 */       Element root = doc.getRootElement();
/* 111 */       Element e = root.getChild("ApplicationName");
/* 112 */       if (e == null) {
/* 113 */         throw new RuntimeException("ApplicationName is null.");
/*     */       }
/* 115 */       aut = e.getText();
/*     */     } catch (Exception e) {
/* 117 */       throw new RuntimeException(e);
/*     */     }
/* 119 */     return aut;
/*     */   }
/*     */ 
/*     */   public static boolean withDocumentum(String requestContext)
/*     */   {
/*     */     try {
/* 125 */       Document doc = new SAXBuilder().build(new ByteArrayInputStream(requestContext.getBytes()));
/*     */ 
/* 127 */       Element e = doc.getRootElement().getChild("ECMType");
/* 128 */       if (e == null)
/* 129 */         return false;
/* 130 */       return "DCTM".equals(e.getText());
/*     */     } catch (Exception e) {
/* 132 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String authenticateAsDashboard(RequestContext context)
/*     */   {
/* 138 */     log.debug("start Authentication.authenticateAsDashboard method");
/*     */     try {
/* 140 */       SecurityService.getInstance().loginAsDashboard(context.getUserName(), context.getPassword(), null);
/*     */ 
/* 142 */       return "5000";
/*     */     } catch (AuthenticationException e) {
/* 144 */       log.error("Authentication error", e);
/* 145 */       if ("DFC_AUTHENTICATE_FAILURE_NO_ROLE".equals(e.getErrorCode()))
/* 146 */         return "5002";
/* 147 */       if ("DFC_DOCBROKER_REQUEST_FAILED".equals(e.getErrorCode()))
/* 148 */         return "5010";
/*     */     }
/* 150 */     return "5001";
/*     */   }
/*     */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.documentumsupport.DocumentumServiceHelper
 * JD-Core Version:    0.6.2
 */