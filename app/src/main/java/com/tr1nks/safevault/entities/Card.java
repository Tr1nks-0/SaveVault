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

    public void setTitleBytes(TitleBytes titleBytes) {
        this.titleBytes = titleBytes;
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
            ArrayList<Integer> text_ids = new ArrayList<>();
            ArrayList<Integer> password_ids = new ArrayList<>();
            ArrayList<Integer> image_ids = new ArrayList<>();
            ArrayList<Integer> userIcon_ids = new ArrayList<>();
            if (null != textBytes && !textBytes.isEmpty()) {
                for (TextBytes tb : textBytes) {
                    tb.save();
                    text_ids.add(tb.getId());
                }
            }
            if (null != passwordBytes && !passwordBytes.isEmpty()) {
                for (PasswordBytes pb : passwordBytes) {
                    pb.save();
                    password_ids.add(pb.getId());
                }
            }
            if (null != imageBytes && !imageBytes.isEmpty()) {
                for (ImageBytes ib : imageBytes) {
                    ib.save();
                    image_ids.add(ib.getId());
                }
            }
            if (null != userIconBytes && !userIconBytes.isEmpty()) {
                for (UserIconBytes ub : userIconBytes) {
                    ub.save();
                    userIcon_ids.add(ub.getId());
                }
            }
            //todo add ids in title
            DBUtil.insertTitleBytes(this.titleBytes);
        } else {

        }
    }

    public void addTextBytes(TextBytes tb) {
        if (null == this.textBytes) {
            this.textBytes = new ArrayList<>();
        }
        this.textBytes.add(tb);
//        this.titleBytes.
    }
}
