package com.tr1nks.safevault.entities.fields;

import android.content.Context;
import android.view.View;
import com.tr1nks.safevault.R;

public class EditTextField extends Field {
    public static final String LAYOUT_FILENAME = "field_edit_text";
    private static final String TITLE_ID_TAG = "titleForTextEditText";
    private static final String EDIT_TEXT_ID_TAG = "textEditText";

    @Override
    public View getView(Context context) {
        return View.inflate(context, R.layout.field_edit_text,null);
    }
    //  *** text (текст)
    //  ** number (номер)
    //  ** login (логин)
    //  ** site (url) (сайт)
    //  ** mail (адрес почты)
    //  ** phone (телефон)
}
