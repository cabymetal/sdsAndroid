package com.example.usuario.api.repositories.person;

import com.example.usuario.api.SdsApi;
import com.example.usuario.api.pojo.ResponseList;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class PersonRepository {

    private final PersonService mService;

    public PersonRepository(){
        mService = SdsApi.create(PersonService.class);
    }

    public rx.Observable<ResponseList> getService() {
        return mService.getCoincidences();

    }
}
