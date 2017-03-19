package com.intplus.shoppingspace.controller;

import android.app.Activity;
import android.content.SharedPreferences;

import com.intplus.shoppingspace.commons.CommonUtils;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.ShopDatabase;

/**
 * Created by harshas on 3/19/2017.
 */

public class AllShopsController extends AppController{
    private static final String APPLOG = "Shop";
    Activity activity;
    SharedPreferences settings=null;
    public static ShopDatabase shopDatabase;
    public CommonUtils utils;

    public AllShopsController(Activity activity){
        super(activity);
        this.activity = activity;
    }

    /*
     Save the new bookmark status in db.
     */
    public void updateBookmarkStatus(Shop shop, boolean bookmarkStatus){

    }
}
