package liu.com.retrofitdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import timber.log.Timber;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class BuildConfig {
    public static final boolean DEBUG=true;

    /**
     * 判断是否有网络
     * @param context
     * @return
     */
    public static boolean isHaveNet(Context context){
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isAvailable()){
            Timber.e("有网络");
            return true;
        }else {
            Timber.e("无网络");
            return false;
        }
    }
}
