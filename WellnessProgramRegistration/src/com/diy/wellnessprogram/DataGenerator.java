package com.diy.wellnessprogram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DataGenerator {

	public static void main(String[] args) {
		try {
			String daily_member_data_xml = File2String(
					"C:/Users/dmadmin/git/WellnessProgramEclipseProjects/WellnessProgramRegistration/WebContent/xml"
					+"/"
					+"daily_member_data.xml");
			String xml = "<daily_data>";
			ResourceBundle rb = ResourceBundle.getBundle("WellnessProgram");
			String badgeNumber = rb.getString("BadgeNumber");
			daily_member_data_xml = daily_member_data_xml.replaceAll("--BADGE_NUMBER--", badgeNumber);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dStart = sdf.parse(rb.getString("StartDate"));
			DateTime ds = new DateTime(dStart);
			Date dToday = new Date();
			DateTime de= new DateTime(dToday);
			int d = Days.daysBetween(ds.toLocalDate(), de.toLocalDate()).getDays();
			int iMin = new Integer(rb.getString("RandomIntMin")).intValue();
			int iMax = new Integer(rb.getString("RandomIntMax")).intValue();
			float fHealthIndexBase = new Float(rb.getString("HealthIndex")).floatValue();
			DateTime cd;
			DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
			for (int e=0;e<=d;e++) {
				cd = ds.plusDays(e);
				float fHealthIndex = fHealthIndexBase * (1 + (new Float(getRandomInteger(iMin, iMax)).floatValue()) / 100 + e/d);
				xml += daily_member_data_xml.replaceAll("--DAILY_INDEX_VALUE--", ""+Math.round(fHealthIndex))
						 					.replaceAll("--DATE_STAMP--", cd.toString(dtf));
			}
			xml += "</daily_data>";
			String2File(xml, "C:/Users/dmadmin/git/WellnessProgramEclipseProjects/WellnessProgramRegistration/WebContent/xml/DailyData_"+badgeNumber+""+".xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void String2File(String str, String path) {
		try {
			File f = new File(path);
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(str.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public static int getRandomInteger(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
}
