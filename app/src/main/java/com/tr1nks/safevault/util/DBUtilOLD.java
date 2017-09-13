package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * работа с бд
 */
public class DBUtilOLD extends SQLiteOpenHelper {
    private static DBUtilOLD instance = null;
    public static final String DATABASE_NAME = "sv.db";
    public static final String CHECK_PASSW_STR = "check_password_string";
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

    public static void setCheckData(byte[] checkData) {

    }


    /**
     * получить данные проверки пароля
     *
     * @return вернет последовательность для проверки пароля
     */
    public byte[] getCheckData() {
        Cursor cursor = this.getReadableDatabase().rawQuery(GET_CHECK_DATA_SQL, null);
        String str;
        if (null != cursor && cursor.moveToFirst()) {
            str = cursor.getString(cursor.getColumnIndex("val"));
            cursor.close();
            return str.getBytes();
        }
        return null;
    }

    /**
     * constructor
     *
     * @param context context
     */
    public DBUtilOLD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.IF_TABLE_EXISTS_PS = this.getReadableDatabase().compileStatement(IF_TABLE_EXISTS_SQL);
        this.GET_CHECK_DATA_PS = this.getReadableDatabase().compileStatement(GET_CHECK_DATA_SQL);
//        this.GET_TITLES_WITH_IDS_PS = this.getReadableDatabase().compileStatement(GET_TITLES_WITH_IDS_SQL);
//        this.GET_RECORD_DATA_PS = this.getReadableDatabase().compileStatement(GET_RECORD_DATA_SQL);
//        this.GET_TITLE_BY_ID_PS = this.getReadableDatabase().compileStatement(GET_TITLE_BY_ID_SQL);
//        this.GET_TITLE_IMG_NAME_BY_ID_PS = this.getReadableDatabase().compileStatement(GET_TITLE_IMG_NAME_BY_ID_SQL);
//        this.GET_DATA_BY_ID_PS = this.getReadableDatabase().compileStatement(GET_DATA_BY_ID_SQL);
    }

    /**
     * Выполняется при создании новой БД
     * {@inheritDoc}
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + CHECK_TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, val TEXT )");
        sqLiteDatabase.execSQL("create table " + DATA_TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, title_img_name TEXT, data BLOB )");
        sqLiteDatabase.execSQL("create unique index data_id_uindex on data (id)");
    }

    /**
     * Выполняется при изменении БД
     * {@inheritDoc}
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    /**
     * Проверяет существует ли БД
     *
     * @param context context
     * @return true если существует
     */
    public boolean dbExists(Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            IF_TABLE_EXISTS_PS.bindString(1, CHECK_TABLE_NAME);
            return IF_TABLE_EXISTS_PS.simpleQueryForLong() > 0;
        } else {
            return false;
        }
    }

    public static void createDb(Context context) {

    }

    /**
     * get Instance for singleton
     *
     * @param context context
     * @return DBUtil
     */
    public static DBUtilOLD getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtilOLD(context);
        }
        return instance;
    }
}
