package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tr1nks.safevault.entities.bytes.Bytes;

public abstract class Field extends Fragment {
    Bytes bytes;

    public Field() {
        // Required empty public constructor
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public void addFieldDeleteButtonHandler(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(Field.this).commit();
            }
        });
        // todo remove all links to make gc attempt to erase it
    }

    public abstract void onParentPauseAction();

    public void setBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    public abstract void setEditable(boolean b);
}
