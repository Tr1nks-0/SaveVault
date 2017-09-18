package com.tr1nks.safevault.entities;

import com.tr1nks.safevault.entities.bytes.*;
import com.tr1nks.safevault.util.DBUtil;

import java.util.ArrayList;

public class Card {
    private TitleBytes titleBytes;
    private ArrayList<TextBytes> textBytes;
    private ArrayList<PasswordBytes> passwordBytes;
    private ArrayList<ImageBytes> imageBytes;
    private ArrayList<UserIconBytes> userIconBytes;


    public TitleBytes getTitleBytes() {
        return titleBytes;
    }

    public ArrayList<TextBytes> getTextBytes(byte[] password) {
        if (null == textBytes) {
            textBytes = DBUtil.getTextBytesByIds(titleBytes.getTextIds(password));
        }
        return textBytes;
    }

    public ArrayList<PasswordBytes> getPasswordBytes(byte[] password) {
        if (null == passwordBytes) {
            passwordBytes = DBUtil.getPasswordBytesByIds(titleBytes.getPasswordIds(password));
        }
        return passwordBytes;
    }

    public ArrayList<ImageBytes> getImageBytes(byte[] password) {
        if (null == imageBytes) {
            imageBytes = DBUtil.getImageBytesByIds(titleBytes.getImageIds(password));
        }
        return imageBytes;
    }

    public ArrayList<UserIconBytes> getUserIconBytes(byte[] password) {
        if (null == userIconBytes) {
            userIconBytes = DBUtil.getUserIconsBytesByIds(titleBytes.getUserIconIds(password));
        }
        return userIconBytes;
    }

    public void save(boolean newCard) {
        if (newCard) {
            DBUtil.insertTitleBytes(this.titleBytes);
        } else {

        }
    }

    public void setTitleBytes(TitleBytes titleBytes) {
        this.titleBytes = titleBytes;
    }
}
