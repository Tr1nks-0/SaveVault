package com.tr1nks.safevault.entities.fields;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.entities.meta.Meta;
import com.tr1nks.safevault.entities.meta.PasswordEntityMeta;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.Serializer;

public class PasswordFieldEntity extends FieldEntity {
    public PasswordFieldEntity(String fieldTitle) {
        setTitleString(fieldTitle);
    }

    @Override
    public void open(FragmentManager fragmentManager) {
        //todo
    }

    public String getTitleString() {
        return (String) Serializer.readFrBytes(Encoder.decode(this.title));
    }

    public void setTitleString(String title) {
        this.title = Encoder.encode(Serializer.writeInBytes(title));
    }

    public String getPasswordString() {
        return (String) Serializer.readFrBytes(Encoder.decode(this.data));
    }

    public void setPasswordString(String data) {
        this.data = Encoder.encode(Serializer.writeInBytes(data));
    }

    @Override
    public PasswordEntityMeta getMetaObj() {
        return (PasswordEntityMeta) Serializer.readFrBytes(Encoder.decode(this.data));
    }

    @Override
    public void setMetaObj(Meta meta) {
        this.meta = Encoder.encode(Serializer.writeInBytes(meta));
    }
}
