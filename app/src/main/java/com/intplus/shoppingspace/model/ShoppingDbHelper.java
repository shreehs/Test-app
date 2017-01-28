package com.intplus.shoppingspace.model;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by harshas on 12/25/2016.
 */
/*
Provides a handle to the database resource.
 */
public class ShoppingDbHelper{
    private static final String APPLOG = "Shop";
    Activity activity;

    public ShoppingDbHelper(Activity activity) {
        this.activity = activity;
    }

    // getters.
    public ArrayList<Shop> getAllShops(){
        ArrayList<Shop> allShops = new ArrayList<>();
        allShops.add(new Shop(1, "Amazon", Boolean.TRUE, "www.amazon.in", "amazon"));
        allShops.add(new Shop(2, "Flipkart", Boolean.TRUE, "www.flipkart.com", "flipkart"));
        allShops.add(new Shop(3, "Snaldeal", Boolean.FALSE, "www.snapdeal.com", "snapdeal"));
        allShops.add(new Shop(4, "Paytm", Boolean.TRUE, "www.paytm.com", "myntra"));
        return allShops;
    }
}
