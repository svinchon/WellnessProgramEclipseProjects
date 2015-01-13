package com.dsc.uniarch.admin.distributionservice;

import com.dsc.uniarch.util.ConfigManager;

// Referenced classes of package com.dsc.uniarch.admin.distributionservice:
//            LogManager

public abstract class DSHandler
    implements Runnable
{

    private long sleepTime;
    private boolean stopped;

    public DSHandler()
    {
        sleepTime = 30000L;
        stopped = false;
        String timeConf = ConfigManager.getInstance().getValue("DISTRIBUTIONSERVICE_START_SLEEP_TIME");
        if(timeConf != null)
        {
            timeConf = timeConf.trim();
            if(timeConf.length() > 0)
            {
                sleepTime = Integer.parseInt(timeConf) * 1000;
            }
        }
    }

    public void run()
    {
        while(!stopped) 
        {
            try
            {
                process();
                Thread.sleep(sleepTime);
            }
            catch(Exception e)
            {
                LogManager.logError((new StringBuilder()).append(getHandlerName()).append(" run error:").toString(), e);
            }
        }
    }

    public void stop()
    {
        stopped = true;
    }

    public boolean isStopped()
    {
        return stopped;
    }

    public abstract String getHandlerName();

    public abstract void process();
}
