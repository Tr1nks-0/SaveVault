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
import com.tr1nks.safevault.activities.fragments.MessageDialogFragment;
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
//        byte[] s = DBUtil.CHECK_PASSW_STR.getBytes();
//        byte[] o = Encoder.encode(Encoder.preparePassw("root".getBytes()), s);
//        int i1 = s.length;
//        int i2 = o.length;
//        Log.d("ENCODED:", Arrays.toString(o));
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
                    MessageDialogFragment dialog = new MessageDialogFragment();
                    dialog.show(getFragmentManager(), "Wrong_Passw_Dialog"); //fixme
                }
            } else {
                MessageDialogFragment dialog = new MessageDialogFragment();
                dialog.show(getFragmentManager(), "Empty_Passw_Dialog"); //fixme
            }
        } else {
            //todo create db ask
        }
    }

    private boolean testPassword(byte[] password, DBUtil dbUtil) {
        byte b[] = Encoder.decode(password, dbUtil.getCheckData());
        //        EditText editText = ((EditText) findViewById(R.id.passwordEditText));
//        String pasw = editText.getText().toString();
//        String inpData = "Test input data for encode";
//        byte[] e = Encoder.encode(Encoder.preparePassw(pasw.getBytes()), inpData.getBytes());
//        byte[] d = Encoder.decode(Encoder.preparePassw(pasw.getBytes()), e);
//        String dec = new String(d);
        return true;
    }
}