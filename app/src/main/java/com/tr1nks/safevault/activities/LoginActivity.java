package com.tr1nks.safevault.activities;

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
import com.tr1nks.safevault.util.Encoder;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = ((EditText) findViewById(R.id.passwordEditText));
        String pasw = editText.getText().toString();
        String inpData = "Test input data for encode";
        byte[] e = Encoder.encode(Encoder.preparePassw(pasw.getBytes()), inpData.getBytes());
        byte[] d = Encoder.decode(Encoder.preparePassw(pasw.getBytes()), e);
        String dec = new String(d);
        Log.d("DEBUG",dec);
//        intent.putExtra("name", name.getText().toString()); // указываем первым параметром ключ, а второе значение
//        intent.putExtra("lastname", lastName.getText().toString());  // по ключу мы будем получать значение с Intent
        //todo password check
        // todo if password - decode data
        startActivity(intent);
    }
}