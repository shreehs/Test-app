package com.intplus.shoppingspace.controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.intplus.shoppingspace.AllShopsActivity;
import com.intplus.shoppingspace.HelpActivity;
import com.intplus.shoppingspace.app.AppConstants;
import com.intplus.shoppingspace.commons.PlayStore;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.ShopDatabase;
import com.intplus.shoppingspace.model.ShoppingDbHelper;

import java.util.ArrayList;

/**
 * Created by harshas on 12/18/2016.
 */
public class AppController {

    private Activity activity;
    private ShoppingDbHelper shoppingDbHelper;
    public static ShopDatabase shopDatabase;
    private static final String APPLOG = "Shop";
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
        // Default shop is amazon.
       /* Shop currentShop = new Shop(0, "Amazon", Boolean.TRUE, "http://www.amazon.in", "amazon");
        // tmp hardcode
        switch (sid) {
            case 0 :
                currentShop = new Shop(0, "Amazon", Boolean.TRUE, "http://www.amazon.in", "amazon");
                break;
            case 1 :
                currentShop = new Shop(1, "Flipkart", Boolean.TRUE, "https://www.flipkart.com", "flipkart");
                break;
            case 2 :
                currentShop = new Shop(2, "Snaldeal", Boolean.FALSE, "https://www.snapdeal.com", "snapdeal");
                break;
            case 3 :
                currentShop = new Shop(3, "Paytm", Boolean.TRUE, "https://www.paytm.com", "myntra");
                break;
        }*/
       Shop currentShop=null;
try
{
    shopDatabase=new ShopDatabase(this.activity);
    shopDatabase.open();
   currentShop=shopDatabase.shopsDao.fetchShopBySid(sid);
    Log.d("APPsds",currentShop.getShopName());
}
catch (Exception e)
{
Log.e(APPLOG,"Shop with given id:"+sid+"is not found");
}
  finally {
    shopDatabase.close();
    Log.e(APPLOG,"Db is closed");
  }
        return currentShop;
    }
    public void launchAppOnPlayStore(){
        PlayStore playStore = new PlayStore();
        playStore.gotoPlayStore(AppConstants.PACKAGE, this.activity);
    }
    public void launchHelpActivity(){
        Intent helpIntent = new Intent(this.activity, HelpActivity.class);
        this.activity.startActivity(helpIntent);
    }
    public void launchAllShopsActivity(){
        Intent helpIntent = new Intent(this.activity, AllShopsActivity.class);
        this.activity.startActivity(helpIntent);
    }
}