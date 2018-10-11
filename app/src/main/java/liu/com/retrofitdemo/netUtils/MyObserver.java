package liu.com.retrofitdemo.netUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import liu.com.retrofitdemo.dialog.MessageDialog;
import liu.com.retrofitdemo.threadUtils.ThreadPoolFactory;
import liu.com.retrofitdemo.utils.BuildConfig;
import timber.log.Timber;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class MyObserver implements Observer<LoginDataInfo> {
    private ObserverOnNextListener listener;
    private Context context;
    //新建一个线程，更新布局的UI
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (listener != null) {
                    listener.onNext((LoginDataInfo) msg.obj);
                }
            }
        }
    };

    public MyObserver(ObserverOnNextListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        LoadingDialog.getInstance(context).show();
        //访问接口判断当前有无网络
        if (!BuildConfig.isHaveNet(context) && null != context) {
            MessageDialog messageDialog = new MessageDialog(context);
            messageDialog.setTitleString("提示");
            messageDialog.setMessageString("当前没有可以使用的网络，请设置网络！");
            messageDialog.setNegativeString("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            messageDialog.setPositiveString("设置", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent wifiIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    wifiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(wifiIntent);
                }
            });
            messageDialog.show();
        }
        Timber.e("onSubscribe: ");
    }

    @Override
    public void onNext(final LoginDataInfo dataInfo) {
//        listener.onNext(dataInfo);

        ThreadPoolFactory.getThreadPoolFactory().execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.obj = dataInfo;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onError(Throwable e) {
        if (LoadingDialog.getInstance(context).isShowing()) {
            LoadingDialog.getInstance(context).dismiss();
        }
        Timber.e("onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        if (LoadingDialog.getInstance(context).isShowing()) {
            LoadingDialog.getInstance(context).dismiss();
        }
        Timber.e("onComplete: Over!");
    }
}
