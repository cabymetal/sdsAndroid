package com.example.usuario.api.repositories.person;

import com.example.usuario.api.SdsApi;
import com.example.usuario.api.pojo.ResponseList;
import com.example.usuario.api.pojo.UserDataLogin;

/**
 * Created by Carlos Murillo on 10/05/2016.
 * Personal ASUS
 */
public class PersonRepository {

    private final PersonService mService;
    private final PersonService mLoginService;

    public PersonRepository(){
        mService = SdsApi.create(PersonService.class);
        mLoginService = SdsApi.createJson(PersonService.class);
    }

    public rx.Observable<ResponseList> getService() {
        return mService.getCoincidences();
    }

    public rx.Observable<UserDataLogin> getLogin(String user, String password){
        return mLoginService.getLogin(user, password);
    }
}
