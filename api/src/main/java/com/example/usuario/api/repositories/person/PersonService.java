package com.example.usuario.api.repositories.person;

import com.example.usuario.api.pojo.ResponseList;
import com.example.usuario.api.pojo.UserDataLogin;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public interface PersonService {
    @GET("info")
    rx.Observable<ResponseList> getCoincidences();

    @GET("login/{user}/{password}")
    rx.Observable<UserDataLogin> getLogin(@Path("user") String user, @Path("password") String password);
    
}
