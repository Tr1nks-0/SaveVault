package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.util.DBUtil;

public class UserIconBytes extends Bytes {
    private byte[] data;

    public UserIconBytes(int id, byte[] data, byte[] meta) {
        super(id, meta);
        this.data = data;
    }

    @Override
    public Object[] toInsertArr() {
        return new byte[][]{data, meta};
    }

    @Override
    public Object[] toUpdateArr() {
        return new Object[]{data, meta, id};
    }


    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {

    }

    @Override
    public void onParentPauseAction() {
        field.onParentPauseAction();//todo in field
        if (this.id == 0) {
            this.id = DBUtil.insertUserIconBytes(this);
        } else {
            DBUtil.updateUserIconBytes(this);
        }
    }

    public byte[] getData() {
        return data;
    }
}
