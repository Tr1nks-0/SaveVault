package com.tr1nks.safevault.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupMenu;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.dialogs.AskFieldNameDialogFragment;
import com.tr1nks.safevault.activities.fragments.fields.EditTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.Field;
import com.tr1nks.safevault.activities.fragments.fields.MultilineTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.PasswordFieldFragment;
import com.tr1nks.safevault.entities.Card;

/**
 * карточка
 */
public class CardActivity extends AppCompatActivity implements AskFieldNameDialogFragment.AskFieldNameDialogFragmentListener {
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
                Bundle b = new Bundle();
                b.putInt("type", menuItem.getItemId());
                AskFieldNameDialogFragment dialogFragment = AskFieldNameDialogFragment.createAskFieldNameDialogFragment(b);
                dialogFragment.show(getFragmentManager(), AskFieldNameDialogFragment.ASK_FIELD_TYPE_DIALOG_NAME);
                return true;
            }
        });
        popup.show();
    }

    @Override
    public void onAskFieldNameDialogFragmentPositiveClick(DialogFragment dialog, String title, int type) {
        addNewField(type, title);
    }

    private void addNewField(int fieldTypeId, String fieldTitle) {
        Field field = null;
        Bundle bundle = new Bundle();
        bundle.putString("title", fieldTitle);
        bundle.putInt("type", fieldTypeId);
        switch (fieldTypeId) {
            case R.id.multilineTextFieldMenuItem: {
                field = new MultilineTextFieldFragment();
                break;
            }
            case R.id.passwordFieldMenuItem: {
                field = new PasswordFieldFragment();
                break;
            }
            case R.id.dateFieldMenuItem: {
                //todo
                break;
            }
            default: {
                field = new EditTextFieldFragment();
                break;
            }
        }
        field.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.recordContainLinearLayout, field, field.getClass().getName() + "_" + fieldTitle)
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
