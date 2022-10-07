package com.myapp.mylibrary.DB;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.boitinhyeu.ModelDanhNgon;

import java.util.ArrayList;
import java.util.List;

public class DatabaseBook extends HandleDB {

    private static final String TABLE = "COMPANY";
    private static final String NUM = "Num";
    private static volatile DatabaseBook INSTANCE;

    private DatabaseBook(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseBook getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseBook.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseBook(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public ArrayList<ItemTruyen> getArrayItem(String src) {
        ArrayList<ItemTruyen> arr  = new ArrayList<>();
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String ret = cursor.getString(1);
                ItemTruyen ret2 = new ItemTruyen(ret,src);
                arr.add(ret2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return arr;
    }

    public String getContent(String item) {
        String ret = null;
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE + " WHERE " + NUM + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{item});

        if (cursor.moveToFirst()) {
            do {
                ret = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return ret;
    }
    public String getTitle(String item) {
        String ret = null;
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE + " WHERE " + NUM + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{item});

        if (cursor.moveToFirst()) {
            do {
                ret = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return ret;
    }

}
