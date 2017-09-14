package com.tr1nks.safevault.entities;

import android.content.Context;
import android.view.View;
import com.tr1nks.safevault.R;

public class DateTimeField extends Field {
    public static final String LAYOUT_FILENAME = "field_date_time";
    private static final String TITLE_ID_TAG = "titleForDatetimeEditText";
    private static final String EDIT_TEXT_ID_TAG = "datetimeEditText";

    @Override
    public View getView(Context context) {
        return View.inflate(context, R.layout.field_date_time, null);
    }
}
