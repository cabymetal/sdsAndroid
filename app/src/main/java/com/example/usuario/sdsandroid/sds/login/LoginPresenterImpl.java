package com.example.usuario.sdsandroid.sds.login;

import android.text.TextUtils;

import com.example.usuario.sdsandroid.R;
import com.example.usuario.sdsandroid.sds.common.TextResourceManager;
import com.example.usuario.sdsandroid.sds.common.Validator;
import com.example.usuario.sdsandroid.sds.login.Contract.LoginView;

/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class LoginPresenterImpl implements Contract.SdsLoginPresenter, Contract.LoginInteractorListener {
    private LoginView mView;
    private Validator mValidator;
    private TextResourceManager mTextResourceManager;
    private LoginInteractorImpl mLoginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractorImpl loginInteractor,
                              Validator validator, TextResourceManager textResourceManager){
        mView = loginView;
        mLoginInteractor = loginInteractor;
        mValidator = validator;
        this.mTextResourceManager = textResourceManager;
    }

    /*
     * return a value that corresponds to the error status
     * 1 : the user is empty
     * 2 : the user is only Digits
     * 3 : the password is empty
     * 4 : the password is too short
     * 0 : all correct
     */

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
        this.mView = null;
    }

    @Override
    public void onButtonLoginClick(String user, String pwd) {
        if(mValidator.isEmpty(user)){
            mView.setUserError(mTextResourceManager.get(R.string.error_field_required));
            return;
        }

        if(mValidator.isEmpty(pwd)){
            mView.setPwdError(mTextResourceManager.get(R.string.error_field_required));
            return;
        }

        if(mValidator.isTooShort(pwd)){
            mView.setShortError(mTextResourceManager.get(R.string.error_invalid_password));
            return;
        }

        mView.showProgressDialog();
        mLoginInteractor.login(user, pwd, this);
    }

    @Override
    public void onLoginSuccess() {
        mView.hideProgressDialog();
    }

    @Override
    public void onLoginError(Exception e) {
        mView.hideProgressDialog();

    }
}
