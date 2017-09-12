package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

/**
 * dialog сообщения-оповещения
 */
public class MessageDialogFragment extends AbstrDialog {
    /**
     * при создании dialog
     * {@inheritDoc}
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = super.onCreateDialogAbstr(getArguments());
        builder.setPositiveButton("OK", null);
        return builder.create();
    }

    /**
     * создать диалог
     * @param args аргументы для создания диалога ({@link AbstrDialog#ARGS_TAGS})
     * @return диалог
     */
    public static MessageDialogFragment createMessageDialogFragment(String... args) {
        return createMessageDialogFragment(createArguments(args));
    }
    /**
     * создать диалог
     * @param bundle аргументы для создания диалога
     * @return диалог
     */
    public static MessageDialogFragment createMessageDialogFragment(Bundle bundle) {
        MessageDialogFragment dialog = new MessageDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
}
