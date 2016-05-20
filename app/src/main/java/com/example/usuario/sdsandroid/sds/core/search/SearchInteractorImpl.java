package com.example.usuario.sdsandroid.sds.core.search;

import com.example.usuario.api.repositories.person.PersonRepository;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Carlos Murillo on 11/05/2016.
 * Personal ASUS
 */
public class SearchInteractorImpl{

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();


    public void onDeattach(){
        if(mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.clear();
        }
    }

    public void performSearch(String loggedUser, String loggedPassword, final Contract.SearchInteractor listener){
        PersonRepository personRepository = new PersonRepository();
        Subscription subscription =  personRepository.getService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseList -> listener.onSearchSuccess(responseList),
                        error -> listener.onSearchError(error));

        mCompositeSubscription.add(subscription);
        /*coincidences.enqueue(new Callback<ResponseList>() {

            @Override
            public void onResponse(Call<ResponseList> call, Response<ResponseList> response) {
                Log.d("SUCCESS", response.body().toString());

            }

            @Override
            public void onFailure(Call<ResponseList> call, Throwable t) {
                Log.d("SUCCESS", "failure");
            }
        }); //OLD WAY WITHOUT RXJAVA */

    }

}
