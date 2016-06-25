package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.usuario.api.pojo.ResponseList;
import com.example.usuario.sdsandroid.sds.R;
import com.example.usuario.sdsandroid.sds.common.CoincidenceAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Carlos Murillo on 18/05/2016.
 * Personal ASUS
 */
public class SearchDetailActivity extends AppCompatActivity {

    @Bind(R.id.card_list) public RecyclerView mRecyclerView;
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);

        //create the toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle(R.string.search_detail_title);
            mActionBar.setSubtitle(R.string.search_detail_subtitle);
        }

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        ResponseList responseList = intent.getParcelableExtra("XML_RESPONSE");

        CoincidenceAdapter coincidenceAdapter = new CoincidenceAdapter(responseList.getResponses());
        mRecyclerView.setAdapter(coincidenceAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
