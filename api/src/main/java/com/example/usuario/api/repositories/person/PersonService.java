package com.example.usuario.api.repositories.person;

import com.example.usuario.api.pojo.ResponseList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public interface PersonService {
    @GET("info")
    Call<ResponseList> getCoincidences();
}
