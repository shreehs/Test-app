package com.intplus.shoppingspace.commons;

import android.app.Activity;
import android.content.Intent;

import com.intplus.shoppingspace.model.Shop;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by kuber on 5/3/17.
 */

public class CommonUtils {

    public ArrayList<Shop> getShopJsonParseOfShop(String jsonString) throws JSONException
    {
        Shop shop;
        JSONObject jsonObject=new JSONObject(jsonString);
        JSONArray shopArray=jsonObject.getJSONArray("shops");
        ArrayList<Shop> shopsList=new ArrayList<Shop>();
        for(int i=0;i<shopArray.length();i++)
        {
            JSONObject shopObject=shopArray.getJSONObject(i);
            int shopId=Integer.parseInt(shopObject.getString("sid").toString());
            String shopName=shopObject.getString("title").toString();
            boolean bookmark=Boolean.parseBoolean(shopObject.getString("bookmark").toString());
            String shopUrl=shopObject.getString("url").toString();
            String shopIcon=shopObject.getString("icon").toString();
            shop=new Shop();
            shop.setSid(shopId);
            shop.setShopName(shopName);
            shop.setBookmark(bookmark);
            shop.setUrl(shopUrl);
            shop.setIcon(shopIcon);
            shopsList.add(shop);
        }
        return shopsList;
    }

    public void shareMessage(Activity activity){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download Shopping Space!.\nA container for all your online shopping.\n" +
                "https://play.google.com/store/apps/details?id=com.intplus.shoppingspace&hl=en";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Shopping Space");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        activity.startActivity(Intent.createChooser(sharingIntent, "Share App via"));
    }
}
