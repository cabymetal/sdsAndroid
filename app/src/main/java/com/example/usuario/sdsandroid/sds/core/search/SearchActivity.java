package com.example.usuario.sdsandroid.sds.core.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.usuario.sdsandroid.sds.R;
import com.example.usuario.sdsandroid.sds.core.scanner.ScannerFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by Carlos Murillo on 04/05/2016.
 * Personal ASUS
 */
public class SearchActivity extends AppCompatActivity {
    //TODO read this from the session intent
    private String[] PERMISSIONS = new String[]{"List", "Search", "Proc", "Scan cc"};
    private String[] icons = new String[]{"", "", ""};

    private String user;
    private String password;

    public final String KEY_USER = "USER";
    public final String KEY_PWD = "PASSWORD";

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_menu_sds);
        ButterKnife.bind(this);

        //set the text of the
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_core);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            //mActionBar.setHomeButtonEnabled(false);
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setTitle(R.string.app_name);
            mActionBar.setSubtitle(R.string.tool_name_search);
        }
        //read the intent
        Intent intent = getIntent();
        Drawer result = createMenu(intent, mToolbar);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


        //once the menu is built create the initial Fragment
        SearchFragment searchFragment = SearchFragment.newInstance(user, password);

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        //firstFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_element, searchFragment).commit();
    }


    public void onSaveInstanceState(Bundle outState){
        outState.putString(KEY_USER, user);
        outState.putString(KEY_PWD, password);
        super.onSaveInstanceState(outState);
    }

    private Drawer createMenu(Intent intent, Toolbar toolbar){
        user = intent.getStringExtra("user");
        password = intent.getStringExtra("email");

        // create the account header
        AccountHeader  accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.bg_1)
                .addProfiles( new ProfileDrawerItem()
                    .withName(user)
                    .withEmail(password).withIcon(getResources().getDrawable(R.drawable.sdslogo))
                )
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                .build();

        // TODO: 05/05/2016 - READ THE PERMISSIONS FROM DE THE SESSION DATA OR INTENT
        SecondaryDrawerItem[] permits = new SecondaryDrawerItem[4];
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
                .withToolbar(toolbar)
                .addDrawerItems(item1,
                        new DividerDrawerItem())
                .addDrawerItems(permits)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    // do something with the clicked item :D
                    long identifier = drawerItem.getIdentifier();
                    ActionBar actionBar = getSupportActionBar();
                    if(identifier==0){
                        if(actionBar != null) {
                            actionBar.setSubtitle(R.string.tool_name_search);
                            SearchFragment searchFragment = SearchFragment.newInstance(user, password);
                            changeFragment(searchFragment);
                        }
                    }else if(identifier==1){
                        if(actionBar != null) {
                            actionBar.setSubtitle(R.string.tool_name_proc);
                            ProcFragment procFragment = new ProcFragment();
                            changeFragment(procFragment);
                        }
                    }else if(identifier==2){
                        if(actionBar != null) {
                            actionBar.setSubtitle(R.string.tool_name_fisc);
                            FiscFragment fiscFragment = new FiscFragment();
                            changeFragment(fiscFragment);
                        }
                    }else if(identifier==3){
                        if(actionBar != null) {
                            actionBar.setSubtitle(R.string.tool_name_fisc);
                            ScannerFragment scannerFragment = new ScannerFragment();
                            changeFragment(scannerFragment);
                        }
                    }
                    return false; //so the menu closes when the users selects one selection
                })
                .build();
        return drawer;
    }

    private void changeFragment(android.support.v4.app.Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_element, fragment).commit();
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
