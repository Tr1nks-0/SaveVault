package com.tr1nks.safevault.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.util.DBUtil;
import com.tr1nks.safevault.util.Encoder;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((EditText) findViewById(R.id.passwordEditText)).setText("");
//        ((CheckBox) findViewById(R.id.showPasswCheckBox)).setOnCheckedChangeListener(this::showPasswOnCheckedChangeListener);
        ((CheckBox) findViewById(R.id.showPasswCheckBox)).setOnCheckedChangeListener(showPasswOnCheckedChangeListener());
    }

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

    public void okButtonHandler(View view) {
        DBUtil dbUtil = DBUtil.getInstance(this);
        boolean b=dbUtil.dbExists(this);
        if (dbUtil.dbExists(this)) {
            if (testPassword(((EditText) findViewById(R.id.passwordEditText)).getText().toString(), dbUtil)) {
                Intent intent = new Intent(this, MainActivity.class);
                //        intent.putExtra("name", name.getText().toString()); // указываем первым параметром ключ, а второе значение
//        intent.putExtra("lastname", lastName.getText().toString());  // по ключу мы будем получать значение с Intent
                startActivity(intent);
            } else {
//            todo mess that wrong passw
            }
        } else {
            //todo create password
        }
    }

    private boolean testPassword(String password, DBUtil dbUtil) {
        byte d[]=dbUtil.getCheckData();
        byte b[] = Encoder.decode(Encoder.preparePassw(password.getBytes()), dbUtil.getCheckData());
        //        EditText editText = ((EditText) findViewById(R.id.passwordEditText));
//        String pasw = editText.getText().toString();
//        String inpData = "Test input data for encode";
//        byte[] e = Encoder.encode(Encoder.preparePassw(pasw.getBytes()), inpData.getBytes());
//        byte[] d = Encoder.decode(Encoder.preparePassw(pasw.getBytes()), e);
//        String dec = new String(d);
        return true;
    }
}