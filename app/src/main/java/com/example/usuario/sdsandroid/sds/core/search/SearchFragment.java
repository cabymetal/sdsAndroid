package com.example.usuario.sdsandroid.sds.core.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.usuario.sdsandroid.sds.R;

;

/**
 * Created by Carlos Murillo on 06/05/2016.
 * Personal ASUS
 */
public class SearchFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        View view = inflater.inflate(R.layout.search_fragment, container, false);

        return view;

    }
}
