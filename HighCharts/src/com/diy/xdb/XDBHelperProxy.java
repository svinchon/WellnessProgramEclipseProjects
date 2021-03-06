package com.diy.xdb;

public class XDBHelperProxy implements com.diy.xdb.XDBHelper {
  private String _endpoint = null;
  private com.diy.xdb.XDBHelper xDBHelper = null;
  
  public XDBHelperProxy() {
    _initXDBHelperProxy();
  }
  
  public XDBHelperProxy(String endpoint) {
    _endpoint = endpoint;
    _initXDBHelperProxy();
  }
  
  private void _initXDBHelperProxy() {
    try {
      xDBHelper = (new com.diy.xdb.XDBHelperServiceLocator()).getXDBHelper();
      if (xDBHelper != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)xDBHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)xDBHelper)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (xDBHelper != null)
      ((javax.xml.rpc.Stub)xDBHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.diy.xdb.XDBHelper getXDBHelper() {
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    xDBHelper.main(args);
  }
  
  public java.lang.String runXQueryFile(java.lang.String strQueryFile) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper.runXQueryFile(strQueryFile);
  }
  
  public java.lang.String storeDoc(java.lang.String strFileName, java.lang.String strDocumentName, java.lang.String strLibrary) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper.storeDoc(strFileName, strDocumentName, strLibrary);
  }
  
  public java.lang.String storeStringAsDoc(java.lang.String string, java.lang.String strDocumentName, java.lang.String strLibrary) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper.storeStringAsDoc(string, strDocumentName, strLibrary);
  }
  
  public java.lang.String removeDoc(java.lang.String strDocumentName, java.lang.String strLibrary) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper.removeDoc(strDocumentName, strLibrary);
  }
  
  public java.lang.String runXQuery(java.lang.String strQuery) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper.runXQuery(strQuery);
  }
  
  public java.lang.String runXQueryReadOnly(java.lang.String strQuery) throws java.rmi.RemoteException{
    if (xDBHelper == null)
      _initXDBHelperProxy();
    return xDBHelper.runXQueryReadOnly(strQuery);
  }
  
  
}