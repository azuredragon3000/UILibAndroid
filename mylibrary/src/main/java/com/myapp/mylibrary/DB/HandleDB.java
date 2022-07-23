package com.myapp.mylibrary.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class HandleDB extends SQLiteOpenHelper {

    private final Context mContext;
    private SQLiteDatabase mDatabase;

    public  String DB_PATH;
    public  String DATABASE_NAME;

    public HandleDB(Context context, String DB_PATH, String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, 1);
        this.DB_PATH = DB_PATH;
        this.DATABASE_NAME = DATABASE_NAME;
        this.mContext = context;

        File database = context.getDatabasePath(DATABASE_NAME);
        if (!database.exists()) {
            this.getReadableDatabase();
            if (coppyDatabase(context)) {
                Log.d("DATABASE_NAME", DATABASE_NAME + ": coppy success");
            } else {
                Log.d("DATABASE_NAME", DATABASE_NAME + ": coppy fail");
            }
        }
    }


    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    private boolean coppyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = DB_PATH + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






}
