package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

public abstract class AbstrDialog extends DialogFragment {
    protected static final String DRAWABLE_DEF_TYPE = "drawable";
    protected static final String LAYOUT_DEF_TYPE = "layout";
    public static final String TITLE_TAG = "dialog_title";
    public static final String MESSAGE_TAG = "dialog_message";
    public static final String ICON_TAG = "dialog_icon";
    public static final String POSITIVE_BUTTON_TAG = "dialog_pos_button";
    public static final String NEGATIVE_BUTTON_TAG = "dialog_neg_button";
    private static final String[] ARGS_TAGS = {
            TITLE_TAG,
            MESSAGE_TAG,
            ICON_TAG
    };

    public AlertDialog.Builder onCreateDialogAbstr(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString(TITLE_TAG) != null ? getArguments().getString(TITLE_TAG) : TITLE_TAG);
        if (getArguments().getString(MESSAGE_TAG) != null) {
            builder.setMessage(getArguments().getString(MESSAGE_TAG));
        }
        if (getArguments().getString(ICON_TAG) != null) {
            builder.setIcon(getResources().getIdentifier(getArguments().getString(ICON_TAG), DRAWABLE_DEF_TYPE, getActivity().getPackageName()));
        }
//        if (getArguments().getString(POSITIVE_BUTTON_TAG) != null) {
//            builder.setPositiveButton(getArguments().getString(POSITIVE_BUTTON_TAG), null);
//        }
        return builder;
    }


    public static Bundle createArguments(String... args) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < (args.length < ARGS_TAGS.length ? args.length : ARGS_TAGS.length); i++) {
            bundle.putString(ARGS_TAGS[i], args[i]);
        }
        return bundle;
    }
}
