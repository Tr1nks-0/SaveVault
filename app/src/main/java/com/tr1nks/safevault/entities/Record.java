package com.tr1nks.safevault.entities;

public abstract class Record {
    private String id;
    private String title;
    private String titleImgName;
    private byte[] data;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleImgName() {
        return titleImgName;
    }

    public void setTitleImgName(String titleImgName) {
        this.titleImgName = titleImgName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
