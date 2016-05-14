package com.example.usuario.api.repositories.person;

import com.example.usuario.api.SdsApi;
import com.example.usuario.api.pojo.ResponseList;

import retrofit2.Call;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class PersonRepository {

    private final PersonService mService;

    public PersonRepository(){
        mService = SdsApi.create(PersonService.class);
    }

    public Call<ResponseList> getService() {
        return mService.getCoincidences();
    }
}
