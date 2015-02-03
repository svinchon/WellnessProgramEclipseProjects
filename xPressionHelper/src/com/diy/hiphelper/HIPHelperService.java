/**
 * HIPHelperService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.hiphelper;

public interface HIPHelperService extends javax.xml.rpc.Service {
    public java.lang.String getHIPHelperAddress();

    public com.diy.hiphelper.HIPHelper getHIPHelper() throws javax.xml.rpc.ServiceException;

    public com.diy.hiphelper.HIPHelper getHIPHelper(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
