package com.tr1nks.safevault.util;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    public static final String CHECK_FILE_NAME = ".c";
    public static final String DB_FILE_NAME = ".db";

    public static byte[] getCheckFileBytes(Context context) {
        return getFileBytes(context, CHECK_FILE_NAME);
    }

    public static byte[] getDBFileBytes(Context context) {
        return getFileBytes(context, DB_FILE_NAME);
    }

    private static byte[] getFileBytes(Context context, String filename) {
        try (FileInputStream inputStream = context.openFileInput(DB_FILE_NAME)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        } catch (IOException e) {
            Log.e("FileUtil", Log.getStackTraceString(e));
        }
        return null;
    }
}
