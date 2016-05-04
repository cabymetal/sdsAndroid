package com.example.usuario.sdsandroid.sds.login;

import android.os.AsyncTask;

/**
 * Created by Carlos Murillo on 02/05/2016.
 * Personal ASUS
 */
public class LoginInteractorImpl {

    public void login(String user, String password, Contract.LoginInteractorListener listener){
        // Request
        DummyLoginTask loginTask = new DummyLoginTask(user, password, listener);
        loginTask.execute();



    }



    private class DummyLoginTask extends AsyncTask<Void, Void, Boolean> {
        private String[] CREDENTIALS;
        private String user;
        private String password;
        private Contract.LoginInteractorListener listener;

        protected DummyLoginTask(String user, String password, Contract.LoginInteractorListener listener){
            this.user = user;
            this.password = password;
            this.listener = listener;
            CREDENTIALS = new String[]{
                    "carlos.murillo:STRADATA2014", "carlos:12345",
                    "foo:hello", "bar:world"
            };
        }

        @Override
        public Boolean doInBackground(Void... params){
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                return false;
            }

            for(String credential : CREDENTIALS){
                String[] keyPair = credential.split(":");
                if(user.equals(keyPair[0]) && password.equals(keyPair[1])){
                    return true;
                }
            }
            return false;
        }

        @Override
        public void onPostExecute(Boolean success){
            if(success){
                listener.onLoginSuccess();
            }else{
                listener.onLoginFailedAuth();
            }
        }

        @Override
        public void onCancelled(){
            listener.onLoginError(new Exception());
        }
    }
}
