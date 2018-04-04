package com.qimiaowa.test_restart;

import android.app.Application;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SettingPreUtil.getInstance();

    }
    public static MyApplication getInstance(){
        return mInstance;
    }




}
