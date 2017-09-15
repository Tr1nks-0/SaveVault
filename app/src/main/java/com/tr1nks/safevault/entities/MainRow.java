package com.tr1nks.safevault.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class MainRow implements Parcelable {
    private int id;
    private byte[] title;
    private byte[] titleImgName;

    public MainRow(int id, byte[] title, byte[] img) {
        this.id = id;
        this.title = title;
        this.titleImgName = img;
    }

    protected MainRow(Parcel in) {
        id = in.readInt();
        title = in.createByteArray();
        titleImgName = in.createByteArray();
    }

    public static final Creator<MainRow> CREATOR = new Creator<MainRow>() {
        @Override
        public MainRow createFromParcel(Parcel in) {
            return new MainRow(in);
        }

        @Override
        public MainRow[] newArray(int size) {
            return new MainRow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeByteArray(title);
        parcel.writeByteArray(titleImgName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getTitle() {
        return title;
    }

    public void setTitle(byte[] title) {
        this.title = title;
    }

    public byte[] getTitleImgName() {
        return titleImgName;
    }

    public void setTitleImgName(byte[] titleImgName) {
        this.titleImgName = titleImgName;
    }
}
