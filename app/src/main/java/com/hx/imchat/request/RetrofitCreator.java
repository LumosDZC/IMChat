package com.hx.imchat.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Hx
 * @date: 2022年03月30日 9:50
 */
public class RetrofitCreator {
    //登录Url
    private static final String LOGIN_URL = "";
    //登录请求Retrofit
    private static final Retrofit Retrofit_For_Login = new Retrofit.Builder().baseUrl(LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //为登录操作创建Retrofit
    public static <T> T createRetrofitForLogin(Class<T> retrofitClass) {
        return Retrofit_For_Login.create(retrofitClass);
    }

}
