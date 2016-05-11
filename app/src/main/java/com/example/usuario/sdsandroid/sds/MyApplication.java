package com.example.usuario.sdsandroid.sds;

import android.app.Application;

import com.example.usuario.api.SdsApi;

/**
 * Created by Carlos Murillo on 11/05/2016.
 * Personal ASUS
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SdsApi.init("http://demo8014364.mockable.io/info");
    }
}
