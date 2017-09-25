package com.tr1nks.safevault.entities.meta;

import com.tr1nks.safevault.util.Serializer;

import java.io.Serializable;

public abstract class Meta implements Serializable {
    public static byte[] serialize(FieldMeta meta) {
        return Serializer.writeInBytes(meta);
    }

    public static FieldMeta restore(byte[] arr) {
        return (FieldMeta) Serializer.readFrBytes(arr);
    }

}
