package com.diy.html;

public class HTMLHelperProxy implements com.diy.html.HTMLHelper {
  private String _endpoint = null;
  private com.diy.html.HTMLHelper hTMLHelper = null;
  
  public HTMLHelperProxy() {
    _initHTMLHelperProxy();
  }
  
  public HTMLHelperProxy(String endpoint) {
    _endpoint = endpoint;
    _initHTMLHelperProxy();
  }
  
  private void _initHTMLHelperProxy() {
    try {
      hTMLHelper = (new com.diy.html.HTMLHelperServiceLocator()).getHTMLHelper();
      if (hTMLHelper != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)hTMLHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)hTMLHelper)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (hTMLHelper != null)
      ((javax.xml.rpc.Stub)hTMLHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.diy.html.HTMLHelper getHTMLHelper() {
    if (hTMLHelper == null)
      _initHTMLHelperProxy();
    return hTMLHelper;
  }
  
  public java.lang.String embedImages(java.lang.String strHTML) throws java.rmi.RemoteException{
    if (hTMLHelper == null)
      _initHTMLHelperProxy();
    return hTMLHelper.embedImages(strHTML);
  }
  
  
}