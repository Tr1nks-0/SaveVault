package com.tr1nks.safevault.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.dialogs.AskFieldNameDialogFragment;
import com.tr1nks.safevault.entities.Card;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

/**
 * карточка
 */
public class CardActivity extends AppCompatActivity implements AskFieldNameDialogFragment.AskFieldNameDialogFragmentListener {
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
            card = new Card(this);
        } else {
            newCard = false;
        }

    }

    /**
     * обработчик кнопки добавить поле
     * вызовет диалог и создание поля переходит в {@link CardActivity#onAskFieldNameDialogFragmentPositiveClick(DialogFragment, String, int)}
     *
     * @param view кнопка
     */
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

    /**
     * обработчик диалога создающий новое поле
     *
     * @param dialog      диалог
     * @param fieldTitle  заголовок поля
     * @param fieldTypeId тип поля
     */
    @Override
    public void onAskFieldNameDialogFragmentPositiveClick(DialogFragment dialog, String fieldTitle, int fieldTypeId) {
        switch (fieldTypeId) {
            case R.id.pinFieldMenuItem:
            case R.id.passwordFieldMenuItem: {
                card.createPasswordField(getSupportFragmentManager(), fieldTitle, fieldTypeId);
                break;
            }
            default: {
                card.createTextField(getSupportFragmentManager(), fieldTitle, fieldTypeId);
                break;
            }
        }
    }

    @Override
    protected void onPause() {
        card.onParentPauseAction();
//        super.onPause();
//        LinearLayout layout = (LinearLayout) findViewById(R.id.recordContainLinearLayout);
//        int childcount = layout.getChildCount();
//        for (int i = 0; i < childcount; i++) {
//            String s = layout.getChildAt(i).getContext().getClass().getName();
//            String s2 = layout.getChildAt(i).getClass().getName();
//
//        }
//
//        if (this.card.getTitleBytes() == null) {
////            this.card.setTitleBytes(new TitleBytes(Encoder.encode(getIntent().getByteArrayExtra("password"), "NO TITLE".getBytes(),)));
//        }
//        card.save(newCard);
////        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }


    public byte[] getTitleBytesTitle() {
        return Encoder.encode(UserPasswordManager.getPassword(), ((EditText) findViewById(R.id.recordTitleEditText)).getText().toString().getBytes());
    }

    public byte[] getTitleBytesIcon() {
//        return Encoder.encode(UserPasswordManager.getPassword(), ((EditText) findViewById(R.id.recordIconImageButton)).getText().toString().getBytes());
        return new byte[0];//fixme
    }
}
