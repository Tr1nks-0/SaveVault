package com.tr1nks.safevault.entities.bytes;

public class ImageBytes {
    private int id;
    private byte[] title;
    private byte[] data;

    public ImageBytes(int id, byte[] title, byte[] data) {
        this.id = id;
        this.title = title;
        this.data = data;
    }
}
