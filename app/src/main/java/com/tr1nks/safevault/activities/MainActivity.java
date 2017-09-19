package com.tr1nks.safevault.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.CardTitleFragment;
import com.tr1nks.safevault.entities.bytes.TitleBytes;
import com.tr1nks.safevault.util.DBUtil;

import java.util.ArrayList;

/**
 * главное меню приложения
 */
public class MainActivity extends AppCompatActivity {
    /**
     * {@inheritDoc}
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        ArrayList<TitleBytes> rows = DBUtil.getTitleBytes();
        refillRecordLinearLayout(rows);
    }

    private void refillRecordLinearLayout(ArrayList<TitleBytes> titleBytes) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.selectCardLinearLayout);
        layout.removeAllViews();
        for (TitleBytes tb : titleBytes) {
//            createRecordRowFragment(tb);
            createCardFieldFragment(tb);
            getLayoutInflater().inflate(R.layout.view_horisontal_line, layout);
        }
    }

    private void createCardFieldFragment(TitleBytes tb) {
        CardTitleFragment fragment = new CardTitleFragment();
        Bundle bundle = new Bundle();
//        bundle.putParcelable("titleBytes", tb);
        bundle.putByteArray("password", getIntent().getByteArrayExtra("password"));
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.selectCardLinearLayout, fragment, String.valueOf(tb.getId()))
                .addToBackStack(null)
                .commit();
    }

//    private void createRecordRowFragment(TitleBytes titleBytes) {
//        CardTitleFragment fragment = new CardTitleFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("titleBytes", titleBytes);
//        bundle.putByteArray("password", getIntent().getByteArrayExtra("password"));
//        fragment.setArguments(bundle);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.selectCardLinearLayout, fragment, String.valueOf(titleBytes.getId()))
//                .addToBackStack(null)
//                .commit();
//    }
//activity
//*
//    private View createMainRowLayout(final RowMainMenu row) {
//        View view = getLayoutInflater().inflate(R.layout.main_row, null);
//        TextView titleTextView = view.findViewById(R.id.titleTextView);
//        titleTextView.setText(new String(Encoder.decode(getIntent().getByteArrayExtra("password"), row.getTitle())));
//        ImageView titleImageView = view.findViewById(R.id.titleImageView);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openRecord(row);
//            }
//        });
//        return view;
//    }

//    private void openRecord(RowMainMenu row) {
//        getIntent().getByteArrayExtra("password");
//        Intent intent = new Intent(this, CardActivity.class);
//        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
//        intent.putExtra("id", row.getId());
//        intent.putExtra("title", row.getTitle());
//        intent.putExtra("title_img", row.getTitleImgName());
//        intent.putExtra("mode", "edit");
//        startActivity(intent);
//    }

    /**
     * обработчик кнопки добавить
     *
     * @param view current view
     */
    public void addRecordButtonHandler(View view) {
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
        intent.putExtra("mode", "new");
        startActivity(intent);
    }

}
