package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

public class MessageDialogFragment extends AbstrDialog {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = super.onCreateDialogAbstr(getArguments());
        builder.setPositiveButton("OK", null);
        return builder.create();
    }

    public MessageDialogFragment() {
    }

    public static MessageDialogFragment createMessageDialogFragment(String ... args) {
        Bundle bundle = createArguments(args);
        MessageDialogFragment dialog = new MessageDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    /*
    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
<TextView
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Hello Android 7"/>
</LinearLayout>


    public Dialog onCreateDialog(Bundle savedInstanceState) {

    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
    return builder
            .setTitle("Диалоговое окно")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setView(R.layout.dialog)
            .setPositiveButton("OK", null)
            .setNegativeButton("Отмена", null)
            .create();
}
     */
}
