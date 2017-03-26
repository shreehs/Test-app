package com.intplus.shoppingspace.controller;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.intplus.shoppingspace.AboutActivity;
import com.intplus.shoppingspace.AllShopsActivity;
import com.intplus.shoppingspace.DashboardActivity;
import com.intplus.shoppingspace.HelpActivity;
import com.intplus.shoppingspace.OptionsActivity;
import com.intplus.shoppingspace.app.AppConstants;
import com.intplus.shoppingspace.commons.CommonUtils;
import com.intplus.shoppingspace.commons.PlayStore;
import com.intplus.shoppingspace.model.AppPrefHelper;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.ShopDatabase;

import java.util.ArrayList;

/**
 * Created by harshas on 12/18/2016.
 */
public class AppController {

    private Activity activity;
    public static ShopDatabase shopDatabase;
    public static AppPrefHelper appPrefHelper;
    private static final String APPLOG = "Shop";
    private CommonUtils commonUtils;

    public AppController(Activity activity) {
        this.activity = activity;
        commonUtils = new CommonUtils();
        shopDatabase = new ShopDatabase(this.activity);
        shopDatabase.open();
        appPrefHelper = new AppPrefHelper(this.activity);
    }

    public ArrayList<Shop> getAllShops() {
        ArrayList<Shop> allShops = shopDatabase.shopsDao.fetchAllShops();
        return allShops;
    }

    public ArrayList<Shop> getDashboardShops() {
        ArrayList<Shop> dashboardItems = new ArrayList<>();
        ArrayList<Shop> allShops = this.getAllShops();
        for (int i = 0; i < allShops.size(); i++) {
            //Log.d(APPLOG,"shop name ="+allShops.get(i).getShopName());
            //Log.d(APPLOG,"package ="+allShops.get(i).getPackage());
            // Check if it is bookmarked shop. Add to dashboard list.
            if (allShops.get(i).getBookmark()) {
                dashboardItems.add(allShops.get(i));
            }
        }
        // Or use filtering query to read selected from db. (check)
        return dashboardItems;
    }

    public Shop getShopById(int sid) {
        Shop currentShop = null;
        try {
            shopDatabase = new ShopDatabase(this.activity);
            currentShop = shopDatabase.shopsDao.fetchShopBySid(sid);
        } catch (Exception e) {
            Log.e(APPLOG, "Shop with given id:" + sid + "is not found");
        } finally {
            Log.e(APPLOG, "Db is closed");
        }
        return currentShop;
    }

    public Boolean getAppDelegateStatus(){
        return appPrefHelper.getBoolean(AppPrefHelper.SK_DELEGATE_TO_NATIVE,
                                            AppPrefHelper.SV_DELEGATE_TO_NATIVE_DEFAULT);
    }
    public void setAppDelegateStatus(Boolean newStatus){
        appPrefHelper.putBoolean(AppPrefHelper.SK_DELEGATE_TO_NATIVE, newStatus);
    }
    public Boolean getRememberCredStatus(){
        return appPrefHelper.getBoolean(AppPrefHelper.SK_REMEMBER_SHOP_CRED,
                AppPrefHelper.SV_REMEMBER_SHOP_CRED_DEFAULT);
    }
    public void setRememberCredStatus(Boolean newStatus){
        appPrefHelper.putBoolean(AppPrefHelper.SK_REMEMBER_SHOP_CRED, newStatus);
    }

    // Share app using other apps
    public void shareApp(){
        this.commonUtils.shareMessage(this.activity);
    }

    // new screen launchers.
    public void launchAppOnPlayStore() {
        PlayStore playStore = new PlayStore();
        playStore.gotoPlayStore(AppConstants.PACKAGE, this.activity);
    }

    public void launchDashboardActivity() {
        Intent dashIntent = new Intent(this.activity, DashboardActivity.class);
        this.activity.startActivity(dashIntent);
    }

    public void launchHelpActivity() {
        Intent helpIntent = new Intent(this.activity, HelpActivity.class);
        this.activity.startActivity(helpIntent);
    }

    public void launchAboutActivity() {
        Intent aboutIntent = new Intent(this.activity, AboutActivity.class);
        this.activity.startActivity(aboutIntent);
    }

    public void launchAllShopsActivity() {
        Intent helpIntent = new Intent(this.activity, AllShopsActivity.class);
        this.activity.startActivity(helpIntent);
    }

    public void launchOptionsActivity() {
        Intent optionsIntent = new Intent(this.activity, OptionsActivity.class);
        this.activity.startActivity(optionsIntent);
    }
}