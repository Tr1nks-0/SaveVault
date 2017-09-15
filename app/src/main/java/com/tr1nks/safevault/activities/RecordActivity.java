package com.tr1nks.safevault.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.RowMainMenu;
import com.tr1nks.safevault.util.Encoder;

public class RecordActivity extends AppCompatActivity {
    public static final String RECORD_TEMPLATE_TAG = "record_template";
    public static final String RECORD_CONTAIN_LINEAR_LAYOUT = "recordContainLinearLayout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Bundle b = this.getIntent().getExtras();
        if (b.getString("mode").equals("new")) {
            // todo new record
        } else {
            RowMainMenu rowMainMenu = b.getParcelable("row");
            ((EditText) this.findViewById(R.id.recordTitleEditText)).setText(
                    new String(Encoder.decode(b.getByteArray("password"), rowMainMenu.getTitle()))
            );
        }
    }
}
