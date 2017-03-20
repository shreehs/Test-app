package com.intplus.shoppingspace.model;

/**
 * Created by harshas on 1/7/2017.
 */

public interface IShopSchema {
    String SHOP_TABLE = "SHOP_DETAILS";
    String COLUMN_SHOP_ID = "SHOP_ID";
    String COLUMN_SHOP_NAME = "SHOP_NAME";
    String COLUMN_SHOP_BOOKMARK = "SHOP_BOOKMARK";
    String COLUMN_SHOP_URL = "SHOP_URL";
    String COLUMN_SHOP_ICON = "SHOP_ICON";

    String SHOP_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + SHOP_TABLE
            + " ("
            + COLUMN_SHOP_ID
            + " INTEGER PRIMARY KEY, "
            + COLUMN_SHOP_NAME
            + " TEXT NOT NULL, "
            + COLUMN_SHOP_BOOKMARK
            + " INTEGER NOT NULL, "
            +COLUMN_SHOP_URL
            +" TEXT, "
            +COLUMN_SHOP_ICON
            +" TEXT "
            + "  ) ";

    String[] SHOP_COLUMNS = new String[] { COLUMN_SHOP_ID,
            COLUMN_SHOP_NAME, COLUMN_SHOP_BOOKMARK,COLUMN_SHOP_URL,COLUMN_SHOP_ICON};
}
