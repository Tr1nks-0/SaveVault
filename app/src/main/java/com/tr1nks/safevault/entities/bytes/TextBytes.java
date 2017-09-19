package com.tr1nks.safevault.entities.bytes;

import android.os.Parcel;
import android.os.Parcelable;
import com.tr1nks.safevault.util.DBUtil;

public class TextBytes implements Parcelable {
    private int id;
    private byte[] title;
    private byte[] data;

    public TextBytes() {
    }

    public TextBytes(int id, byte[] titles, byte[] data) {
        this.id = id;
        this.title = titles;
        this.data = data;
    }

    public TextBytes(byte[] title) {
        this.title = title;
    }

    protected TextBytes(Parcel in) {
        id = in.readInt();
        title = in.createByteArray();
        data = in.createByteArray();
    }

    public static final Creator<TextBytes> CREATOR = new Creator<TextBytes>() {
        @Override
        public TextBytes createFromParcel(Parcel in) {
            return new TextBytes(in);
        }

        @Override
        public TextBytes[] newArray(int size) {
            return new TextBytes[size];
        }
    };

    public Object[] toInsertArr() {
        return new byte[][]{title, data};
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeByteArray(title);
        parcel.writeByteArray(data);
    }

    public void save() {
        this.id = DBUtil.insertTextBytes(this);
    }

    public int getId() {
        return id;
    }

    public void setTitle(byte[] title) {
        this.title = title;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
