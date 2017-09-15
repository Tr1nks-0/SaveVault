package com.tr1nks.safevault.entities;

public class PasswordBytes {
    private int id;
    private byte[] title;
    private byte[] data;

    public PasswordBytes(int id, byte[] title, byte[] data) {
        this.id = id;
        this.title = title;
        this.data = data;
    }
}
