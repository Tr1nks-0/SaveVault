package com.tr1nks.safevault.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.MainRow;
import com.tr1nks.safevault.util.DBUtil;
import com.tr1nks.safevault.util.Encoder;

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
        setContentView(R.layout.activity_main);
        ArrayList<MainRow> rows = DBUtil.getTitles();
        refillRecordLinearLayout(rows);
    }

    private void refillRecordLinearLayout(ArrayList<MainRow> rows) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLinearLayout);
        layout.removeAllViews();
        for (MainRow row : rows) {
            layout.addView(createMainRowLayout(row));
        }
    }

    private View createMainRowLayout(final MainRow row) {
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);
//        View view = getLayoutInflater().inflate(R.layout.main_row, linearLayout);
        View view = getLayoutInflater().inflate(R.layout.main_row, null);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        titleTextView.setText(new String(Encoder.decode(getIntent().getByteArrayExtra("password"), row.getTitle())));
        ImageView titleImageView = view.findViewById(R.id.titleImageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecord(row);
            }
        });
        return view;
    }

    private void openRecord(MainRow row) {
        getIntent().getByteArrayExtra("password");
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
        intent.putExtra("id", row.getId());
        intent.putExtra("title", row.getTitle());
        intent.putExtra("title_img", row.getTitleImgName());
        startActivity(intent);
    }

    /**
     * обработчик кнопки добавить
     *
     * @param view current view
     */
    public void addButtonHandler(View view) {
        getIntent().getByteArrayExtra("password");
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
        startActivity(intent);
    }
}
