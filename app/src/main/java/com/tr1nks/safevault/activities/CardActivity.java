package com.tr1nks.safevault.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.fields.EditTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.Field;
import com.tr1nks.safevault.entities.Card;

/**
 * карточка
 */
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
        }
    }

    public void addFieldButtonHandler(View view) {
        PopupMenu popup = new PopupMenu(CardActivity.this, findViewById(R.id.addFieldButton));
        popup.getMenuInflater().inflate(R.menu.menu_field_type, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                addNewField(menuItem.getItemId());
                return true;
            }
        });
        popup.show();
    }

    private void addNewField(int fieldTypeId) {
        /*CardTitleFragment fragment = new CardTitleFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("titleBytes", tb);
        bundle.putByteArray("password", getIntent().getByteArrayExtra("password"));
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.selectCardLinearLayout, fragment, String.valueOf(tb.getId()))
                .addToBackStack(null)
                .commit();*/
        Field field = null;
        switch (fieldTypeId) {
            case R.id.textFieldMenuItem: {
                field = new EditTextFieldFragment();
                break;
            }
            case R.id.numberFieldMenuItem: {
                field = new EditTextFieldFragment();
                ((EditText)field.getActivity().findViewById(R.id.textEditText)).setInputType();
                break;
            }
            case R.id.loginFieldMenuItem: {
                break;
            }
            case R.id.passwordFieldMenuItem: {
                break;
            }
            case R.id.multilineTextFieldMenuItem: {
                break;
            }
            case R.id.dateFieldMenuItem: {
                break;
            }
            case R.id.urlFieldMenuItem: {
                break;
            }
            case R.id.emailFieldMenuItem: {
                break;
            }
            case R.id.pinFieldMenuItem: {
                break;
            }
            default:
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
//                .add(R.id.recordContainLinearLayout, field, "String.valueOf(tb.getId())")
                .add(R.id.recordContainLinearLayout, field)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.card.getTitleBytes() == null) {
//            this.card.setTitleBytes(new TitleBytes(Encoder.encode(getIntent().getByteArrayExtra("password"), "NO TITLE".getBytes(),)));
        }
        card.save(newCard);
//        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }
}
