package com.intplus.shoppingspace.controller;

import android.app.Activity;

import com.intplus.shoppingspace.helpers.Shop;
import com.intplus.shoppingspace.model.ShoppingDbHelper;

import java.util.ArrayList;

/**
 * Created by harshas on 12/18/2016.
 */
public class AppController {

    private Activity activity;
    private ShoppingDbHelper shoppingDbHelper;

    public AppController(Activity activity){
        this.activity = activity;
        this.shoppingDbHelper = new ShoppingDbHelper(this.activity);
    }

    public ArrayList<Shop> getDashboardShops(){
        ArrayList<Shop> dashboardItems = new ArrayList<>();
        ArrayList<Shop> allShops = this.shoppingDbHelper.getAllShops();
        for(int i=0; i<allShops.size(); i++){
            dashboardItems.add(allShops.get(i));
        }
        // Or use filtering query to read selected from db. (check)
        return dashboardItems;
    }

    public Shop getShopById(int sid){
        /*
        Get the shop details from db.
        Create a Shop object and return.
         */
        Shop currentShop = new Shop();
        return currentShop;
    }
}
