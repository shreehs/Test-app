package com.intplus.shoppingspace.helpers;

/**
 * Created by harshas on 12/17/2016.
 */
public class Shop {
    /*
    Example shop json.
    {
           "sid": 1,
           "title": "Amazon",
           "bookmark": true,
           "url": "www.amazon.in",
           "icon": "amazon"
     }
     */

    public int sid; // unique shop id.
    public String shopName; // shop display name (title).
    public Boolean bookmark;
    public String url;
    public String icon;

    public Shop(){
    }

    public Shop(int sid, String shopName, Boolean bookmark, String url, String icon){
        this.sid = sid;
        this.shopName = shopName;
        this.bookmark = bookmark;
        this.url = url;
        this.icon = icon;
    }
}
