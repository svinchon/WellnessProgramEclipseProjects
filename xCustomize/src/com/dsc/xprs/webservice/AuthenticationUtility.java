/*    */ package com.dsc.xprs.webservice;
/*    */ 
/*    */ import com.dsc.xprs.framework.security.IAuthentication;
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ 
/*    */ public class AuthenticationUtility
/*    */ {
/*    */   public static final String XPRS_AUTHENTICATION_PROVIDER = "XPRS_AUTHENTICATION_PROVIDER";
/*    */ 
/*    */   public static String authenticate(String request, String role)
/*    */   {
/* 13 */     String result = "";
/* 14 */     String authenticationPath = System.getProperty("XPRS_AUTHENTICATION_PROVIDER");
/*    */     try {
/* 16 */       Class clazz = Class.forName(authenticationPath);
/* 17 */       Constructor cons = clazz.getConstructor(new Class[0]);
/* 18 */       IAuthentication authentication = (IAuthentication)cons.newInstance(new Object[0]);
/*    */ 
/* 20 */       result = authentication.authenticate(request, role);
/*    */     } catch (ClassNotFoundException ex) {
/* 22 */       throw new RuntimeException(ex);
/*    */     } catch (NoSuchMethodException ex) {
/* 24 */       throw new RuntimeException(ex);
/*    */     } catch (InvocationTargetException ex) {
/* 26 */       throw new RuntimeException(ex);
/*    */     } catch (IllegalAccessException ex) {
/* 28 */       throw new RuntimeException(ex);
/*    */     } catch (InstantiationException ex) {
/* 30 */       throw new RuntimeException(ex);
/*    */     }
/* 32 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\xPRS_xFramework.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.xprs.webservice.AuthenticationUtility
 * JD-Core Version:    0.6.2
 */