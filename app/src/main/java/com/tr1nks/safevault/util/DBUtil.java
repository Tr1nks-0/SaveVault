package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tr1nks.safevault.entities.Title;

import java.util.ArrayList;

/**
 * работа с бд
 */
public class DBUtil {
    public static final String DATABASE_NAME = "sv.db";
    private static final int DATABASE_VERSION = 1;
    public static final String CHECK_PASSW_STR = "Check_Password_String_32_SymbolS";
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

    public static ArrayList<Title> getTitles() {
        return worker.getTitles();
    }

//    public static boolean dbFileExistsCheck() {
//        return getDatabasePath(DBUtil.DATABASE_NAME).exists();
//    }//todo

    private static class DBWorker extends SQLiteOpenHelper {
        //creation
        private static final String[] CREATION_SQL_SCRIPT = {
                "DROP TABLE IF EXISTS pass_check",
                "DROP TABLE IF EXISTS titles",
                "DROP TABLE IF EXISTS texts",
                "DROP TABLE IF EXISTS passwords",
                "DROP TABLE IF EXISTS images",
                "DROP TABLE IF EXISTS user_icons",
                "CREATE TABLE pass_check (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, val BLOB)",
                "CREATE TABLE titles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title BLOB NOT NULL,icon BLOB NOT NULL,meta BLOB NOT NULL)",
                "CREATE TABLE texts (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title_id BLOB NOT NULL CONSTRAINT texts_titles_id_fk REFERENCES titles (id),title BLOB NOT NULL,data BLOB)",
                "CREATE TABLE passwords (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title_id BLOB NOT NULL CONSTRAINT passwords_titles_id_fk REFERENCES titles (id),title BLOB NOT NULL,data BLOB)",
                "CREATE TABLE images (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title_id BLOB NOT NULL CONSTRAINT images_titles_id_fk REFERENCES titles (id),title BLOB NOT NULL,data BLOB)",
                "CREATE TABLE user_icons ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title_id BLOB NOT NULL CONSTRAINT user_icons_titles_id_fk REFERENCES titles (id), data BLOB NOT NULL)"
        };

        //chck table
        private static final String INSERT_CHECK_DATA_SQL = "insert INTO pass_check(val) VALUES (?)";
        private static final String SELECT_CHECK_DATA_SQL = "SELECT val from pass_check LIMIT 1";
        //data table

        DBWorker(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//            getReadableDatabase().execSQL("SELECT 1 FROM chck");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            for (String s : CREATION_SQL_SCRIPT) {
                sqLiteDatabase.execSQL(s);
            }
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

        public ArrayList<Title> getTitles() {
            return null;
        }

//        public ArrayList<RowMainMenu> getTitles() {
//            ArrayList<RowMainMenu> arr = new ArrayList<>();
//            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_TITLES_SQL, null)) {
//                if (null != cursor && cursor.moveToFirst()) {
//                    cursor.moveToPrevious();
//                    while (cursor.moveToNext()) {
//                        arr.add(new RowMainMenu(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("img"))));
//                    }
//                }
//            }
//            return arr;
//        }
    }
}
