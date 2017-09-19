package com.tr1nks.safevault.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class UserPasswordManager extends BroadcastReceiver {

    private static byte[] password;
    private static boolean wasScreenOn = true;

    public static void register(Context c) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        c.registerReceiver(new UserPasswordManager(), filter);
    }


    public static byte[] getPassword() {
        return password;
    }

    public static void setPassword(byte[] password) {
        UserPasswordManager.password = password;
    }

    @Override
    public void onReceive(Context context, Intent intent) {//todo on app lost focus
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            password = null;
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            wasScreenOn = true;
        }
    }
}
