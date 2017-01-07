package com.intplus.shoppingspace;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.intplus.shoppingspace.adapters.TestAdapter;

public class TestActivity extends AppCompatActivity {

    private RecyclerView tRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TestAdapter testAdapter;
    private String[] myDataset = {"String 1", "String 2", "String 3", "String 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tRecyclerView = (RecyclerView) this.findViewById(R.id.test_recycler_view);
        // To improve the performance
        tRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        tRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter
        testAdapter = new TestAdapter(this.myDataset);
        tRecyclerView.setAdapter(testAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
