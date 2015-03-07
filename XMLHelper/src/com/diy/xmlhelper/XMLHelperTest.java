package com.diy.xmlhelper;

public class XMLHelperTest {

	public static void main(String[] args) {
		String strInputAsString = "<root><item index='1'>item A</item><item index='2'>item B</item></root>";
		String strXQUERYAsString = "declare variable $doc external;<xdata>{for $i in $doc/root/item return $i}</xdata>";			
		String strXML = XMLHelper.strRunXQuery(strInputAsString, strXQUERYAsString);
		System.out.println(strXML);
		System.out.println(XMLHelper.strGetValueFromXML(strInputAsString, "/root/item"));
		System.out.println(XMLHelper.strGetValuesFromXML(strInputAsString, "/root/item")[1]);
	}
	
}
