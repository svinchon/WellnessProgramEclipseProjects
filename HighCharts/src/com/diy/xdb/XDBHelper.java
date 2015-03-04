/**
 * XDBHelper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.xdb;

public interface XDBHelper extends java.rmi.Remote {
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public java.lang.String runXQueryFile(java.lang.String strQueryFile) throws java.rmi.RemoteException;
    public java.lang.String storeDoc(java.lang.String strFileName, java.lang.String strDocumentName, java.lang.String strLibrary) throws java.rmi.RemoteException;
    public java.lang.String storeStringAsDoc(java.lang.String string, java.lang.String strDocumentName, java.lang.String strLibrary) throws java.rmi.RemoteException;
    public java.lang.String removeDoc(java.lang.String strDocumentName, java.lang.String strLibrary) throws java.rmi.RemoteException;
    public java.lang.String runXQuery(java.lang.String strQuery) throws java.rmi.RemoteException;
    public java.lang.String runXQueryReadOnly(java.lang.String strQuery) throws java.rmi.RemoteException;
}
