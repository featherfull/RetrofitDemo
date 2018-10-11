package liu.com.retrofitdemo.threadUtils;

/**
 * Created by ahrz7 on 2018/10/11.
 */

public class ThreadPoolFactory {
    static ThreadPoolProxy mThreadPoolProxy;

    public static ThreadPoolProxy getThreadPoolFactory(){
        if (mThreadPoolProxy==null){
            synchronized (ThreadPoolFactory.class){
                if (mThreadPoolProxy==null){
                    mThreadPoolProxy=new ThreadPoolProxy(5,5);
                }
            }
        }
        return mThreadPoolProxy;
    }
}
