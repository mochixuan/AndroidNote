package com.wx.retrofitmaster.network.juhe;

import com.wx.retrofitmaster.bean.JhPostParam;
import com.wx.retrofitmaster.bean.WeChatBean;
import com.wx.retrofitmaster.constant.DataConstants;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by wangxuan on 2018/1/22.
 */
public interface JuHeService {

    //weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Call<ResponseBody> getWeChatSelection1();

    //没有id模拟，只能这样模拟一下，但不能进行pno={pno}会报参数异常 {path}
    @GET("weixin/{query}?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Call<ResponseBody> getWeChatSelection2(@Path("query") String query);

    /**
     * @method 表示请求的方法，区分大小写
     * @path 表示路径
     * @hasBody 表示是否有请求体
     */
    @HTTP(
        method = "GET",
        path = "weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63",
        hasBody = false
    )
    Call<ResponseBody> getWeChatSelection3();

    /**
    * 注意这里GET()可以为空参数写在BaseUrl里效果一样的 QueryMap效果一样，名字就随意了
    */
    @GET("weixin/query")
    Call<ResponseBody> getWeChatSelection4(@Query("pno") int pno,
                                           @Query("ps") int ps,
                                           @Query("dtype") String dtype,
                                           @Query("key") String key);

    //模拟post接口 传入的是json格式的 和下面结合,必须服务端接收才行
    @POST("weixin/query")
    Call<WeChatBean> getWeChatSelection5(@Body JhPostParam para);

    /**
     * 表单形式提交Form表单 FieldMap
     * @FieldMap Map<String, Object> map
     * Content-Type:application/x-www-form-urlencoded
     */
    @FormUrlEncoded
    @POST("weixin/query")
    Call<ResponseBody> getWeChatSelection6(@Field("pno") int pno,
                                           @Field("ps") int ps,
                                           @Field("dtype") String dtype,
                                           @Field("key") String key);

    /**
     * 表示相应体用流的形式返回
     * 如果没有该注释，默认数据会全部载入内存
     * 之后通过流的形式读取，是在内存中读，
     * 所以数据较大时，使用该注释
     */
    @Streaming
    @GET(DataConstants.BAIDU_IMG_END)
    Call<ResponseBody> downBaiDuImage7();

    /**
     *  带文件的上传
     *  Part 后面支持三种类型，RequestBody、okhttp3.MultipartBody.Part 、任意类型
     *  其中@Part MultipartBody.Part代表文件，@Part("key") RequestBody代表参数需要添加@Multipart表示支持文件上传的表单
     * okhttp3.MultipartBody.Part 以外，其它类型都必须带上表单字段 okhttp3.MultipartBody.Part 中已经包含了表单字段的信息
     * PartMap 注解支持一个Map作为参数，支持 RequestBody  类型
     * 如 果有其它的类型，会被 retrofit2.
     * Converter 转换，
     */
    @Multipart
    @POST
    Call<ResponseBody> uploadFile8(
            @Url String url,
            @Part("uKey") RequestBody  uKey,
            @Part("_api_key") RequestBody  _api_key,
            @Part("installType") RequestBody  installType,
            @Part("password") RequestBody  password,
            @Part("updateDescription") RequestBody  updateDescription,
            @Part MultipartBody.Part file
    );

    //添加请求头
    //@Headers("headers1:wang1")
    @Headers({
        "headers1:wang1",
        "headers2:wang2"
    })
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Call<ResponseBody> getWeChatSelection9(@Header("header3") String header3);

    //Gson转换器
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Call<WeChatBean> getWeChatSelection10();

    //Scalars转换位String
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Call<String> getWeChatSelection11();

    //rxjava1支持
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Observable<WeChatBean> getWeChatSelection12();

    //rxjava2支持 返回状态
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Observable<Response<WeChatBean>> getWeChatSelection13();

    //自定义转换器
    @GET("weixin/query?pno=1&ps=1&dtype=json&key=97ccfd2896c2fe7d5662510254995d63")
    Observable<Response<String>> getWeChatSelection14();

}
