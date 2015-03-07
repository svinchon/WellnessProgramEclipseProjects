/**
 * WP02ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WellnessProgram;

@SuppressWarnings({"rawtypes", "serial", "unchecked"})
public class WP02ServiceLocator extends org.apache.axis.client.Service implements WellnessProgram.WP02Service {

    public WP02ServiceLocator() {
    }


    public WP02ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WP02ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WP02Port
    private java.lang.String WP02Port_address = "http://localhost:7000/bps/webservice/4b0d8b6680018175/WP02";

    public java.lang.String getWP02PortAddress() {
        return WP02Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WP02PortWSDDServiceName = "WP02Port";

    public java.lang.String getWP02PortWSDDServiceName() {
        return WP02PortWSDDServiceName;
    }

    public void setWP02PortWSDDServiceName(java.lang.String name) {
        WP02PortWSDDServiceName = name;
    }

    public WellnessProgram.WP02 getWP02Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WP02Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWP02Port(endpoint);
    }

    public WellnessProgram.WP02 getWP02Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WellnessProgram.WP02BindingStub _stub = new WellnessProgram.WP02BindingStub(portAddress, this);
            _stub.setPortName(getWP02PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWP02PortEndpointAddress(java.lang.String address) {
        WP02Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WellnessProgram.WP02.class.isAssignableFrom(serviceEndpointInterface)) {
                WellnessProgram.WP02BindingStub _stub = new WellnessProgram.WP02BindingStub(new java.net.URL(WP02Port_address), this);
                _stub.setPortName(getWP02PortWSDDServiceName());
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
        if ("WP02Port".equals(inputPortName)) {
            return getWP02Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("WellnessProgram", "WP02Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("WellnessProgram", "WP02Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WP02Port".equals(portName)) {
            setWP02PortEndpointAddress(address);
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
