package com.intplus.shoppingspace.controller;

import android.app.Activity;
import android.util.Log;

import com.intplus.shoppingspace.commons.JSONResourceReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by harshas on 12/18/2016.
 */
public class MainActivityController {

    private static final String APPLOG = "Shop";
    Activity activity;

    public MainActivityController(Activity activity){
        this.activity = activity;
    }

    public Boolean isFirstRun(){
        Boolean isItFirstRun = Boolean.TRUE;
        return isItFirstRun;
    }

    public void initialize(){
        /*
        Do all initializations for app first run on device.
            - read, parse json and update db.
         */
        String shopsJSON = "";
        JSONResourceReader jsonResourceReader = new JSONResourceReader(this.activity);
        int jsonFileResourceId = this.activity.getResources()
                        .getIdentifier("shopping", "raw", this.activity.getPackageName());
        try{
        shopsJSON = jsonResourceReader.readJSONFile(jsonFileResourceId);
        }catch (UnsupportedEncodingException erUnEn){
            Log.e(APPLOG, " json encoding Exception");
        }catch (IOException erIO){
            Log.e(APPLOG," json file read IO Exception");
        }
        Log.d(APPLOG,"json string : \n"+ shopsJSON);
        // json parse and update db.
    }

    public Boolean isChangeOfVersion(){
        Boolean isVersionUpgrade = Boolean.FALSE;
        return isVersionUpgrade;
    }

    public void handleVersionChange(){
        /*
        Check old and new version.
        Handle all the different old to current version upgrade.
         */
    }
}
