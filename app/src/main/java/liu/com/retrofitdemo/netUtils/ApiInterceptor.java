package liu.com.retrofitdemo.netUtils;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liu.com.retrofitdemo.utils.BuildConfig;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import timber.log.Timber;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class ApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original =chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        if (original!=null&&original.method().equals("POST")) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            formBodyBuilder.add("appid","4");//此处添加apptoken参数
            RequestBody formBody = formBodyBuilder.build();
            String postBodyString = bodyToString(original.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
//            Log.e("########",postBodyString);
            Timber.e(postBodyString);
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
        }
        original=requestBuilder.build();
//        Log.e("########",original.url().toString());
        Timber.e(original.url().toString());
        return chain.proceed(original);
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
