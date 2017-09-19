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
//        view.findViewById(R.id.deleteFieldImageButton).setOnClickListener(super.fieldDeleteButtonHandler(view));
       super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        final EditText editText = view.findViewById(R.id.fragmentPasswEditText);
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
        int inpType = 129;
        int inpTypeId = getArguments().getInt("type");
        switch (inpTypeId) {
            case R.id.passwordFieldMenuItem: {
                inpType = 129 | InputType.TYPE_NUMBER_VARIATION_PASSWORD;
                break;
            }
            default: {
                break;
            }
        }
        ((TextView) view.findViewById(R.id.fragmentTitleForPasswEditText)).setText(getArguments().getString("title"));
        editText.setInputType(inpType);
        return view;
    }

}
