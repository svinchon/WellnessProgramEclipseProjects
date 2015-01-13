package com.dsc.uniarch.admin.distributionservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

@SuppressWarnings("unused")
public class HtmlConverter
{
	boolean body_found = false;
	boolean in_body = false;
	boolean center = false;
	boolean pre = false;
	String href = "";

	public static void main(String[] argv) throws Exception
	{
		try {
			HtmlConverter h = new HtmlConverter();
			System.out.println(h.convert("C:\\html.html"));
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public String convert(String filePath) throws Exception {
		FileInputStream fis = null;
		String source = null;

		File file = new File(filePath);
		fis = new FileInputStream(file);
		byte[] buf = new byte[fis.available()];

		fis.read(buf);
		fis.close();
		fis = null;
		source = new String(buf);
		return convertSource(source);
	}

	public String convertSource(String source) throws Exception
	{
		String result = "";
		try
		{
			result = source.replaceAll("\r", " ");

			result = result.replaceAll("\n", " ");

			result = result.replaceAll("\t", "");

			result = result.replaceAll("( )+", " ");

			result = result.replaceAll("<( )*head([^>])*>", "<head>");
			result = result.replaceAll("(<( )*(/)( )*head( )*>)", "</head>");
			result = result.replaceAll("(<head>).*(</head>)", "");

			result = result.replaceAll("<( )*script([^>])*>", "<script>");
			result = result.replaceAll("(<( )*(/)( )*script( )*>)", "</script>");
			result = result.replaceAll("(<script>).*(</script>)", "");

			result = result.replaceAll("<( )*style([^>])*>", "<style>");
			result = result.replaceAll("(<( )*(/)( )*style( )*>)", "</style>");
			result = result.replaceAll("(<style>).*(</style>)", "");

			result = result.replaceAll("<( )*td([^>])*>", "\t");

			result = result.replaceAll("<( )*br( )*>", "\r");
			result = result.replaceAll("<( )*li( )*>", "\r");

			result = result.replaceAll("<( )*div([^>])*>", "\r");

			result = result.replaceAll("<( )*tr([^>])*>", "\r");

			result = result.replaceAll("<( )*th([^>])*>", "\r");

			result = result.replaceAll("<( )*p([^>])*>", "\r");

			result = result.replaceAll("<( )*embed([^>])*>", "");
			result = result.replaceAll("<( )*a([^>])*>", "");
			result = result.replaceAll("<( )*link([^>])*>", "");
			result = result.replaceAll("<( )*img([^>])*>", "");

			result = result.replaceAll("<VarDefs>.*?</VarDefs>", "");
			result = result.replaceAll("<TableDefs>.*?</TableDefs>", "");
			result = result.replaceAll("<RawSchema>.*?</RawSchema>", "");
			result = result.replaceAll("<ParseSchema>.*?</ParseSchema>", "");

			result = result.replaceAll("&nbsp;", " ");

			result = result.replaceAll("&bull;", " * ");

			result = result.replaceAll("&lsaquo;", "<");

			result = result.replaceAll("&rsaquo;", ">");

			result = result.replaceAll("&trade;", "(tm)");

			result = result.replaceAll("&frasl;", "/");

			result = result.replaceAll("<", "<");

			result = result.replaceAll(">", ">");

			result = result.replaceAll("<( )*hr([^>])*>", "_________________________________________");

			result = result.replaceAll("&(.{2,6});", "");

			result = result.replaceAll("&lt;", "<");
			result = result.replaceAll("&gt;", ">");
			result = result.replaceAll("&amp;", "&");
			result = result.replaceAll("&nbsp;", " ");
			result = result.replaceAll("&quot;", "\"");
			result = result.replaceAll("&copy;", "[Copyright]");
			result = result.replaceAll("&reg;", "[Registered]");
			result = result.replaceAll("&trade;", "[Trademark]");

			result = result.replaceAll("\n", "\r");

			result = result.replaceAll("<[^>|^x]*>", "");

			result = result.replaceAll("(\r)( )+(\r)", "\r\r");

			result = result.replaceAll("(\t)( )+(\t)", "\t\t");

			result = result.replaceAll("(\t)( )+(\r)", "\t\r");

			result = result.replaceAll("(\r)( )+(\t)", "\r\t");

			result = result.replaceAll("(\r)(\t)+(\r)", "\r\r");

			result = result.replaceAll("(\r)(\t)+", "\r\t");

			String breaks = "\r\r\r";

			String tabs = "\t\t\t\t\t";

			result = result.replaceAll(breaks, "\r\r");
			result = result.replaceAll(tabs, "\t\t\t\t");

			result = result.replaceAll("\r\r\r", "\r");
			result = result.replaceAll("\r\r", "\r");
			result = result.replaceAll("\t\t\t", "\t");
			result = result.replaceAll("\t\t", "\t");
			result = result.replaceAll("-->", "");
			result = result.replaceAll("]]>", "");
		}
		catch (Exception e)
		{
		}

		return result;
	}
}

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\UniArch_Admin.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.uniarch.admin.distributionservice.HtmlConverter
 * JD-Core Version:    0.6.2
 */