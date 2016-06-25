package com.example.usuario.sdsandroid.sds.core.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usuario.sdsandroid.sds.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//import scanner packages

/**
 * Created by Carlos Murillo on 21/05/2016.
 * Personal ASUS
 */
public class ScannerFragment extends Fragment{

    //Scanner Builder
    public static ScannerFragment newInstance(String user, String password){
        ScannerFragment scannerFragment = new ScannerFragment();
        Bundle args = new Bundle();
        args.putString("user",user);
        args.putString("password", password);
        scannerFragment.setArguments(args);
        return scannerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        View view = inflater.inflate(R.layout.fisc_fragment, container, false);
        setHasOptionsMenu(true);

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_scanner, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_cc_item:
                IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(this);
                intentIntegrator.initiateScan();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if(intentResult != null){
            String scanContent = intentResult.getContents();
            String scanFormat =  intentResult.getFormatName();

            Toast toast = Toast.makeText(getContext(), scanContent, Toast.LENGTH_LONG);
            toast.show();

            Toast toast2 = Toast.makeText(getContext(), scanFormat, Toast.LENGTH_LONG);
            toast2.show();

        }else{
            Toast toast = Toast.makeText(getContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}