package com.intplus.shoppingspace.model;

import java.util.ArrayList;

/**
 * Created by harshas on 1/7/2017.
 */

public interface IShopsDao {
    // Read
    public Shop fetchShopBySid(int sid);
    public ArrayList<Shop> fetchAllShops();
    // Create
    public boolean insertShop(Shop shop);
    public boolean insertShops(ArrayList<Shop> shops);
    // Delete
    public boolean deleteAllShops();
    public int deleteShop(int sid);
    //Update
    public int updateShop(int sid,Shop shop);
}
