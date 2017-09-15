package com.tr1nks.safevault.entities.fields;

import android.content.Context;
import android.view.View;
import com.tr1nks.safevault.R;

public class MultilineTextField extends Field {
    public static final String LAYOUT_FILENAME = "field_multiline_text";
    private static final String TITLE_ID_TAG = "titleForMultilineEditText";
    private static final String EDIT_TEXT_ID_TAG = "multilineEditText";
    @Override
    public View getView(Context context) {
         return View.inflate(context, R.layout.field_multiline_text,null);
    }
}
