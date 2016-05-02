package com.example.usuario.sdsandroid.sds.login;

/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class SdsLoginPresenterImpl implements SdsLoginPresenter {
    private SdsLoginActivity mLoginActivity;

    public SdsLoginPresenterImpl(SdsLoginActivity sdsLoginActivity){
        mLoginActivity = sdsLoginActivity;
    }

    @Override
    public void validateLogin(String user, String pwd){
        if(mLoginActivity != null){
           mLoginActivity.showProgress();
        }
    }
    @Override
    public void onDestroy(){
        this.mLoginActivity = null;
    }
}
