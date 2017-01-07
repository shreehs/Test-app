package com.intplus.shoppingspace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intplus.shoppingspace.R;

/**
 * Created by harshas on 12/18/2016.
 * Eg : https://inducesmile.com/android/android-gridlayoutmanager-with-recyclerview-in-material-design/
 */
public class TestGridAdapter extends RecyclerView.Adapter<TestGridAdapter.GridItemHolder>{

    private String[] mDataSet;

    public TestGridAdapter(String[] mDataSet){
        this.mDataSet = mDataSet;
    }

    @Override
    public GridItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.test_grid_item, parent, false);
        GridItemHolder gridItemHolder = new GridItemHolder(view);
        return gridItemHolder;
    }

    @Override
    public void onBindViewHolder(GridItemHolder holder, int position) {
        holder.gridItemTv.setText(this.mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.length;
    }

    public class GridItemHolder extends RecyclerView.ViewHolder {
        TextView gridItemTv;
        public GridItemHolder(View itemView) {
            super(itemView);
            gridItemTv = (TextView) itemView.findViewById(R.id.test_grid_card_tv);
        }
    }
}
