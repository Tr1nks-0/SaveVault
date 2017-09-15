package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tr1nks.safevault.entities.RowMainMenu;
import com.tr1nks.safevault.entities.RowMainMenu;

import java.util.ArrayList;

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

    public static ArrayList<RowMainMenu> getTitles() {
        return worker.getTitles();
    }

//    public static boolean dbFileExistsCheck() {
//        return getDatabasePath(DBUtil.DATABASE_NAME).exists();
//    }//todo

    private static class DBWorker extends SQLiteOpenHelper {
        //creation
        private static final String DROP_CHECK_TABLE_SQL = "DROP TABLE IF EXISTS chck";
        private static final String CREATE_CHECK_TABLE_SQL = "CREATE TABLE chck (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, val BLOB)";
        private static final String DROP_DATA_TABLE_SQL = "DROP TABLE IF EXISTS data";
        private static final String CREATE_DATA_TABLE_SQL = "CREATE TABLE data (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB, img BLOB, data BLOB)";
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
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_CHECK_DATA_SQL, null)) {
                if (null != cursor && cursor.moveToFirst()) {
                    return cursor.getBlob(cursor.getColumnIndex("val"));
                }
            }
            return null;
        }

        void setCheckData(byte[] checkData) {
            this.getWritableDatabase().execSQL(INSERT_CHECK_DATA_SQL, new Object[]{checkData});
        }

        public ArrayList<RowMainMenu> getTitles() {
//            getWritableDatabase().execSQL("INSERT INTO data(title, img, data) VALUES (?,?,null)", new Object[]{Encoder.encode(Encoder.preparePassw("root".getBytes()), "title 1".getBytes()), Encoder.encode(Encoder.preparePassw("root".getBytes()), "img name 1".getBytes())});//debug
//            getWritableDatabase().execSQL("INSERT INTO data(title, img, data) VALUES (?,?,null)", new Object[]{Encoder.encode(Encoder.preparePassw("root".getBytes()), "title 2".getBytes()), Encoder.encode(Encoder.preparePassw("root".getBytes()), "img name 2".getBytes())});//debug
            ArrayList<RowMainMenu> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_TITLES_SQL, null)) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new RowMainMenu(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("img"))));
                    }
                }
            }
            return arr;
        }
    }
}
