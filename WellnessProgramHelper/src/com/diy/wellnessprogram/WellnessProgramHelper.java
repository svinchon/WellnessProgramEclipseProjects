package com.diy.wellnessprogram;

import java.util.Date;
//import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

public class WellnessProgramHelper {
	
	public static void main(String[] args) {
		WellnessProgramHelper.getMemberPerformances("");
	}

	@SuppressWarnings("deprecation")
	public static String getEmailPrefix(String strEmail) {
		String str = strEmail.substring(0, strEmail.indexOf("@"));
		System.out.println("HealthProgramHelper => getEmailPrefix: "+str+" at "+new Date().toGMTString());
	    return str;
	}

	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		System.out.println("HealthProgramHelper => deAccent");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public static int getRandomInteger(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	@SuppressWarnings("deprecation")
	public static String getMemberPerformances(String strMemberId) {
		ResourceBundle rb = ResourceBundle.getBundle("WellnessProgramHelper");
		int iDelay = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			Date dStart = sdf.parse(rb.getString("ProgramStartDate"));
			Date dToday = new Date();
			Long lDelay = (dToday.getTime() - dStart.getTime())/1000/3600/24;
			iDelay = lDelay.intValue();
			//writeToLog(""+iDelay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int iMin = new Integer(rb.getString("RandomIntMin")).intValue();
		int iMax = new Integer(rb.getString("RandomIntMax")).intValue();
		float fHealthIndex = new Float(rb.getString("HealthIndex")).floatValue();
		float fWeightLoss = new Float(rb.getString("WeightLoss")).floatValue();
		float fSleepTime = new Float(rb.getString("SleepTime")).floatValue();
		float fExerciceTime = new Float(rb.getString("ExerciseTime")).floatValue();
		float fHealthIndexInteger= new Integer(rb.getString("HealthIndexInteger")).floatValue();
		fHealthIndex = fHealthIndex * (1+ (new Float(getRandomInteger(iMin, iMax)).floatValue()/100));
		fWeightLoss = fWeightLoss * (1+ (new Float(getRandomInteger(iMin, iMax)).floatValue()/100));
		fSleepTime = fSleepTime * (1+ (new Float(getRandomInteger(iMin, iMax)).floatValue()/100));
		fExerciceTime = fExerciceTime * (1+ (new Float(getRandomInteger(iMin, iMax)).floatValue()/100));
		fHealthIndexInteger = (fHealthIndexInteger+iDelay) * (1+(new Float(getRandomInteger(iMin, iMax)).floatValue()/100));
		int iHealthIndexInteger =Math.round(fHealthIndexInteger);
		//System.out.println("HPH => W: "+fWeightLoss);
		StringBuffer strbuffMembPerf = new StringBuffer();
		strbuffMembPerf.append("<MemberPerformances>\n");
		strbuffMembPerf.append(""
			+"	<MemberId>"+strMemberId+"</MemberId>\n"
			+"	<DateTime>"+new Date().toGMTString()+"</DateTime>\n"
			+"	<HealthIndex>"+fHealthIndex+"</HealthIndex>\n"
			+"	<WeightLoss>"+fWeightLoss+"</WeightLoss>\n"
			+"	<ExerciceTime>"+fExerciceTime+"</ExerciceTime>\n"
			+"	<SleepTime>"+fSleepTime+"</SleepTime>\n"
			+"	<HealthIndexInteger>"+iHealthIndexInteger+"</HealthIndexInteger>\n");
		strbuffMembPerf.append("</MemberPerformances>");
		String strMembPerf=strbuffMembPerf.toString();
		System.out.println("HealthProgramHelper => Extracted XML: \n"+strMembPerf+"\nat "+new Date().toGMTString());
		return strMembPerf;
	}

	public static void writeToLog(String str) {
		System.out.println("WellnessProgramHelper => "+str);
	}
}
