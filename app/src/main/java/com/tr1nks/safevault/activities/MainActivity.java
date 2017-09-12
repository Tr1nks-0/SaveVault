package com.tr1nks.safevault.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.tr1nks.safevault.R;

/**
 * главное меню приложения
 */
public class MainActivity extends AppCompatActivity {
    /**
     * {@inheritDoc}
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * обработчик кнопки добавить
     *
     * @param view current view
     */
    public void addButtonHandler(View view) {
//        Log.d("DEBUG", "DEBUG");
        getIntent().getByteArrayExtra("password");
    }
}
