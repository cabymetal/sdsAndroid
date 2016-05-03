package com.example.usuario.sdsandroid.sds.common;

import android.content.res.Resources;
import android.support.annotation.StringRes;

import java.lang.ref.WeakReference;

/**
 * Created by Carlos Murillo on 02/05/2016.
 * Personal ASUS
 */
public class TextResourceManager {
    private final WeakReference<Resources> mResourcesWeakReference;

    public TextResourceManager(Resources resources){
        mResourcesWeakReference = new WeakReference<>(resources);
    }

    public String get(@StringRes int stringId) {
        Resources resources = mResourcesWeakReference.get();
        if(resources == null){
            return "";
        }

        return resources.getString(stringId);
    }
}
