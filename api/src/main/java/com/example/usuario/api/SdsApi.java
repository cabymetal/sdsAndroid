package com.example.usuario.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class SdsApi {

    private static Retrofit retrofit;
    private static Retrofit jsonRetrofit;


    public static void init(String baseUrl){
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        jsonRetrofit = new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();

    }


    public static <G> G create(Class<G> service){
        return retrofit.create(service);
    }

    public static <G> G createJson(Class<G> service){
        return jsonRetrofit.create(service);
    }
}
