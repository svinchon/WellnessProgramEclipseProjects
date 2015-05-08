/*     */ package com.dsc.xprs.webservice;
/*     */ 
/*     */ import com.docscience.xpression.PublishingService;
/*     */ import com.docscience.xpression.ServiceFactory;
/*     */ import com.docscience.xpression.document.DocumentURL;
/*     */ import com.docscience.xpression.document.versioning.NumericVersion;
/*     */ import com.docscience.xpression.document.versioning.VersionPolicy;
/*     */ import com.docscience.xpression.document.versioning.VersioningHelper;
/*     */ import com.dsc.uniarch.cr.common.CREJBHelper;
/*     */ import com.dsc.uniarch.cr.common.UcdDetailInfoHandler;
/*     */ import com.dsc.uniarch.cr.ejb.CRAclSL;
/*     */ import com.dsc.uniarch.cr.ejb.CRCategorySL;
/*     */ import com.dsc.uniarch.cr.ejb.CRDocumentSL;
/*     */ import com.dsc.uniarch.cr.ejb.CRPackageSL;
/*     */ import com.dsc.uniarch.cr.error.CRACLException;
/*     */ import com.dsc.uniarch.cr.error.CREJBHelperException;
/*     */ import com.dsc.uniarch.cr.error.CRException;
/*     */ import com.dsc.uniarch.cr.error.CRPackageException;
/*     */ import com.dsc.uniarch.cr.service.CRQueryUtil;
/*     */ import com.dsc.uniarch.cr.vo.UcdDocItemVO;
/*     */ import com.dsc.uniarch.cr.vo.VersionedSubDocVO;
/*     */ import com.dsc.uniarch.exception.InvalidInputException;
/*     */ import com.dsc.uniarch.exception.SystemException;
/*     */ import com.dsc.uniarch.util.LogManager;
/*     */ import com.dsc.uniarch.util.Validator;
/*     */ import com.dsc.xprs.framework.securitycontext.RequestContext;
/*     */ import com.dsc.xprs.framework.util.AuthenticationUtil;
/*     */ import com.dsc.xprs.webservice.documentumsupport.DocumentumDocumentService;
/*     */ import com.dsc.xprs.webservice.documentumsupport.DocumentumServiceHelper;
/*     */ import com.dsc.xprs.webservice.documentumsupport.Utility;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.List;
/*     */ import javax.xml.soap.SOAPException;
/*     */ 
/*     */ public class XPressoDocumentOnlineService extends OnlineModeService
/*     */ {
/*     */   public static final String ACTION_WRITE = "Write";
/*     */   public static final String ACTION_READ = "Read";
/*     */   public static final String URL_PRE = "cr::xPression Repository:/";
/*     */   private static DocumentumDocumentService documentumDocumentservice;
/*     */   private boolean isDESupported;
/*     */ 
/*     */   public XPressoDocumentOnlineService()
/*     */   {
/*  52 */     if (DocumentumServiceHelper.licenseSupportsDE()) {
/*  53 */       documentumDocumentservice = new DocumentumDocumentService();
/*  54 */       this.isDESupported = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] checkOutAndDownload(String requestContext, String document, String version) throws SOAPException
/*     */   {
/*  60 */     long sessionId = -1L;
/*     */     try {
/*  62 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/*  64 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/*  66 */         return documentumDocumentservice.checkOutAndDownload(requestContext, document, version);
/*     */       }
/*     */ 
/*  69 */       sessionId = AuthenticationUtil.createSession(ctx);
/*     */ 
/*  71 */       String userName = ctx.getUserName();
/*  72 */       String[] names = CRQueryUtil.parsePath(document);
/*  73 */       String docName = names[1];
/*  74 */       String categoryName = names[0];
/*  75 */       CRPackageSL bean = getPackageBean();
/*  76 */       if (!hasWritePermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), categoryName))
/*     */       {
/*  78 */         throw operationNotPrivileged("5013");
/*     */       }
/*  80 */       byte[] data = null;
/*  81 */       long pkgId = bean.checkOut(docName, version, userName);
/*  82 */       data = bean.getPackageDataById(pkgId);
/*  83 */       return data;
/*     */     } catch (Throwable t) {
/*  85 */       LogManager.logError(t);
/*  86 */       throw makeSOAPException(t);
/*     */     } finally {
/*  88 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getThumbnail(String requestContext, String url) throws SOAPException
/*     */   {
/*  94 */     LogManager.logInfo(new StringBuilder().append("getThumnail->url:").append(url).toString());
/*     */     try {
/*  96 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/*  98 */         return documentumDocumentservice.getThumbnail(requestContext, url);
/*     */       }
/* 100 */       CRPackageSL bean = getPackageBean();
/* 101 */       DocumentURL du = new DocumentURL(url);
/* 102 */       String docName = du.getPackageNameWithExtension();
/* 103 */       String version = du.getVersion();
/* 104 */       return bean.getThumbnailByNameAndVersion(docName, version);
/*     */     } catch (Throwable t) {
/* 106 */       LogManager.logError(t);
/* 107 */       throw makeSOAPException(t);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] loadDocument(String requestContext, String path, String effectiveDate) throws SOAPException
/*     */   {
/* 113 */     long sessionId = -1L;
/*     */     try {
/* 115 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 117 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 119 */         return documentumDocumentservice.loadDocument(requestContext, path, effectiveDate);
/*     */       }
/*     */ 
/* 122 */       sessionId = AuthenticationUtil.createSession(ctx);
/*     */ 
/* 124 */       String[] names = CRQueryUtil.parsePath(path);
/* 125 */       String docName = names[1];
/* 126 */       String categoryName = names[0];
/* 127 */       if (!hasReadPermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), categoryName))
/*     */       {
/* 129 */         throw operationNotPrivileged("5015");
/*     */       }
/*     */ 
/* 132 */       byte[] data = null;
/*     */ 
/* 134 */       CRPackageSL bean = getPackageBean();
/* 135 */       data = bean.getPackageData(docName, effectiveDate);
/*     */ 
/* 137 */       return data;
/*     */     } catch (Throwable t) {
/* 139 */       LogManager.logError(t);
/* 140 */       throw makeSOAPException(t);
/*     */     } finally {
/* 142 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean cancelCheckOut(String requestContext, String path, String version) throws SOAPException
/*     */   {
/* 148 */     long sessionId = -1L;
/*     */     try {
/* 150 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 153 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 155 */         return documentumDocumentservice.cancelCheckOut(requestContext, path, version);
/*     */       }
/*     */ 
/* 158 */       sessionId = AuthenticationUtil.createSession(ctx);
/*     */ 
/* 160 */       String userName = ctx.getUserName();
/* 161 */       String[] names = CRQueryUtil.parsePath(path);
/* 162 */       String docName = names[1];
/* 163 */       if (!hasWritePermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), names[0]))
/*     */       {
/* 165 */         throw operationNotPrivileged("5011");
/*     */       }
/* 167 */       CRPackageSL bean = getPackageBean();
/* 168 */       bean.cancelCheckOut(docName, version, userName);
/* 169 */       return true;
/*     */     } catch (Throwable t) {
/* 171 */       LogManager.logError(t);
/* 172 */       throw makeSOAPException(t);
/*     */     } finally {
/* 174 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String checkIn(String requestContext, byte[] data, String path, String oldVersion, String checkInInfo)
/*     */     throws SOAPException
/*     */   {
/* 193 */     long sessionId = -1L;
/*     */     try {
/* 195 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 197 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 198 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 200 */         return documentumDocumentservice.checkIn(requestContext, data, path, oldVersion, checkInInfo);
/*     */       }
/*     */ 
/* 204 */       String[] names = CRQueryUtil.parsePath(path);
/* 205 */       String categoryName = names[0];
/* 206 */       String docName = null;
/* 207 */       String version = null;
/* 208 */       if ((names[1] != null) && (names[1].indexOf("?version=") > -1))
/*     */       {
/* 210 */         DocumentURL du = new DocumentURL(names[1]);
/* 211 */         docName = du.getPackageNameWithExtension();
/* 212 */         version = du.getVersion();
/*     */       } else {
/* 214 */         docName = names[1];
/* 215 */         version = "1.0";
/*     */       }
/* 217 */       String appName = ctx.getApplicationName();
/* 218 */       String userName = ctx.getUserName();
/* 219 */       if (!hasWritePermission(appName, AuthenticationUtil.authenticate(ctx), categoryName))
/*     */       {
/* 221 */         throw operationNotPrivileged("5012");
/*     */       }
/* 223 */       preCheckIn(docName, oldVersion, userName);
/* 224 */       CRPackageSL bean = getPackageBean();
/* 225 */       bean.checkIn(docName, categoryName, null, null, userName, data, version, checkInInfo);
/*     */ 
/* 227 */       return version;
/*     */     }
/*     */     catch (Throwable t) {
/* 230 */       LogManager.logError(t);
/* 231 */       throw makeSOAPException(t);
/*     */     } finally {
/* 233 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void preCheckIn(String docName, String oldVersion, String userName)
/*     */   {
/* 239 */     CRPackageSL bean = getPackageBean();
/* 240 */     if (NumericVersion.isSpecificVersion(oldVersion))
/* 241 */       if (userName.equals(bean.checkOutBy(docName, oldVersion)))
/* 242 */         bean.cancelCheckOut(docName, oldVersion, userName);
/*     */       else
/* 244 */         throw new CRPackageException(9705, new String[] { docName, oldVersion, userName });
/*     */   }
/*     */ 
/*     */   public String checkOutBy(String requestContext, String path, String effectiveDate)
/*     */     throws SOAPException
/*     */   {
/* 252 */     long sessionId = -1L;
/*     */     try {
/* 254 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 256 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 258 */         return documentumDocumentservice.checkOutBy(requestContext, path, effectiveDate);
/*     */       }
/*     */ 
/* 261 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 262 */       String[] names = CRQueryUtil.parsePath(path);
/* 263 */       String docName = names[1];
/* 264 */       String by = null;
/* 265 */       CRPackageSL bean = getPackageBean();
/* 266 */       by = bean.checkOutBy(docName, effectiveDate);
/* 267 */       return by;
/*     */     } catch (Throwable t) {
/* 269 */       LogManager.logError(t);
/* 270 */       throw makeSOAPException(t);
/*     */     } finally {
/* 272 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isDocExisted(String requestContext, String path) throws SOAPException
/*     */   {
/* 278 */     long sessionId = -1L;
/*     */     try {
/* 280 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 282 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 284 */         int index = path.lastIndexOf("?version=");
/* 285 */         if (index > -1) {
/* 286 */           path = path.substring(0, index);
/*     */         }
/* 288 */         return documentumDocumentservice.isDocExisted(requestContext, path);
/*     */       }
/*     */ 
/* 291 */       sessionId = AuthenticationUtil.createSession(ctx);
/*     */ 
/* 293 */       String[] names = CRQueryUtil.parsePath(path);
/* 294 */       String docName = names[1];
/* 295 */       boolean exists = false;
/* 296 */       int index = docName.lastIndexOf("?version=");
/* 297 */       if (index > -1) {
/* 298 */         docName = docName.substring(0, index);
/*     */       }
/*     */ 
/* 302 */       CRPackageSL pkgBean = getPackageBean();
/* 303 */       CRDocumentSL docBean = getDocumentBean();
/* 304 */       exists = (pkgBean.isPackageExist(docName)) || (docBean.isDocExistByName(docName));
/*     */ 
/* 306 */       return exists;
/*     */     } catch (Throwable t) {
/* 308 */       LogManager.logError(t);
/* 309 */       throw makeSOAPException(t);
/*     */     } finally {
/* 311 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[] queryDocumentInfoById(String requestContext, String id) throws SOAPException
/*     */   {
/* 317 */     long sessionId = -1L;
/*     */     try {
/* 319 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 321 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 322 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 324 */         return documentumDocumentservice.queryDocumentInfoById(requestContext, id);
/*     */       }
/*     */ 
/* 327 */       String appName = ctx.getApplicationName();
/* 328 */       String[] pack = getPackage(id);
/* 329 */       long cateId = Long.parseLong(pack[3]);
/* 330 */       CRCategorySL cateBean = getCategoryBean();
/* 331 */       String cateName = cateBean.getCategoryNameFromID(cateId);
/* 332 */       String docName = pack[1];
/* 333 */       String path = new StringBuilder().append("/").append(cateName).append("/").append(docName).toString();
/* 334 */       String effeDate = formatEffectiveDate(pack[5]);
/* 335 */       String checkOutBy = pack[7];
/* 336 */       String editable = pack[8];
/* 337 */       if ((editable.equals("1")) && (hasWritePermission(appName, AuthenticationUtil.authenticate(ctx), cateName)))
/*     */       {
/* 340 */         editable = "true";
/*     */       }
/* 342 */       else editable = "false";
/*     */ 
/* 344 */       String[] info = { path, effeDate, checkOutBy, editable };
/* 345 */       return info;
/*     */     }
/*     */     catch (Throwable t) {
/* 348 */       LogManager.logError(t);
/* 349 */       throw makeSOAPException(t);
/*     */     } finally {
/* 351 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean hasWritePermission(String appName, String[] principals, String cateName)
/*     */   {
/* 357 */     CRAclSL bean = getAclBean();
/*     */     try {
/* 359 */       long allowed = bean.isAllowed(appName, cateName, principals, null, "Write");
/*     */ 
/* 361 */       return allowed > 0L;
/*     */     } catch (CRACLException t) {
/* 363 */       throw new RuntimeException(t);
/*     */     } catch (InvalidInputException t) {
/* 365 */       throw new RuntimeException(t);
/*     */     } catch (SystemException t) {
/* 367 */       throw new RuntimeException(t);
/*     */     } catch (Exception t) {
/* 369 */       throw new RuntimeException(t);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean hasReadPermission(String appName, String[] principals, String cateName)
/*     */   {
/* 375 */     CRAclSL bean = getAclBean();
/*     */     try {
/* 377 */       long allowed = bean.isAllowed(appName, cateName, principals, null, "Read");
/*     */ 
/* 379 */       return allowed > 0L;
/*     */     } catch (CRACLException t) {
/* 381 */       throw new RuntimeException(t);
/*     */     } catch (InvalidInputException t) {
/* 383 */       throw new RuntimeException(t);
/*     */     } catch (SystemException t) {
/* 385 */       throw new RuntimeException(t);
/*     */     } catch (Exception t) {
/* 387 */       throw new RuntimeException(t);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String formatEffectiveDate(String effDate) {
/* 392 */     if (effDate.length() != 8) {
/* 393 */       throw new IllegalArgumentException(new StringBuilder().append("Invalid effective date:").append(effDate).toString());
/*     */     }
/*     */ 
/* 397 */     StringBuffer buffer = new StringBuffer();
/* 398 */     buffer.append(effDate.substring(0, 4)).append('-').append(effDate.substring(4, 6)).append('-').append(effDate.substring(6));
/*     */ 
/* 401 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   private static CRDocumentSL getDocumentBean() {
/*     */     try {
/* 406 */       return CREJBHelper.getCRDocumentSL();
/*     */     }
/*     */     catch (CREJBHelperException e) {
/* 409 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static CRPackageSL getPackageBean() {
/*     */     try {
/* 415 */       return CREJBHelper.getCRPackageSL();
/*     */     }
/*     */     catch (CREJBHelperException e) {
/* 418 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static CRAclSL getAclBean() {
/*     */     try {
/* 424 */       return CREJBHelper.getCRAclSL();
/*     */     }
/*     */     catch (CREJBHelperException e) {
/* 427 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static CRCategorySL getCategoryBean() {
/*     */     try {
/* 433 */       return CREJBHelper.getCRCategorySL();
/*     */     }
/*     */     catch (CREJBHelperException e) {
/* 436 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String[] getPackage(String packageId) {
/* 441 */     String condition = new StringBuilder().append("PACKAGE_ID=").append(packageId).toString();
/* 442 */     CRPackageSL packageBean = null;
/* 443 */     String[][] packages = (String[][])null;
/*     */     try {
/* 445 */       packageBean = getPackageBean();
/* 446 */       String[] columns = { "package_id", "name", "package_type", "category_id", "last_modified_time", "effective_date", "check_out", "check_out_by", "editable" };
/*     */ 
/* 449 */       packages = packageBean.getAllPackageInfos(columns, condition);
/* 450 */       if ((packages != null) && (packages.length > 0)) {
/* 451 */         return packages[0];
/*     */       }
/* 453 */       throw new IllegalArgumentException(new StringBuilder().append("Package with id ").append(packageId).append(" is not found").toString());
/*     */     }
/*     */     catch (CRException e) {
/* 456 */       throw new RuntimeException(e);
/*     */     } catch (InvalidInputException e) {
/* 458 */       throw new RuntimeException(e);
/*     */     } catch (SystemException e) {
/* 460 */       throw new RuntimeException(e);
/*     */     } catch (Exception e) {
/* 462 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public UCDocumentInfo[] getTheLatestVersionInfo(String requestContext, String[] document) throws SOAPException
/*     */   {
/* 468 */     long sessionId = -1L;
/*     */     try {
/* 470 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 472 */       sessionId = AuthenticationUtil.createSession(ctx);
/*     */ 
/* 474 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 476 */         return documentumDocumentservice.getTheLatestVersionInfo(requestContext, document);
/*     */       }
/*     */ 
/* 479 */       UCDocumentInfo[] result = new UCDocumentInfo[document.length];
/* 480 */       int i = 0;
/* 481 */       for (String name : document) {
/* 482 */         result[i] = new UCDocumentInfo();
/* 483 */         result[i].setDocumentName(name);
/* 484 */         DocumentURL documentURL = new DocumentURL(name);
/* 485 */         String docName = documentURL.getPackageNameWithExtension();
/* 486 */         String latest = VersioningHelper.findTheLatestVersion(docName);
/* 487 */         result[i].setLatestVersion(latest);
/* 488 */         String last = queryLastModifyTime(docName, latest);
/* 489 */         result[i].setLastModifiedTime(last);
/* 490 */         i++;
/*     */       }
/*     */ 
/* 493 */       return result;
/*     */     }
/*     */     catch (Throwable t) {
/* 496 */       LogManager.logError(t);
/* 497 */       throw makeSOAPException(t);
/*     */     } finally {
/* 499 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String queryLastModifyTime(String name, String latest)
/*     */   {
/* 505 */     String condition = new StringBuilder().append("NAME='").append(name).append("' AND NUMERIC_VERSION='").append(latest).append("'").toString();
/*     */ 
/* 507 */     CRPackageSL packageBean = null;
/* 508 */     String[][] packages = (String[][])null;
/*     */     try {
/* 510 */       packageBean = getPackageBean();
/* 511 */       String[] columns = { "last_modified_time" };
/* 512 */       packages = packageBean.getAllPackageInfos(columns, condition);
/* 513 */       if ((packages != null) && (packages.length > 0))
/* 514 */         return packages[0][0];
/*     */     }
/*     */     catch (CRException e) {
/* 517 */       throw new RuntimeException(e);
/*     */     } catch (InvalidInputException e) {
/* 519 */       throw new RuntimeException(e);
/*     */     } catch (SystemException e) {
/* 521 */       throw new RuntimeException(e);
/*     */     } catch (Exception e) {
/* 523 */       throw new RuntimeException(e);
/*     */     }
/* 525 */     return null;
/*     */   }
/*     */ 
/*     */   public String checkInOut(String requestContext, byte[] data, String document, String oldVersion, String comments)
/*     */     throws SOAPException
/*     */   {
/* 539 */     RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/* 540 */     if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */     {
/* 542 */       return documentumDocumentservice.checkInOut(requestContext, data, document, oldVersion, comments);
/*     */     }
/*     */ 
/* 546 */     String[] names = CRQueryUtil.parsePath(document);
/* 547 */     String categoryName = names[0];
/* 548 */     String docName = null;
/* 549 */     String version = null;
/* 550 */     if ((names[1] != null) && (names[1].indexOf("?version=") > -1))
/*     */     {
/* 552 */       DocumentURL du = new DocumentURL(document);
/* 553 */       docName = du.getPackageNameWithExtension();
/* 554 */       version = du.getVersion();
/*     */     } else {
/* 556 */       docName = names[1];
/* 557 */       version = "1.0";
/*     */     }
/*     */ 
/* 560 */     long sessionId = -1L;
/*     */     try {
/* 562 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 563 */       String appName = ctx.getApplicationName();
/* 564 */       String userName = ctx.getUserName();
/* 565 */       CRPackageSL bean = getPackageBean();
/* 566 */       if (hasWritePermission(appName, AuthenticationUtil.authenticate(ctx), categoryName))
/*     */       {
/* 568 */         preCheckIn(docName, oldVersion, userName);
/* 569 */         bean.checkIn(docName, categoryName, null, null, userName, data, version, comments);
/*     */ 
/* 571 */         bean.checkOut(docName, version, userName);
/*     */       } else {
/* 573 */         throw operationNotPrivileged("5012");
/*     */       }
/*     */     } catch (Throwable t) {
/* 576 */       LogManager.logError(t);
/* 577 */       throw makeSOAPException(t);
/*     */     } finally {
/* 579 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */ 
/* 582 */     return version;
/*     */   }
/*     */ 
/*     */   public String createMajorVersionBranch(String requestContext, String referenceTag, String branchVersion, String comments)
/*     */     throws SOAPException
/*     */   {
/* 602 */     long sessionId = -1L;
/*     */     try {
/* 604 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 606 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 607 */       String[] names = CRQueryUtil.parsePath(referenceTag);
/* 608 */       String docName = names[1];
/* 609 */       String categoryName = names[0];
/* 610 */       if (!hasWritePermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), categoryName))
/*     */       {
/* 612 */         throw operationNotPrivileged("5013");
/*     */       }
/* 614 */       CRPackageSL bean = getPackageBean();
/* 615 */       byte[] data = bean.getPackageData(docName, branchVersion);
/* 616 */       VersionPolicy vp = VersionPolicy.newInstance(docName, branchVersion);
/*     */ 
/* 618 */       String targetVersion = vp.getNextMajor();
/* 619 */       bean.checkIn(docName, categoryName, null, null, ctx.getUserName(), data, targetVersion, comments);
/*     */ 
/* 621 */       return targetVersion;
/*     */     } catch (Throwable t) {
/* 623 */       LogManager.logError(t);
/* 624 */       throw makeSOAPException(t);
/*     */     } finally {
/* 626 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean setEffectiveWithdrawDateOfTrunk(String requestContext, String referenceTag, String trunkVersion, String effectiveDate, String withdrawDate)
/*     */     throws SOAPException
/*     */   {
/* 634 */     long sessionId = -1L;
/*     */     try {
/* 636 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 638 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 639 */       String[] names = CRQueryUtil.parsePath(referenceTag);
/* 640 */       String docName = names[1];
/* 641 */       String categoryName = names[0];
/* 642 */       if (!hasWritePermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), categoryName))
/*     */       {
/* 644 */         throw operationNotPrivileged("5013");
/*     */       }
/* 646 */       long eDate = -1L;
/* 647 */       if ((effectiveDate != null) && (!effectiveDate.isEmpty())) {
/* 648 */         eDate = Long.parseLong(effectiveDate);
/*     */       }
/*     */       else {
/* 651 */         eDate = -1L;
/*     */       }
/* 653 */       long wDate = -1L;
/* 654 */       if ((withdrawDate != null) && (!withdrawDate.isEmpty())) {
/* 655 */         wDate = Long.parseLong(withdrawDate);
/*     */       }
/* 657 */       CRPackageSL bean = getPackageBean();
/* 658 */       bean.updateEffectiveWithdrawDateOfTrunk(docName, trunkVersion, eDate, wDate);
/*     */ 
/* 660 */       return true;
/*     */     } catch (Throwable t) {
/* 662 */       LogManager.logError(t);
/* 663 */       throw makeSOAPException(t);
/*     */     } finally {
/* 665 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean setEffectiveDateProcessing(String requestContext, String[] referenceTags, boolean[] values) throws SOAPException
/*     */   {
/* 671 */     long sessionId = -1L;
/*     */     try {
/* 673 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 675 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 676 */       String[] names = new String[referenceTags.length];
/* 677 */       int i = 0;
/* 678 */       for (String referenceTag : referenceTags) {
/* 679 */         String[] temp = CRQueryUtil.parsePath(referenceTag);
/* 680 */         String docName = temp[1];
/* 681 */         String categoryName = temp[0];
/* 682 */         if (!hasWritePermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), categoryName))
/*     */         {
/* 684 */           throw operationNotPrivileged("5013");
/*     */         }
/* 686 */         names[(i++)] = docName;
/*     */       }
/* 688 */       Integer[] inte = new Integer[values.length];
/* 689 */       int ii = 0;
/* 690 */       for (boolean v : values) {
/* 691 */         inte[(ii++)] = Integer.valueOf(v ? 1 : 0);
/*     */       }
/* 693 */       CRPackageSL bean = getPackageBean();
/* 694 */       bean.updateEffectiveDateProcessing(names, inte);
/* 695 */       return true;
/*     */     } catch (Throwable t) {
/* 697 */       LogManager.logError(t);
/* 698 */       throw makeSOAPException(t);
/*     */     } finally {
/* 700 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getUCReferencing(String requestContext, String referenced)
/*     */     throws SOAPException
/*     */   {
/* 719 */     long sessionId = -1L;
/* 720 */     StringBuilder sb = new StringBuilder();
/*     */     try
/*     */     {
/* 723 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 725 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 726 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 728 */         return documentumDocumentservice.getUCReferencing(requestContext, referenced);
/*     */       }
/*     */ 
/* 731 */       DocumentURL du = new DocumentURL(referenced);
/* 732 */       String catName = du.getLocation().substring(1);
/* 733 */       String docName = du.getPackageNameWithExtension();
/* 734 */       if (!hasReadPermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), catName))
/*     */       {
/* 736 */         throw operationNotPrivileged("5015");
/*     */       }
/* 738 */       sb.append("<SourcePackage>");
/* 739 */       sb.append("<SourceReference>");
/* 740 */       sb.append(referenced);
/* 741 */       sb.append("</SourceReference>");
/* 742 */       String version = du.getVersion();
/* 743 */       sb.append("<MasterDocuments>");
/* 744 */       sb.append(buildMasterRef(docName, version));
/* 745 */       sb.append("</MasterDocuments>");
/* 746 */       sb.append("</SourcePackage>");
/* 747 */       return sb.toString();
/*     */     } catch (Throwable t) {
/* 749 */       LogManager.logError(t);
/* 750 */       throw makeSOAPException(t);
/*     */     } finally {
/* 752 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String buildOneMasterRef(String ref, String type) {
/* 757 */     StringBuilder sb = new StringBuilder();
/* 758 */     sb.append("<Master>");
/* 759 */     sb.append("<MasterReference>");
/* 760 */     sb.append(ref);
/* 761 */     sb.append("</MasterReference>");
/* 762 */     sb.append(new StringBuilder().append("<Link type=\"").append(type).append("\"/>").toString());
/* 763 */     sb.append("</Master>");
/* 764 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private String buildMasterRef(String name, String version) {
/* 768 */     StringBuilder sb = new StringBuilder();
/* 769 */     UcdDetailInfoHandler handler = UcdDetailInfoHandler.getInstance();
/* 770 */     List<UcdDocItemVO> list = handler.getPkgNumbericVersionedUcdMasterDocInfo(name, version);
/*     */ 
/* 772 */     CRPackageSL bean = getPackageBean();
/* 773 */     for (UcdDocItemVO vo : list)
/* 774 */       if (vo.isPkg())
/*     */       {
/* 777 */         StringBuilder masterURL = new StringBuilder();
/* 778 */         masterURL.append("cr::xPression Repository:/");
/* 779 */         String category = bean.getPackageCategoryNameByName(vo.getDocName());
/*     */ 
/* 781 */         masterURL.append(category);
/* 782 */         masterURL.append("/");
/* 783 */         masterURL.append(vo.getDocName());
/* 784 */         masterURL.append("?version=");
/* 785 */         masterURL.append(vo.getNumericVersion());
/* 786 */         String referenceType = vo.isReferencedAsLastest() ? "latest" : "version";
/* 787 */         sb.append(buildOneMasterRef(masterURL.toString(), referenceType));
/*     */       }
/* 789 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String getUCReferenced(String requestContext, String referencing)
/*     */     throws SOAPException
/*     */   {
/* 807 */     long sessionId = -1L;
/* 808 */     StringBuilder sb = new StringBuilder();
/*     */     try {
/* 810 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 812 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 813 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 815 */         return documentumDocumentservice.getUCReferenced(requestContext, referencing);
/*     */       }
/*     */ 
/* 818 */       DocumentURL du = new DocumentURL(referencing);
/* 819 */       String catName = du.getLocation().substring(1);
/* 820 */       String docName = du.getPackageNameWithExtension();
/* 821 */       if (!hasReadPermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), catName))
/*     */       {
/* 823 */         throw operationNotPrivileged("5015");
/*     */       }
/* 825 */       sb.append("<SourcePackage>");
/* 826 */       sb.append("<SourceReference>");
/* 827 */       sb.append(referencing);
/* 828 */       sb.append("</SourceReference>");
/* 829 */       sb.append("<ChildDocuments>");
/* 830 */       String version = du.getVersion();
/* 831 */       if ("LATEST".equalsIgnoreCase(version)) {
/* 832 */         version = VersioningHelper.findTheLatestVersion(docName);
/*     */       }
/* 834 */       CRPackageSL bean = getPackageBean();
/* 835 */       List<VersionedSubDocVO> list = bean.getSubDocList(docName, version);
/* 836 */       for (VersionedSubDocVO vo : list) {
/* 837 */         sb.append(buildChildRef(vo));
/*     */       }
/* 839 */       sb.append("</ChildDocuments>");
/* 840 */       sb.append("</SourcePackage>");
/* 841 */       return sb.toString();
/*     */     } catch (Throwable t) {
/* 843 */       LogManager.logError(t);
/* 844 */       throw makeSOAPException(t);
/*     */     } finally {
/* 846 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String buildChildRef(VersionedSubDocVO vo) {
/* 851 */     StringBuilder sb = new StringBuilder();
/* 852 */     sb.append("<Child>");
/* 853 */     sb.append("<ChildReference>");
/* 854 */     String url = vo.getUsedAsUCURL();
/* 855 */     sb.append(url);
/* 856 */     DocumentURL du = new DocumentURL(url);
/* 857 */     String version = du.getVersion();
/* 858 */     sb.append("</ChildReference>");
/* 859 */     String type = "LATEST".equalsIgnoreCase(version) ? "latest" : "version";
/*     */ 
/* 861 */     sb.append(new StringBuilder().append("<Link type=\"").append(type).append("\">").toString());
/* 862 */     sb.append(version);
/* 863 */     sb.append("</Link>");
/* 864 */     sb.append("</Child>");
/* 865 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String[] versionsForDocument(String requestContext, String documentName) throws SOAPException
/*     */   {
/* 870 */     Validator.checkEmptyParameter("documentName", documentName);
/* 871 */     long sessionId = -1L;
/*     */     try {
/* 873 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 875 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 876 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 878 */         return documentumDocumentservice.versionsForDocument(requestContext, documentName);
/*     */       }
/*     */ 
/* 881 */       CRPackageSL pkgBean = CREJBHelper.getCRPackageSL();
/* 882 */       String[] names = CRQueryUtil.parsePath(documentName);
/* 883 */       String docName = names[1];
/* 884 */       if (!hasReadPermission(ctx.getApplicationName(), AuthenticationUtil.authenticate(ctx), names[0]))
/*     */       {
/* 886 */         throw operationNotPrivileged("5015");
/*     */       }
/* 888 */       String[] versions = pkgBean.getVersionsForDocument(docName, false);
/* 889 */       VersioningHelper.sortInReverseOrder(versions);
/* 890 */       return versions;
/*     */     } catch (Throwable t) {
/* 892 */       LogManager.logError(t);
/* 893 */       throw makeSOAPException(t);
/*     */     } finally {
/* 895 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] previewDocumentWithPath(String requestContext, String path, String effectiveDate, byte[] customerData, String profile) throws SOAPException
/*     */   {
/* 901 */     byte[] packageData = loadDocument(requestContext, path, effectiveDate);
/* 902 */     return previewDocumentWithData(requestContext, packageData, customerData, profile);
/*     */   }
/*     */ 
/*     */   public byte[] previewDocumentWithData(String requestContext, byte[] packageData, byte[] customerData, String profile) throws SOAPException
/*     */   {
/* 907 */     long sessionId = -1L;
/*     */     try {
/* 909 */       RequestContext ctx = AuthenticationUtil.authenticate(requestContext);
/*     */ 
/* 911 */       if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */       {
/* 913 */         return documentumDocumentservice.previewDocumentWithData(requestContext, packageData, customerData, profile);
/*     */       }
/*     */ 
/* 916 */       sessionId = AuthenticationUtil.createSession(ctx);
/* 917 */       ServiceFactory factory = ServiceFactory.getInstance();
/* 918 */       PublishingService pubService = factory.getPublishingService();
/* 919 */       String customer = null;
/*     */       try {
/* 921 */         customer = new String(customerData, "utf-8");
/*     */       } catch (UnsupportedEncodingException e) {
/* 923 */         customer = new String(customerData);
/*     */       }
/*     */ 
/* 927 */       byte[] data = pubService.publishPackage(Utility.getAuthoringTool(requestContext), packageData, customer, profile);
/*     */       byte[] arrayOfByte2;
/* 928 */       if (data == null)
/* 929 */         return new byte[0];
/* 930 */       return data;
/*     */     } catch (Throwable t) {
/* 932 */       LogManager.logError(t);
/* 933 */       throw makeSOAPException(t);
/*     */     } finally {
/* 935 */       AuthenticationUtil.removeSession(sessionId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getMetadata(String requestContext, String path, String version) throws SOAPException
/*     */   {
/* 941 */     if ((this.isDESupported) && (DocumentumServiceHelper.withDocumentum(requestContext)))
/*     */     {
/* 943 */       return documentumDocumentservice.getMetadata(requestContext, path, version);
/*     */     }
/* 945 */     return new byte[0];
/*     */   }
/*     */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.XPressoDocumentOnlineService
 * JD-Core Version:    0.6.2
 */