package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.util.DBUtil;

public class ImageBytes extends Bytes {
    private byte[] title;
    private byte[] data;

    public ImageBytes(int id, byte[] title, byte[] data, byte[] meta) {
        super(id,meta);
        this.title = title;
        this.data = data;
    }
    @Override
    public void onParentPauseAction() {
        //todo
    }
    @Override
    public Object[] toInsertArr() {
        return new byte[][]{title, data,meta};
    }

    @Override
    public void save() {
        this.id = DBUtil.insertImageBytes(this);
    }

    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {

    }
}
