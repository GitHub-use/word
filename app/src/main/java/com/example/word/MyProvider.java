package com.example.word;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Matcher;

public class MyProvider extends ContentProvider {
    private Context mContext=null;
    DBHelper my_dp = null;
    SQLiteDatabase dp = null;
    private static UriMatcher uriMatcher =null;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.word","word",1);
    }
    @Override
    public boolean onCreate() {
        mContext=getContext();
        my_dp=new DBHelper(mContext);
        dp = my_dp.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String table = getTableName(uri);

        return dp.query(table,strings,s,strings1,null,null,s1,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String table = getTableName(uri);
        dp.insert(table,null,contentValues);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri){
        String tableName=null;
        switch (uriMatcher.match(uri)){
            case 1:
                tableName=DBHelper.TABLE_NAME_FIRST;
                break;
        }
        return tableName;
    }
}
