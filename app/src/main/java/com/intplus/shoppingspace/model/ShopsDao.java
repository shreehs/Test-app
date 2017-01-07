package com.intplus.shoppingspace.model;

import java.util.ArrayList;

/**
 * Created by harshas on 1/7/2017.
 */

/*
Example from : http://wale.oyediran.me/2015/04/02/android-sqlite-dao-design/
 */
public class ShopsDao extends DbContentProvider implements IShopsDao,IShopSchema {

    @Override
    public Shop fetchShopBySid(int sid) {
        return null;
    }

    @Override
    public ArrayList<Shop> fetchAllShops() {
        return null;
    }

    @Override
    public boolean insertShop(Shop shop) {
        return false;
    }

    @Override
    public boolean insertShops(ArrayList<Shop> shops) {
        return false;
    }

    @Override
    public boolean deleteAllShops() {
        return false;
    }
}
