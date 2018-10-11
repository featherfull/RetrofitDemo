package liu.com.retrofitdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import liu.com.retrofitdemo.netUtils.Api;
import liu.com.retrofitdemo.netUtils.ApiMethods;
import liu.com.retrofitdemo.netUtils.LoginDataInfo;
import liu.com.retrofitdemo.netUtils.MyObserver;
import liu.com.retrofitdemo.netUtils.ObserverOnNextListener;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.phone)
    EditText phone;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.show)
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    /**
     * 登录
     */
    private void login() {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone.getText().toString());
        map.put("passwd", MD5Util.encrypt(password.getText().toString()));
        ApiMethods.postData(Api.Login, new MyObserver(new ObserverOnNextListener() {
            @Override
            public void onNext(LoginDataInfo dataInfo) {
                Timber.e(dataInfo.toString());
                show.setText(dataInfo.toString());
            }
        }, MainActivity.this), map);
    }


}
