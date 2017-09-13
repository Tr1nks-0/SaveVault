package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.tr1nks.safevault.R;

public class CreatePasswordDialogFragment extends AbstrDialog {
    public static final String CREATE_PASSWORD_DIALOG_NAME = "Create_password_dialog";
    private static final String CREATE_PASSW_DIALOG_LAYOUT_FILENAME = "enter_passw_dialog";

    public interface CreatePasswordDialogListener {
        public void onCreatePasswordDialogPositiveClick(DialogFragment dialog);

        public void onCreatePasswordDialogNegativeClick(DialogFragment dialog);
    }

    CreatePasswordDialogListener listener;

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

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(getResources().getIdentifier(CREATE_PASSW_DIALOG_LAYOUT_FILENAME, LAYOUT_DEF_TYPE, getActivity().getPackageName()), null));
        builder.setPositiveButton(R.string.dialogs_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onCreatePasswordDialogPositiveClick(CreatePasswordDialogFragment.this);
            }
        });
        Dialog dialog = builder.create();

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (CreatePasswordDialogListener) context;
    }
//    private DialogInterface.OnClickListener positiveOnClickListener() {
//        return new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //todo
//            }
//        };
//    }
//
//    private DialogInterface.OnClickListener negativeOnClickListener() {
//        return new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                CreatePasswordDialogFragment.this.getDialog().cancel();
//            }
//        };
//    }

    /**
     * создать диалог
     *
     * @param args аргументы для создания диалога ({@link AbstrDialog#ARGS_TAGS})
     * @return диалог
     */
    public static CreatePasswordDialogFragment createCreatePasswordDialogFragment(String... args) {
        return createCreatePasswordDialogFragment(createArguments(args));
    }

    /**
     * создать диалог
     *
     * @param bundle аргументы для создания диалога
     * @return диалог
     */
    public static CreatePasswordDialogFragment createCreatePasswordDialogFragment(Bundle bundle) {
        CreatePasswordDialogFragment dialog = new CreatePasswordDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
}
