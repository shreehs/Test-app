package com.intplus.shoppingspace.model;

/**
 * Created by harshas on 1/7/2017.
 */

public interface IShopSchema {
    String SHOP_TABLE = "SHOP_DETAILS";
    String COLUMN_S_ID = "S_ID";
    String COLUMN_SHOP_NAME = "SHOP_NAME";
    String COLUMN_BOOKMARK = "BOOKMARK";
    String COLUMN_URL = "URL";
    String COLUMN_IMG_NAME = "IMG_NAME";
    String SHOP_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + SHOP_TABLE
            + " ("
            + COLUMN_S_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_SHOP_NAME
            + " TEXT NOT NULL, "
            + COLUMN_BOOKMARK
            + " TEXT, "
            +COLUMN_URL
            +"TEXT, "
            +COLUMN_IMG_NAME
            +"TEXT"
            + ")";

    String[] SHOP_COLUMNS = new String[] { COLUMN_S_ID,
            COLUMN_SHOP_NAME, COLUMN_BOOKMARK,COLUMN_URL,COLUMN_IMG_NAME};
}
