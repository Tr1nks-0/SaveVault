package com.tr1nks.safevault.activities.fragments.fields;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.fields.PasswordFieldEntity;
import com.tr1nks.safevault.entities.meta.PasswordEntityMeta;

public class PasswordFieldFragment extends FieldFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
//        ((TextView) view.findViewById(R.id.fragmentTitleForTextEditText)).setText(((TextFieldEntity) entity).getTitleString());
        ((TextView) view.findViewById(R.id.fragmentTitleForPasswEditText)).setText(((PasswordFieldEntity) entity).getTitleString());
        final EditText editText = view.findViewById(R.id.fragmentPasswEditText);
        editText.setInputType(((PasswordEntityMeta) entity.getMetaObj()).getFieldTypeInt());
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
        return view;
    }
}
