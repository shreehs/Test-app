package com.intplus.shoppingspace;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.intplus.shoppingspace.adapters.DashboardGridAdapter;
import com.intplus.shoppingspace.controller.AppController;
import com.intplus.shoppingspace.controller.DashboardController;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.AppPrefHelper;

import java.util.ArrayList;

import static com.intplus.shoppingspace.app.AppConstants.APPLOG;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
            DashboardGridAdapter.OnItemClickListner{

    private static final String TAG = "Shop";
    // Model helpers.
    AppPrefHelper appPrefHelper;
    // Controllers
    AppController appController;
    DashboardController dashboardController;
    // Others
    ArrayList<Shop> dashBoardShops;
    Button btnGotoWebView;
    Button btnTest;
    Button btnGrid;
    RecyclerView rGridview;
    GridLayoutManager gridLayoutManager;
    DashboardGridAdapter dashboardGridAdapter;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Goto all apps activity", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                appController.launchAllShopsActivity();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dashboardController = new DashboardController(this);
        appController = new AppController(this);
        // Check and handle first run case.
        if (dashboardController.isFirstRun()){
            Log.d(APPLOG, "It is first run, initializing.. ");
            dashboardController.initialize();
        }
        // Check and handle app upgrade. Change of version.
        if (dashboardController.isChangeOfVersion()){
            Log.d(APPLOG, "It is a change of version, initializing.. ");
            dashboardController.handleVersionChange();
        }

        // Launch the view stub.
        //  ViewStub stub = (ViewStub) findViewById(R.id.vs_dashboard);
        // stub.setLayoutResource(R.layout.content_dashboard);
        // View inflated = stub.inflate();

        // Grid initialization.
        rGridview = (RecyclerView) this.findViewById(R.id.grid_view_dashboard);

        // To improve the performance
        rGridview.setHasFixedSize(true);
        // use a linear layout manager
        gridLayoutManager = new GridLayoutManager(this, 3);
        rGridview.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);
        // Get list of dashboard shops to display.
        dashBoardShops = appController.getDashboardShops();
        // pass dash board items to GridView.
        dashboardGridAdapter = new DashboardGridAdapter(this, dashBoardShops);
        rGridview.setAdapter(dashboardGridAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_all_shops) {
            Intent allShopsIntent = new Intent(this, AllShopsActivity.class);
            startActivity(allShopsIntent);
            finish();

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {
            this.appController.launchAppOnPlayStore();
        } else if (id == R.id.nav_help) {
            this.appController.launchHelpActivity();
        } else if (id == R.id.nav_about) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListItemClick(int position) {
        int sid = this.dashBoardShops.get(position).sid;
        System.out.println("Shop Dashboard click SID : "+sid);
        // pass sid to web view activity.
        Intent webViewIntent = new Intent(this, ActivityWebView.class);
        webViewIntent.putExtra("sid", sid);
        startActivity(webViewIntent);
    }
}
