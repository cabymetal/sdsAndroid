package com.example.usuario.sdsandroid.sds.core.search;

import android.util.Log;

import com.example.usuario.api.pojo.ResponseList;
import com.example.usuario.api.repositories.person.PersonRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Carlos Murillo on 11/05/2016.
 * Personal ASUS
 */
public class SearchInteractorImpl {

    public void performSearch(String loggedUser, String loggedPassword, final Contract.SearchInteractor listener){
        PersonRepository personRepository = new PersonRepository();
        Call<ResponseList> coincidences = personRepository.getService();

        coincidences.enqueue(new Callback<ResponseList>() {

            @Override
            public void onResponse(Call<ResponseList> call, Response<ResponseList> response) {
                Log.d("SUCCESS", response.body().toString());
                
            }



            @Override
            public void onFailure(Call<ResponseList> call, Throwable t) {
                Log.d("SUCCESS", "failure");
            }
        });

        return;

    }

}
