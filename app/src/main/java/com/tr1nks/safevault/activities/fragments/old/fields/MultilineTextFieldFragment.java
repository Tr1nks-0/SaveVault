package com.tr1nks.safevault.activities.fragments.old.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.old.bytes.TextBytes;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultilineTextFieldFragment extends Field {

    private TextBytes textBytes;

    public MultilineTextFieldFragment() {
        // Required empty public constructor
    }

    public void setTextBytes(TextBytes textBytes) {
        this.textBytes = textBytes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiline_text_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        ((TextView) view.findViewById(R.id.titleForMultilineText)).setText(new String(Encoder.decode(UserPasswordManager.getPassword(), ((TextBytes) bytes).getTitle())));
        return view;
    }

    @Override
    public void onParentPauseAction() {

    }

    @Override
    public void setEditable(boolean b) {
//        EditText editText = getView().findViewById(R.id.fragmentPasswEditText);
//        if (b) {
//            editText.setTag(editText.getKeyListener());
//            editText.setKeyListener(null);
//        } else {
//            editText.setKeyListener((KeyListener) editText.getTag());
//        }
    }
}
