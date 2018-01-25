package com.ppdl.rerxokdemo1.NetRequest;

import com.ppdl.rerxokdemo1.Bean.SecretBean;
import com.ppdl.rerxokdemo1.Constant.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpService {

    @FormUrlEncoded
    @POST(Constant.QT_BASE_END)
    public Call<ResponseBody> getToken(@Field("client_id") String id,
                                       @Field("client_secret") String secret);

}
