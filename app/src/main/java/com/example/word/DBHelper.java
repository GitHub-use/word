package com.example.word;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DBHELPER_NAME="word";
    private final static int DBHELPER_VER=1;
    public final static String TABLE_NAME_FIRST="word";

    public DBHelper(Context context){
        super(context,DBHELPER_NAME,null,DBHELPER_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME_FIRST+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"en TEXT,"+"ch TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
