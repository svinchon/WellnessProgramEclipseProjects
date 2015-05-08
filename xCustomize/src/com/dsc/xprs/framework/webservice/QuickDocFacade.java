/*     */ package com.dsc.xprs.framework.webservice;
/*     */ 
/*     */ import com.docscience.xpression.AuditingService;
/*     */ import com.docscience.xpression.DocumentService;
/*     */ import com.docscience.xpression.PublishingService;
/*     */ import com.docscience.xpression.SecurityService;
/*     */ import com.docscience.xpression.ServiceFactory;
/*     */ import com.docscience.xpression.document.AbstractDocument;
/*     */ import com.docscience.xpression.document.AssemblyInputParameters;
/*     */ import com.docscience.xpression.document.AssemblyInputParameters.MergeMode;
/*     */ import com.docscience.xpression.document.AssemblyInputParameters.OptionalObjectsMode;
/*     */ import com.docscience.xpression.document.AssemblyInputParameters.TextPieceStatus;
/*     */ import com.docscience.xpression.document.DocumentURL;
/*     */ import com.docscience.xpression.outputprofile.MultiStream;
/*     */ import com.docscience.xpression.security.IllegalLicenseException;
/*     */ import com.dsc.diagnostics.DiagnosticsAdmin;
/*     */ import com.dsc.uniarch.util.ContentType;
/*     */ import com.dsc.uniarch.util.LicensedComponent;
/*     */ import com.dsc.uniarch.util.Validator;
/*     */ import com.dsc.xprs.framework.securitycontext.RequestContext;
/*     */ import com.dsc.xprs.framework.util.AuthenticationUtil;
/*     */ import com.dsc.xprs.webservice.documentumsupport.DocumentumQuickDoc;
/*     */ import com.dsc.xprs.webservice.documentumsupport.DocumentumServiceHelper;
/*     */ import com.dsc.xprs.webservice.documentumsupport.Utility;
/*     */ 
/*     */ public abstract class QuickDocFacade
/*     */ {
/*     */   protected ServiceFactory factory;
/*     */   protected SecurityService securityService;
/*     */   protected DocumentService documentService;
/*     */   protected RequestContext requestContext;
/*     */   public static final int COMPONENTID_WEBSERVICE = 6;
/*     */   public static final String CONFIGURATION_ENABLE_TRUSTED_USER = "enableTrustedUser";
/*     */ 
/*     */   protected QuickDocFacade(RequestContext requestContext)
/*     */   {
/*  31 */     this.requestContext = requestContext;
/*  32 */     this.factory = ServiceFactory.getInstance();
/*  33 */     this.securityService = this.factory.getSecurityService();
/*  34 */     this.documentService = this.factory.getDocumentService();
/*     */   }
/*     */ 
/*     */   public static QuickDocFacade getInstance(String requestContext, String documentName)
/*     */   {
/*  43 */     RequestContext context = RequestContext.valueOf(requestContext);
/*  44 */     AuthenticationUtil.authenticate(context);
/*  45 */     if (documentName != null) {
/*  46 */       DocumentURL documentURL = new DocumentURL(documentName);
/*  47 */       if (documentURL.isFromDocumentum()) {
/*  48 */         checkDELicense();
/*  49 */         dctmAuthenticate(context);
/*  50 */         return new DocumentumQuickDoc(context);
/*     */       }
/*     */     }
/*  53 */     return new DefaultQuickDoc(context);
/*     */   }
/*     */ 
/*     */   private static void dctmAuthenticate(RequestContext context) {
/*  57 */     if ((AuthenticationUtil.isTrustedUserEnabled()) && (context.isTrusted())) {
/*  58 */       return;
/*     */     }
/*  60 */     String result = DocumentumServiceHelper.authenticateAsDashboard(context);
/*     */ 
/*  62 */     if (!"5000".equals(result)) {
/*  63 */       if (result.equals("5002")) {
/*  64 */         throw new RuntimeException(Utility.getErrorMessage("DFC_AUTHENTICATE_FAILURE_NO_ROLE", new String[] { "", "xpression_dashboard" }));
/*     */       }
/*     */ 
/*  71 */       throw new RuntimeException(Utility.getErrorMessage("9002") + "(Documentum)");
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void checkDELicense()
/*     */   {
/*  77 */     if (!DocumentumServiceHelper.licenseSupportsDE())
/*  78 */       throw new IllegalLicenseException("Documentum support is not licensed.");
/*     */   }
/*     */ 
/*     */   public abstract String[] categoriesForUser();
/*     */ 
/*     */   public abstract String[] documentsForCategory(String paramString);
/*     */ 
/*     */   public String descriptionForDocument(String documentName)
/*     */   {
/*  88 */     Validator.checkEmptyParameter("documentName", documentName);
/*  89 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/*  92 */       AbstractDocument document = this.documentService.getAbstractDocument(documentName);
/*     */ 
/*  94 */       checkReadRightAgainstCategory(document);
/*  95 */       return document.getDescription();
/*     */     } finally {
/*  97 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String designToolForDocument(String documentName) {
/* 102 */     Validator.checkEmptyParameter("documentName", documentName);
/* 103 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/* 106 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 108 */       checkReadRightAgainstCategory(doc);
/* 109 */       return doc.getAuthorizingTool();
/*     */     } finally {
/* 111 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] thumbnailForDocument(String documentName) {
/* 116 */     Validator.checkEmptyParameter("documentName", documentName);
/*     */ 
/* 118 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/* 121 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 123 */       checkReadRightAgainstCategory(doc);
/* 124 */       return this.documentService.getThumbnail(documentName);
/*     */     } finally {
/* 126 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[] outputProfilesForDocument(String documentName) {
/* 131 */     Validator.checkEmptyParameter("documentName", documentName);
/*     */ 
/* 133 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/* 136 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 138 */       checkReadRightAgainstCategory(doc);
/* 139 */       String[] names = doc.getAssociatedOPs();
/* 140 */       return names;
/*     */     } finally {
/* 142 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public MultiStream publishAndReturnDocument(String documentName, String customerData, String outputProfileName)
/*     */   {
/* 148 */     long sessionId = -1L;
/* 149 */     AuditingService auditServ = ServiceFactory.getInstance().getAuditingService();
/*     */ 
/* 151 */     auditServ.startCall(this.requestContext.getUserName(), LicensedComponent.QuickDocService);
/*     */     try
/*     */     {
/* 154 */       Validator.checkEmptyParameter("documentName", documentName);
/* 155 */       Validator.checkEmptyParameter("customerData", customerData);
/* 156 */       Validator.checkEmptyParameter("outputProfileName", outputProfileName);
/*     */ 
/* 159 */       setDiagnosticContext(this.requestContext);
/*     */ 
/* 161 */       sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */ 
/* 164 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 166 */       checkReadRightAgainstCategory(doc);
/*     */ 
/* 168 */       auditServ.document(documentName);
/* 169 */       auditServ.outputProfileName(outputProfileName);
/*     */ 
/* 171 */       AssemblyInputParameters inputPara = new AssemblyInputParameters();
/* 172 */       inputPara.setTextPieceStatus(AssemblyInputParameters.TextPieceStatus.ANY);
/*     */ 
/* 174 */       inputPara.setOptionalObjectsMode(AssemblyInputParameters.OptionalObjectsMode.OPTIONAL_MODE_BATCH);
/*     */ 
/* 176 */       inputPara.setMergeMode(AssemblyInputParameters.MergeMode.STYLED_ELEMENT_MERGE);
/*     */ 
/* 178 */       PublishingService pubService = this.factory.getPublishingService();
/* 179 */       MultiStream ms = pubService.publishDocument(this.requestContext.getApplicationName(), inputPara, documentName, customerData, outputProfileName, true);
/*     */ 
/* 182 */       return ms;
/*     */     }
/*     */     catch (RuntimeException e) {
/* 185 */       auditServ.error(e);
/* 186 */       throw e;
/*     */     } catch (Error e) {
/* 188 */       auditServ.error(e);
/* 189 */       throw e;
/*     */     } finally {
/* 191 */       auditServ.endCall();
/* 192 */       DiagnosticsAdmin.outputDiagnosticsReport();
/* 193 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String publishDocument(String documentName, String customerData, String outputProfileName)
/*     */   {
/* 199 */     PublishingService pubService = this.factory.getPublishingService();
/*     */ 
/* 201 */     long sessionId = -1L;
/* 202 */     AuditingService auditServ = ServiceFactory.getInstance().getAuditingService();
/*     */ 
/* 204 */     auditServ.startCall(this.requestContext.getUserName(), LicensedComponent.QuickDocService);
/*     */     try
/*     */     {
/* 207 */       Validator.checkEmptyParameter("documentName", documentName);
/* 208 */       Validator.checkEmptyParameter("customerData", customerData);
/* 209 */       Validator.checkEmptyParameter("outputProfileName", outputProfileName);
/*     */ 
/* 212 */       setDiagnosticContext(this.requestContext);
/*     */ 
/* 214 */       sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */ 
/* 216 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 218 */       checkReadRightAgainstCategory(doc);
/*     */ 
/* 220 */       auditServ.document(documentName);
/* 221 */       auditServ.outputProfileName(outputProfileName);
/*     */ 
/* 223 */       AssemblyInputParameters inputPara = new AssemblyInputParameters();
/* 224 */       inputPara.setTextPieceStatus(AssemblyInputParameters.TextPieceStatus.ANY);
/*     */ 
/* 226 */       inputPara.setOptionalObjectsMode(AssemblyInputParameters.OptionalObjectsMode.OPTIONAL_MODE_BATCH);
/*     */ 
/* 228 */       inputPara.setMergeMode(AssemblyInputParameters.MergeMode.STYLED_ELEMENT_MERGE);
/*     */ 
/* 230 */       pubService.publishDocument(this.requestContext.getApplicationName(), inputPara, documentName, customerData, outputProfileName, false);
/*     */ 
/* 233 */       return "Document '" + documentName + "' was successfully published to output profile '" + outputProfileName + "'.";
/*     */     }
/*     */     catch (RuntimeException e)
/*     */     {
/* 237 */       auditServ.error(e);
/* 238 */       throw e;
/*     */     } catch (Error e) {
/* 240 */       auditServ.error(e);
/* 241 */       throw e;
/*     */     } finally {
/* 243 */       auditServ.endCall();
/* 244 */       DiagnosticsAdmin.outputDiagnosticsReport();
/* 245 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String publishDocuments(String[] documentNames, String customerData, String outputProfileName)
/*     */   {
/* 251 */     long sessionId = -1L;
/* 252 */     AuditingService auditServ = ServiceFactory.getInstance().getAuditingService();
/*     */ 
/* 254 */     auditServ.startCall(this.requestContext.getUserName(), LicensedComponent.QuickDocService);
/*     */     try
/*     */     {
/* 257 */       setDiagnosticContext(this.requestContext);
/*     */ 
/* 259 */       Validator.isNullOrEmptyArray("documentNames", documentNames);
/* 260 */       Validator.checkEmptyParameter("customerData", customerData);
/* 261 */       Validator.checkEmptyParameter("outputProfileName", outputProfileName);
/*     */ 
/* 263 */       sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */ 
/* 265 */       StringBuffer docNamesBuf = new StringBuffer();
/* 266 */       for (String documentName : documentNames) {
/* 267 */         AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 269 */         checkReadRightAgainstCategory(doc);
/* 270 */         docNamesBuf.append("'").append(documentName).append("',");
/* 271 */         auditServ.document(documentName);
/*     */       }
/* 273 */       auditServ.outputProfileName(outputProfileName);
/* 274 */       PublishingService pubService = this.factory.getPublishingService();
/* 275 */       pubService.publishXPressoDocuments(this.requestContext.getApplicationName(), documentNames, customerData, outputProfileName);
/*     */ 
/* 279 */       return "Document '" + docNamesBuf.substring(0, docNamesBuf.length() - 1).toString() + "' was successfully published to output profile '" + outputProfileName + "'.";
/*     */     }
/*     */     catch (RuntimeException e)
/*     */     {
/* 286 */       auditServ.error(e);
/* 287 */       throw e;
/*     */     } catch (Error e) {
/* 289 */       auditServ.error(e);
/* 290 */       throw e;
/*     */     } finally {
/* 292 */       auditServ.endCall();
/* 293 */       DiagnosticsAdmin.outputDiagnosticsReport();
/* 294 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[][] versionsForDocumentWithWorkflowStatus(String documentName) {
/* 299 */     Validator.checkEmptyParameter("documentName", documentName);
/* 300 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/* 303 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 305 */       if (!doc.getType().isXPressoDocument()) {
/* 306 */         throw new UnsupportedOperationException("versionsForDocumentWithWorkflowStatus is not supported for xDesign document.");
/*     */       }
/*     */ 
/* 309 */       checkReadRightAgainstCategory(doc);
/* 310 */       String[][] versions = this.documentService.versionsForDocumentWithWorkflowStatus(documentName);
/*     */       int i;
/* 312 */       if (doc.getType() != ContentType.xWordDesigner) {
/* 313 */         for (i = 0; i < versions.length; i++) {
/* 314 */           versions[i][1] = null;
/*     */         }
/*     */       }
/* 317 */       return versions;
/*     */     } finally {
/* 319 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public String variableMetaDataForDocument(String documentName, String customerData) {
/* 325 */     Validator.checkEmptyParameter("documentName", documentName);
/* 326 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/* 329 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 331 */       checkReadRightAgainstCategory(doc);
/* 332 */       return this.documentService.variableMetaDataForDocument(documentName, customerData);
/*     */     } finally {
/* 334 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected abstract void checkReadRightAgainstCategory(AbstractDocument paramAbstractDocument);
/*     */ 
/*     */   protected abstract void checkReadRightAgainstCategory(String paramString);
/*     */ 
/*     */   private static void setDiagnosticContext(RequestContext requestContext) {
/* 343 */     if (requestContext == null) {
/* 344 */       return;
/*     */     }
/* 346 */     boolean doDiagnostics = requestContext.isDoDiagnostics();
/* 347 */     if (doDiagnostics)
/* 348 */       DiagnosticsAdmin.initDiagnosticContext();
/*     */   }
/*     */ 
/*     */   public String variablesForDocument(String documentName, String customerData)
/*     */   {
/* 353 */     Validator.checkEmptyParameter("documentName", documentName);
/* 354 */     long sessionId = this.securityService.createSession(6, this.requestContext.getUserName());
/*     */     try
/*     */     {
/* 357 */       AbstractDocument doc = this.documentService.getAbstractDocument(documentName);
/*     */ 
/* 359 */       checkReadRightAgainstCategory(doc);
/* 360 */       return this.documentService.variablesForDocument(documentName, customerData);
/*     */     } finally {
/* 362 */       this.securityService.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.framework.webservice.QuickDocFacade
 * JD-Core Version:    0.6.2
 */