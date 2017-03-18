package com.intplus.shoppingspace.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.intplus.shoppingspace.commons.CommonUtils;
import com.intplus.shoppingspace.commons.JSONResourceReader;
import com.intplus.shoppingspace.model.Shop;
import com.intplus.shoppingspace.model.ShopDatabase;
import org.json.JSONException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by harshas on 12/18/2016.
 */
public class DashboardController extends AppController{
    private static final String APPLOG = "Shop";
    Activity activity;
    SharedPreferences settings=null;
    public static ShopDatabase shopDatabase;
    public CommonUtils utils;

    public DashboardController(Activity activity){
        super(activity);
        this.activity = activity;
    }

    public boolean isFirstRun()
    {
        Boolean firstTime=true;
        if (firstTime == true) {
            SharedPreferences mPreferences = this.activity.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            //int i=mPreferences.getInt("firstrun",0);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
                //i=1;
            }
        }
        return firstTime;
    }

    public void initialize(){
        /*
        Do all initializations for app first run on device.
            - read, parse json and update db.
         */
        shopDatabase=new ShopDatabase(this.activity);
        shopDatabase.open();
        //String shopsJSON = "";
        JSONResourceReader jsonResourceReader = new JSONResourceReader(this.activity);
        int jsonFileResourceId = this.activity.getResources()
                        .getIdentifier("shopping", "raw", this.activity.getPackageName());
        try{
            String  shopsJSON = jsonResourceReader.readJSONFile(jsonFileResourceId);
            utils=new CommonUtils();
            ArrayList<Shop> shopsList= utils.getShopJsonParseOfShop(shopsJSON);
            //MainActivity activity=new MainActivity();
            if(isFirstRun()==true)
            {
                boolean shopsInsertedFlag=shopDatabase.shopsDao.insertShops(shopsList);
                if(shopsInsertedFlag==true)
                {
                    Log.d(APPLOG,"Shops are inserted into database during first run.");
                }
            }
            else
            {
                Log.e(APPLOG,"Shops are already inserted into database.");
            }

        }catch (UnsupportedEncodingException erUnEn){
            Log.e(APPLOG, " json encoding Exception");
        }catch (IOException erIO){
            Log.e(APPLOG," json file read IO Exception");
        }
        catch(JSONException e){
            Log.e(APPLOG,"json string is not valid");
        }
        finally {
            shopDatabase.close();
        }

       // Log.d(APPLOG,"json string : \n"+ shopsJSON);
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
