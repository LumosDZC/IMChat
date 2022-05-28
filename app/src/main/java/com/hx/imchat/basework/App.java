package com.hx.imchat.basework;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * @author: Hx
 * @date: 2022年03月27日 16:44
 */
public class App extends Application {

    private static final String TAG = "TAG_AppUtil";
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        mContext = getApplicationContext();
    }

    //获取Context方法
    public static Context getContext() {
        return mContext;
    }
}
