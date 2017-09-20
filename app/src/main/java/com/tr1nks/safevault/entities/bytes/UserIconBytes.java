package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.util.DBUtil;

public class UserIconBytes extends Bytes {
    private byte[] data;

    public UserIconBytes(int id, byte[] data, byte[] meta) {
        super(id,meta);
        this.data = data;
    }

    @Override
    public Object[] toInsertArr() {
        return new byte[][]{data,meta};
    }

    @Override
    public void save() {
        this.id = DBUtil.insertUserIconBytes(this);
    }

    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {

    }

    @Override
    public void onParentPauseAction() {
        //todo
    }
}
