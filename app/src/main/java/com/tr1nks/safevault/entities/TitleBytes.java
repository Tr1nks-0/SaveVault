package com.tr1nks.safevault.entities;

import java.util.ArrayList;

public class TitleBytes {
    private int id;
    private byte[] title;
    private byte[] icon;
    private byte[] meta;
    private byte[] text_ids;
    private byte[] password_ids;
    private byte[] image_ids;
    private byte[] icon_ids;

    public TitleBytes(int id, byte[] title, byte[] icon, byte[] meta, byte[] text_ids, byte[] password_ids, byte[] image_ids, byte[] icon_ids) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.meta = meta;
        this.text_ids = text_ids;
        this.password_ids = password_ids;
        this.image_ids = image_ids;
        this.icon_ids = icon_ids;
    }

    public ArrayList<Integer> getTextIds(byte[] password) {
        return null;//stub todo fixme
    }

    public ArrayList<Integer> getPasswordIds(byte [] password) {
        return null;//stub todo fixme
    }

    public ArrayList<Integer> getImageIds(byte [] password) {
        return null;//stub todo fixme
    }

    public ArrayList<Integer> getUserIconIds(byte [] password) {
        return null;//stub todo fixme
    }
}
