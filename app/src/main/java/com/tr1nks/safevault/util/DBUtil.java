package com.tr1nks.safevault.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tr1nks.safevault.entities.CardNew;
import com.tr1nks.safevault.entities.CardTitleNew;
import com.tr1nks.safevault.entities.old.bytes.*;
import com.tr1nks.safevault.entities.fields.TextFieldEntity;

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

    public static long insertTitleBytes(TitleBytes titleBytes) {
        return worker.insertUpdateTitleBytes(titleBytes, false);
    }

    public static void updateTitleBytes(TitleBytes titleBytes) {
        worker.insertUpdateTitleBytes(titleBytes, true);
    }

    public static long insertTextBytes(TextBytes textBytes) {
        return worker.insertUpdateTextBytes(textBytes, false);
    }

    public static void updateTextBytes(TextBytes textBytes) {
        worker.insertUpdateTextBytes(textBytes, true);
    }

    public static long insertPasswordBytes(PasswordBytes passwordBytes) {
        return worker.insertUpdatePasswordBytes(passwordBytes, false);
    }

    public static void updatePasswordBytes(PasswordBytes passwordBytes) {
        worker.insertUpdatePasswordBytes(passwordBytes, true);
    }

    public static long insertImageBytes(ImageBytes imageBytes) {
        return worker.insertUpdateImageBytes(imageBytes, false);
    }

    public static void updateImageBytes(ImageBytes imageBytes) {
        worker.insertUpdateImageBytes(imageBytes, true);
    }

    public static long insertUserIconBytes(UserIconBytes userIconBytes) {
        return worker.insertUpdateUserIconBytes(userIconBytes, false);
    }

    public static void updateUserIconBytes(UserIconBytes userIconBytes) {
        worker.insertUpdateUserIconBytes(userIconBytes, true);
    }

    public static ArrayList<TextFieldEntity> getTextFieldEntitiesById(ArrayList<TextFieldEntity> textIdsList) {
        return worker.getTextFieldEntitiesById(textIdsList);
    }

    public static ArrayList<CardNew> getCards() {
        return worker.getCards();
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
                "CREATE TABLE texts (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, data BLOB,meta BLOB NOT NULL )",
                "CREATE TABLE passwords (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, data BLOB,meta BLOB NOT NULL)",
                "CREATE TABLE images (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, data BLOB,meta BLOB NOT NULL)",
                "CREATE TABLE user_icons (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, data BLOB NOT NULL,meta BLOB NOT NULL)",
                "CREATE TABLE titles (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title BLOB NOT NULL, icon BLOB NOT NULL, meta BLOB NOT NULL, text_ids BLOB, password_ids BLOB, image_ids BLOB, icon_ids BLOB)"
        };

        //pass_check table
        private static final String INSERT_CHECK_DATA_SQL = "insert INTO pass_check(val) VALUES (?)";
        private static final String SELECT_CHECK_DATA_SQL = "SELECT val from pass_check LIMIT 1";

        //bytes table generic
        private static final String BYTES_ID = "id";

        //titles table
        private static final String TITLES_TABLENAME = "titles";
        private static final String TITLES_ID = "id";
        private static final String TITLES_TITLE = "title";
        private static final String TITLES_ICON = "icon";
        private static final String TITLES_META = "meta";
        private static final String TITLES_TEXT_IDS = "text_ids";
        private static final String TITLES_PASSWORD_IDS = "password_ids";
        private static final String TITLES_IMAGE_IDS = "image_ids";
        private static final String TITLES_USER_ICON_IDS = "icon_ids";
        private static final String SELECT_TITLES_SQL = "SELECT id,title,icon,meta,text_ids,password_ids,image_ids,icon_ids FROM titles";

        //texts table
        private static final String TEXTS_TABLENAME = "texts";
        private static final String TEXTS_ID = "id";
        private static final String TEXTS_TITLE = "title";
        private static final String TEXTS_DATA = "data";
        private static final String TEXTS_META = "meta";
        private static final String SELECT_TEXTS_BY_ID_SQL = "SELECT id,title,data,meta FROM texts WHERE id in (?)";

        //passwords table
        private static final String PASSWORDS_TABLENAME = "passwords";
        private static final String PASSWORDS_ID = "id";
        private static final String PASSWORDS_TITLE = "title";
        private static final String PASSWORDS_DATA = "data";
        private static final String PASSWORDS_META = "meta";
        private static final String SELECT_PASSWORDS_BY_ID_SQL = "SELECT id,title,data,meta FROM passwords WHERE id in (?)";

        //images table
        private static final String IMAGES_TABLENAME = "images";
        private static final String IMAGES_ID = "id";
        private static final String IMAGES_TITLE = "title";
        private static final String IMAGES_DATA = "data";
        private static final String IMAGES_META = "meta";
        private static final String SELECT_IMAGES_BY_ID_SQL = "SELECT id,title,data,meta FROM images WHERE id in (?)";

        //user_icons table
        private static final String USER_ICONS_TABLENAME = "user_icons";
        private static final String USER_ICONS_ID = "id";
        private static final String USER_ICONS_DATA = "data";
        private static final String USER_ICONS_META = "meta";
        private static final String SELECT_USER_ICONS_BY_ID_SQL = "SELECT id,data,meta FROM user_icons WHERE id in (?)";


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

        public ArrayList<CardNew> getCards() {
            ArrayList<CardNew> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_TITLES_SQL, null)) {
                if (null != cursor && cursor.moveToFirst()) {
                    cursor.moveToPrevious();
                    while (cursor.moveToNext()) {
                        arr.add(new CardNew(
                                new CardTitleNew(
                                        cursor.getInt(cursor.getColumnIndex("id")),
                                        cursor.getBlob(cursor.getColumnIndex("title")),
                                        cursor.getBlob(cursor.getColumnIndex("icon")),
                                        cursor.getBlob(cursor.getColumnIndex("meta")),
                                        cursor.getBlob(cursor.getColumnIndex("text_ids")),
                                        cursor.getBlob(cursor.getColumnIndex("password_ids")),
                                        cursor.getBlob(cursor.getColumnIndex("image_ids")),
                                        cursor.getBlob(cursor.getColumnIndex("icon_ids"))
                                )
                        ));
                    }
                }
            }
            return arr;
        }

        ArrayList<TextFieldEntity> getTextFieldEntitiesById(ArrayList<TextFieldEntity> textIdsList) {
            ArrayList<TextFieldEntity> arr = new ArrayList<>();
            try (Cursor cursor = this.getReadableDatabase().rawQuery(SELECT_TEXTS_BY_ID_SQL, null)) {
//todo
            }
            return arr;
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
                        arr.add(new TextBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("data")), cursor.getBlob(cursor.getColumnIndex("meta"))));
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
                        arr.add(new PasswordBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("data")), cursor.getBlob(cursor.getColumnIndex("meta"))));
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
                        arr.add(new ImageBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("title")), cursor.getBlob(cursor.getColumnIndex("data")), cursor.getBlob(cursor.getColumnIndex("meta"))));
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
                        arr.add(new UserIconBytes(cursor.getInt(cursor.getColumnIndex("id")), cursor.getBlob(cursor.getColumnIndex("data")), cursor.getBlob(cursor.getColumnIndex("meta"))));
                    }
                }
            }
            return arr;
        }

        //////////////////////////////
        private String[] prepareIds(ArrayList<Integer> textIds) {
            return new String[]{textIds.toString().replace("[", "").replace("]", "")};
        }

        long insertBytes(String tablename, ContentValues values) {
            return getWritableDatabase().insert(tablename, null, values);
        }

        long updateBytes(String tablename, ContentValues values, String[] whereArgs) {
            return getWritableDatabase().update(tablename, values, TEXTS_ID + "=?", whereArgs);
        }

        private ContentValues prepareBytesContentValues(String[] valuesTitle, byte[][] valuesVal) {
            ContentValues values = new ContentValues();
            for (int i = 0; i < valuesTitle.length; i++) {
                values.put(valuesTitle[i], valuesVal[i]);
            }
            return values;
        }

        //////////////////////////////////
        long insertUpdateTitleBytes(TitleBytes titleBytes, boolean update) {
            ContentValues cv = prepareBytesContentValues(new String[]{TITLES_TITLE, TITLES_ICON, TITLES_META, TITLES_TEXT_IDS, TITLES_PASSWORD_IDS, TITLES_IMAGE_IDS, TITLES_USER_ICON_IDS},
                    new byte[][]{titleBytes.getTitle(), titleBytes.getIcon(), titleBytes.getMeta(), titleBytes.getTextIds(), titleBytes.getPasswordIds(), titleBytes.getImageIds(), titleBytes.getUserIconIds(),});
            if (update) {
                return updateBytes(TITLES_TABLENAME, cv, new String[]{String.valueOf(titleBytes.getId())});
            } else {
                return insertBytes(TITLES_TABLENAME, cv);
            }
        }

        long insertUpdateTextBytes(TextBytes textBytes, boolean update) {
            ContentValues cv = prepareBytesContentValues(new String[]{TEXTS_TITLE, TEXTS_DATA, TEXTS_META},
                    new byte[][]{textBytes.getTitle(), textBytes.getData(), textBytes.getMeta()});
            if (update) {
                return updateBytes(TEXTS_TABLENAME, cv, new String[]{String.valueOf(textBytes.getId())});
            } else {
                return insertBytes(TEXTS_TABLENAME, cv);
            }
        }

        long insertUpdatePasswordBytes(PasswordBytes passwordBytes, boolean update) {
            ContentValues cv = prepareBytesContentValues(new String[]{PASSWORDS_TITLE, PASSWORDS_DATA, PASSWORDS_META},
                    new byte[][]{passwordBytes.getTitle(), passwordBytes.getData(), passwordBytes.getMeta()});
            if (update) {
                return updateBytes(PASSWORDS_TABLENAME, cv, new String[]{String.valueOf(passwordBytes.getId())});
            } else {
                return insertBytes(PASSWORDS_TABLENAME, cv);
            }
        }


        long insertUpdateImageBytes(ImageBytes imageBytes, boolean update) {
            ContentValues cv = prepareBytesContentValues(new String[]{IMAGES_TITLE, IMAGES_DATA, IMAGES_META},
                    new byte[][]{imageBytes.getTitle(), imageBytes.getData(), imageBytes.getMeta()});
            if (update) {
                return updateBytes(IMAGES_TABLENAME, cv, new String[]{String.valueOf(imageBytes.getId())});
            } else {
                return insertBytes(IMAGES_TABLENAME, cv);
            }
        }


        long insertUpdateUserIconBytes(UserIconBytes userIconBytes, boolean update) {
            ContentValues cv = prepareBytesContentValues(new String[]{USER_ICONS_DATA, USER_ICONS_META},
                    new byte[][]{userIconBytes.getData(), userIconBytes.getMeta()});
            if (update) {
                return updateBytes(USER_ICONS_TABLENAME, cv, new String[]{String.valueOf(userIconBytes.getId())});
            } else {
                return insertBytes(USER_ICONS_TABLENAME, cv);
            }
        }


    }
}
