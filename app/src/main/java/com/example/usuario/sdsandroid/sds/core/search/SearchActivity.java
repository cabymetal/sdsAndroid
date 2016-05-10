package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Intent;
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

    private ActionBar mActionBar;



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
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(false);
            mActionBar.setTitle(R.string.app_name);
            mActionBar.setSubtitle(R.string.tool_name_search);
        }
        //read the intent
        Intent intent = getIntent();
        Drawer result = createMenu(intent);
        //result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        //once the menu is built create the initial Fragment
        SearchFragment searchFragment = new SearchFragment();
        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        // firstFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_element, searchFragment).commit();
    }

    private Drawer createMenu(Intent intent){
        user = intent.getStringExtra("user");
        password = intent.getStringExtra("pwd");

        // create the account header
        AccountHeader  accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .addProfiles( new ProfileDrawerItem()
                        .withName(user)
                        .withEmail(password).withIcon(getResources().getDrawable(R.drawable.sdslogo))
                        )
                .withHeaderBackground(R.drawable.bg_1)
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
            permits[i] = (SecondaryDrawerItem) new SecondaryDrawerItem().withName(permit)
            .withIdentifier(i);
            i++;
        }

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_title_home)
                .withSelectable(false);
        Drawer drawer = new DrawerBuilder().withActivity(this)
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
                        long identifier = drawerItem.getIdentifier();
                        if(identifier==0){
                            mActionBar.setSubtitle(R.string.tool_name_search);
                            SearchFragment searchFragment =  new SearchFragment();
                            changeFragment(searchFragment);
                        }else if(identifier==1){
                            mActionBar.setSubtitle(R.string.tool_name_proc);
                            ProcFragment procFragment = new ProcFragment();
                            changeFragment(procFragment);
                        }else if(identifier==2){
                            mActionBar.setSubtitle(R.string.tool_name_fisc);
                            FiscFragment fiscFragment = new FiscFragment();
                            changeFragment(fiscFragment);
                        }
                        return false; //so the menu closes when the users selects one selection
                    }
                })
                .build();
        return drawer;
    }

    private void changeFragment(android.support.v4.app.Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_element, fragment).commit();
    }



}
