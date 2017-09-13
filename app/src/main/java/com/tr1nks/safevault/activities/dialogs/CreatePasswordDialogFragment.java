package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.tr1nks.safevault.R;

public class CreatePasswordDialogFragment extends AbstrDialog {
    public static final String CREATE_PASSWORD_DIALOG_NAME = "Create_password_dialog";
    private static final String CREATE_PASSW_DIALOG_LAYOUT_FILENAME = "enter_passw_dialog";

    public interface CreatePasswordDialogListener {
        void onCreatePasswordDialogPositiveClick(DialogFragment dialog, String passw);

        void onCreatePasswordDialogNegativeClick(DialogFragment dialog);
    }

    private CreatePasswordDialogListener listener;

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
        builder.setPositiveButton(R.string.dialogs_yes, null);
        final Dialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                CheckBox c = getDialog().findViewById(R.id.dialogShowPasswCheckBox);
                c.setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                EditText editText1 = getDialog().findViewById(R.id.dialogEnterPasswEditText);
                                EditText editText2 = getDialog().findViewById(R.id.dialogConfirmPasswEditText);
                                TextView textView = getDialog().findViewById(R.id.dialog_checkbox_text_size_TextView);
                                int pos = editText1.getSelectionEnd();
                                if (b) {
                                    editText1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    editText2.setVisibility(View.INVISIBLE);
                                    textView.setVisibility(View.INVISIBLE);
                                } else {
                                    editText1.setInputType(129);
                                    editText2.setVisibility(View.VISIBLE);
                                    textView.setVisibility(View.VISIBLE);
                                }
                                editText1.setSelection(pos);
                            }
                        });
                Button b = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s1 = ((EditText) getDialog().findViewById(R.id.dialogEnterPasswEditText)).getText().toString();
                        String s2 = ((EditText) getDialog().findViewById(R.id.dialogConfirmPasswEditText)).getText().toString();
                        CheckBox checkBox = getDialog().findViewById(R.id.dialogShowPasswCheckBox);
                        if (s1.equals(s2) || checkBox.isChecked()) {
                            listener.onCreatePasswordDialogPositiveClick(CreatePasswordDialogFragment.this, ((EditText) getDialog().findViewById(R.id.dialogEnterPasswEditText)).getText().toString());
                            dialog.dismiss();
                        } else {
                            ((EditText) getDialog().findViewById(R.id.dialogEnterPasswEditText)).setText("");
                            ((EditText) getDialog().findViewById(R.id.dialogConfirmPasswEditText)).setText("");
                            Toast toast = Toast.makeText(getActivity(), getResources().getString(R.string.toasts_message_passws_mismatch_message), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        });
        return dialog;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (CreatePasswordDialogListener) context;
    }

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
