package com.example.usuario.api.pojo;

/**
 * Created by Carlos Murillo on 29/06/2016.
 * Personal ASUS
 */
public class UserDataLogin {
    private String user;
    private String email;
    private String username;
    private String phoneNumber;

    public UserDataLogin(){

    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }
}

