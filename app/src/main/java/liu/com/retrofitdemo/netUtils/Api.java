package liu.com.retrofitdemo.netUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class Api {
    public static final String baseUrl = "https://m-api-dev.lindans.cn/lindans_m_api/";
    public static final String Login = "partnerUser/loginByPASS.do";
    public static ApiService apiService;
    private OkHttpClient okHttpClient;

    //单利
    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        //接口访问拦截器，添加公共参数
        ApiInterceptor interceptor = new ApiInterceptor();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true).addInterceptor
                        (interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }
}
