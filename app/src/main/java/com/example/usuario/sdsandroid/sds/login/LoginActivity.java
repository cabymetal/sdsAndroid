package com.example.usuario.sdsandroid.sds.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.usuario.api.pojo.UserDataLogin;
import com.example.usuario.sdsandroid.sds.R;
import com.example.usuario.sdsandroid.sds.common.TextResourceManager;
import com.example.usuario.sdsandroid.sds.common.Validator;
import com.example.usuario.sdsandroid.sds.core.search.SearchActivity;
import com.example.usuario.sdsandroid.sds.login.Contract.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private Contract.SdsLoginPresenter mPresenter;

    //UI Elements
    @Bind(R.id.username) public EditText mUserView;
    @Bind(R.id.passwordSds) public EditText mPasswordView;
    @Bind(R.id.login_text) public TextView mLoginTextView;
    //UI Layouts
    @Bind(R.id.login_form) public View mLoginForm;
    @Bind(R.id.login_progress) public View mLoginProgress;



    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login_sds);
        ButterKnife.bind(this);


        mPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl(), new Validator(),
                new TextResourceManager(getResources()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
    }

    @OnClick(R.id.signinButton)
    public void login(){
        String usr = mUserView.getText().toString();
        String pwd = mPasswordView.getText().toString();
        mPresenter.onButtonLoginClick(usr, pwd);
    }


    public void onDestroy(){
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override public void setUserError(String text) {
        mUserView.setError(text);
        mUserView.requestFocus();
    }

    @Override public void setPwdError(String text) {
        mPasswordView.setError(text);
        mPasswordView.requestFocus();
    }

    @Override public void setShortError(String text){
        mPasswordView.setError(text);
        mPasswordView.requestFocus();
    }

    @Override public void setOnlyNumberError(String text){
        mUserView.setError(text);
        mUserView.requestFocus();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showProgressDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            //there is animation API.
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginForm.setVisibility(View.GONE);
            mLoginForm.animate().setDuration(shortAnimTime).alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mLoginForm.setVisibility(View.GONE);
                }
            });

            mLoginProgress.setVisibility(View.VISIBLE);
            mLoginProgress.animate().setDuration(shortAnimTime).alpha(1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mLoginProgress.setVisibility(View.VISIBLE);
                }
            });

            mLoginTextView.setVisibility(View.VISIBLE);
        }else{
            mLoginForm.setVisibility(View.GONE);
            mLoginProgress.setVisibility(View.VISIBLE);
            mLoginTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void hideProgressDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            //there is animation API.
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginForm.setVisibility(View.VISIBLE);
            mLoginForm.animate().setDuration(shortAnimTime).alpha(1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mLoginForm.setVisibility(View.VISIBLE);
                }
            });

            mLoginProgress.setVisibility(View.GONE);
            mLoginProgress.animate().setDuration(shortAnimTime).alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mLoginProgress.setVisibility(View.GONE);
                }
            });
            mLoginTextView.setVisibility(View.GONE);
        }else{
            mLoginForm.setVisibility(View.VISIBLE);
            mLoginProgress.setVisibility(View.GONE);
            mLoginTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void startCore(UserDataLogin userDataLogin){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("user", userDataLogin.getUser());
        intent.putExtra("email", userDataLogin.getEmail());
        intent.putExtra("username", userDataLogin.getUsername());
        intent.putExtra("phone", userDataLogin.getPhone_number());
        startActivity(intent);
        finish();
    }
}
