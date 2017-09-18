package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.tr1nks.safevault.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTextFieldFragment extends Field {


    public EditTextFieldFragment() {
        // Required empty public constructor
    }

    private int inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_text_field, container, false);
//        view.findViewById(R.id.deleteFieldImageButton).setOnClickListener(super.fieldDeleteButtonHandler(view));
        int i = ((EditText) view.findViewById(R.id.textEditText)).getInputType();
        ((EditText) view.findViewById(R.id.textEditText)).setInputType(inputType);
        return view;
    }

    public void setEditTextType(int type) {
        this.inputType = type;
    }
}
