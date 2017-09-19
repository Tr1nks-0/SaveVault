package com.tr1nks.safevault.entities.bytes;

import com.tr1nks.safevault.util.DBUtil;

public class UserIconBytes {
    private int id;
    private byte[] data;

    public UserIconBytes(int id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public Object[] toInsertArr() {
        return new byte[][]{data};
    }

    public void save() {
        this.id = DBUtil.insertUserIconBytes(this);
    }

    public int getId() {
        return id;
    }
}
