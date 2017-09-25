package com.tr1nks.safevault.entities.fields;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.entities.meta.Meta;

public abstract class FieldEntity {
    byte[] data;
    byte[] title;
    byte[] meta;

    public abstract void open(FragmentManager fragmentManager);

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getTitle() {
        return title;
    }

    public void setTitle(byte[] title) {
        this.title = title;
    }

    public byte[] getMeta() {
        return meta;
    }

    public abstract Meta getMetaObj();

    public abstract void setMetaObj(Meta meta);
}
