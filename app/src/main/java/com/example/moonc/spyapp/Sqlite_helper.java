package com.example.moonc.spyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moonc on 11/14/2017.
 */

public class Sqlite_helper extends SQLiteOpenHelper {
    private static String TABLE_NAME = "victims";
    private static String DB_NAME = "VictimList.db";
    private static String COL_1 = "_id";
    private static String COL_2 = "name";

    public Sqlite_helper(Context context) {
        super(context, DB_NAME, null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists "+ TABLE_NAME + " ( "  + COL_2 + " text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean add(String name)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        long insert = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(insert<0)
            return false;

        return true;
    }


    public Cursor show()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        return cursor;
    }
}
