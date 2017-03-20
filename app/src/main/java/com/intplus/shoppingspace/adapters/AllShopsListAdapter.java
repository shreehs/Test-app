package com.intplus.shoppingspace.adapters;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intplus.shoppingspace.R;
import com.intplus.shoppingspace.app.AppConstants;
import com.intplus.shoppingspace.model.Shop;

import java.util.ArrayList;

import static com.intplus.shoppingspace.app.AppConstants.APPLOG;

/**
 * Created by harshas on 1/1/2017.
 */

public class AllShopsListAdapter extends RecyclerView.Adapter<AllShopsListAdapter.ShopListViewHolder> {

    private Activity activity;
    private ArrayList<Shop> allShops = new ArrayList<>();
    private OnItemClickListner onItemClickListner;
    private int lightGrey;
    private int green;

    public AllShopsListAdapter(Activity activity, ArrayList<Shop> shops){
        this.activity = activity;
        this.allShops = shops;
        this.onItemClickListner = (OnItemClickListner) this.activity;
        lightGrey = ContextCompat.getColor(this.activity, R.color.lightGrey);
        green = ContextCompat.getColor(this.activity, R.color.colorAccent);
        System.out.println("Total Shops : " + this.allShops.size());
    }

    @Override
    public ShopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grid_item_shops_list,parent, false);
        ShopListViewHolder shopListViewHolder = new ShopListViewHolder(view);
        return shopListViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopListViewHolder holder, int position) {
        String shop_icon_name = this.allShops.get(position).icon;
        String shop_name = this.allShops.get(position).shopName;
        holder.shopIcon.setImageResource(this.activity.getResources()
                .getIdentifier(shop_icon_name, "drawable", AppConstants.PACKAGE));
        holder.shopText.setText(shop_name);
        int bookmarkColor = lightGrey;
        if (this.allShops.get(position).bookmark){
            bookmarkColor = green;
        }
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{bookmarkColor});
        holder.shopBookmark.setSupportBackgroundTintList(csl);
    }

    @Override
    public int getItemCount() {
        return this.allShops.size();
    }

    public class ShopListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AppCompatImageView shopIcon;
        AppCompatTextView shopText;
        AppCompatImageView shopBookmark;
        public ShopListViewHolder(View itemView) {
            super(itemView);
            CardView shoppingListCV = (CardView) itemView.findViewById(R.id.cv_shopping_list_item);
            shopIcon = (AppCompatImageView) itemView.findViewById(R.id.iv_shopping_list_item);
            shopText = (AppCompatTextView) itemView.findViewById(R.id.tv_shopping_list_item);
            shopBookmark = (AppCompatImageView) itemView.findViewById(R.id.iv_star_shopping_list_item);
            // Set on click listners.
            shoppingListCV.setOnClickListener(this);
            shopBookmark.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()){
                case R.id.iv_star_shopping_list_item:
                    Log.d(APPLOG, "Bookmark toggle");
                    Boolean newBookmarkState = !(allShops.get(position).bookmark);
                    toggleBookmark(position, newBookmarkState);
                    allShops.get(position).bookmark = newBookmarkState;
                    // Show on the view
                    int newBookmarkColor = lightGrey;
                    if (newBookmarkState){
                        newBookmarkColor = green;
                    }
                    ColorStateList newColorStateList = new ColorStateList(new int[][]{new int[0]}, new int[]{newBookmarkColor});
                    this.shopBookmark.setSupportBackgroundTintList(newColorStateList);
                    // Call the callback implementation to do more actions.
                    onItemClickListner.onBookmarkToggle(position,newBookmarkState);
                    break;
                case R.id.cv_shopping_list_item:
                    onItemClickListner.onListItemClick(position);
                    Log.d(APPLOG, "Card View clicked "+ String.valueOf(getAdapterPosition()));
                    break;
            }
        }
    }

    private void toggleBookmark(int position, boolean newBookmarkState){
    }

    public interface OnItemClickListner{
        public void onListItemClick(int position);
        public void onBookmarkToggle(int position, boolean currentState);
    }
}
