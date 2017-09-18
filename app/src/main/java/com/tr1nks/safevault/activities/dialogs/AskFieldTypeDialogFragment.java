package com.tr1nks.safevault.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import com.tr1nks.safevault.R;

public class AskFieldTypeDialogFragment extends AbstrDialog {
    public interface AskFieldTypeDialogListener {

    }

    private AskFieldTypeDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialogs_ask_field_type_title)
//                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                         The 'which' argument contains the index position
        // of the selected item
//                    }
//                });
        ;
        return builder.create();
    }
}
