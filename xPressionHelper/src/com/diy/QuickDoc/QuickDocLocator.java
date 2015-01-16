/**
 * QuickDocLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.QuickDoc;

public class QuickDocLocator extends org.apache.axis.client.Service implements com.diy.QuickDoc.QuickDoc {


	private static final long serialVersionUID = 1L;

	public QuickDocLocator() {
    }


    public QuickDocLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QuickDocLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QuickDocHttpSoap11Endpoint
    private java.lang.String QuickDocHttpSoap11Endpoint_address = "http://localhost:18080/xFramework/services/QuickDoc.QuickDocHttpSoap11Endpoint/";

    public java.lang.String getQuickDocHttpSoap11EndpointAddress() {
        return QuickDocHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QuickDocHttpSoap11EndpointWSDDServiceName = "QuickDocHttpSoap11Endpoint";

    public java.lang.String getQuickDocHttpSoap11EndpointWSDDServiceName() {
        return QuickDocHttpSoap11EndpointWSDDServiceName;
    }

    public void setQuickDocHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        QuickDocHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.diy.QuickDoc.QuickDocPortType getQuickDocHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QuickDocHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQuickDocHttpSoap11Endpoint(endpoint);
    }

    public com.diy.QuickDoc.QuickDocPortType getQuickDocHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.diy.QuickDoc.QuickDocSoap11BindingStub _stub = new com.diy.QuickDoc.QuickDocSoap11BindingStub(portAddress, this);
            _stub.setPortName(getQuickDocHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQuickDocHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        QuickDocHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.diy.QuickDoc.QuickDocPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.diy.QuickDoc.QuickDocSoap11BindingStub _stub = new com.diy.QuickDoc.QuickDocSoap11BindingStub(new java.net.URL(QuickDocHttpSoap11Endpoint_address), this);
                _stub.setPortName(getQuickDocHttpSoap11EndpointWSDDServiceName());
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
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QuickDocHttpSoap11Endpoint".equals(inputPortName)) {
            return getQuickDocHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.framework.xprs.dsc.com", "QuickDoc");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.framework.xprs.dsc.com", "QuickDocHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QuickDocHttpSoap11Endpoint".equals(portName)) {
            setQuickDocHttpSoap11EndpointEndpointAddress(address);
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
