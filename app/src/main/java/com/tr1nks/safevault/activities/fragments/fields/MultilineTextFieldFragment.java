package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tr1nks.safevault.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultilineTextFieldFragment extends Field {


    public MultilineTextFieldFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiline_text_field, container, false);
//        view.findViewById(R.id.deleteFieldImageButton).setOnClickListener(super.fieldDeleteButtonHandler(view));
        ((TextView) view.findViewById(R.id.fragmentTitleForTextEditText)).setText(getArguments().getString("title"));
        return view;
    }
}
