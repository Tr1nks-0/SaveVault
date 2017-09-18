package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Field extends Fragment {
    public Field() {
        // Required empty public constructor
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void addFieldDeleteButtonHandler(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo delete this field from parent
            }
        });
    }
}
