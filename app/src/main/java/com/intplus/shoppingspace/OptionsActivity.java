package com.intplus.shoppingspace;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.intplus.shoppingspace.adapters.OptionsListAdapter;
import com.intplus.shoppingspace.commons.PlayStore;
import com.intplus.shoppingspace.controller.AppController;
import com.intplus.shoppingspace.model.options.Options;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity implements OptionsListAdapter.OnItemClickListner,
        NavigationView.OnNavigationItemSelectedListener{

    ArrayList<Options> optionsArrayList;
    OptionsListAdapter optionsListAdapter;
    NavigationView navigationView;
    RecyclerView rvOptions;
    LinearLayoutManager linearLayoutManager;
    AppController appController;
    PlayStore playStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.getMenu().getItem(1).setChecked(true); // First item belongs to current activity.
        navigationView.setNavigationItemSelectedListener(this);

        playStore = new PlayStore();
        appController = new AppController(this);
        optionsArrayList = Options.getAllOptions();

        // Get the saved status for options.
        Boolean delegateStatus = this.appController.getAppDelegateStatus();
        optionsArrayList.get(0).setCbEnable(delegateStatus);
        Boolean rememberCredStatus = this.appController.getRememberCredStatus();
        optionsArrayList.get(1).setCbEnable(rememberCredStatus);

        rvOptions = (RecyclerView) this.findViewById(R.id.rv_options);
        // To improve the performance
        rvOptions.setHasFixedSize(true);
        // use a linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        rvOptions.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_settings);
        optionsListAdapter = new OptionsListAdapter(this, optionsArrayList);
        rvOptions.setAdapter(optionsListAdapter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            this.appController.launchDashboardActivity();
            this.finish();
        } else if (id == R.id.nav_all_shops) {
            appController.launchAllShopsActivity();
            this.finish();
        } else if (id == R.id.nav_settings) {
        } else if (id == R.id.nav_share) {
            this.appController.shareApp();
        } else if (id == R.id.nav_help) {
            this.appController.launchHelpActivity();
        } else if (id == R.id.nav_about) {
            this.appController.launchAboutActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOptLoveApp(int optId) {
        System.out.println("App love "+optId);
        switch (optId) {
            case 3: // Rate app
                playStore.gotoPlayStore("com.intplus.shoppingspace", this);
                Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
                break;
            case 4: // Facebook like.
                System.out.println("Facebook like.");
                break;
        }
    }

    @Override
    public void onOptEnableFeature(int optId, boolean featureState) {
        switch (optId) {
            case 1: // Delegate option
                this.appController.setAppDelegateStatus(featureState);
                break;
            case 2: // Remember cred status
                this.appController.setRememberCredStatus(featureState);
                break;
        }
    }
}
