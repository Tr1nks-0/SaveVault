package com.tr1nks.safevault.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.dialogs.AskDialogFragment;
import com.tr1nks.safevault.activities.dialogs.CreatePasswordDialogFragment;
import com.tr1nks.safevault.util.DBUtil;
import com.tr1nks.safevault.util.Encoder;

/**
 * стартовое окно приложения, авторизация пользователя, проверка наличия базы в системе, вызов создания базы
 */
public class LoginActivity extends AppCompatActivity implements AskDialogFragment.AskDialogListener, CreatePasswordDialogFragment.CreatePasswordDialogListener {
    /**
     * {@inheritDoc}
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ((EditText) findViewById(R.id.passwordEditText)).setText("");
//        ((CheckBox) findViewById(R.id.showPasswCheckBox)).setOnCheckedChangeListener(this::showPasswOnCheckedChangeListener);
        ((CheckBox) findViewById(R.id.showPasswCheckBox)).setOnCheckedChangeListener(showPasswOnCheckedChangeListener());
//debug
//        MessageDialogFragment dialog = MessageDialogFragment.createCreatePasswordDialogFragment("title", "message");
//        CreatePasswordDialogFragment dialog2 = CreatePasswordDialogFragment.createCreatePasswordDialogFragment("title 2", "message 2");
//        CreatePasswordDialogFragment dialog = CreatePasswordDialogFragment.createCreatePasswordDialogFragment("TEST", "TEST TEST TEST");
//        dialog.show(getFragmentManager(), "Test_Dialog");
//        dialog2.show(getFragmentManager(), "Test_Dialog");
        if (!dbFileExistsCheck()) {
            askCreateDb();
        }
    }


    /**
     * создание handler для CheckBox показать пароль
     *
     * @return handler для CheckBox показать пароль
     */
    private CompoundButton.OnCheckedChangeListener showPasswOnCheckedChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                EditText editText = ((EditText) findViewById(R.id.passwordEditText));
                int pos = editText.getSelectionEnd();
                if (b) {
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    editText.setInputType(129);
                }
                editText.setSelection(pos);
            }
        };
    }
//    /**
//     * показать спрятать пароль обработчик checkbox
//     *
//     * @param compoundButton checkbox
//     * @param b              checkbox is checked
//     */
//        private void showPasswOnCheckedChangeListener(CompoundButton compoundButton, boolean b) {
//            EditText editText = ((EditText) findViewById(R.id.passwordEditText));
//            int pos = editText.getSelectionEnd();
//            if (b) {
//                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//            } else {
//                editText.setInputType(129);
//            }
//            editText.setSelection(pos);
//        }

    /**
     * handler для кнопки ok
     *
     * @param view current view
     */
    public void okButtonHandler(View view) {
//        byte[] s = DBUtil.CHECK_PASSW_STR.getBytes();
//        byte[] o = Encoder.encode(Encoder.preparePassw("root".getBytes()), s);
//        int i1 = s.length;
//        int i2 = o.length;
//        Log.d("ENCODED:", Arrays.toString(o));
        if (dbFileExistsCheck()) {
            DBUtil dbUtil = DBUtil.getInstance(this);
            if (dbUtil.dbExists(this)) {
                String pasw = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
                if (null != pasw && !"".equals(pasw)) {
                    byte[] paswBytes = Encoder.preparePassw(pasw.getBytes());
                    if (testPassword(paswBytes, dbUtil)) {
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("password", paswBytes);
                        startActivity(intent);
                    } else {
//                    MessageDialogFragment dialog = MessageDialogFragment.createCreatePasswordDialogFragment("Введите пароль", "Поле с паролем пусто, введите пароль");
//                    dialog.show(getFragmentManager(), "Wrong_Passw_Dialog");
                    }
                } else {
//                MessageDialogFragment dialog = MessageDialogFragment.createCreatePasswordDialogFragment("Неверный пароль", "Пароль неверен, проверьте правильность и повторите ввод");
//                dialog.show(getFragmentManager(), "Empty_Passw_Dialog");
                }
            } else {
                //todo create db ask
            }
        } else {
            askCreateDb();
        }
    }

    /**
     * проверить пароль на правильность
     *
     * @param password пароль
     * @param dbUtil   утилита работы с бд
     * @return true если пароль верный
     */
    private boolean testPassword(byte[] password, DBUtil dbUtil) {
        byte b[] = Encoder.decode(Encoder.preparePassw(password), dbUtil.getCheckData());
        //        EditText editText = ((EditText) findViewById(R.id.passwordEditText));
//        String pasw = editText.getText().toString();
//        String inpData = "Test input data for encode";
//        byte[] e = Encoder.encode(Encoder.preparePassw(pasw.getBytes()), inpData.getBytes());
//        byte[] d = Encoder.decode(Encoder.preparePassw(pasw.getBytes()), e);
//        String dec = new String(d);
        return true;
    }

    private boolean dbFileExistsCheck() {
        return getDatabasePath(DBUtil.DATABASE_NAME).exists();
    }

    private void askCreateDb() {
        AskDialogFragment askDialog = AskDialogFragment.createAskDialogFragment(getString(R.string.dialogs_ask_create_db_title), getString(R.string.dialogs_ask_create_db_message), String.valueOf(android.R.drawable.ic_dialog_alert));
        askDialog.show(getFragmentManager(), AskDialogFragment.ASK_DIALOG_NAME);
    }

    @Override
    public void onAskDialogPositiveClick(DialogFragment dialog) {
        DBUtil.createDb(this);
        CreatePasswordDialogFragment createPasswDialog = CreatePasswordDialogFragment.createCreatePasswordDialogFragment(getString(R.string.dialogs_create_passw_create_db_pasw_title), getString(R.string.dialogs_create_passw_create_db_pasw_message));
        createPasswDialog.show(getFragmentManager(), CreatePasswordDialogFragment.CREATE_PASSWORD_DIALOG_NAME);
    }

    @Override
    public void onAskDialogNegativeClick(DialogFragment dialog) {
//        Log.d("W", "");
        //todo  don't create new db
    }

    @Override
    public void onCreatePasswordDialogPositiveClick(DialogFragment dialog,String passw) {
        Log.d("W", "");
    }

    @Override
    public void onCreatePasswordDialogNegativeClick(DialogFragment dialog) {
        Log.d("W", "");
    }
}