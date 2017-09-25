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
import com.tr1nks.safevault.entities.CardNew;

/**
 * карточка
 */
public class CardActivityNew extends AppCompatActivity implements AskFieldNameDialogFragment.AskFieldNameDialogFragmentListener {
    private CardNew card;
//    private boolean newCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_card);
        Bundle b = this.getIntent().getExtras();
        this.card = b.getParcelable("card");
//        TitleBytes tb = b.getParcelable("titleBytes");
//        this.card = new Card(this, tb);
//        if (b.getString("mode").equals("new")) {
//            this.newCard = true;
//            //make edit button invisible
//        } else {
//            this.newCard = false;
//            //make edit button visible
        EditText titleEditText = ((EditText) findViewById(R.id.recordTitleEditText));
        titleEditText.setText(this.card.getCardTitle().getTitleString());
        this.card.open(getSupportFragmentManager());

    }

    private void onEditButtonHandler(View view) {//todo
//titleEditText.setKeyListener((KeyListener) titleEditText.getTag());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * обработчик кнопки добавить поле
     * вызовет диалог и создание поля переходит в {@link CardActivityNew#onAskFieldNameDialogFragmentPositiveClick(DialogFragment, String, int)}
     *
     * @param view кнопка
     */
    public void addFieldButtonHandler(View view) {
        PopupMenu popup = new PopupMenu(CardActivityNew.this, findViewById(R.id.addFieldButton));
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
                this.card.createPasswordField(getSupportFragmentManager(), fieldTitle, fieldTypeId);
                break;
            }
            default: {
                this.card.createTextField(getSupportFragmentManager(), fieldTitle, fieldTypeId);
                break;
            }
        }
    }

    @Override
    protected void onPause() {
//        super.onPause();
//        this.card.onParentPauseAction();
//        Log.d("", "");
    }
}
