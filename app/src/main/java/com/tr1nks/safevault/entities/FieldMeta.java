package com.tr1nks.safevault.entities;

import com.tr1nks.safevault.util.Serializer;

import java.io.Serializable;

public class FieldMeta implements Serializable {
    public static byte[] serialize(FieldMeta meta) {
        return Serializer.writeInBytes(meta);
    }

    public static FieldMeta restore(byte[] arr) {
        return (FieldMeta) Serializer.readFrBytes(arr);
    }

    private int fieldType;
    private int fieldOrder;

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(int fieldOrder) {
        this.fieldOrder = fieldOrder;
    }
}
