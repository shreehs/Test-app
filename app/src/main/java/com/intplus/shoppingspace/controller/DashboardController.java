package com.intplus.shoppingspace.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.intplus.shoppingspace.commons.CommonUtils;
import com.intplus.shoppingspace.commons.JSONResourceReader;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.ShopDatabase;
import com.intplus.shoppingspace.utils.VersionControl;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by harshas on 12/18/2016.
 */
public class DashboardController extends AppController {
    private static final String APPLOG = "Shop";
    Activity activity;
    SharedPreferences settings = null;
    SharedPreferences mPreferences;
    public static ShopDatabase shopDatabase;
    public CommonUtils utils;

    public DashboardController(Activity activity) {
        super(activity);
        this.activity = activity;
        mPreferences = this.activity.getSharedPreferences("first_time", Context.MODE_PRIVATE);
    }

    public boolean isFirstRun() {
        Boolean isFirstRun = mPreferences.getBoolean("first_rime", true);
        if (isFirstRun) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean("first_rime", false);
            editor.commit();
        }
        return isFirstRun;
    }

    public void initialize() {
        /*
        Do all initializations for app first run on device.
            - read, parse json and update db.
         */
        shopDatabase = new ShopDatabase(this.activity);
        shopDatabase.open();
        JSONResourceReader jsonResourceReader = new JSONResourceReader(this.activity);
        int jsonFileResourceId = this.activity.getResources()
                .getIdentifier("shopping", "raw", this.activity.getPackageName());
        try {
                String  shopsJSON = jsonResourceReader.readJSONFile(jsonFileResourceId);
                utils=new CommonUtils();
                ArrayList<Shop> shopsList= utils.getShopJsonParseOfShop(shopsJSON);
                boolean shopsInsertedFlag=false;
                for(Shop shop:shopsList) {
                Log.d(APPLOG, "json shop icon : "+shop.icon);
                shopsInsertedFlag=shopDatabase.shopsDao.insertShop(shop);
                }
                if(shopsInsertedFlag==true) {
                    Log.d(APPLOG,"Shops are inserted into database during first run.");
                }
                else {
                    Log.d(APPLOG,"Shops are inserted");
                }
        }
        catch (UnsupportedEncodingException erUnEn) {
            Log.e(APPLOG, " json encoding Exception");
        }
        catch (IOException erIO) {
            Log.e(APPLOG, " json file read IO Exception");
        }
        catch (JSONException e) {
            Log.e(APPLOG, "json string is not valid");
        }

        // Log.d(APPLOG,"json string : \n"+ shopsJSON);
        // json parse and update db.


    }

    public VersionControl checkVersion() {
        VersionControl versionControl = new VersionControl();
        int newVersion = 0;
        try {
            PackageInfo pInfo = this.activity.getPackageManager().getPackageInfo(this.activity.getPackageName(), 0);
            newVersion = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int oldVersion = mPreferences.getInt("old_version", newVersion);
        if (oldVersion == newVersion) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt("old_version", newVersion);
            editor.commit();
        }
        else if (newVersion > oldVersion) {
            System.out.println("Handle app version upgrade");
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putInt("old_version", newVersion);
            editor.commit();
            versionControl.isVersionChange = true;
            versionControl.oldVersion = oldVersion;
            versionControl.newVersion = newVersion;
        }
        return versionControl;
    }

    public void handleVersionChange(VersionControl versionControl) {
        /*
        Check old and new version.
        Handle all the different old to current version upgrade.
         */

    }
}
