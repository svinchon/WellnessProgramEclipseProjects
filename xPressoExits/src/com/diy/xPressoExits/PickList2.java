package com.diy.xPressoExits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PickList2 {

	public static void main(String[] args) {
		PickList2 p = new PickList2();
		System.out.println(p.getItem("A03"));
	}
	
	public PickList2() {
	}
	
	public String getItem(String labelCode) {
		String returnValue ="";
		try {
			String fileName = ResourceBundle.getBundle("xPressoExits").getString("LabelsLocation");
			String Labels = File2String(fileName);
			String regexp = labelCode + "\\t"+"([^\\t]*)\r";
			Matcher matcher = Pattern.compile(regexp).matcher(Labels);
			if (matcher.find()) {
				returnValue = matcher.group(1);
				if (returnValue.substring(0,1).equals("\"")) {
					returnValue = returnValue.substring(1, returnValue.length()-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	static String File2String(String path) {
		String strReturn = "";
		try {
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f);
			byte[] bDoc = new byte[fis.available()];
			fis.read(bDoc);
			strReturn = new String(bDoc);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strReturn;
	}

}
