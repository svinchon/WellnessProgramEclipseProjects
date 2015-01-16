/**
 * QuickDocPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.QuickDoc;

public interface QuickDocPortType extends java.rmi.Remote {
    public byte[] publishAndReturnDocument(java.lang.String requestContext, java.lang.String documentName, java.lang.String customerData, java.lang.String outputProfileName) throws java.rmi.RemoteException;
    public java.lang.String[] outputProfilesForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException;
    public java.lang.String descriptionForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException;
    public java.lang.String[] categoriesForUser(java.lang.String requestContext) throws java.rmi.RemoteException;
    public java.lang.String publishDocument(java.lang.String requestContext, java.lang.String documentName, java.lang.String customerData, java.lang.String outputProfileName) throws java.rmi.RemoteException;
    public byte[] thumbnailForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException;
    public java.lang.String[] documentsForCategory(java.lang.String requestContext, java.lang.String categoryName) throws java.rmi.RemoteException;
    public java.lang.String publishDocuments(java.lang.String requestContext, java.lang.String[] documentPackageNames, java.lang.String outputProfileName, java.lang.String customerData) throws java.rmi.RemoteException;
    public java.lang.String designToolForDocument(java.lang.String requestContext, java.lang.String documentName) throws java.rmi.RemoteException;
}
