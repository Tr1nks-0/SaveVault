package com.tr1nks.safevault.entities.fields;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.fragments.fields.EditTextFieldFragment;
import com.tr1nks.safevault.activities.fragments.fields.FieldFragment;
import com.tr1nks.safevault.entities.meta.Meta;
import com.tr1nks.safevault.entities.meta.TextEntityMeta;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.Serializer;

public class TextFieldEntity extends FieldEntity {
    public TextFieldEntity(String fieldTitle) {
        setTitleString(fieldTitle);
    }

    @Override
    public void open(FragmentManager fragmentManager) {
        int fieldType = getMetaObj().getFieldTypeInt();
        FieldFragment fragment = null;
        if (fieldType == R.id.multilineTextFieldMenuItem) {
//            multiline todo
        } else if (fieldType == R.id.dateFieldMenuItem) {
            //date todo
        } else {
            fragment = new EditTextFieldFragment();
        }
        fragment.setEntity(this);
        fragmentManager
                .beginTransaction()
                .add(R.id.recordContainLinearLayout, fragment, fragment.getClass().getName() + "_" + title)//todo tag
                .addToBackStack(null)
                .commit();
    }


    public String getTitleString() {
        return (String) Serializer.readFrBytes(Encoder.decode(this.title));
    }

    public void setTitleString(String title) {
        this.title = Encoder.encode(Serializer.writeInBytes(title));
    }

    public String getTextString() {
        return (String) Serializer.readFrBytes(Encoder.decode(this.data));
    }

    public void setTextString(String data) {
        this.data = Encoder.encode(Serializer.writeInBytes(data));
    }

    @Override
    public TextEntityMeta getMetaObj() {
        return (TextEntityMeta) Serializer.readFrBytes(Encoder.decode(this.data));
    }

    @Override
    public void setMetaObj(Meta meta) {
        this.meta = Encoder.encode(Serializer.writeInBytes(meta));
    }
}
