package com.example.usuario.sdsandroid.sds.login;

import com.example.usuario.sdsandroid.sds.R;
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

        if(mValidator.isOnlyNumber(user)){
            mView.setOnlyNumberError(mTextResourceManager.get(R.string.error_invalid_username));
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

    @Override
    public void onLoginFailedAuth(){
        mView.setPwdError(mTextResourceManager.get(R.string.error_incorrect_password));
        mView.hideProgressDialog();
    }
}
