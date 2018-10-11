package liu.com.retrofitdemo.netUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import liu.com.retrofitdemo.R;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class LoadingDialog extends Dialog {
    @InjectView(R.id.loading)
    AVLoadingIndicatorView loading;
    private Context context;

    public static LoadingDialog dialog;

    public static LoadingDialog getInstance(Context context) {
        if (dialog == null) {
            synchronized (LoadingDialog.class) {
                dialog = new LoadingDialog(context);
            }
        }
        return dialog;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.DialogTheme);
        this.context = context;
    }

    public LoadingDialog(@NonNull Context context) {
        this(context, 0);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        ButterKnife.inject(this);
        this.setCanceledOnTouchOutside(false);
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                dismiss();
                ((Activity) context).finish();
                return false; //默认返回 false
            }
        });
        loading.show();
    }
}
