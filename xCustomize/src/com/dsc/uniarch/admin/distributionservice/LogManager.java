package com.dsc.uniarch.admin.distributionservice;

import com.dsc.uniarch.util.XPressionResources;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;

import org.apache.log4j.*;

// Referenced classes of package com.dsc.uniarch.admin.distributionservice:
//            Log4jConfigUtil

@SuppressWarnings("unused")
public class LogManager
{

    private static Category category = Logger.getLogger("DistributionService");
	private static final String CATEGORY_NAME = "DistributionService";
    private static final String CONFIG_PROPERTY = "xPressionHome";
    private static final String LOG_CONFIGURATION = "ServiceLogConfiguration";

    public LogManager()
    {
    }

    private static boolean isDebugEnabled()
    {
        return category.isDebugEnabled();
    }

    private static boolean isInfoEnabled()
    {
        return category.isInfoEnabled();
    }

    public static void logInfo(String msg)
    {
        if(isInfoEnabled())
        {
            category.info(msg);
        }
    }

    public static void logDebug(String msg)
    {
        if(isDebugEnabled())
        {
            category.debug(msg);
        }
    }

    public static void logWarn(String msg)
    {
        category.warn(msg);
    }

    public static void logError(String msg)
    {
        logError(msg, null);
    }

    public static void logError(Throwable t)
    {
        logError(null, t);
    }

    public static void logError(String msg, Throwable t)
    {
        String result = "";
        if(msg != null)
        {
            result = (new StringBuilder()).append(result).append(msg).toString();
        }
        if(t != null)
        {
            result = (new StringBuilder()).append(result).append(generateStackTraceString(t)).toString();
        }
        category.error(result);
    }

    public static void logFatal(String msg)
    {
        category.fatal(msg);
    }

    public static String generateStackTraceString(Throwable t)
    {
        StringWriter s = new StringWriter();
        t.printStackTrace(new PrintWriter(s));
        return s.toString();
    }

    static 
    {
        ResourceBundle rb = XPressionResources.getBundle("xPressionHome");
        String configFileDir = rb.getString("xPressionHome");
        PropertyConfigurator.configureAndWatch((new StringBuilder()).append(configFileDir).append("ServiceLogConfiguration").toString(), Log4jConfigUtil.getDelayTimebyConfigurationName("ServiceLogConfiguration"));
    }
}
