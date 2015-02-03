/**
 * HIPHelperServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.hiphelper;

public class HIPHelperServiceLocator extends org.apache.axis.client.Service implements com.diy.hiphelper.HIPHelperService {

    public HIPHelperServiceLocator() {
    }


    public HIPHelperServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HIPHelperServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HIPHelper
    private java.lang.String HIPHelper_address = "http://localhost:18080/HIPHelper/services/HIPHelper";

    public java.lang.String getHIPHelperAddress() {
        return HIPHelper_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HIPHelperWSDDServiceName = "HIPHelper";

    public java.lang.String getHIPHelperWSDDServiceName() {
        return HIPHelperWSDDServiceName;
    }

    public void setHIPHelperWSDDServiceName(java.lang.String name) {
        HIPHelperWSDDServiceName = name;
    }

    public com.diy.hiphelper.HIPHelper getHIPHelper() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HIPHelper_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHIPHelper(endpoint);
    }

    public com.diy.hiphelper.HIPHelper getHIPHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.diy.hiphelper.HIPHelperSoapBindingStub _stub = new com.diy.hiphelper.HIPHelperSoapBindingStub(portAddress, this);
            _stub.setPortName(getHIPHelperWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHIPHelperEndpointAddress(java.lang.String address) {
        HIPHelper_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.diy.hiphelper.HIPHelper.class.isAssignableFrom(serviceEndpointInterface)) {
                com.diy.hiphelper.HIPHelperSoapBindingStub _stub = new com.diy.hiphelper.HIPHelperSoapBindingStub(new java.net.URL(HIPHelper_address), this);
                _stub.setPortName(getHIPHelperWSDDServiceName());
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
        if ("HIPHelper".equals(inputPortName)) {
            return getHIPHelper();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://hiphelper.diy.com", "HIPHelperService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://hiphelper.diy.com", "HIPHelper"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HIPHelper".equals(portName)) {
            setHIPHelperEndpointAddress(address);
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
