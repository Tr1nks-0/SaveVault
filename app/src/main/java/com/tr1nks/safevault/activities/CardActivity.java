package com.tr1nks.safevault.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.dialogs.AskFieldNameDialogFragment;
import com.tr1nks.safevault.activities.fragments.fields.*;
import com.tr1nks.safevault.entities.Card;
import com.tr1nks.safevault.entities.bytes.TextBytes;

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
        Field field;
        Bundle bundle = new Bundle();
        bundle.putString("title", fieldTitle);
        bundle.putInt("type", fieldTypeId);
        bundle.putByteArray("password", getIntent().getByteArrayExtra("password"));
        switch (fieldTypeId) {
            case R.id.multilineTextFieldMenuItem: {
                field = new MultilineTextFieldFragment();
                TextBytes textBytes = new TextBytes();
                bundle.putParcelable("textBytes", textBytes);
                card.addTextBytes(textBytes);
                break;
            }
            case R.id.passwordFieldMenuItem: {
                field = new PasswordFieldFragment();
                break;
            }
            case R.id.dateFieldMenuItem: {
                field = new DateFieldFragment();
                break;
            }
            default: {
                field = new EditTextFieldFragment();
                TextBytes textBytes = new TextBytes();
                bundle.putParcelable("textBytes", textBytes);
                card.addTextBytes(textBytes);
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
        LinearLayout layout = (LinearLayout) findViewById(R.id.recordContainLinearLayout);
        int childcount = layout.getChildCount();
        for (int i=0; i < childcount; i++){
            String s =layout.getChildAt(i).getContext().getClass().getName();
            String s2 =layout.getChildAt(i).getClass().getName();

        }

        if (this.card.getTitleBytes() == null) {
//            this.card.setTitleBytes(new TitleBytes(Encoder.encode(getIntent().getByteArrayExtra("password"), "NO TITLE".getBytes(),)));
        }
        card.save(newCard);
//        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }


}
