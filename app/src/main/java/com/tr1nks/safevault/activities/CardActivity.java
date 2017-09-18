package com.tr1nks.safevault.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.Card;

public class CardActivity extends AppCompatActivity {
    //    private Record record;
    private Card card;
    private boolean newCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_card);
        Bundle b = this.getIntent().getExtras();
        if (b.getString("mode").equals("new")) {
            newCard = true;
            card = new Card();
        } else {
            newCard = false;
//            RowMainMenu rowMainMenu = b.getParcelable("row");
//            ((EditText) this.findViewById(R.id.recordTitleEditText)).setText(new String(Encoder.decode(b.getByteArray("password"), rowMainMenu.getTitle())));
        }
    }

    public void addFieldButtonHandler(View view) {

//        CardTitleFragment fragment = new CardTitleFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("titleBytes", tb);
//        bundle.putByteArray("password", getIntent().getByteArrayExtra("password"));
//        fragment.setArguments(bundle);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.selectCardLinearLayout, fragment, String.valueOf(tb.getId()))
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        card.save(newCard);
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_LONG).show();
//    }

    //    @Override
//    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(), "Thanks for using application!!", Toast.LENGTH_LONG).show();
//        finish();
//        return;
//    }
}
