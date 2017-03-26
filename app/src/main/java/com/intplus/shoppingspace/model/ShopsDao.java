package com.intplus.shoppingspace.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshas on 1/7/2017.
 */

/*
Example from : http://wale.oyediran.me/2015/04/02/android-sqlite-dao-design/
 */
public class ShopsDao extends DbContentProvider implements IShopsDao,IShopSchema {

    private Cursor cursor;
    private ContentValues initialValues;

    public ShopsDao(SQLiteDatabase db)
    {
        super(db);
    }

    //Fetch shop by sid.
    @Override
    public Shop fetchShopBySid(int sid) {
        final String selectionArgs[] = { String.valueOf(sid) };
        final String selection =IShopSchema.COLUMN_SHOP_ID+" = ?";
        Shop shop = new Shop();
        cursor = super.query(SHOP_TABLE, SHOP_COLUMNS, selection,
                selectionArgs, COLUMN_SHOP_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                shop = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return shop;
    }

    //Fetch all shop
    @Override
    public ArrayList<Shop> fetchAllShops() {

        ArrayList<Shop> shopList = new ArrayList<Shop>();
        cursor = super.query(SHOP_TABLE, SHOP_COLUMNS, null,
                null, COLUMN_SHOP_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Shop shop = cursorToEntity(cursor);
                shopList.add(shop);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return shopList;
    }

    //Insert shop into database
    @Override
    public boolean insertShop(Shop shop) {
        // set values
        setContentValue(shop);
        try {
            return super.insert(SHOP_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){

            return false;
        }
    }

    //Insert all shops to database
    @Override
    public boolean insertShops(ArrayList<Shop> shops) {

        for(int i=0;i<shops.size();i++)
        {
            setContentValue(shops.get(i));
        }

        try {
            return super.insert(SHOP_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            return false;
        }
    }

    //Delete shop.
     @Override
    public int deleteShop(int sid)
    {
        final String selectionArgs[] = { String.valueOf(sid) };
        final String selection =IShopSchema.COLUMN_SHOP_ID+" = ?";
        int deletedRows = super.delete(SHOP_TABLE, selection,selectionArgs);
        return deletedRows;
    }

    //Delete all shops.
    @Override
    public boolean deleteAllShops() {
        String sql="delete from "+ SHOP_TABLE;
        Cursor cursor=super.rawQuery(sql,null);
        int deletedRows=cursor.getCount();
        boolean flag;
        if(deletedRows>0)
        {
            flag=true;
        }
        else
        {
            flag=false;
        }
        return flag;
    }

    //Update shop.
    @Override
    public int updateShop(Shop shop, boolean bookmark)
    {
        int i=bookmark?1:0;
        System.out.println("i : "+i);
        //String tableName, ContentValues values,String selection, String[] selectionArgs
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_SHOP_BOOKMARK,i);
        String selectionArgs[] = {String.valueOf(shop.getSid())};
        String selection = IShopSchema.COLUMN_SHOP_ID+" = ?";
        int noOfRowsUpdated=super.update(SHOP_TABLE, contentValues, selection, selectionArgs);
        //super.rawQuery()
        return noOfRowsUpdated;

    }

    //Cursor object.
    @Override
    protected Shop cursorToEntity(Cursor cursor) {

        Shop shop = new Shop();

        int sidIndex;
        int shopNameIndex;
        int bookmarkIndex;
        int urlIndex;
        int iconIndex;
        int packageNameIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_SHOP_ID) != -1) {
                sidIndex = cursor.getColumnIndexOrThrow(COLUMN_SHOP_ID);
                shop.sid = cursor.getInt(sidIndex);
            }
            if (cursor.getColumnIndex(COLUMN_SHOP_NAME) != -1) {
                shopNameIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_SHOP_NAME);
                shop.shopName = cursor.getString(shopNameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_SHOP_BOOKMARK) != -1) {
                bookmarkIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_SHOP_BOOKMARK);
                shop.bookmark = cursor.getInt(bookmarkIndex)==1;
            }

            if (cursor.getColumnIndex(COLUMN_SHOP_URL) != -1) {
                urlIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_SHOP_URL);
                shop.url = cursor.getString(urlIndex);
            }
            if (cursor.getColumnIndex(COLUMN_SHOP_ICON) != -1) {
                iconIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_SHOP_ICON);
                shop.icon = cursor.getString(iconIndex);
            }

            if (cursor.getColumnIndex(COLUMN_SHOP_PACKAGE) != -1) {
                packageNameIndex = cursor.getColumnIndexOrThrow(
                        COLUMN_SHOP_PACKAGE);
                shop.packageName = cursor.getString(packageNameIndex);
            }
        }
        return shop;
    }

    //Setting all values to shop object.
    private void setContentValue(Shop shop) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_SHOP_ID, shop.sid);
        initialValues.put(COLUMN_SHOP_NAME, shop.shopName);
        initialValues.put(COLUMN_SHOP_BOOKMARK,shop.bookmark);
        initialValues.put(COLUMN_SHOP_URL, shop.url);
        initialValues.put(COLUMN_SHOP_ICON, shop.icon);
        initialValues.put(COLUMN_SHOP_PACKAGE, shop.getPackage());
    }

    //Returning content values.
    private ContentValues getContentValue() {
        return initialValues;
    }

}
