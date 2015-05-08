/*    */ package com.dsc.xprs.webservice;
/*    */ 
/*    */ import com.docscience.xpression.security.FailedAuthenticateUserException;
/*    */ import com.docscience.xpression.system.SystemException;
/*    */ import com.dsc.uniarch.cr.error.CRPackageException;
/*    */ import com.dsc.uniarch.util.LocalizedException;
/*    */ import com.dsc.uniarch.util.LocalizedExceptionBuilder;
/*    */ import com.dsc.uniarch.util.LocalizedMessageLoader;
/*    */ import com.dsc.xprs.framework.security.AuthenticationException;
/*    */ import com.dsc.xprs.framework.util.ExceptionHandler;
/*    */ import javax.xml.soap.SOAPException;
/*    */ 
/*    */ public abstract class OnlineModeService
/*    */ {
/*    */   public static final String EE_ERR_CODE_NOT_PRIVILEGED = "9035";
/*    */   public static final String XPRESSO_ERR_CODE_AUTHENTICATION_FAILED = "5001";
/*    */   public static final String XPRESSO_ERR_CODE_NOT_LICENSED = "5005";
/*    */   public static final String XPRESSO_ERR_CODE_CHECKIN_NOT_PRIVILEGED = "5012";
/*    */   public static final String XPRESSO_ERR_CODE_CHECKOUT_NOT_PRIVILEGED = "5013";
/*    */   public static final String XPRESSO_ERR_CODE_CANCLE_CHECKOUT_NOT_PRIVILEGED = "5011";
/*    */   public static final String XPRESSO_ERR_CODE_OPEN_PRIVILEGED = "5015";
/*    */   public static final String XPRESSO_ERR_CODE_ADDLABEL_NOT_PRIVILEGED = "5016";
/*    */   public static final String XPRESSO_ERR_CODE_DELETELABEL_NOT_PRIVILEGED = "5017";
/*    */   public static final String XPRESSO_ERR_CODE_MORE_THAN_ONE_VERSION_CHECKED_OUT = "9701";
/*    */ 
/*    */   protected SOAPException makeSOAPException(Throwable t)
/*    */   {
/* 29 */     if ((t instanceof SOAPException)) {
/* 30 */       return (SOAPException)t;
/*    */     }
/* 32 */     LocalizedException le = null;
/* 33 */     if ((t instanceof LocalizedException)) {
/* 34 */       le = (LocalizedException)t;
/*    */     } else {
/* 36 */       if ((t instanceof CRPackageException)) {
/* 37 */         int code = ((CRPackageException)t).getErrorCode();
/* 38 */         return new SOAPException(code + "");
/*    */       }
/* 40 */       le = LocalizedExceptionBuilder.build(SystemException.class, t);
/*    */     }
/*    */ 
/* 43 */     ExceptionHandler.printException(le.getErrorCode(), le.getLocalizedMessage(), le);
/*    */ 
/* 45 */     String errorCode = toXPressoErrorCode(le);
/*    */ 
/* 48 */     return new SOAPException(errorCode);
/*    */   }
/*    */ 
/*    */   protected SOAPException operationNotPrivileged(String errorCode)
/*    */   {
/* 53 */     String errMsg = LocalizedMessageLoader.load("9035");
/* 54 */     ExceptionHandler.printException("9035", errMsg, null);
/*    */ 
/* 56 */     return new SOAPException(errorCode);
/*    */   }
/*    */ 
/*    */   private String toXPressoErrorCode(LocalizedException exception) {
/* 60 */     if (((exception instanceof AuthenticationException)) || ((exception instanceof FailedAuthenticateUserException))) {
/* 61 */       return "5001";
/*    */     }
/* 63 */     return exception.getErrorCode();
/*    */   }
/*    */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.OnlineModeService
 * JD-Core Version:    0.6.2
 */