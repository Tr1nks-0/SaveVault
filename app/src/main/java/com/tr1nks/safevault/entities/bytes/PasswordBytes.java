package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.util.DBUtil;

public class PasswordBytes extends Bytes {
    private byte[] title;
    private byte[] data;

    public PasswordBytes() {}
    public PasswordBytes(int id, byte[] title, byte[] data) {
        super(id);
        this.title = title;
        this.data = data;
    }

    @Override
    public Object[] toInsertArr() {
        return new byte[][]{title, data};
    }

    @Override
    public void save() {
        this.id = DBUtil.insertPasswordBytes(this);
    }

    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {
//        if (fieldTypeId == R.id.multilineTextFieldMenuItem) {
//            field = new MultilineTextFieldFragment();
//            field.setBytes(this);
//        } else {
//            field = new EditTextFieldFragment();
//            field.setBytes(this);
//        }
//        fragmentManager
//                .beginTransaction()
//                .add(R.id.recordContainLinearLayout, field, field.getClass().getName() + "_" + title)
//                .addToBackStack(null)
//                .commit();
    }

}
