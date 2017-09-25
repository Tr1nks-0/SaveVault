package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.fields.DateFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.EditTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.MultilineTextFieldFragment;
import com.tr1nks.safevault.util.DBUtil;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

public class TextBytes extends Bytes {
    private byte[] title;
    private byte[] data;


    public TextBytes() {
    }

    public TextBytes(byte[] meta) {
        super(meta);
    }

    public TextBytes(byte[] title, byte[] meta) {
        super(meta);
        this.title = title;
    }

    public TextBytes(int id, byte[] title, byte[] data, byte[] meta) {
        super(id, meta);
        this.title = title;
        this.data = data;
    }

    @Override
    public void onParentPauseAction() {
        field.onParentPauseAction();
        if (this.id == 0) {
            this.id = DBUtil.insertTextBytes(this);
        } else {
            DBUtil.updateTextBytes(this);
        }
    }

    @Override
    public void open(FragmentManager fragmentManager) {
        createFieldFragment(fragmentManager, new String(Encoder.decode(UserPasswordManager.getPassword(), title)), 0);//0 - stub
        this.field.setEditable(false);
    }

    @Override
    public Object[] toInsertArr() {
        return new byte[][]{title, data, meta};
    }

    @Override
    public Object[] toUpdateArr() {
        return new Object[]{title, data, meta, id};
    }

//    @Override
//    public void save() {
//        this.id = DBUtil.insertTextBytes(this);
//    }

    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {
        if (fieldTypeId == R.id.multilineTextFieldMenuItem) {
            this.field = new MultilineTextFieldFragment();
        } else if (fieldTypeId == R.id.dateFieldMenuItem) {
            this.field = new DateFieldFragment();
        } else {
            this.field = new EditTextFieldFragment();
        }
        this.field.setBytes(this);
        fragmentManager
                .beginTransaction()
                .add(R.id.recordContainLinearLayout, this.field, this.field.getClass().getName() + "_" + title)
                .addToBackStack(null)
                .commit();
    }

    //get set
    public void setTitle(byte[] title) {
        this.title = title;
    }

    public byte[] getTitle() {
        return title;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
