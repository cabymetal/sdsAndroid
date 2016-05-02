package com.example.usuario.sdsandroid.sds.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.usuario.sdsandroid.R;


/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class SdsLoginActivity extends AppCompatActivity{
    private SdsLoginPresenter mLoginPresenter;

    //UI Elements
    private EditText mUserView;
    private EditText mPasswordView;
    private Context mContext;


    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        //initialize Login presenter
        this.mLoginPresenter = new SdsLoginPresenterImpl(this);
        mContext = getApplicationContext();

        mUserView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.passwordsds);

    }

    public void login(){
        String usr = mUserView.getText().toString();
        String pwd = mPasswordView.getText().toString();

        int errorCode = mLoginPresenter.validateLogin(usr, pwd);
        View focusView = null;
        switch (errorCode){
            case 1:
                mUserView.setError(mContext.getString(R.string.error_field_required));
                focusView = mUserView;
                break;
            case 2:
                mUserView.setError(mContext.getString(R.string.error_invalid_username));
                focusView = mUserView;
                break;
            case 3:
                mPasswordView.setError(mContext.getString(R.string.error_field_required));
                focusView = mPasswordView;
                break;
            case 4:
                mPasswordView.setError(mContext.getString(R.string.error_invalid_password));
                focusView = mPasswordView;
                break;
            case 0:
                break;
        }
    }

    public void onDestroy(){
        mLoginPresenter.onDestroy();
        super.onDestroy();
    }
    public void showProgress(){

    }
}
