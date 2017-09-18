package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import com.tr1nks.safevault.R;

public class AskFieldNameDialogFragment extends AbstrDialog {
    public static final String ASK_FIELD_TYPE_DIALOG_NAME = "Ask_Field_Type_dialog";

    public interface AskFieldNameDialogFragmentListener {
        void onAskFieldNameDialogFragmentPositiveClick(DialogFragment dialog, String title, int type);
    }

    private AskFieldNameDialogFragmentListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = super.onCreateDialogAbstr(getArguments());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_ask_field_title, null));
        builder.setPositiveButton(R.string.dialogs_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = ((EditText) getDialog().findViewById(R.id.dialogEnterFieldNameEditText)).getText().toString();
                listener.onAskFieldNameDialogFragmentPositiveClick(AskFieldNameDialogFragment.this, title, getArguments().getInt("type"));
            }
        });
        final Dialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (AskFieldNameDialogFragment.AskFieldNameDialogFragmentListener) context;
    }

    /**
     * создать диалог
     *
     * @param args аргументы для создания диалога ({@link AbstrDialog#ARGS_TAGS})
     * @return диалог
     */
    public static AskFieldNameDialogFragment createAskFieldNameDialogFragment(String... args) {
        return createAskFieldNameDialogFragment(createArguments(args));
    }

    /**
     * создать диалог
     *
     * @param bundle аргументы для создания диалога
     * @return диалог
     */
    public static AskFieldNameDialogFragment createAskFieldNameDialogFragment(Bundle bundle) {
        AskFieldNameDialogFragment dialog = new AskFieldNameDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
}
