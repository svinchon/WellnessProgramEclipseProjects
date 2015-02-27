package com.diy.charthelper.client;

public class XML2ChartHelperProxy implements com.diy.charthelper.client.XML2ChartHelper {
  private String _endpoint = null;
  private com.diy.charthelper.client.XML2ChartHelper xML2ChartHelper = null;
  
  public XML2ChartHelperProxy() {
    _initXML2ChartHelperProxy();
  }
  
  public XML2ChartHelperProxy(String endpoint) {
    _endpoint = endpoint;
    _initXML2ChartHelperProxy();
  }
  
  private void _initXML2ChartHelperProxy() {
    try {
      xML2ChartHelper = (new com.diy.charthelper.client.XML2ChartHelperServiceLocator()).getXML2ChartHelper();
      if (xML2ChartHelper != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)xML2ChartHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)xML2ChartHelper)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (xML2ChartHelper != null)
      ((javax.xml.rpc.Stub)xML2ChartHelper)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.diy.charthelper.client.XML2ChartHelper getXML2ChartHelper() {
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    xML2ChartHelper.main(args);
  }
  
  public java.lang.String generateTSChartFromXML(java.lang.String xml) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper.generateTSChartFromXML(xml);
  }
  
  public java.lang.String strRunXQuery(java.lang.String strInputAsString, java.lang.String strXQUERYAsString) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper.strRunXQuery(strInputAsString, strXQUERYAsString);
  }
  
  public java.lang.String[] strGetValuesFromXML(java.lang.String strXML, java.lang.String strXPath) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper.strGetValuesFromXML(strXML, strXPath);
  }
  
  public java.lang.String strGetValueFromXML(java.lang.String strXML, java.lang.String strXPath) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper.strGetValueFromXML(strXML, strXPath);
  }
  
  public java.lang.String generateGaugesFromXML(java.lang.String xml) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper.generateGaugesFromXML(xml);
  }
  
  public java.lang.String generateLineChartFromXML(java.lang.String xml) throws java.rmi.RemoteException{
    if (xML2ChartHelper == null)
      _initXML2ChartHelperProxy();
    return xML2ChartHelper.generateLineChartFromXML(xml);
  }
  
  
}