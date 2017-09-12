package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class CreatePasswordDialogFragment extends AbstrDialog {
    public static final String CREATE_PASSWORD_DIALOG_NAME = "Create_password_dialog";
    private static final String CREATE_PASSW_DIALOG_LAYOUT_FILENAME = "enter_passw_dialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = super.onCreateDialogAbstr(getArguments());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(getResources().getIdentifier(CREATE_PASSW_DIALOG_LAYOUT_FILENAME, LAYOUT_DEF_TYPE, getActivity().getPackageName()), null));
        builder.setPositiveButton(getArguments().getString(POSITIVE_BUTTON_TAG), positiveOnClickListener());
        Dialog dialog = builder.create();

        return dialog;
    }

    private DialogInterface.OnClickListener positiveOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //todo
            }
        };
    }

    private DialogInterface.OnClickListener negativeOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CreatePasswordDialogFragment.this.getDialog().cancel();
            }
        };
    }

    public static CreatePasswordDialogFragment createMessageDialogFragment(String... args) {
        Bundle bundle = createArguments(args);
        CreatePasswordDialogFragment dialog = new CreatePasswordDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
}
