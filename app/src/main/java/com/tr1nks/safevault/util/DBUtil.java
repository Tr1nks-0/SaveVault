package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sv.db";
    private static final int DATABASE_VERSION = 1;
    private static final String IF_TABLE_EXISTS_SQL = "SELECT count(1) FROM sqlite_master WHERE name=? AND type='table' LIMIT 1";
    private static final String CHECK_TABLE_NAME = "check";
    private static SQLiteStatement IF_TABLE_EXISTS_PS;
    //    private static final String CREATE_TABLE_SQL = "SELECT count(1) FROM sqlite_master WHERE name='?' AND type='table' LIMIT 1";
    private static DBUtil instance = null;

    public byte[] getCheckData() {
        return null;
    }

    public DBUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        IF_TABLE_EXISTS_PS = this.getReadableDatabase().compileStatement(IF_TABLE_EXISTS_SQL);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean dbExists(Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            IF_TABLE_EXISTS_PS.bindString(1, CHECK_TABLE_NAME);
            return IF_TABLE_EXISTS_PS.simpleQueryForLong() > 0;
        } else {
            return false;
        }
    }

    public static DBUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtil(context);
        }
        return instance;
    }
}
