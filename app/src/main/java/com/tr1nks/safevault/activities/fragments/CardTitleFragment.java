package com.tr1nks.safevault.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.bytes.TitleBytes;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

/**
 * Отображение Карточки в меню выбора карточек (заголовок карточки и титульное изображение)
 * A simple {@link Fragment} subclass.
 */
public class CardTitleFragment extends Fragment {
    private TitleBytes bytes;

    public CardTitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_title, container, false);
        ((TextView) view.findViewById(R.id.mainCardTitleFragmentTitleTextView)).setText(new String(Encoder.decode(UserPasswordManager.getPassword(), bytes.getTitle())));
        view.findViewById(R.id.mainCardTitleFragmentLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bytes.openCard("open", getContext());
            }
        });
        return view;
    }


    public void setBytes(TitleBytes bytes) {
        this.bytes = bytes;
    }
}
