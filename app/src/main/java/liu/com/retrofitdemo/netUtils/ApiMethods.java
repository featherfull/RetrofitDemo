package liu.com.retrofitdemo.netUtils;


import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public class ApiMethods {
    /**
     * 封装线程管理和订阅过程
     */
    public static void ApiSubscribe(Observable<LoginDataInfo> observable, Observer<LoginDataInfo> observer) {
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn
                (AndroidSchedulers.mainThread()).subscribe(observer);
    }

    /**
     * post 访问服务器数据
     * @param url    访问地址
     * @param observer  返回数据
     * @param map    访问参数
     */
    public static void postData(String url,MyObserver observer, Map<String,String>map){
        ApiSubscribe(Api.getApiService().postData(url,map),observer);
    }

    /**
     * get 访问服务器数据
     * @param url    访问地址
     * @param observer  返回数据
     * @param map    访问参数
     */
    public static void getData(String url,MyObserver observer, Map<String,String>map){
        ApiSubscribe(Api.getApiService().getData(url,map),observer);
    }
}
