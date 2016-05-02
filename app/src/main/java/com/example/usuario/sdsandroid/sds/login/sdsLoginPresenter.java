package com.example.usuario.sdsandroid.sds.login;

/**
 * Created by Carlos Murillo on 01/05/2016.
 * Personal ASUS
 */
public interface SdsLoginPresenter {
    int validateLogin(String user, String password);
    void onDestroy();
}
