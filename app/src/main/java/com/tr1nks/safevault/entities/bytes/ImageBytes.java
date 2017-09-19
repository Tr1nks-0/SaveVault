package com.tr1nks.safevault.entities.bytes;

import com.tr1nks.safevault.util.DBUtil;

public class ImageBytes {
    private int id;
    private byte[] title;
    private byte[] data;

    public ImageBytes(int id, byte[] title, byte[] data) {
        this.id = id;
        this.title = title;
        this.data = data;
    }

    public Object[] toInsertArr() {
        return new byte[][]{title, data};
    }

    public void save() {
        this.id = DBUtil.insertImageBytes(this);
    }

    public int getId() {
        return id;
    }
}
