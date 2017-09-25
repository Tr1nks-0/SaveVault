package com.tr1nks.safevault.entities.old.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.activities.fragments.old.fields.Field;

public abstract class Bytes {
    long id;
    byte[] meta;
    transient Field field;

    public Bytes() {
    }

    public Bytes(int id, byte[] meta) {
        this.id = id;
        this.meta = meta;
    }

    public Bytes(byte[] meta) {
        this.meta=meta;
    }

    public abstract Object[] toInsertArr();

    public abstract Object[] toUpdateArr();

    public abstract void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId);

    public long getId() {
        return id;
    }

    public byte[] getMeta() {
        return meta;
    }

    public abstract void onParentPauseAction();

    public abstract void open(FragmentManager fragmentManager);
}
