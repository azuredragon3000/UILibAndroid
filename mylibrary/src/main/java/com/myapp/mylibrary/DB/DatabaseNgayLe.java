package com.myapp.mylibrary.DB;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseNgayLe extends HandleDB {

    private static volatile DatabaseNgayLe INSTANCE;
    private static final String TABLE_NGAY_LE = "nifiticationDate";
    private DatabaseNgayLe(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseNgayLe getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseNgayLe.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseNgayLe(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public List<NgayLe> getNgayLe(){
        List<NgayLe> list = new ArrayList<>();
        openDatabase();
        String strQuery = "SELECT * FROM "+TABLE_NGAY_LE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new NgayLe(cursor.getString(2),cursor.getString(1),cursor.getString(3)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return list;
    }



}
