package com.wx.retrofitmaster.network.juhe;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 一个简单的转换
 * Created by wangxuan on 2018/1/23.
 */

public class StringConverterFactory extends Converter.Factory{

    public static final StringConverterFactory INSTANCE = new StringConverterFactory();

    public static StringConverterFactory create() {
        return INSTANCE;
    }

    /**
     *
     * @param type 需要转换位什么类型Response<T>
     * @param annotations
     * @param retrofit
     * @return
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        if (type == String.class) {
            return StringConverter.INSTANCE;
        }

        return super.responseBodyConverter(type, annotations, retrofit);
    }

    public static class StringConverter implements Converter<ResponseBody,String> {

        public static final StringConverter INSTANCE = new StringConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

}
