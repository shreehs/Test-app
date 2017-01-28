package com.intplus.shoppingspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.intplus.shoppingspace.adapters.DashboardGridAdapter;
import com.intplus.shoppingspace.controller.AppController;
import com.intplus.shoppingspace.controller.MainActivityController;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.AppPrefHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Shop";
    // Model helpers.
    AppPrefHelper appPrefHelper;
    // Controllers
    AppController appController;
    MainActivityController mainActivityController;
    // Others
    ArrayList<Shop> dashBoardShops;
    Button btnGotoWebView;
    Button btnTest;
    Button btnGrid;
    RecyclerView rGridview;
    GridLayoutManager gridLayoutManager;
    DashboardGridAdapter dashboardGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityController = new MainActivityController(this);
        appController = new AppController(this);
        // Check and handle first run case.
        if (mainActivityController.isFirstRun()){
            mainActivityController.initialize();
        }
        // Check and handle app upgrade. Change of version.
        if (mainActivityController.isChangeOfVersion()){
            mainActivityController.handleVersionChange();
        }

        btnGotoWebView = (Button) this.findViewById(R.id.btn_web_view);
        btnGotoWebView.setOnClickListener(this);
        btnTest = (Button) this.findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);
        btnGrid = (Button) this.findViewById(R.id.btn_grid);
        btnGrid.setOnClickListener(this);

        // Grid initialization.
        rGridview = (RecyclerView) findViewById(R.id.grid_view_dashboard);

        // To improve the performance
        rGridview.setHasFixedSize(true);
        // use a linear layout manager
        gridLayoutManager = new GridLayoutManager(this, 3);
        rGridview.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get list of dashboard shops to display.
        dashBoardShops = appController.getDashboardShops();
        // pass dash board items to GridView.
        dashboardGridAdapter = new DashboardGridAdapter(this, dashBoardShops);
        rGridview.setAdapter(dashboardGridAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_web_view:
                Log.d(TAG, "goto web view activity");
                Intent webViewIntent = new Intent(this, ActivityWebView.class);
                startActivity(webViewIntent);
                break;
            case R.id.btn_test:
                Intent testIntent = new Intent(this, TestActivity.class);
                startActivity(testIntent);
                break;
            case R.id.btn_grid:
                Intent testGridIntent = new Intent(this, TestGridActivity.class);
                startActivity(testGridIntent);
        }
    }


    // Custom implementations
}
