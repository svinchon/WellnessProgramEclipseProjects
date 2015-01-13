package com.dsc.uniarch.admin.distributionservice;

import java.util.concurrent.*;

// Referenced classes of package com.dsc.uniarch.admin.distributionservice:
//            DSHandler

public abstract class DSThreadPoolHandler extends DSHandler
{

    private ThreadPoolExecutor pool;

    public DSThreadPoolHandler()
    {
        pool = null;
        pool = createThreadPool();
    }

    public void execute(Runnable worker)
    {
        pool.execute(worker);
    }

    public void stop()
    {
        super.stop();
        pool.shutdown();
    }

    protected ThreadPoolExecutor createThreadPool()
    {
        return createThreadPool(1, 1);
    }

    protected ThreadPoolExecutor createThreadPool(int minPoolSize, int maxPoolSize)
    {
        return new ThreadPoolExecutor(minPoolSize, maxPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
}
