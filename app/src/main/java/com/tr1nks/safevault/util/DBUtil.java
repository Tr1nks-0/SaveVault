package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBUtil extends SQLiteOpenHelper {
    private static DBUtil instance = null;
    private static final String DATABASE_NAME = "sv.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CHECK_TABLE_NAME = "chck";
    private static final String DATA_TABLE_NAME = "data";
    private static final String SQLITE_MASTER_TABLE_NAME = "sqlite_master";

    private static final String IF_TABLE_EXISTS_SQL = "SELECT count(1) FROM " + SQLITE_MASTER_TABLE_NAME + " WHERE name=? AND type='table' LIMIT 1";
    private SQLiteStatement IF_TABLE_EXISTS_PS;

    private static final String GET_CHECK_DATA_SQL = "select val from " + CHECK_TABLE_NAME + " LIMIT 1";
    private SQLiteStatement GET_CHECK_DATA_PS;

    private static final String GET_TITLES_WITH_IDS_SQL = "SELECT title, id FROM " + DATA_TABLE_NAME;
    private SQLiteStatement GET_TITLES_WITH_IDS_PS;

    private static final String GET_RECORD_DATA_SQL = "SELECT * FROM " + DATA_TABLE_NAME + " WHERE id = ?";
    private SQLiteStatement GET_RECORD_DATA_PS;

    private static final String GET_TITLE_BY_ID_SQL = "SELECT title FROM " + DATA_TABLE_NAME + " WHERE id = ?";
    private SQLiteStatement GET_TITLE_BY_ID_PS;

    private static final String GET_TITLE_IMG_NAME_BY_ID_SQL = "SELECT title_img_name FROM " + DATA_TABLE_NAME + " WHERE id = ?";
    private SQLiteStatement GET_TITLE_IMG_NAME_BY_ID_PS;

    private static final String GET_DATA_BY_ID_SQL = "SELECT data FROM " + DATA_TABLE_NAME + " WHERE id = ?";
    private SQLiteStatement GET_DATA_BY_ID_PS;


    public byte[] getCheckData() {
//        return GET_CHECK_DATA_PS.simpleQueryForString().getBytes();
        /*Cursor c = db.rawQuery("query",null);
int id[] = new int[c.getCount()];
int i = 0;
if (c.getCount() > 0)
{
    c.moveToFirst();
    do {
        id[i] = c.getInt(c.getColumnIndex("field_name"));
        i++;
    } while (c.moveToNext());
    c.close();
}*/
//        this.getReadableDatabase().execSQL("insert INTO chck(val) VALUES ('first test str'),('second test str')");
        Cursor c = this.getReadableDatabase().rawQuery("select val from " + CHECK_TABLE_NAME, null);
        boolean bf2 = c.moveToFirst();
        String q = c.getColumnName(0);
        int index = c.getColumnIndex(q);
        int is = c.getType(0);
        String b = c.getString(index);
        c.close();
        return b.getBytes();
    }

    public DBUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.IF_TABLE_EXISTS_PS = this.getReadableDatabase().compileStatement(IF_TABLE_EXISTS_SQL);
        this.GET_CHECK_DATA_PS = this.getReadableDatabase().compileStatement(GET_CHECK_DATA_SQL);
//        this.GET_TITLES_WITH_IDS_PS = this.getReadableDatabase().compileStatement(GET_TITLES_WITH_IDS_SQL);
//        this.GET_RECORD_DATA_PS = this.getReadableDatabase().compileStatement(GET_RECORD_DATA_SQL);
//        this.GET_TITLE_BY_ID_PS = this.getReadableDatabase().compileStatement(GET_TITLE_BY_ID_SQL);
//        this.GET_TITLE_IMG_NAME_BY_ID_PS = this.getReadableDatabase().compileStatement(GET_TITLE_IMG_NAME_BY_ID_SQL);
//        this.GET_DATA_BY_ID_PS = this.getReadableDatabase().compileStatement(GET_DATA_BY_ID_SQL);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table `" + CHECK_TABLE_NAME + "`( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, val TEXT )");
        sqLiteDatabase.execSQL("create table " + DATA_TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, title_img_name TEXT, data BLOB )");
        sqLiteDatabase.execSQL("create unique index data_id_uindex on data (id)");
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
