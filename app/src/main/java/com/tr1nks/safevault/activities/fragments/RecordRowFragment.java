package com.tr1nks.safevault.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.RecordActivity;
import com.tr1nks.safevault.entities.old.RowMainMenu;
import com.tr1nks.safevault.util.Encoder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordRowFragment extends Fragment {

    public RecordRowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_row, container, false);
        final RowMainMenu row = getArguments().getParcelable("row");
        ((TextView) view.findViewById(R.id.titleTextView)).setText(
                new String(Encoder.decode(getArguments().getByteArray("password"), row.getTitle()))
        );
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecord(row);
            }
        });
        return view;
    }

    private void openRecord(RowMainMenu row) {
        Intent intent = new Intent(getActivity(), RecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putByteArray("password", getArguments().getByteArray("password"));
        bundle.putParcelable("row", row);
        bundle.putString("mode", "open");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
