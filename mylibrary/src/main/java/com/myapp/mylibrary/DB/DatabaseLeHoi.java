package com.myapp.mylibrary.DB;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseLeHoi extends HandleDB {

    private static volatile DatabaseLeHoi INSTANCE;
    private static final String TABLE_LE_HOI = "vhv_le_hoi_viet_nam";
    private DatabaseLeHoi(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseLeHoi getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseLeHoi.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseLeHoi(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public List<NgayLe> getNgayLe(){
        List<NgayLe> list = new ArrayList<>();
        openDatabase();
        String strQuery = "SELECT * FROM "+TABLE_LE_HOI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new NgayLe(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        list.remove(0);
        return list;
    }



}
