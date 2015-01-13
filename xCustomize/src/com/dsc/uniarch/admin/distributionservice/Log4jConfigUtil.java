package com.dsc.uniarch.admin.distributionservice;

import com.dsc.uniarch.util.XPressionResources;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class Log4jConfigUtil
{
	private static final String CONFIG_PROPERTY = "xPressionHome";
	private static final String KEY_DELAY = "delay_time";
	private static String CONFIGFILEDIR;

	private static long DEFAULT_DELAY = 60000L;

	private static Properties getProperties(String configFileName)
	{
		FileInputStream istream = null;
		Properties props = new Properties();
		try {
			istream = new FileInputStream(configFileName);
			props.load(istream);
			istream.close();
			return props;
		} catch (Exception e) {
			System.out.println("Could not read configuration file [" + configFileName + "].");

			System.out.println("Ignoring configuration file [" + configFileName + "].");

			return null;
		} finally {
			if (istream != null)
				try {
					istream.close();
				}
			catch (Throwable ignore)
			{
			}
		}
	}

	public static long getDelayTimebyConfigurationName(String configName) {
		configName = CONFIGFILEDIR + configName;

		return getDelayTimebyConfigurationFile(configName);
	}

	public static long getDelayTimebyConfigurationFile(String configFileName)
	{
		Properties props = getProperties(configFileName);

		if (props == null) {
			return DEFAULT_DELAY;
		}
		String delay_time = props.getProperty("delay_time", String.valueOf(DEFAULT_DELAY));

		long delayTime = Long.valueOf(delay_time).longValue();

		return delayTime;
	}

	static
	{
		ResourceBundle rb = XPressionResources.getBundle("xPressionHome");
		CONFIGFILEDIR = rb.getString("xPressionHome");
	}
}

/* Location:           C:\xStuff\4.5SP1B13\jboss-as-7.1.1.Final\standalone\deployments\xPression.ear\UniArch_Admin.war\WEB-INF\classes\
 * Qualified Name:     com.dsc.uniarch.admin.distributionservice.Log4jConfigUtil
 * JD-Core Version:    0.6.2
 */