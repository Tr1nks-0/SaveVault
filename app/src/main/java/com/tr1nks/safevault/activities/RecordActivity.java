package com.tr1nks.safevault.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tr1nks.safevault.R;

public class RecordActivity extends AppCompatActivity {
    public static final String RECORD_TEMPLATE_TAG = "record_template";
    public static final String RECORD_CONTAIN_LINEAR_LAYOUT = "recordContainLinearLayout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        if(getIntent().getStringExtra("mode").equals("new")){
            // todo new record
        }else {
            //todo open exist record
        }
    }
}
