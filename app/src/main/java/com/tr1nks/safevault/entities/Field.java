package com.tr1nks.safevault.entities;


import android.content.Context;
import android.view.View;

import java.io.Serializable;

public abstract class Field implements Serializable {
    protected static final String DELETE_IMG_BUTT_ID_TAG = "deleteFieldImageButton";

    public abstract View getView(Context context);
}
