package liu.com.retrofitdemo.netUtils;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by ahrz7 on 2018/9/26.
 */

public interface ApiService {
    @GET("{url}")
    Observable<LoginDataInfo> getData(@Path(value = "url",encoded = true) String url, @QueryMap Map<String,String>map);
    @POST("{url}")
    Observable<LoginDataInfo> postData(@Path(value = "url",encoded = true) String url, @QueryMap Map<String,String>map);
}
