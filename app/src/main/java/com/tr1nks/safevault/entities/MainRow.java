package com.tr1nks.safevault.entities;

public class MainRow {
    private int id;
    private byte[] title;
    private byte[] titleImgName;

    public MainRow(int id, byte[] title, byte[] img) {
        this.id = id;
        this.title = title;
        this.titleImgName = img;
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
