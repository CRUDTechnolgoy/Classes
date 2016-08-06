package com.crud.myfirstapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "demo";
    public static final String TABLE_USERTABLE = " student_data";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CITY = "city";
    //User Table
   /* public static final String TABLE_USERTABLE = "users";
    public static final String KEY_USERID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";*/

    public DataManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERTABLE = "create table " + TABLE_USERTABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " text ,"
                + KEY_PASSWORD + " text ," +
                KEY_CITY + " text " +
                ")";
        Log.e("table", CREATE_USERTABLE);

        db.execSQL(CREATE_USERTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int paramInt1, int paramInt2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERTABLE);
        onCreate(db);
    }


}
