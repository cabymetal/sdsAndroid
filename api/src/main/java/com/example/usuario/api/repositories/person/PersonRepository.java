package com.example.usuario.api.repositories.person;

import com.example.usuario.api.SdsApi;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class PersonRepository {

    private final PersonService mService;

    public PersonRepository(){
        mService = SdsApi.create(PersonService.class);
    }
}
