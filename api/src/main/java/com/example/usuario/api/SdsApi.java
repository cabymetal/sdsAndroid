package com.example.usuario.api;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class SdsApi {

    private static Retrofit retrofit;

    public static void init(String baseUrl){
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    public static <G> G create(Class<G> service){
        return retrofit.create(service);
    }
}
