package com.tr1nks.safevault.entities.meta;

import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.Serializer;

public class PasswordEntityMeta extends Meta {
    private byte[] fieldType;

    public PasswordEntityMeta(int fieldTypeId) {
        setFieldTypeInt(fieldTypeId);
    }

    public int getFieldTypeInt() {
        return (Integer) Serializer.readFrBytes(Encoder.decode(this.fieldType));
    }

    public byte[] getFieldType() {
        return fieldType;
    }

    public void setFieldTypeInt(int fieldType) {
        this.fieldType = Encoder.encode(Serializer.writeInBytes(fieldType));
    }

    public void setFieldType(byte[] fieldType) {
        this.fieldType = fieldType;
    }
}
