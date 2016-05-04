package com.example.usuario.sdsandroid.sds.login;

/**
 * Created by Carlos Murillo on 02/05/2016.
 * Personal ASUS
 */
public interface Contract {
     interface LoginView {
         void setUserError(String text);
         void setPwdError(String text);
         void showProgressDialog();
         void hideProgressDialog();
         void setShortError(String s);

         void setOnlyNumberError(String s);
     }

     interface SdsLoginPresenter {
        void onDestroy();
        void onButtonLoginClick(String usr, String pwd);
    }

    interface LoginInteractorListener{
        void onLoginSuccess();
        void onLoginError(Exception e);

        void onLoginFailedAuth();
    }
}
