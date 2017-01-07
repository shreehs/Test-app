package com.intplus.shoppingspace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intplus.shoppingspace.R;

/**
 * Created by harshas on 12/17/2016.
 * Ref for Heterogeneous items in list view : https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private String[] mDataSet;

    public TestAdapter(String[] dataSet) {
        this.mDataSet = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.test_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        TestViewHolder testViewHolder = new TestViewHolder(view);
        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.myTextView.setText(mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder{
        public TextView myTextView;
        public TestViewHolder(View tv){
            super(tv);
            myTextView = (TextView) tv.findViewById(R.id.test_info_text);
        }
    }
}
