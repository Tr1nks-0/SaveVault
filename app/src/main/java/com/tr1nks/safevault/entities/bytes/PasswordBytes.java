package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.fields.PasswordFieldFragment;
import com.tr1nks.safevault.util.DBUtil;

public class PasswordBytes extends Bytes {
    private byte[] title;
    private byte[] data;

    public PasswordBytes() {
    }

    public PasswordBytes(byte[] meta) {
        super(meta);
    }

    public PasswordBytes(byte[] title, byte[] meta) {
        super(meta);
        this.title = title;
    }

    public PasswordBytes(int id, byte[] title, byte[] data, byte[] meta) {
        super(id, meta);
        this.title = title;
        this.data = data;
    }

    @Override
    public Object[] toInsertArr() {
        return new byte[][]{title, data, meta};
    }

    @Override
    public void save() {
        this.id = DBUtil.insertPasswordBytes(this);
    }

    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {
        this.field = new PasswordFieldFragment();
        this.field.setBytes(this);
        fragmentManager
                .beginTransaction()
                .add(R.id.recordContainLinearLayout, this.field, this.field.getClass().getName() + "_" + title)
                .addToBackStack(null)
                .commit();
    }

    public byte[] getTitle() {
        return this.title;
    }
}
