package liu.com.retrofitdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import liu.com.retrofitdemo.R;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class MessageDialog extends Dialog {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.message)
    TextView message;
    @InjectView(R.id.negative)
    TextView negative;
    @InjectView(R.id.positive)
    TextView positive;

    private String titleString;
    private String messageString;
    private String negativeString;
    private String positiveString;

    private View.OnClickListener negativeListener;
    private View.OnClickListener positiveListener;

    public static MessageDialog messageDialog;

    public static MessageDialog getInstance(Context context){
        if (messageDialog==null){
            synchronized (MessageDialog.class){
                messageDialog=new MessageDialog(context);
            }
        }
        return messageDialog;
    }

    public MessageDialog setTitleString(String titleString) {
        this.titleString = titleString;
        return this;
    }

    public MessageDialog setMessageString(String messageString) {
        this.messageString = messageString;
        return this;
    }

    public MessageDialog setNegativeString(String negativeString, View.OnClickListener clickListener) {
        this.negativeString = negativeString;
        negativeListener=clickListener;
        return this;
    }

    public MessageDialog setPositiveString(String positiveString,View.OnClickListener clickListener) {
        this.positiveString = positiveString;
        positiveListener=clickListener;
        return this;
    }

    public MessageDialog(@NonNull Context context) {
        this(context, 0);
    }

    public MessageDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_dialog);
        ButterKnife.inject(this);
        if (!TextUtils.isEmpty(titleString)){
            title.setText(titleString);
            title.setVisibility(View.VISIBLE);
        }else {
            title.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(messageString)){
            message.setText(messageString);
            message.setVisibility(View.VISIBLE);
        }else {
            message.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(negativeString)){
            negative.setText(negativeString);
            negative.setVisibility(View.VISIBLE);
        }else {
            negative.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(positiveString)){
            positive.setText(positiveString);
            positive.setVisibility(View.VISIBLE);
        }else {
            positive.setVisibility(View.GONE);
        }

        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (negativeListener!=null){
                    negativeListener.onClick(view);
                }
                dismiss();
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positiveListener!=null){
                    positiveListener.onClick(view);
                }
                dismiss();
            }
        });

    }
}
