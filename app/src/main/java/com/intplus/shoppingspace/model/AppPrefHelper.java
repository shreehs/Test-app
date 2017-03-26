package com.intplus.shoppingspace.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by harshas on 12/18/2016.
 */
public class AppPrefHelper extends PrefHelper{
    /*
    last app version, first run,
     */
    public static final String SK_APP_FIRST_RUN = "first_run";
    public static final String SK_DELEGATE_TO_NATIVE = "delegate_to_native";
    public static final String SK_REMEMBER_SHOP_CRED = "remember_shop_credentials";

    public static final Boolean SV_DELEGATE_TO_NATIVE_DEFAULT = false;
    public static final Boolean SV_REMEMBER_SHOP_CRED_DEFAULT = true;
    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    public AppPrefHelper(Activity activity) {
        mPreferences = activity.getSharedPreferences("app_pref", Context.MODE_PRIVATE);
        editor = mPreferences.edit();
    }

    public void putBoolean(String key, Boolean value){
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return this.mPreferences.getBoolean(key, defaultValue);
    }

}
