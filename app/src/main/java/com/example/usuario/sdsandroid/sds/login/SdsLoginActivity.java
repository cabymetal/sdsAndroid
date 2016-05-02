package com.example.usuario.sdsandroid.sds.login;

import android.os.Bundle;

/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class SdsLoginActivity{
    private SdsLoginPresenter mLoginPresenter;

    public SdsLoginActivity(SdsLoginPresenter mLoginPresenter){
        this.mLoginPresenter = new SdsLoginPresenterImpl(this);
    }

    public void onCreate(Bundle savedInstance){
        
    }
    public void showProgress(){

    }
}
