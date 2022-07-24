package com.myapp.mylibrary.boitinhyeu;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;

public class DatabaseBoiTinhYeuCHD extends HandleDB {

    private static volatile DatabaseBoiTinhYeuCHD INSTANCE;
    private static final String TABLE_NAM_SINH = "Product";
    private static final String TITLE = "title";
    private DatabaseBoiTinhYeuCHD(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static DatabaseBoiTinhYeuCHD getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (DatabaseBoiTinhYeuCHD.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DatabaseBoiTinhYeuCHD(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public String getContent(String item) {
        String ret = null;
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE_NAM_SINH + " WHERE " + TITLE + " = ?";
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
}
