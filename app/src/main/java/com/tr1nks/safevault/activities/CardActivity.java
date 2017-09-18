package com.tr1nks.safevault.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupMenu;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.fields.EditTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.Field;
import com.tr1nks.safevault.activities.fragments.fields.MultilineTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.PasswordFieldFragment;
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

        Field field = null;
        if (fieldTypeId == R.id.multilineTextFieldMenuItem) {
            field = new MultilineTextFieldFragment();
        } else if (fieldTypeId == R.id.passwordFieldMenuItem) {
            field = new PasswordFieldFragment();
            if (fieldTypeId == R.id.pinFieldMenuItem) {
                ((EditTextFieldFragment) field).setEditTextType(129 | InputType.TYPE_CLASS_NUMBER);
            }
        } else if (fieldTypeId == R.id.dateFieldMenuItem) {
        } else {
            field = new EditTextFieldFragment();
            switch (fieldTypeId) {
                case R.id.numberFieldMenuItem: {
                    ((EditTextFieldFragment) field).setEditTextType(InputType.TYPE_CLASS_NUMBER);
                }
                case R.id.loginFieldMenuItem: {
                    ((EditTextFieldFragment) field).setEditTextType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                case R.id.urlFieldMenuItem: {
                    ((EditTextFieldFragment) field).setEditTextType(InputType.TYPE_TEXT_VARIATION_URI);
                }
                case R.id.emailFieldMenuItem: {
                    ((EditTextFieldFragment) field).setEditTextType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                }
                default: {
                    break;
                }
            }
        }

        if (fieldTypeId == R.id.multilineTextFieldMenuItem)

        {

        } else if (fieldTypeId == R.id.dateFieldMenuItem)

        {
            //todo
        } else

        {

        }

        getSupportFragmentManager()
                .

                        beginTransaction()
//                .add(R.id.recordContainLinearLayout, field, "String.valueOf(tb.getId())")
                .

                        add(R.id.recordContainLinearLayout, field)
                .

                        addToBackStack(null)
                .

                        commit();

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
