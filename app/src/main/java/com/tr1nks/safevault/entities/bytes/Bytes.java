package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.activities.fragments.fields.Field;

public abstract class Bytes {
    int id;
    transient Field field;

    public Bytes() {
    }

    public Bytes(int id) {
        this.id = id;
    }

    public abstract Object[] toInsertArr();

    public abstract void save();

    public abstract void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId);

    public int getId() {
        return id;
    }
}
