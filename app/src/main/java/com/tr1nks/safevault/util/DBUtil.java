package com.tr1nks.safevault.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tr1nks.safevault.entities.bytes.PasswordBytes;
import com.tr1nks.safevault.entities.bytes.TextBytes;
import com.tr1nks.safevault.entities.bytes.TitleBytes;
import com.tr1nks.safevault.entities.bytes.UserIconBytes;
import com.tr1nks.safevault.entities.bytes.ImageBytes;

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

    public static ArrayList<TitleBytes> getTitleBytes() {
        return worker.getTitleBytes();
    }

    public static ArrayList<TextBytes> getTextBytesByIds(ArrayList<Integer> textIds) {
        return worker.getTextBytesByIds(textIds);
    }

    public static ArrayList<PasswordBytes> getPasswordBytesByIds(ArrayList<Integer> passwordIds) {
        return worker.getPasswordBytesByIds(passwordIds);
    }

    public static ArrayList<ImageBytes> getImageBytesByIds(ArrayList<Integer> imageIds) {
        return worker.getImageBytesByIds(imageIds);
    }

    public static ArrayList<UserIconBytes> getUserIconsBytesByIds(ArrayList<Integer> userIconIds) {
        return worker.getUserIconsBytesByIds(userIconIds);
    }

    public static void insertTitleBytes(TitleBytes titleBytes) {
        worker.insertTitleBytes(titleBytes);
    }

    public static int insertTextBytes(TextBytes textBytes) {
        return worker.insertTextBytes(textBytes);
    }

    public static int insertPasswordBytes(PasswordBytes passwordBytes) {
        return worker.insertPasswordBytes(passwordBytes);
    }

    public static int insertImageBytes(ImageBytes imageBytes) {
        return worker.insertImageBytes(imageBytes);
    }

    public static int insertUserIconBytes(UserIconBytes userIconBytes) {
        return worker.insertIconBytes(userIconBytes);
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
                "CREATE TABLE texts (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, data BLOB)",
                "CREATE TABLE passwords (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, data BLOB)",
                "CREATE TABLE images (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, data BLOB)",
                "CREATE TABLE user_icons (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data BLOB NOT NULL)",
                "CREATE TABLE titles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, icon BLOB NOT NULL, meta BLOB NOT NULL, text_ids BLOB, password_ids BLOB, image_ids BLOB, icon_ids BLOB)"
        };

        //pass_check table
        private static final String INSERT_CHECK_DATA_SQL = "insert INTO pass_check(val) VALUES (?)";
        private static final String SELECT_CHECK_DATA_SQL = "SELECT val from pass_check LIMIT 1";

        //titles table
        private static final String SELECT_TITLES_SQL = "SELECT id,title,icon,meta,text_ids,password_ids,image_ids,icon_ids FROM titles";
        private static final String INSERT_TITLES_SQL = "INSERT INTO titles (title, icon, meta, text_ids, password_ids, image_ids, icon_ids) VALUES (?,?,?,?,?,?,?)";
        private static final String SELECT_LAST_INSERT_ID_TITLES_SQL = "select last_insert_rowid() as id from titles";

        //texts table
        private static final String SELECT_TEXTS_BY_ID_SQL = "SELECT id,title,data FROM texts WHERE id in (?)";
        private static final String INSERT_TEXTS_SQL = "INSERT INTO texts (title, data) VALUES (?,?)";
        private static final String SELECT_LAST_INSERT_ID_TEXTS_SQL = "select last_insert_rowid() as id from texts";

        //passwords table
        private static final String SELECT_PASSWORDS_BY_ID_SQL = "SELECT id,title,data FROM passwords WHERE id in (?)";
        private static final String INSERT_PASSWORDS_SQL = "INSERT INTO passwords (title, data) VALUES (?,?)";
        private static final String SELECT_LAST_INSERT_ID_PASSWORDS_SQL = "select last_insert_rowid() as id from passwords";

        //images table
        private static final String SELECT_IMAGES_BY_ID_SQL = "SELECT id,title,data FROM images WHERE id in (?)";
        private static final String INSERT_IMAGES_SQL = "INSERT INTO images (title, data) VALUES (?,?)";
        private static final String SELECT_LAST_INSERT_ID_IMAGES_SQL = "select last_insert_rowid() as id from images";

        //user_icons table
        private static final String SELECT_USER_ICONS_BY_ID_SQL = "SELECT id,data FROM user_icons WHERE id in (?)";
        private static final String INSERT_USER_ICONS = "INSERT INTO user_icons ( data) VALUES (?)";
        private static final String SELECT_LAST_INSERT_ID_USER_ICONS_SQL = "select last_insert_rowid() as id from user_icons";


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

        ArrayList<TitleBytes> getTitleBytes() {
//            getWritableDatabase().execSQL("DELETE FROM titles WHERE id >0");
//            getWritableDatabase().execSQL("INSERT INTO titles (title, icon, meta, text_ids, password_ids, image_ids, icon_ids) VALUES ('" + Encoder.encode(Encoder.preparePassw("q".getBytes()), "title 1".getBytes()).toString() + "','"+ Encoder.encode(Encoder.preparePassw("q".getBytes()), "icon1".getBytes()).toString() + "','" + Encoder.encode(Encoder.preparePassw("q".getBytes()), "meta 1".getBytes()).toString() + "',null,null,null,null)");
            ArrayList<TitleBytes> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_TITLES_SQL, null)) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new TitleBytes(
                                cursor.getInt(cursor.getColumnIndex("id")),
                                cursor.getBlob(cursor.getColumnIndex("title")),
                                cursor.getBlob(cursor.getColumnIndex("icon")),
                                cursor.getBlob(cursor.getColumnIndex("meta")),
                                cursor.getBlob(cursor.getColumnIndex("text_ids")),
                                cursor.getBlob(cursor.getColumnIndex("password_ids")),
                                cursor.getBlob(cursor.getColumnIndex("image_ids")),
                                cursor.getBlob(cursor.getColumnIndex("icon_ids"))));
                    }
                }
            }
            return arr;
        }

        ArrayList<TextBytes> getTextBytesByIds(ArrayList<Integer> textIds) {
            ArrayList<TextBytes> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_TEXTS_BY_ID_SQL, prepareIds(textIds))) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new TextBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("data"))));
                    }
                }
            }
            return arr;
        }

        ArrayList<PasswordBytes> getPasswordBytesByIds(ArrayList<Integer> passwordIds) {
            ArrayList<PasswordBytes> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_PASSWORDS_BY_ID_SQL, prepareIds(passwordIds))) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new PasswordBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("data"))));
                    }
                }
            }
            return arr;
        }

        ArrayList<ImageBytes> getImageBytesByIds(ArrayList<Integer> imageIds) {
            ArrayList<ImageBytes> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_IMAGES_BY_ID_SQL, prepareIds(imageIds))) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new ImageBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("data"))));
                    }
                }
            }
            return arr;
        }

        ArrayList<UserIconBytes> getUserIconsBytesByIds(ArrayList<Integer> userIconIds) {
            ArrayList<UserIconBytes> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_USER_ICONS_BY_ID_SQL, prepareIds(userIconIds))) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new UserIconBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("data"))));
                    }
                }
            }
            return arr;
        }

        private String[] prepareIds(ArrayList<Integer> textIds) {
            return new String[]{textIds.toString().replace("[", "").replace("]", "")};
        }

        int insertTitleBytes(TitleBytes titleBytes) {
            getWritableDatabase().execSQL(INSERT_TITLES_SQL, titleBytes.toInsertArr());
            try (Cursor c = getReadableDatabase().rawQuery(SELECT_LAST_INSERT_ID_TITLES_SQL, null)) {
                return c.getInt(c.getColumnIndex("id"));
            }
        }

        int insertTextBytes(TextBytes textBytes) {
            getWritableDatabase().execSQL(INSERT_TEXTS_SQL, textBytes.toInsertArr());
            try (Cursor c = getReadableDatabase().rawQuery(SELECT_LAST_INSERT_ID_TEXTS_SQL, null)) {
                return c.getInt(c.getColumnIndex("id"));
            }
        }

        int insertPasswordBytes(PasswordBytes passwordBytes) {
            getWritableDatabase().execSQL(INSERT_PASSWORDS_SQL, passwordBytes.toInsertArr());
            try (Cursor c = getReadableDatabase().rawQuery(SELECT_LAST_INSERT_ID_PASSWORDS_SQL, null)) {
                return c.getInt(c.getColumnIndex("id"));
            }
        }

        int insertImageBytes(ImageBytes imageBytes) {
            getWritableDatabase().execSQL(INSERT_IMAGES_SQL, imageBytes.toInsertArr());
            try (Cursor c = getReadableDatabase().rawQuery(SELECT_LAST_INSERT_ID_IMAGES_SQL, null)) {
                return c.getInt(c.getColumnIndex("id"));
            }
        }

        int insertIconBytes(UserIconBytes userIconBytes) {
            getWritableDatabase().execSQL(INSERT_USER_ICONS, userIconBytes.toInsertArr());
            try (Cursor c = getReadableDatabase().rawQuery(SELECT_LAST_INSERT_ID_USER_ICONS_SQL, null)) {
                return c.getInt(c.getColumnIndex("id"));
            }
        }
    }
}
