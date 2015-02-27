/**
 * XML2ChartHelper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.diy.charthelper.client;

public interface XML2ChartHelper extends java.rmi.Remote {
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public java.lang.String generateTSChartFromXML(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String strRunXQuery(java.lang.String strInputAsString, java.lang.String strXQUERYAsString) throws java.rmi.RemoteException;
    public java.lang.String[] strGetValuesFromXML(java.lang.String strXML, java.lang.String strXPath) throws java.rmi.RemoteException;
    public java.lang.String strGetValueFromXML(java.lang.String strXML, java.lang.String strXPath) throws java.rmi.RemoteException;
    public java.lang.String generateGaugesFromXML(java.lang.String xml) throws java.rmi.RemoteException;
    public java.lang.String generateLineChartFromXML(java.lang.String xml) throws java.rmi.RemoteException;
}
