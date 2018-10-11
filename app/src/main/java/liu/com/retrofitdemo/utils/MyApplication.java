package liu.com.retrofitdemo.utils;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            //debug版本
            Timber.plant(new Timber.DebugTree());
        }
    }
}
