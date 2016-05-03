package com.example.usuario.sdsandroid.sds.common;

import android.text.TextUtils;

/**
 * Created by Carlos Murillo on 02/05/2016.
 * Personal ASUS
 */
public class Validator {
    public boolean isEmpty(String text) { return (TextUtils.isEmpty(text))?true:false; }

    public boolean isTooShort(String pwd) { return (pwd.length() <= 3)? true: false; }


}
