package com.intplus.shoppingspace.commons;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by harshas on 1/14/2017.
 */

public class PlayStore {
    public PlayStore(){

    }

    public void gotoPlayStore(String pckg,Activity activity){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + pckg));
        try{
            activity.startActivity(intent);
        }
        catch(Exception e){
            // if play store is not present, open in browser.
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+pckg));
        }
    }
}

