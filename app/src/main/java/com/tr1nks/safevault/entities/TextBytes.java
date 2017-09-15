package com.tr1nks.safevault.entities;

public class TextBytes {
    private int id;
    private byte[] title;
    private byte[] data;

    public TextBytes(int id, byte[] titles, byte[] data) {
        this.id = id;
        this.title = titles;
        this.data = data;
    }
}
