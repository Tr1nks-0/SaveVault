package com.tr1nks.safevault.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.dialogs.AskDialogFragment;
import com.tr1nks.safevault.activities.dialogs.CreatePasswordDialogFragment;
import com.tr1nks.safevault.activities.dialogs.MessageDialogFragment;
import com.tr1nks.safevault.util.DBUtil;
import com.tr1nks.safevault.util.Encoder;

import java.util.Arrays;

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
        ((CheckBox) findViewById(R.id.showPasswCheckBox)).setOnCheckedChangeListener(showPasswOnCheckedChangeListener());
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

    /**
     * handler для кнопки ok
     *
     * @param view current view
     */
    public void okButtonHandler(View view) {
        if (dbFileExistsCheck()) {
            byte[] password = Encoder.preparePassw(((EditText) findViewById(R.id.passwordEditText)).getText().toString().getBytes());
            if (testPassword(password)) {
                openMainActivity(password);
            } else {
                MessageDialogFragment dialog = MessageDialogFragment.createMessageDialogFragment(getString(R.string.dialogs_message_wrong_passw_title), getString(R.string.dialogs_message_wrong_passw_message), String.valueOf(android.R.drawable.ic_dialog_alert));
                dialog.show(getFragmentManager(), MessageDialogFragment.MESSAGE_DIALOG_NAME);
            }
        } else {
            askCreateDb();
        }
    }

    /**
     * проверить пароль на правильность
     *
     * @param password пароль
     * @return true если пароль верный
     */
    private boolean testPassword(byte[] password) {
        DBUtil.initWorker(this);
        byte b[] = Encoder.decode(Encoder.preparePassw(password), DBUtil.getCheckData());
      boolean bw =null !=b && Arrays.equals(b, DBUtil.CHECK_PASSW_STR.getBytes());
        return null !=b && Arrays.equals(b, DBUtil.CHECK_PASSW_STR.getBytes());
    }

    /**
     * Запрашивает о необходимости создания БД
     */
    private void askCreateDb() {
        AskDialogFragment askDialog = AskDialogFragment.createAskDialogFragment(getString(R.string.dialogs_ask_create_db_title), getString(R.string.dialogs_ask_create_db_message), String.valueOf(android.R.drawable.ic_dialog_alert));
        askDialog.show(getFragmentManager(), AskDialogFragment.ASK_DIALOG_NAME);
    }

    /**
     * обработчик положительного ответа о создании базы
     *
     * @param dialog даилог
     */
    @Override
    public void onAskDialogPositiveClick(DialogFragment dialog) {
        DBUtil.createDb(this);
        CreatePasswordDialogFragment createPasswDialog = CreatePasswordDialogFragment.createCreatePasswordDialogFragment(getString(R.string.dialogs_create_passw_create_db_passw_title), getString(R.string.dialogs_create_passw_create_db_passw_message));
        createPasswDialog.show(getFragmentManager(), CreatePasswordDialogFragment.CREATE_PASSWORD_DIALOG_NAME);
    }

    /**
     * обработчик отрицательного ответа о создании базы
     *
     * @param dialog даилог
     */
    @Override
    public void onAskDialogNegativeClick(DialogFragment dialog) {
//        Log.d("W", "");
        //todo  don't create new db
    }

    /**
     * обработчик ввода пароля для новой бд
     *
     * @param dialog диалог
     * @param passw  пароль
     */
    @Override
    public void onCreatePasswordDialogPositiveClick(DialogFragment dialog, String passw) {
        byte[] passwBytes = Encoder.preparePassw(passw.getBytes());
        DBUtil.setCheckData(Encoder.encode(passwBytes, DBUtil.CHECK_PASSW_STR.getBytes()));
        openMainActivity(passwBytes);
    }

    /**
     * проверяет на существование файлов бд
     *
     * @return true если существуют
     */
    private boolean dbFileExistsCheck() {
        return getDatabasePath(DBUtil.DATABASE_NAME).exists();
    }

    /**
     * открыть Main Activity
     *
     * @param passwBytes подготовленный пароль
     */
    private void openMainActivity(byte[] passwBytes) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("password", passwBytes);
        startActivity(intent);
    }
}