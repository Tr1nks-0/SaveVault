package com.tr1nks.safevault.entities;

import com.tr1nks.safevault.entities.fields.TextFieldEntity;
import com.tr1nks.safevault.entities.meta.TitleMeta;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.Serializer;

import java.util.ArrayList;

public class CardTitleNew {
    private int id;
    private byte[] title;
    private byte[] icon;
    private byte[] textIds;
    private byte[] password_ids;
    private byte[] image_ids;
    private byte[] icon_ids;
    private byte[] meta;

    public CardTitleNew(int id, byte[] title, byte[] icon, byte[] meta, byte[] text_ids, byte[] password_ids, byte[] image_ids, byte[] icon_ids) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.meta = meta;
        this.textIds = text_ids;
        this.password_ids = password_ids;
        this.image_ids = image_ids;
        this.icon_ids = icon_ids;
    }

    public CardTitleNew() {

    }

    // todo check if title loaded or load
    public byte[] getTitle() {
        return title;
    }

    public String getTitleString() {
        return (String) Serializer.readFrBytes(Encoder.decode(title));
    }

    public void setTitle(byte[] title) {
        this.title = title;
    }

    public void setTitleString(String title) {
        this.title = Encoder.encode(Serializer.writeInBytes(title));
    }

    public byte[] getTextIds() {
        return textIds;
    }

    // todo check if textIds loaded or load
    public ArrayList<TextFieldEntity> getTextIdsList() {
        return (ArrayList<TextFieldEntity>) Serializer.readFrBytes(Encoder.decode(textIds));
    }

    public void setTextIdsList(ArrayList<TextFieldEntity> textIds) {
        this.textIds = Encoder.encode(Serializer.writeInBytes(textIds));
    }

    public void setTextIds(byte[] textIds) {
        this.textIds = textIds;
    }

    public TitleMeta getMetaObj() {
        return (TitleMeta) Serializer.readFrBytes(Encoder.decode(this.meta));
    }

    public void setMetaObj(TitleMeta meta) {
        this.meta = Encoder.encode(Serializer.writeInBytes(meta));
    }
}
