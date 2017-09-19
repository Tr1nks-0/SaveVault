package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.fields.DateFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.EditTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.MultilineTextFieldFragment;
import com.tr1nks.safevault.util.DBUtil;

public class TextBytes extends Bytes {
    private byte[] title;
    private byte[] data;


    public TextBytes() {
    }

    public TextBytes(int id, byte[] title, byte[] data) {
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
        this.id = DBUtil.insertTextBytes(this);
    }

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

    public void setData(byte[] data) {
        this.data = data;
    }
}
