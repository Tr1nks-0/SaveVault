package com.tr1nks.safevault.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.RecordRowFragment;
import com.tr1nks.safevault.entities.old.RowMainMenu;

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
//        ArrayList<RowMainMenu> rows = DBUtil.getTitles();
//        refillRecordLinearLayout(rows);
    }

    private void refillRecordLinearLayout(ArrayList<RowMainMenu> rows) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLinearLayout);
        layout.removeAllViews();
        for (RowMainMenu row : rows) {
//            layout.addView(createMainRowLayout(row));
            createRecordRowFragment(row);
            getLayoutInflater().inflate(R.layout.horisontal_line, layout);
        }
    }

    private void createRecordRowFragment(RowMainMenu row) {
//        NextFragment nextFrag= new NextFragment();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.Layout_container, nextFrag,"findThisFragment")
//                .addToBackStack(null)
//                .commit();

//        Fragment fr = new FirstFragment();
//        fr.setArguments(args);
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_place, fr);
//        fragmentTransaction.commit();

//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.container,YOUR_FRAGMENT_NAME,YOUR_FRAGMENT_STRING_TAG);
//        transaction.addToBackStack(null);
//        transaction.commit();

        RecordRowFragment fragment = new RecordRowFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("row", row);
        bundle.putByteArray("password", getIntent().getByteArrayExtra("password"));
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainLinearLayout, fragment, String.valueOf(row.getId()))
                .addToBackStack(null)
                .commit();
    }

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

    private void openRecord(RowMainMenu row) {
        getIntent().getByteArrayExtra("password");
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
        intent.putExtra("id", row.getId());
        intent.putExtra("title", row.getTitle());
        intent.putExtra("title_img", row.getTitleImgName());
        intent.putExtra("mode", "edit");
        startActivity(intent);
    }

    /**
     * обработчик кнопки добавить
     *
     * @param view current view
     */
    public void addRecordButtonHandler(View view) {
        getIntent().getByteArrayExtra("password");
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
        intent.putExtra("mode", "new");
        startActivity(intent);
    }
}
