package com.diy.hiphelper;

public class HIPHelperProxy implements com.diy.hiphelper.HIPHelper {
  private String _endpoint = null;
  private com.diy.hiphelper.HIPHelper hIPHelper = null;
  
  public HIPHelperProxy() {
    _initHIPHelperProxy();
  }
  
  public HIPHelperProxy(String endpoint) {
    _endpoint = endpoint;
    _initHIPHelperProxy();
  }
  
  private void _initHIPHelperProxy() {
    try {
      hIPHelper = (new com.diy.hiphelper.HIPHelperServiceLocator()).getHIPHelper();
      if (hIPHelper != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)hIPHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)hIPHelper)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (hIPHelper != null)
      ((javax.xml.rpc.Stub)hIPHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.diy.hiphelper.HIPHelper getHIPHelper() {
    if (hIPHelper == null)
      _initHIPHelperProxy();
    return hIPHelper;
  }
  
  public java.lang.String[] getPatientDocNamesList(java.lang.String strPatientId) throws java.rmi.RemoteException{
    if (hIPHelper == null)
      _initHIPHelperProxy();
    return hIPHelper.getPatientDocNamesList(strPatientId);
  }
  
  public byte[] getDocContentByDocId(java.lang.String strDocId) throws java.rmi.RemoteException{
    if (hIPHelper == null)
      _initHIPHelperProxy();
    return hIPHelper.getDocContentByDocId(strDocId);
  }
  
  public java.lang.String getPatientDocsXML(java.lang.String strPatientId, java.lang.String strDocId) throws java.rmi.RemoteException{
    if (hIPHelper == null)
      _initHIPHelperProxy();
    return hIPHelper.getPatientDocsXML(strPatientId, strDocId);
  }
  
  public byte[] getDocContentByPatientIdAndDocId(java.lang.String strPatientId, java.lang.String strDocId) throws java.rmi.RemoteException{
    if (hIPHelper == null)
      _initHIPHelperProxy();
    return hIPHelper.getDocContentByPatientIdAndDocId(strPatientId, strDocId);
  }
  
  public void storeDoc(java.lang.String strPatientId, java.lang.String strDocId, byte[] bDoc) throws java.rmi.RemoteException{
    if (hIPHelper == null)
      _initHIPHelperProxy();
    hIPHelper.storeDoc(strPatientId, strDocId, bDoc);
  }
  
  
}