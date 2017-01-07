package com.intplus.shoppingspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.intplus.shoppingspace.adapters.TestAdapter;
import com.intplus.shoppingspace.adapters.TestGridAdapter;

public class TestGridActivity extends AppCompatActivity {

    private TestGridAdapter testGridAdapter;
    private GridLayoutManager gridLayoutManager;
    private String[] myDataset = {"String 1", "String 2", "String 3", "String 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grid);

        RecyclerView rGridview = (RecyclerView) findViewById(R.id.test_grid_view);

        // To improve the performance
        rGridview.setHasFixedSize(true);
        // use a linear layout manager
        gridLayoutManager = new GridLayoutManager(this, 3);
        rGridview.setLayoutManager(gridLayoutManager);
        // specify an adapter
        testGridAdapter = new TestGridAdapter(myDataset);
        rGridview.setAdapter(testGridAdapter);
    }
}
