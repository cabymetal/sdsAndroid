package com.example.usuario.sdsandroid.sds.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.usuario.sdsandroid.R;
import com.example.usuario.sdsandroid.sds.common.TextResourceManager;
import com.example.usuario.sdsandroid.sds.common.Validator;
import com.example.usuario.sdsandroid.sds.login.Contract.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private Contract.SdsLoginPresenter mPresenter;

    //UI Elements
    @BindView(R.id.username) private EditText mUserView;
    @BindView(R.id.password) private EditText mPasswordView;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login_sds);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl(), new Validator(),
                new TextResourceManager(getResources()));
    }

    @OnClick(R.id.signin)
    public void login(){
        String usr = mUserView.getText().toString();
        String pwd = mPasswordView.getText().toString();

        mPresenter.onButtonLoginClick(usr, pwd);
    }

    public void onDestroy(){
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setUserError(String text) {
        mUserView.setError(text);
        mUserView.requestFocus();
    }

    @Override
    public void setPwdError(String text) {
        mPasswordView.setError(text);
        mPasswordView.requestFocus();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void setShortError(String text){
        mPasswordView.setError(text);
        mPasswordView.requestFocus();
    }
}
