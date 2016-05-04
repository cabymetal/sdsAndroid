package com.example.usuario.sdsandroid.sds.common;

/**
 * Created by Carlos Murillo on 04/05/2016.
 * Personal ASUS
 *
 * Transfer Object
 */
public class SessionTO {
    private String user;
    private String pwd;

    public SessionTO(String user, String pwd){
        this.pwd = pwd;
        this.user = user;
    }

    public String getUser(){
        return user;
    }

    public String getPwd(){
        return pwd;
    }
}
