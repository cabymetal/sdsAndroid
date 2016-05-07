package com.example.usuario.sdsandroid.sds.core.search;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.sdsandroid.sds.R;

/**
 * Created by Carlos Murillo on 06/05/2016.
 * Personal ASUS
 */
public class ProcFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        View view = inflater.inflate(R.layout.proc_fragment, container, false);
        return view;

    }
}
