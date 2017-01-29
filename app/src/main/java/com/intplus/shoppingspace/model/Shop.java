package com.intplus.shoppingspace.model;

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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Shop(int sid, String shopName, Boolean bookmark, String url, String icon){
        this.sid = sid;
        this.shopName = shopName;
        this.bookmark = bookmark;
        this.url = url;
        this.icon = icon;
    }
}
