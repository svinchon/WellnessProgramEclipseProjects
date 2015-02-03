/**
 * HIPHelper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.hiphelper;

public interface HIPHelper extends java.rmi.Remote {
    public java.lang.String[] getPatientDocNamesList(java.lang.String strPatientId) throws java.rmi.RemoteException;
    public byte[] getDocContentByDocId(java.lang.String strDocId) throws java.rmi.RemoteException;
    public java.lang.String getPatientDocsXML(java.lang.String strPatientId, java.lang.String strDocId) throws java.rmi.RemoteException;
    public byte[] getDocContentByPatientIdAndDocId(java.lang.String strPatientId, java.lang.String strDocId) throws java.rmi.RemoteException;
    public void storeDoc(java.lang.String strPatientId, java.lang.String strDocId, byte[] bDoc) throws java.rmi.RemoteException;
}
