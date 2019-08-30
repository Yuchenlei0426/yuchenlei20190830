package com.bawei.yuchenlei20190731.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDao {

    private final SQLiteDatabase readableDatabase;

    public  MyDao(Context context){
        MySqlite mySqlite = new MySqlite(context);
        readableDatabase = mySqlite.getReadableDatabase();
    }

    public void add(String json){
        ContentValues contentValues = new ContentValues();
        contentValues.put("json",json);
        readableDatabase.insert("date",null,contentValues);
    }
}
