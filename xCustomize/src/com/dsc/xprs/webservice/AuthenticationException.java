/*    */ package com.dsc.xprs.webservice;
/*    */ 
/*    */ import com.dsc.uniarch.util.ConfigManager;
/*    */ 
/*    */ public class AuthenticationException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 3023338787544149297L;
/*    */   private static final String ERROR_CODE = "AuthenticationFailed";
/*    */ 
/*    */   public AuthenticationException()
/*    */   {
/* 11 */     super(getErrorMessage());
/*    */   }
/*    */ 
/*    */   private static String getErrorMessage() {
/* 15 */     String message = ConfigManager.getInstance().getValue("AuthenticationFailed");
/* 16 */     if (message == null) {
/* 17 */       message = "AuthenticationFailed";
/*    */     }
/* 19 */     return message;
/*    */   }
/*    */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.AuthenticationException
 * JD-Core Version:    0.6.2
 */