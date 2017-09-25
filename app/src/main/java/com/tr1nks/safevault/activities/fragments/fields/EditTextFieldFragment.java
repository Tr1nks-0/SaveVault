package com.tr1nks.safevault.activities.fragments.fields;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.fields.TextFieldEntity;
import com.tr1nks.safevault.entities.meta.TextEntityMeta;

public class EditTextFieldFragment extends FieldFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        ((TextView) view.findViewById(R.id.fragmentTitleForTextEditText)).setText(((TextFieldEntity) entity).getTitleString());
        ((EditText) view.findViewById(R.id.fragmentEditText)).setInputType(((TextEntityMeta) entity.getMetaObj()).getFieldTypeInt());
        return view;
    }
}
