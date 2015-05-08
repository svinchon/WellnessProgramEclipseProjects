/*     */ package com.dsc.xprs.framework.webservice;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.docscience.xpression.DocumentService;
import com.docscience.xpression.ServiceFactory;
import com.docscience.xpression.document.AbstractDocument;
import com.docscience.xpression.outputprofile.MultiStream;
/*     */ import com.docscience.xpression.outputprofile.PartitionData;
/*     */ import com.docscience.xpression.security.IllegalLicenseException;
/*     */ import com.dsc.uniarch.util.LogManager;
/*     */ import com.dsc.uniarch.util.XPressionContext;
/*     */ import com.dsc.uniarch.util.XPressionContexts;
/*     */ import com.dsc.xprs.framework.util.ExceptionHandler;
import com.dsc.xprs.webservice.XPressoDocumentOnlineService;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;
/*     */ 
/*     */ public class QuickDocService
/*     */ {
/*     */   public String[] categoriesForUser(String requestContext)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/*  43 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, null);
/*     */ 
/*  45 */       return quickDoc.categoriesForUser();
/*     */     } catch (Throwable er) {
/*  47 */       LogManager.logError(er);
/*  48 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[] documentsForCategory(String requestContext, String categoryName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/*  71 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, null);
/*     */ 
/*  74 */       return quickDoc.documentsForCategory(categoryName);
/*     */     }
/*     */     catch (Throwable er)
/*     */     {
/*  78 */       LogManager.logError(er);
/*  79 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String designToolForDocument(String requestContext, String documentName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 106 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 108 */       return quickDoc.designToolForDocument(documentName);
/*     */     } catch (Throwable er) {
/* 110 */       LogManager.logError(er);
/*     */ 
/* 115 */       if ((er instanceof IllegalLicenseException)) {
/* 116 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 118 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[] outputProfilesForDocument(String requestContext, String documentName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 149 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 151 */       return quickDoc.outputProfilesForDocument(documentName);
/*     */     } catch (Throwable er) {
/* 153 */       LogManager.logError(er);
/* 154 */       if ((er instanceof IllegalLicenseException)) {
/* 155 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 157 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String descriptionForDocument(String requestContext, String documentName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 182 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 184 */       return quickDoc.descriptionForDocument(documentName);
/*     */     } catch (Throwable er) {
/* 186 */       LogManager.logError(er);
/* 187 */       if ((er instanceof IllegalLicenseException)) {
/* 188 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 190 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] thumbnailForDocument(String requestContext, String documentName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 216 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 218 */       return quickDoc.thumbnailForDocument(documentName);
/*     */     } catch (Throwable er) {
/* 220 */       LogManager.logError(er);
/* 221 */       if ((er instanceof IllegalLicenseException)) {
/* 222 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 224 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String publishDocument(String requestContext, String documentName, String customerData, String outputProfileName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 256 */       XPressionContexts.getContextOfCurrentThread().disableStreamingInDataDirectLibrary();
/* 257 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 259 */       return quickDoc.publishDocument(documentName, customerData, outputProfileName);
/*     */     }
/*     */     catch (Throwable er) {
/* 262 */       LogManager.logError(er);
/* 263 */       if ((er instanceof IllegalLicenseException)) {
/* 264 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 266 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     } finally {
/* 268 */       XPressionContexts.clearContextOfCurrentThread();
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] publishAndReturnDocument(String requestContext, String documentName, String customerData, String outputProfileName)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 314 */       XPressionContexts.getContextOfCurrentThread().disableStreamingInDataDirectLibrary();
/*     */ 
/* 316 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 318 */       MultiStream ms = quickDoc.publishAndReturnDocument(documentName, customerData, outputProfileName);
/*     */       byte[] arrayOfByte;
/* 319 */       if (!ms.empty()) {
/* 320 */         ms.removeAllTempFiles();
/* 321 */         return ms.getStream()[0].getPartitions()[0].getPartitionData()[0].getData();
/*     */       }
/* 323 */       return null;
/*     */     } catch (Throwable er) {
/* 325 */       LogManager.logError(er);
/* 326 */       if ((er instanceof IllegalLicenseException))
/*     */       {
/* 328 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 330 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     } finally {
/* 332 */       XPressionContexts.clearContextOfCurrentThread();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MultiStream publishAndReturnDocumentMultipleStream(String requestContext, String documentName, String customerData, String outputProfileName, String includeMetaData)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 341 */       XPressionContexts.getContextOfCurrentThread().disableStreamingInDataDirectLibrary();
/* 342 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 344 */       MultiStream ms = quickDoc.publishAndReturnDocument(documentName, customerData, outputProfileName);
/*     */ 
/* 346 */       if (!"true".equals(includeMetaData)) {
/* 347 */         ms.removeAllMetaData();
/*     */       }
/* 349 */       ms.removeAllTempFiles();
/* 350 */       return ms;
/*     */     } catch (Throwable er) {
/* 352 */       LogManager.logError(er);
/* 353 */       if ((er instanceof IllegalLicenseException))
/*     */       {
/* 355 */         throw ExceptionHandler.makeWebServiceException("9550", er.getMessage());
/*     */       }
/* 357 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     } finally {
/* 359 */       XPressionContexts.clearContextOfCurrentThread();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String publishDocuments(String requestContext, String[] documentPackageNames, String outputProfileName, String customerData)
/*     */     throws SOAPFaultException
/*     */   {
/*     */     try
/*     */     {
/* 372 */       XPressionContexts.getContextOfCurrentThread().disableStreamingInDataDirectLibrary();
/* 373 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, null);
/*     */ 
/* 375 */       return quickDoc.publishDocuments(documentPackageNames, customerData, outputProfileName);
/*     */     }
/*     */     catch (Throwable er) {
/* 378 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     } finally {
/* 380 */       XPressionContexts.clearContextOfCurrentThread();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[][] versionsForDocumentWithWorkflowStatus(String requestContext, String documentName) throws SOAPFaultException
/*     */   {
/*     */     try {
/* 387 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 389 */       return quickDoc.versionsForDocumentWithWorkflowStatus(documentName);
/*     */     } catch (Throwable er) {
/* 391 */       LogManager.logError(er);
/* 392 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public String getDataCollectionTemplate(String requestContext, String documentName, String customerData) throws SOAPFaultException
/*     */   {
/*     */     try {
/* 400 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 402 */       return quickDoc.variableMetaDataForDocument(documentName, customerData);
/*     */     } catch (Throwable er) {
/* 404 */       LogManager.logError(er);
/* 405 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTemplateFields(String requestContext, String documentName, String customerData) throws SOAPFaultException
/*     */   {
/*     */     try {
/* 412 */       QuickDocFacade quickDoc = QuickDocFacade.getInstance(requestContext, documentName);
/*     */ 
/* 414 */       return quickDoc.variablesForDocument(documentName, customerData);
/*     */     } catch (Throwable er) {
/* 416 */       LogManager.logError(er);
/* 417 */       throw ExceptionHandler.makeWebServiceException(er);
/*     */     }
/*     */   }

			//str = ds.variablesForDocument(documentName, null);
			//System.out.println(entryName);
			//strRet = doc.getVariableMetadata();
			//String documentName = "ForturamaXWord_Template";
			//String effectiveDate;
			//effectiveDate = "1.13"; // version!
			//DocumentService ds = ServiceFactory.getInstance().getDocumentService();
			//AbstractDocument doc = ds.getAbstractDocument(documentName);
//String strUser = "xDesigner";
//String strPassword = "Pa55word";
			
			public static String getXWordTemplateXSD(String category, String documentName, String version, String user, String password, String path) {
				String strRet;
				String documentPath ="/"+category+"/"+documentName;
				String effectiveDate = version;
				XPressoDocumentOnlineService c = new XPressoDocumentOnlineService();
				String requestContext = "<RequestContext><Credentials method=\"UserID and Password\"><UserID>"+user+"</UserID><Password>"+password+"</Password></Credentials><ApplicationName>Word</ApplicationName></RequestContext>";
				byte[] bdoc = null;
				try {
					bdoc = c.loadDocument(requestContext, documentPath, effectiveDate);
				} catch (SOAPException e) {
					e.printStackTrace();
				}
				if (bdoc == null) { strRet = "ERROR"; }
				else {
					try {
						ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(bdoc));
						ZipEntry entry;
						while ((entry = zipStream.getNextEntry()) != null) {
							String entryName = entry.getName();
							if (entryName.indexOf("xsd")>=0) {
								FileOutputStream fos = new FileOutputStream(new File(path + entryName.substring(entryName.lastIndexOf("/"))));
								copyStream(zipStream, fos, entry);
								fos.close();								
							}
						}
						zipStream.close();
					} catch (FileNotFoundException e) {
							e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					strRet = "SUCCESS";
				}
				return strRet;
			}
			
			private static void copyStream(InputStream in, OutputStream out, ZipEntry entry) throws IOException {
			    byte[] buffer = new byte[1024 * 4];
			    long count = 0;
			    int n = 0;
			    long size = entry.getSize();
			    while (-1 != (n = in.read(buffer)) && count < size) {
			        out.write(buffer, 0, n);
			        count += n;
			    }
			}
/*     */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.framework.webservice.QuickDocService
 * JD-Core Version:    0.6.2
 */