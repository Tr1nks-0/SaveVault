package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.tr1nks.safevault.R;

public class AskDialogFragment extends AbstrDialog {
    public static final String ASK_DIALOG_NAME = "Ask_dialog";

    public interface AskDialogListener {
        public void onAskDialogPositiveClick(DialogFragment dialog);

        public void onAskDialogNegativeClick(DialogFragment dialog);
    }

    AskDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = super.onCreateDialogAbstr(getArguments());
        builder.setPositiveButton(R.string.dialogs_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onAskDialogPositiveClick(AskDialogFragment.this);
            }
        });
        builder.setNegativeButton(R.string.dialogs_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onAskDialogNegativeClick(AskDialogFragment.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (AskDialogListener) context;
    }

    /**
     * создать диалог
     *
     * @param args аргументы для создания диалога ({@link AbstrDialog#ARGS_TAGS})
     * @return диалог
     */
    public static AskDialogFragment createAskDialogFragment(String... args) {
        return createAskDialogFragment(createArguments(args));
    }

    /**
     * создать диалог
     *
     * @param bundle аргументы для создания диалога
     * @return диалог
     */
    public static AskDialogFragment createAskDialogFragment(Bundle bundle) {
        AskDialogFragment dialog = new AskDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
}
