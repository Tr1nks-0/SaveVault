package com.tr1nks.safevault.entities.bytes;

import android.os.Parcel;
import android.os.Parcelable;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.Serializer;

import java.util.ArrayList;

public class TitleBytes implements Parcelable {
    private int id;
    private byte[] title;
    private byte[] icon;
    private byte[] meta;
    private byte[] text_ids;
    private byte[] password_ids;
    private byte[] image_ids;
    private byte[] userIcon_ids;

    public TitleBytes(int id, byte[] title, byte[] icon, byte[] meta, byte[] text_ids, byte[] password_ids, byte[] image_ids, byte[] userIcon_ids) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.meta = meta;
        this.text_ids = text_ids;
        this.password_ids = password_ids;
        this.image_ids = image_ids;
        this.userIcon_ids = userIcon_ids;
    }

    protected TitleBytes(Parcel in) {
        id = in.readInt();
        title = in.createByteArray();
        icon = in.createByteArray();
        meta = in.createByteArray();
        text_ids = in.createByteArray();
        password_ids = in.createByteArray();
        image_ids = in.createByteArray();
        userIcon_ids = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeByteArray(title);
        parcel.writeByteArray(icon);
        parcel.writeByteArray(meta);
        parcel.writeByteArray(text_ids);
        parcel.writeByteArray(password_ids);
        parcel.writeByteArray(image_ids);
        parcel.writeByteArray(userIcon_ids);
    }

    public static final Creator<TitleBytes> CREATOR = new Creator<TitleBytes>() {
        @Override
        public TitleBytes createFromParcel(Parcel in) {
            return new TitleBytes(in);
        }

        @Override
        public TitleBytes[] newArray(int size) {
            return new TitleBytes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    //get set

    public int getId() {
        return id;
    }

    public byte[] getTitle() {
        return title;
    }

    public void setTitle(byte[] title) {
        this.title = title;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public byte[] getMeta() {
        return meta;
    }

    public void setMeta(byte[] meta) {
        this.meta = meta;
    }

    public ArrayList<Integer> getTextIds(byte[] password) {
        return (ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(password, text_ids));
    }

    public ArrayList<Integer> getPasswordIds(byte[] password) {
        return (ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(password, password_ids));
    }

    public ArrayList<Integer> getImageIds(byte[] password) {
        return (ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(password, image_ids));
    }

    public ArrayList<Integer> getUserIconIds(byte[] password) {
        return (ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(password, userIcon_ids));
    }

    public void setText_ids(ArrayList<Integer> text_ids, byte[] password) {
        this.text_ids = Encoder.encode(password, Serializer.writeInBytes(text_ids));
    }

    public void setPassword_ids(byte[] password_ids, byte[] password) {
        this.password_ids = Encoder.encode(password, Serializer.writeInBytes(password_ids));
    }

    public void setImage_ids(byte[] image_ids, byte[] password) {
        this.image_ids = Encoder.encode(password, Serializer.writeInBytes(image_ids));
    }

    public void setUserIcon_ids(byte[] userIcon_ids, byte[] password) {
        this.userIcon_ids = Encoder.encode(password, Serializer.writeInBytes(userIcon_ids));
    }


    public Object[] toInsertArr() {
//        INSERT INTO titles (title, icon, meta, text_ids, password_ids, image_ids, icon_ids) VALUES (?,?,?,?,?,?,?)
        return new byte[][]{title, icon, meta, text_ids, password_ids, image_ids, icon};
    }
}
