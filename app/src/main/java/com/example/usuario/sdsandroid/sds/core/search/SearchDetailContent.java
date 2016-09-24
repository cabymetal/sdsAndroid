package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.usuario.sdsandroid.sds.R;

/**
 * Created by Carlos Murillo on 27/06/2016.
 * Personal ASUS
 */
public class SearchDetailContent extends AppCompatActivity {

    public static final String ID = "ID";
    public static final String NAME= "NAME";
    public static final String PHONE_NUMBERS = "PHONE_NUMBERS";
    public static final String NACIONALITY = "NACIONALITY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coincidence);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_detail));
        ActionBar mActionBar = getSupportActionBar();
        if(mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Intent data = getIntent();

        String id = data.getStringExtra(ID);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name)+": "+id);

        String name = data.getStringExtra(NAME);
        TextView nameText = (TextView) findViewById(R.id.coincidence_name_detail);
        nameText.setText(name);

        String nacionality = data.getStringExtra(NACIONALITY);
        TextView nacionalityText = (TextView) findViewById(R.id.coincidence_nacionality_detail);
        nacionalityText.setText(nacionality);

        String phoneNumbers = data.getStringExtra(PHONE_NUMBERS);
        TextView phoneNumbersText = (TextView) findViewById(R.id.coincidence_telephone_detail);

        String[] phone = phoneNumbers.split(",");
        String text=getResources().getString(R.string.phone_text)+" \n";
        for (String p: phone) {
            text += "\u2022 "+ p+ " \n";
        }
        phoneNumbersText.setText(text);


    }
}
