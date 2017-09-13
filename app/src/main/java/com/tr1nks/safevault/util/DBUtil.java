package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * работа с бд
 */
public class DBUtil {
    public static final String DATABASE_NAME = "sv.db";
    private static final int DATABASE_VERSION = 1;
    public static final String CHECK_PASSW_STR = "check_password_string";
    private static DBWorker worker;

    public static void createDb(Context context) {//todo decide
//        context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        worker = new DBWorker(context);
    }

    public static void initWorker(Context context) { //todo decide
        if (null == worker) {
            worker = new DBWorker(context);
        }
    }

    public static byte[] getCheckData() {
        return worker.getCheckData();
    }

    public static void setCheckData(byte[] checkData) {
        worker.setCheckData(checkData);
    }

//    public static boolean dbFileExistsCheck() {
//        return getDatabasePath(DBUtil.DATABASE_NAME).exists();
//    }//todo

    private static class DBWorker extends SQLiteOpenHelper {
        //creation
        private static final String DROP_CHECK_TABLE_SQL = "DROP TABLE IF EXISTS chck";
        private static final String CREATE_CHECK_TABLE_SQL = "CREATE TABLE chck (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, val BLOB)";
        private static final String DROP_DATA_TABLE_SQL = "DROP TABLE IF EXISTS data";
        private static final String CREATE_DATA_TABLE_SQL = "CREATE TABLE data (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, img TEXT, data BLOB)";
        //chck table
        private static final String INSERT_CHECK_DATA_SQL = "insert INTO chck(val) VALUES (?)";
        private static final String SELECT_CHECK_DATA_SQL = "SELECT val from chck LIMIT 1";
        //data table
        private static final String INSERT_DATA_DATA_SQL = "INSERT INTO data(title, img, data) VALUES (?,?,?)";
        private static final String SELECT_TITLES_SQL = "SELECT id,title,img FROM data";
        private static final String SELECT_DATA_BY_ID_SQL = "SELECT data FROM data WHERE id =?";

        DBWorker(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//            getReadableDatabase().execSQL("SELECT 1 FROM chck");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DROP_CHECK_TABLE_SQL);
            sqLiteDatabase.execSQL(CREATE_CHECK_TABLE_SQL);
            sqLiteDatabase.execSQL(DROP_DATA_TABLE_SQL);
            sqLiteDatabase.execSQL(CREATE_DATA_TABLE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        byte[] getCheckData() {
            Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_CHECK_DATA_SQL, null);
            if (null != cursor && cursor.moveToFirst()) {
                String str = cursor.getString(cursor.getColumnIndex("val"));
                cursor.close();
                return str.getBytes();
            }
            return null;
        }

        void setCheckData(byte[] checkData) {
            this.getWritableDatabase().execSQL(INSERT_CHECK_DATA_SQL, new Object[]{checkData});
        }
    }
}
