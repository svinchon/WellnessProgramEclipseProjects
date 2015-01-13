/**
 * HTMLHelperServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.html;

@SuppressWarnings("serial")
public class HTMLHelperServiceLocator extends org.apache.axis.client.Service implements com.diy.html.HTMLHelperService {

    public HTMLHelperServiceLocator() {
    }


    public HTMLHelperServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HTMLHelperServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HTMLHelper
    private java.lang.String HTMLHelper_address = "http://localhost:18080/HTMLHelper/services/HTMLHelper";

    public java.lang.String getHTMLHelperAddress() {
        return HTMLHelper_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HTMLHelperWSDDServiceName = "HTMLHelper";

    public java.lang.String getHTMLHelperWSDDServiceName() {
        return HTMLHelperWSDDServiceName;
    }

    public void setHTMLHelperWSDDServiceName(java.lang.String name) {
        HTMLHelperWSDDServiceName = name;
    }

    public com.diy.html.HTMLHelper getHTMLHelper() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTMLHelper_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTMLHelper(endpoint);
    }

    public com.diy.html.HTMLHelper getHTMLHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.diy.html.HTMLHelperSoapBindingStub _stub = new com.diy.html.HTMLHelperSoapBindingStub(portAddress, this);
            _stub.setPortName(getHTMLHelperWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTMLHelperEndpointAddress(java.lang.String address) {
        HTMLHelper_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(@SuppressWarnings("rawtypes") Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.diy.html.HTMLHelper.class.isAssignableFrom(serviceEndpointInterface)) {
                com.diy.html.HTMLHelperSoapBindingStub _stub = new com.diy.html.HTMLHelperSoapBindingStub(new java.net.URL(HTMLHelper_address), this);
                _stub.setPortName(getHTMLHelperWSDDServiceName());
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
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, @SuppressWarnings("rawtypes") Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HTMLHelper".equals(inputPortName)) {
            return getHTMLHelper();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://html.diy.com", "HTMLHelperService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://html.diy.com", "HTMLHelper"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HTMLHelper".equals(portName)) {
            setHTMLHelperEndpointAddress(address);
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
