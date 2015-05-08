/*     */ package com.dsc.xprs.webservice.documentumsupport;
/*     */ 
/*     */ import com.docscience.xpression.PublishingService;
/*     */ import com.docscience.xpression.ServiceFactory;
/*     */ import com.docscience.xprs.exception.UnCheckedException;
/*     */ import com.docscience.xprs.log.LogManager;
/*     */ import com.docscience.xprs.log.Logger;
/*     */ import com.dsc.xprs.documentumsupport.documentservice.AuthoringTool;
/*     */ import com.dsc.xprs.documentumsupport.documentservice.Document;
/*     */ import com.dsc.xprs.documentumsupport.documentservice.DocumentManager;
/*     */ import com.dsc.xprs.documentumsupport.documentservice.IDocumentManager;
/*     */ import com.dsc.xprs.webservice.UCDocumentInfo;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Locale;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import javax.xml.soap.SOAPException;
/*     */ //import sun.reflect.generics.reflectiveObjects.NotImplementedException;
/*     */ import org.apache.commons.lang.NotImplementedException;

/*     */ public class DocumentumDocumentService
/*     */ {
/*  29 */   private Logger log = LogManager.getLogger("webservice");
/*     */ 
/*     */   public boolean cancelCheckOut(String requestContext, String path, String version)
/*     */     throws SOAPException
/*     */   {
/*  40 */     this.log.debug("Start DocumentService.cancelCheckOut method");
/*     */ 
/*  42 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/*  44 */     if (!result.equals("5000"))
/*  45 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/*  48 */       IDocumentManager dtm = new DocumentManager();
/*  49 */       dtm.cancelCheckout(path, version);
/*  50 */       return true;
/*     */     } catch (UnCheckedException e) {
/*  52 */       throw new SOAPException("5011", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String checkIn(String requestContext, byte[] doc, String path, String oldVersion, String versionRule)
/*     */     throws SOAPException
/*     */   {
/*  65 */     this.log.debug("Start DocumentService.checkIn method");
/*     */ 
/*  68 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/*  70 */     if (!result.equals("5000"))
/*  71 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/*  74 */       IDocumentManager dtm = new DocumentManager();
/*  75 */       Document dt = buildDocument(DocumentumServiceHelper.getAuthoringTool(requestContext), doc, path, oldVersion);
/*     */ 
/*  78 */       return dtm.checkinDocument(dt, Integer.parseInt(versionRule));
/*     */     } catch (UnCheckedException e) {
/*  80 */       throw new SOAPException("5012", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] checkOutAndDownload(String requestContext, String path, String version)
/*     */     throws SOAPException
/*     */   {
/*  93 */     this.log.debug("Start DocumentService.checkOutAndDownload method");
/*     */ 
/*  95 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/*  97 */     if (!result.equals("5000"))
/*  98 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/* 101 */       IDocumentManager dtm = new DocumentManager();
/* 102 */       Document dt = buildDocument(DocumentumServiceHelper.getAuthoringTool(requestContext), null, path, version);
/*     */ 
/* 105 */       dtm.checkoutDocument(dt);
/* 106 */       return dtm.fetchDocumentContent(path, version);
/*     */     } catch (UnCheckedException e) {
/* 108 */       throw new SOAPException("5013", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String checkOutBy(String requestContext, String path, String version)
/*     */     throws SOAPException
/*     */   {
/* 122 */     this.log.debug("Start DocumentService.checkOutBy method");
/*     */ 
/* 124 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 126 */     if (!result.equals("5000"))
/* 127 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/* 130 */       IDocumentManager dtm = new DocumentManager();
/* 131 */       return dtm.documentCheckedOutBy(path, version);
/*     */     } catch (UnCheckedException e) {
/* 133 */       throw new SOAPException("5014", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isDocExisted(String requestContext, String path)
/*     */     throws SOAPException
/*     */   {
/* 147 */     this.log.debug("Start DocumentService.isDocExisted method");
/* 148 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 150 */     if (!result.equals("5000")) {
/* 151 */       throw new SOAPException(result);
/*     */     }
/* 153 */     IDocumentManager dtm = new DocumentManager();
/* 154 */     return dtm.isDocumentExistent(path);
/*     */   }
/*     */ 
/*     */   public byte[] loadDocument(String requestContext, String path, String version)
/*     */     throws SOAPException
/*     */   {
/* 166 */     this.log.debug("Start DocumentService.loadDocument method");
/*     */ 
/* 168 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 170 */     if (!result.equals("5000"))
/* 171 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/* 174 */       IDocumentManager dtm = new DocumentManager();
/* 175 */       return dtm.fetchDocumentContent(path, version);
/*     */     } catch (UnCheckedException e) {
/* 177 */       throw new SOAPException("5015", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getThumbnail(String requestContext, String url) throws SOAPException
/*     */   {
/* 183 */     IDocumentManager dtm = new DocumentManager();
/* 184 */     return dtm.getThumbnail(url);
/*     */   }
/*     */ 
/*     */   public byte[] getMetadata(String requestContext, String path, String version) throws SOAPException
/*     */   {
/*     */     try {
/* 190 */       byte[] xDocData = loadDocument(requestContext, path, version);
/* 191 */       ZipInputStream xDoc = new ZipInputStream(new ByteArrayInputStream(xDocData));
/* 192 */       ZipEntry xDocItem = xDoc.getNextEntry();
/* 193 */       while (xDocItem != null) {
/* 194 */         String name = xDocItem.getName().toLowerCase(Locale.ENGLISH);
/* 195 */         if (name.endsWith("metadata.xml")) {
/* 196 */           ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 197 */           byte[] buf = new byte[2048];
/*     */           int count;
/* 199 */           while (-1 != (count = xDoc.read(buf)))
/* 200 */             out.write(buf, 0, count);
/* 201 */           return out.toByteArray();
/*     */         }
/* 203 */         xDocItem = xDoc.getNextEntry();
/*     */       }
/*     */     } catch (IOException ex) {
/* 206 */       throw new SOAPException("5004", ex);
/*     */     }
/* 208 */     return new byte[0];
/*     */   }
/*     */ 
/*     */   public String[] queryDocumentInfoById(String requestContext, String id) throws SOAPException
/*     */   {
/* 213 */     this.log.debug("Start DocumentService.queryDocumentInfoById method");
/*     */ 
/* 215 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 217 */     if (!result.equals("5000"))
/* 218 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/* 221 */       IDocumentManager dtm = new DocumentManager();
/* 222 */       return dtm.queryDocumentInfoById(id);
/*     */     } catch (UnCheckedException e) {
/* 224 */       throw new SOAPException("5016", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Document buildDocument(String authoringTool, byte[] doc, String path, String ver)
/*     */   {
/* 231 */     Document dt = new Document();
/* 232 */     dt.setAuthoringTool(AuthoringTool.valueOf(authoringTool));
/* 233 */     if (doc != null) {
/* 234 */       dt.setContent(doc);
/*     */     }
/* 236 */     dt.setName(path);
/* 237 */     dt.setVersion(ver);
/* 238 */     return dt;
/*     */   }
/*     */ 
/*     */   public UCDocumentInfo[] getTheLatestVersionInfo(String requestContext, String[] document)
/*     */     throws SOAPException
/*     */   {
/* 244 */     this.log.debug("Start DocumentService.getTheLatestVersionInfo method");
/*     */ 
/* 246 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 248 */     if (!result.equals("5000"))
/* 249 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/* 252 */       UCDocumentInfo[] array = new UCDocumentInfo[document.length];
/* 253 */       IDocumentManager dtm = new DocumentManager();
/* 254 */       int i = 0;
/* 255 */       for (String name : document) {
/* 256 */         UCDocumentInfo info = new UCDocumentInfo();
/* 257 */         String[] infoStr = dtm.getTheLatestVersionInfo(name);
/* 258 */         info.setDocumentName(name);
/* 259 */         info.setLatestVersion(infoStr[0]);
/* 260 */         info.setLastModifiedTime(infoStr[1]);
/* 261 */         array[(i++)] = info;
/*     */       }
/* 263 */       return array;
/*     */     } catch (UnCheckedException e) {
/* 265 */       throw new SOAPException("5016", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String checkInOut(String requestContext, byte[] data, String document, String oldVersion, String versionRule)
/*     */     throws SOAPException
/*     */   {
/* 273 */     this.log.debug("Start DocumentService.checkIn method");
/*     */ 
/* 276 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 278 */     if (!result.equals("5000"))
/* 279 */       throw new SOAPException(result);
/*     */     try
/*     */     {
/* 282 */       IDocumentManager dtm = new DocumentManager();
/* 283 */       dtm.cancelCheckout(document, oldVersion);
/* 284 */       String type = DocumentumServiceHelper.getAuthoringTool(requestContext);
/*     */ 
/* 286 */       Document dt = buildDocument(type, data, document, oldVersion);
/* 287 */       String version = dtm.checkinDocument(dt, Integer.parseInt(versionRule));
/*     */ 
/* 289 */       Document dtnew = buildDocument(type, null, document, version);
/* 290 */       dtm.checkoutDocument(dtnew);
/* 291 */       return version;
/*     */     } catch (UnCheckedException e) {
/* 293 */       throw new SOAPException("5012", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getUCReferencing(String requestContext, String referenced) {
/* 298 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   public String getUCReferenced(String requestContext, String referencing) {
/* 302 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   public String[] versionsForDocument(String requestContext, String documentName)
/*     */   {
/* 307 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   public byte[] previewDocumentWithPath(String requestContext, String path, String version, byte[] customerData, String profile) throws SOAPException
/*     */   {
/* 312 */     byte[] packageData = loadDocument(requestContext, path, version);
/* 313 */     return previewDocumentWithData(requestContext, packageData, customerData, profile);
/*     */   }
/*     */ 
/*     */   public byte[] previewDocumentWithData(String requestContext, byte[] packageData, byte[] customerData, String profile) throws SOAPException
/*     */   {
/* 318 */     String result = DocumentumServiceHelper.authenticate(requestContext, "xpression_designer");
/*     */ 
/* 320 */     if (!result.equals("5000")) {
/* 321 */       throw new SOAPException(result);
/*     */     }
/*     */ 
/* 324 */     ServiceFactory factory = ServiceFactory.getInstance();
/* 325 */     PublishingService pubService = factory.getPublishingService();
/* 326 */     String customer = null;
/*     */     try {
/* 328 */       customer = new String(customerData, "utf-8");
/*     */     } catch (UnsupportedEncodingException e) {
/* 330 */       customer = new String(customerData);
/*     */     }
/*     */ 
/* 333 */     byte[] data = pubService.publishPackage(Utility.getAuthoringTool(requestContext), packageData, customer, profile);
/* 334 */     if (data == null)
/* 335 */       return new byte[0];
/* 336 */     return data;
/*     */   }
/*     */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.documentumsupport.DocumentumDocumentService
 * JD-Core Version:    0.6.2
 */