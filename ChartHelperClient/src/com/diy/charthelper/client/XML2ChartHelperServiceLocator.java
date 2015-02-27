/**
 * XML2ChartHelperServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.charthelper.client;

public class XML2ChartHelperServiceLocator extends org.apache.axis.client.Service implements com.diy.charthelper.client.XML2ChartHelperService {

    public XML2ChartHelperServiceLocator() {
    }


    public XML2ChartHelperServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public XML2ChartHelperServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XML2ChartHelper
    private java.lang.String XML2ChartHelper_address = "http://localhost:18080/ChartHelper/services/XML2ChartHelper";

    public java.lang.String getXML2ChartHelperAddress() {
        return XML2ChartHelper_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XML2ChartHelperWSDDServiceName = "XML2ChartHelper";

    public java.lang.String getXML2ChartHelperWSDDServiceName() {
        return XML2ChartHelperWSDDServiceName;
    }

    public void setXML2ChartHelperWSDDServiceName(java.lang.String name) {
        XML2ChartHelperWSDDServiceName = name;
    }

    public com.diy.charthelper.client.XML2ChartHelper getXML2ChartHelper() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XML2ChartHelper_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXML2ChartHelper(endpoint);
    }

    public com.diy.charthelper.client.XML2ChartHelper getXML2ChartHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.diy.charthelper.client.XML2ChartHelperSoapBindingStub _stub = new com.diy.charthelper.client.XML2ChartHelperSoapBindingStub(portAddress, this);
            _stub.setPortName(getXML2ChartHelperWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXML2ChartHelperEndpointAddress(java.lang.String address) {
        XML2ChartHelper_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.diy.charthelper.client.XML2ChartHelper.class.isAssignableFrom(serviceEndpointInterface)) {
                com.diy.charthelper.client.XML2ChartHelperSoapBindingStub _stub = new com.diy.charthelper.client.XML2ChartHelperSoapBindingStub(new java.net.URL(XML2ChartHelper_address), this);
                _stub.setPortName(getXML2ChartHelperWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("XML2ChartHelper".equals(inputPortName)) {
            return getXML2ChartHelper();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://charthelper.diy.com", "XML2ChartHelperService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://charthelper.diy.com", "XML2ChartHelper"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XML2ChartHelper".equals(portName)) {
            setXML2ChartHelperEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
