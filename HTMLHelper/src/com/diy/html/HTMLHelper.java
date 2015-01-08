package com.diy.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

public class HTMLHelper {

	public static void main(String[] args) {
		try {
			// server change again
			FileInputStream fis1 = new FileInputStream(new File("D:/xData/Business/Projects/2014/Q3/2014q3-09.WellnessProgram/xDW/T03.html"));
			byte[] bHTMLIn = new byte[fis1.available()];
			fis1.read(bHTMLIn);
			FileOutputStream fos = new FileOutputStream(new File("D:/xData/Business/Projects/2014/Q3/2014q3-09.WellnessProgram/xDW/Final.html"));
			byte[] bHTMLOut = bEmbedImages(bHTMLIn);
			fos.write(bHTMLOut);
			fos.close();
			fis1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] bEmbedImages(byte[] bHTML) {
		byte[] bReturn = null;
		String strHTML = new String(bHTML);
	    String strPattern;
        Pattern pattern;
        Matcher matcher;
	    StringBuffer sb;
	    strPattern = "(img src=\"[^\"]*\")";
        pattern = Pattern.compile(strPattern);
        matcher = pattern.matcher(strHTML);
        sb = new StringBuffer();
        while(matcher.find()) {
        	String strFoundString = matcher.group(1);
            String strName = strFoundString.substring(strFoundString.lastIndexOf("/")+1, strFoundString.length()-1);
            matcher.appendReplacement(sb, "img src=\"data:image/png;base64,"+new String(getAndBase64EncodeImage(strName))+"\"");
        }
        matcher.appendTail(sb);
        strHTML = sb.toString();
	    strPattern = "(url\\([^\\)]*\\))";
	    //System.out.println(strPattern);
        pattern = Pattern.compile(strPattern);
        matcher = pattern.matcher(strHTML);
        sb = new StringBuffer();
        while(matcher.find()) {
        	String strFoundString = matcher.group(1);
            String strName = strFoundString.substring(strFoundString.lastIndexOf("/")+1, strFoundString.length()-1);
            matcher.appendReplacement(sb, "url(data:image/png;base64,"+new String(getAndBase64EncodeImage(strName))+")");
        }
        matcher.appendTail(sb);
        strHTML = sb.toString();
        bReturn = strHTML.getBytes();
		return bReturn;
	}
	
	public static byte[] getAndBase64EncodeImage(String Name) {
		byte[] bReturn = null;
		try {
			FileInputStream fis = new FileInputStream(
				new File(ResourceBundle.getBundle("HTMLHelper").getString("ImageDirectory")+"/"+Name)
			);
			byte[] bImage = new byte[fis.available()];
			fis.read(bImage);
			bReturn = Base64.encodeBase64(bImage);
			//bReturn = new String(bImage).getBytes();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bReturn;
	}

}
