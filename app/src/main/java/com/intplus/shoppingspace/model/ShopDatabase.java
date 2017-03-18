package com.intplus.shoppingspace.model;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kuber on 28/1/17.
 */

public class ShopDatabase {
    private static final String TAG = "ShopDatabase";
    private static final String DATABASE_NAME = "shop_database.db";
    private DatabaseHelper sDbHelper;
    // Increment DB Version on any schema change
    private static final int DATABASE_VERSION = 1;
    private final Activity sActivity;
    public static ShopsDao shopsDao;



    public ShopDatabase open() throws SQLException {
        sDbHelper = new DatabaseHelper(sActivity);
        SQLiteDatabase sDb = sDbHelper.getWritableDatabase();

        shopsDao = new ShopsDao(sDb);

        return this;
    }

    public void close() {
        sDbHelper.close();
    }

    public ShopDatabase(Activity activity) {
        this.sActivity = activity;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IShopSchema.SHOP_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(TAG, "Upgrading database from version "
                    + oldVersion + " to "
                    + newVersion + " which destroys all old data");

            db.execSQL("DROP TABLE IF EXISTS "
                    + IShopSchema.SHOP_TABLE);
            onCreate(db);

        }
    }
}
