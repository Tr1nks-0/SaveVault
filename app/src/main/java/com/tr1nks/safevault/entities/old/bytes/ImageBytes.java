package com.tr1nks.safevault.entities.old.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.util.DBUtil;

public class ImageBytes extends Bytes {
    private byte[] title;
    private byte[] data;

    public ImageBytes(int id, byte[] title, byte[] data, byte[] meta) {
        super(id, meta);
        this.title = title;
        this.data = data;
    }

    @Override
    public void onParentPauseAction() {
        field.onParentPauseAction();//todo in field
        if (this.id == 0) {
            this.id = DBUtil.insertImageBytes(this);
        } else {
            DBUtil.updateImageBytes(this);
        }
    }

    @Override
    public void open(FragmentManager fragmentManager) {

    }

    @Override
    public Object[] toInsertArr() {
        return new byte[][]{title, data, meta};
    }

    @Override
    public Object[] toUpdateArr() {
        return new Object[]{title, data, meta, id};
    }


    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {

    }

    public byte[] getData() {
        return data;
    }

    public byte[] getTitle() {
        return title;
    }
}
