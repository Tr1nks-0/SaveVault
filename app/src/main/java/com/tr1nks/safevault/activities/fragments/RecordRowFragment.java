package com.tr1nks.safevault.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.MainRow;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordRowFragment extends Fragment {


    private MainRow row;

    public RecordRowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_record_row, container, false);
//        view.findViewById(R.id.deleteFieldImageButton).setOnClickListener(super.fieldDeleteButtonHandler(view));
        Bundle b = getArguments();
        this.row = getArguments().getParcelable("row");
        return view;
    }

}
