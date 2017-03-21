package com.intplus.shoppingspace.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by harshas on 1/7/2017.
 */

/*
CRUD functionality is abstracted.
 */

/**
 * Created by kuber on 30/12/16.
 */

public abstract class DbContentProvider {
    public SQLiteDatabase mDb;

    public int delete(String tableName,String selection,String[] selectionArgs)
    {
        return mDb.delete(tableName,selection,selectionArgs);
    }

    public long insert(String tableName, ContentValues values)
    {
        return  mDb.insert(tableName,null,values);
    }

    public DbContentProvider(SQLiteDatabase db)
    {
        this.mDb=db;

    }

    protected  abstract  <T> T cursorToEntity(Cursor cursor);

    public Cursor query(String tableName,String[] columns,String selection,String[] selectionArgs,String sortOrder,String limit)
    {
        return mDb.query(tableName,columns,selection,selectionArgs,null,null,sortOrder,limit);
    }

    public Cursor query(String tableName,String[] columns,String selection,String[] selectionArgs,String sortOrder)
    {
        final  Cursor cursor=mDb.query(tableName,columns,selection,selectionArgs,null,null,sortOrder);
        return  cursor;
    }

    public Cursor query(String tableName, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit) {

        return mDb.query(tableName, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

    public int update(String tableName, ContentValues values,
                      String selection, String[] selectionArgs) {
        return mDb.update(tableName, values, selection,
                selectionArgs);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return mDb.rawQuery(sql, selectionArgs);
    }

    public void rawQuery(String sql) {
        mDb.execSQL(sql);
    }
}

