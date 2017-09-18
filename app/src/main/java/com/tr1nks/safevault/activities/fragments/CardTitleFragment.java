package com.tr1nks.safevault.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.CardActivity;
import com.tr1nks.safevault.entities.bytes.TitleBytes;

/**
 * Отображение Карточки в меню выбора карточек (заголовок карточки и титульное изображение)
 * A simple {@link Fragment} subclass.
 */
public class CardTitleFragment extends Fragment {

    public CardTitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_title, container, false);
        final TitleBytes titleBytes = getArguments().getParcelable("titleBytes");
//        final RowMainMenu row = getArguments().getParcelable("row");
//        byte [] p =getArguments().getByteArray("password");
//        String ps=new String(p);
//        byte [] d =titleBytes.getTitle();
//        String text =new String(Encoder.decode(getArguments().getByteArray("password"), titleBytes.getTitle()));
//        ((TextView) view.findViewById(R.id.titleTextView)).setText(new String(Encoder.decode(getArguments().getByteArray("password"), titleBytes.getTitle())));
        ((TextView) view.findViewById(R.id.titleTextView)).setText("test");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecord(titleBytes, getArguments().getByteArray("password"));
            }
        });
        return view;
    }

    private void openRecord(TitleBytes titleBytes, byte[] passwords) {
        Intent intent = new Intent(getActivity(), CardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putByteArray("password", passwords);
        bundle.putParcelable("titleBytes", titleBytes);
        bundle.putString("mode", "open");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
