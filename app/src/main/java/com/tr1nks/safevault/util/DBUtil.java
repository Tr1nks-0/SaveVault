package com.tr1nks.safevault.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sv";
    private static final int DATABASE_VERSION = 1;
    private static final String IF_TABLE_EXISTS_SQL = "SELECT count(1) FROM sqlite_master WHERE name='?' AND type='table' LIMIT 1";
    private static SQLiteDatabase db;

    public static void test(ContextWrapper contextWrapper) {
//        SQLiteDatabase db = contextWrapper.getBaseContext().openOrCreateDatabase("sv.db", MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS `chck`(id INTEGER, val TEXT)");
//        db.compileStatement()

        /*SQLiteDatabase db = dbHelper.getWritableDatabase();
SQLiteStatement stmt = db.compileStatement("SELECT * FROM Country WHERE code = ?");
stmt.bindString(1, "US");
stmt.execute();
*/
    }

    public DBUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
