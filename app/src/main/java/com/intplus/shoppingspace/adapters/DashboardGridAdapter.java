package com.intplus.shoppingspace.adapters;

import android.app.Activity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intplus.shoppingspace.R;
import com.intplus.shoppingspace.app.AppConstants;
import com.intplus.shoppingspace.model.Shop;

import java.util.ArrayList;

/**
 * Created by harshas on 12/29/2016.
 */
public class DashboardGridAdapter extends RecyclerView.Adapter<DashboardGridAdapter.GridItemViewHolder>{

    private Activity activity;
    private ArrayList<Shop> dashBoardShops = new ArrayList<>();

    public DashboardGridAdapter(Activity activity, ArrayList<Shop> shops){
        this.activity = activity;
        this.dashBoardShops = shops;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grid_item_dashboard, parent, false);
        GridItemViewHolder gridItemViewHolder = new GridItemViewHolder(view);
        return gridItemViewHolder;
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        String shop_icon_name = this.dashBoardShops.get(position).icon;
        String shop_name = this.dashBoardShops.get(position).shopName;
        holder.shopIcon.setImageResource(this.activity.getResources()
                            .getIdentifier(shop_icon_name, "drawable", AppConstants.PACKAGE));
        holder.shopText.setText(shop_name);
    }

    @Override
    public int getItemCount() {
        return this.dashBoardShops.size();
    }

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView shopIcon;
        AppCompatTextView shopText;
        public GridItemViewHolder(View itemView) {
            super(itemView);
            shopIcon = (AppCompatImageView) itemView.findViewById(R.id.grid_shop_card_image);
            shopText = (AppCompatTextView) itemView.findViewById(R.id.grid_shop_card_name);
        }
    }
}
