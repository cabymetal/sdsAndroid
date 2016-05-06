package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.usuario.sdsandroid.sds.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.ButterKnife;


/**
 * Created by Carlos Murillo on 04/05/2016.
 * Personal ASUS
 */
public class SearchActivity extends AppCompatActivity {
    private Toolbar mToolbar;


    //TODO read this from the session intent
    private String[] PERMISSIONS = new String[]{"List", "Search", "Proc"};
    private String[] icons = new String[]{"", "", ""};

    private String user;
    private String password;


    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_menu_sds);
        ButterKnife.bind(this);
        //proba ahi no se
        //set the text of the
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
        //read from intent
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        password = intent.getStringExtra("pwd");

        // create the account header
        AccountHeader  accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .addProfiles( new ProfileDrawerItem()
                        .withName(user)
                        .withTextColor(Color.BLACK)
                        .withEmail(password).withIcon(getResources().getDrawable(R.drawable.sdslogo))
                        .withTextColor(Color.BLACK))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        // TODO: 05/05/2016 - READ THE PERMISSIONS FROM DE THE SESSION DATA OR INTENT
        SecondaryDrawerItem[] permits = new SecondaryDrawerItem[3];
        int i= 0;
        for(String permit : PERMISSIONS){
            permits[i++] = (SecondaryDrawerItem) new SecondaryDrawerItem().withName(permit);
        }

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_title_home);
        new DrawerBuilder().withActivity(this)
                .withAccountHeader(accountHeader)
                .withTranslucentStatusBar(true)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(item1,
                        new DividerDrawerItem())
                .addDrawerItems(permits)

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return false;
                    }
                })
                .build();
    }

}
