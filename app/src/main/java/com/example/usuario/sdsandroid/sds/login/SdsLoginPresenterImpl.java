package com.example.usuario.sdsandroid.sds.login;

import android.text.TextUtils;

/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class SdsLoginPresenterImpl implements SdsLoginPresenter {
    private SdsLoginActivity mLoginActivity;

    public SdsLoginPresenterImpl(SdsLoginActivity sdsLoginActivity){
        mLoginActivity = sdsLoginActivity;
    }

    /*
     * return a value that corresponds to the error status
     * 1 : the user is empty
     * 2 : the user is only Digits
     * 3 : the password is empty
     * 4 : the password is too short
     * 0 : all correct
     */
    @Override
    public int validateLogin(String user, String pwd){
        int errorCode = 0;
        //empty username
        if(TextUtils.isEmpty(user)){
            errorCode = 1;
        }
        //only Digits
        if(!TextUtils.isEmpty(user) && !TextUtils.isDigitsOnly(user)){
            errorCode = 2;
        }
        if(TextUtils.isEmpty(pwd)){
            errorCode = 3;
        }
        if(pwd.length() < 4){
            errorCode = 4;
        }

        return errorCode;
    }
    @Override
    public void onDestroy(){
        this.mLoginActivity = null;
    }
}
