/**
 * XDBHelperServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.xdb;

public class XDBHelperServiceLocator extends org.apache.axis.client.Service implements com.diy.xdb.XDBHelperService {

    public XDBHelperServiceLocator() {
    }


    public XDBHelperServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public XDBHelperServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XDBHelper
    private java.lang.String XDBHelper_address = "http://localhost:18080/XDBHelper/services/XDBHelper";

    public java.lang.String getXDBHelperAddress() {
        return XDBHelper_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XDBHelperWSDDServiceName = "XDBHelper";

    public java.lang.String getXDBHelperWSDDServiceName() {
        return XDBHelperWSDDServiceName;
    }

    public void setXDBHelperWSDDServiceName(java.lang.String name) {
        XDBHelperWSDDServiceName = name;
    }

    public com.diy.xdb.XDBHelper getXDBHelper() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XDBHelper_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXDBHelper(endpoint);
    }

    public com.diy.xdb.XDBHelper getXDBHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.diy.xdb.XDBHelperSoapBindingStub _stub = new com.diy.xdb.XDBHelperSoapBindingStub(portAddress, this);
            _stub.setPortName(getXDBHelperWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXDBHelperEndpointAddress(java.lang.String address) {
        XDBHelper_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.diy.xdb.XDBHelper.class.isAssignableFrom(serviceEndpointInterface)) {
                com.diy.xdb.XDBHelperSoapBindingStub _stub = new com.diy.xdb.XDBHelperSoapBindingStub(new java.net.URL(XDBHelper_address), this);
                _stub.setPortName(getXDBHelperWSDDServiceName());
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
        if ("XDBHelper".equals(inputPortName)) {
            return getXDBHelper();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://xdb.diy.com", "XDBHelperService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://xdb.diy.com", "XDBHelper"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XDBHelper".equals(portName)) {
            setXDBHelperEndpointAddress(address);
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
