package com.tr1nks.safevault.entities;

import android.media.Image;

public abstract class Record  {
    private String id;
    private String title;
    private Image titleImg;


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

    public Image getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(Image titleImg) {
        this.titleImg = titleImg;
    }
}
