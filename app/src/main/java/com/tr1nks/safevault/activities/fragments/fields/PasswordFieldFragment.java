package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.FieldMeta;
import com.tr1nks.safevault.entities.bytes.PasswordBytes;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFieldFragment extends Field {
    public PasswordFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        final EditText editText = view.findViewById(R.id.fragmentPasswEditText);
        ((TextView) view.findViewById(R.id.fragmentTitleForPasswEditText)).setText(new String(Encoder.decode(UserPasswordManager.getPassword(), ((PasswordBytes) bytes).getTitle())));
        ((CheckBox) view.findViewById(R.id.fragmentShowPasswordCheckBox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int pos = editText.getSelectionEnd();
                if (b) {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    editText.setInputType(129);
                }
                editText.setSelection(pos);
            }
        });
        FieldMeta meta = FieldMeta.restore(Encoder.decode(UserPasswordManager.getPassword(), bytes.getMeta()));
        int inpType = 129;
        switch (meta.getFieldType()) {
            case R.id.pinFieldMenuItem: {
                inpType = 129 | InputType.TYPE_CLASS_NUMBER;//fixme pin
                break;
            }
            case R.id.passwordFieldMenuItem: {
                break;
            }
            default: {
                break;
            }
        }
        editText.setInputType(inpType);
        return view;
    }

    @Override
    public void onParentPauseAction() {

    }
}
