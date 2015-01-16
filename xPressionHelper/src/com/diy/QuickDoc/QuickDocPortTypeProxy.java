package com.diy.QuickDoc;

public class QuickDocPortTypeProxy implements com.diy.QuickDoc.QuickDocPortType {
  private String _endpoint = null;
  private com.diy.QuickDoc.QuickDocPortType quickDocPortType = null;
  
  public QuickDocPortTypeProxy() {
    _initQuickDocPortTypeProxy();
  }
  
  public QuickDocPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initQuickDocPortTypeProxy();
  }
  
  private void _initQuickDocPortTypeProxy() {
    try {
      quickDocPortType = (new com.diy.QuickDoc.QuickDocLocator()).getQuickDocHttpSoap11Endpoint();
      if (quickDocPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)quickDocPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)quickDocPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (quickDocPortType != null)
      ((javax.xml.rpc.Stub)quickDocPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.diy.QuickDoc.QuickDocPortType getQuickDocPortType() {
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType;
  }
  
  public byte[] publishAndReturnDocument(java.lang.String requestContext, java.lang.String documentName, java.lang.String customerData, java.lang.String outputProfileName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.publishAndReturnDocument(requestContext, documentName, customerData, outputProfileName);
  }
  
  public java.lang.String[] outputProfilesForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.outputProfilesForDocument(requestContext, documentName);
  }
  
  public java.lang.String descriptionForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.descriptionForDocument(requestContext, documentName);
  }
  
  public java.lang.String[] categoriesForUser(java.lang.String requestContext) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.categoriesForUser(requestContext);
  }
  
  public java.lang.String publishDocument(java.lang.String requestContext, java.lang.String documentName, java.lang.String customerData, java.lang.String outputProfileName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.publishDocument(requestContext, documentName, customerData, outputProfileName);
  }
  
  public byte[] thumbnailForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.thumbnailForDocument(requestContext, documentName);
  }
  
  public java.lang.String[] documentsForCategory(java.lang.String requestContext, java.lang.String categoryName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.documentsForCategory(requestContext, categoryName);
  }
  
  public java.lang.String publishDocuments(java.lang.String requestContext, java.lang.String[] documentPackageNames, java.lang.String outputProfileName, java.lang.String customerData) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.publishDocuments(requestContext, documentPackageNames, outputProfileName, customerData);
  }
  
  public java.lang.String designToolForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException{
    if (quickDocPortType == null)
      _initQuickDocPortTypeProxy();
    return quickDocPortType.designToolForDocument(requestContext, documentName);
  }
  
  
}