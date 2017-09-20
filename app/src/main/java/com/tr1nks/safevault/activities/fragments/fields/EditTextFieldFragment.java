package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.FieldMeta;
import com.tr1nks.safevault.entities.bytes.TextBytes;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTextFieldFragment extends Field {
    public EditTextFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        FieldMeta meta = FieldMeta.restore(Encoder.decode(UserPasswordManager.getPassword(), bytes.getMeta()));
        ((TextView) view.findViewById(R.id.fragmentTitleForTextEditText)).setText(new String(Encoder.decode(UserPasswordManager.getPassword(), ((TextBytes) bytes).getTitle())));
        int inpType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
        switch (meta.getFieldType()) {
            case R.id.numberFieldMenuItem: {
                inpType = InputType.TYPE_CLASS_NUMBER;
                break;
            }
            case R.id.loginFieldMenuItem: {
                inpType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                break;
            }
            case R.id.urlFieldMenuItem: {
                inpType = InputType.TYPE_TEXT_VARIATION_URI;
                break;
            }
            case R.id.emailFieldMenuItem: {
                inpType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
                break;
            }
            default: {
                break;
            }
        }
        ((EditText) view.findViewById(R.id.fragmentEditText)).setInputType(inpType);
        return view;
    }

    @Override
    public void onParentPauseAction() {
        View v = getView();
        assert v != null;
        ((TextBytes) this.bytes).setData(Encoder.encode(UserPasswordManager.getPassword(), ((EditText) v.findViewById(R.id.fragmentEditText)).getText().toString().getBytes()));
    }
}
