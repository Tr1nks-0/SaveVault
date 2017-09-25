package com.tr1nks.safevault.entities;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.activities.CardActivity;
import com.tr1nks.safevault.entities.bytes.*;
import com.tr1nks.safevault.entities.meta.FieldMeta;
import com.tr1nks.safevault.util.DBUtil;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.Serializer;
import com.tr1nks.safevault.util.UserPasswordManager;

import java.util.ArrayList;

public class Card {
    private TitleBytes titleBytes;
    private ArrayList<TextBytes> textBytes;
    private ArrayList<PasswordBytes> passwordBytes;
    private ArrayList<ImageBytes> imageBytes;
    private ArrayList<UserIconBytes> userIconBytes;

    public Card(CardActivity cardActivity, TitleBytes tb) {
        this.titleBytes = tb;
        tb.setCardActivity(cardActivity);
    }


    public void onParentPauseAction() {
        if (null != textBytes && !textBytes.isEmpty()) {
            for (TextBytes tb : textBytes) {
                tb.onParentPauseAction();
            }
        }
        if (null != passwordBytes && !passwordBytes.isEmpty()) {
            for (PasswordBytes pb : passwordBytes) {
                pb.onParentPauseAction();
            }
        }
        if (null != imageBytes && !imageBytes.isEmpty()) {
            for (ImageBytes ib : imageBytes) {
                ib.onParentPauseAction();
            }
        }
        if (null != userIconBytes && !userIconBytes.isEmpty()) {
            for (UserIconBytes ub : userIconBytes) {
                ub.onParentPauseAction();
            }
        }
        titleBytes.onParentPauseAction();
    }

    public void open(FragmentManager fragmentManager) {
        if (null == this.textBytes && this.titleBytes.getTextIds() != null) {
            this.textBytes = DBUtil.getTextBytesByIds((ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(UserPasswordManager.getPassword(), this.titleBytes.getTextIds())));
        }
        if (null != this.textBytes && !this.textBytes.isEmpty()) {
            for (TextBytes tb : this.textBytes) {
                tb.open(fragmentManager);
            }
        }
        if (null == this.passwordBytes && this.titleBytes.getPasswordIds() != null) {
            this.passwordBytes = DBUtil.getPasswordBytesByIds((ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(UserPasswordManager.getPassword(), this.titleBytes.getPasswordIds())));
        }
        if (null != this.passwordBytes && !this.passwordBytes.isEmpty()) {
            for (PasswordBytes pb : this.passwordBytes) {
                pb.open(fragmentManager);
            }
        }
        if (null == this.imageBytes && this.titleBytes.getImageIds() != null) {
            this.imageBytes = DBUtil.getImageBytesByIds((ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(UserPasswordManager.getPassword(), this.titleBytes.getImageIds())));
        }
        if (null != this.imageBytes && !this.imageBytes.isEmpty()) {
            for (ImageBytes ib : this.imageBytes) {
                ib.open(fragmentManager);
            }
        }
        if (null == this.userIconBytes && this.titleBytes.getUserIconIds() != null) {
            this.userIconBytes = DBUtil.getUserIconsBytesByIds((ArrayList<Integer>) Serializer.readFrBytes(Encoder.decode(UserPasswordManager.getPassword(), this.titleBytes.getUserIconIds())));
        }
        if (null != this.userIconBytes && !this.userIconBytes.isEmpty()) {
            for (UserIconBytes ub : this.userIconBytes) {
                ub.open(fragmentManager);
            }
        }
    }

    public void createTextField(FragmentManager fragmentManager, String title, int fieldTypeId) {
        FieldMeta meta = new FieldMeta();
        meta.setFieldType(fieldTypeId);
        if (null == this.textBytes) {
            this.textBytes = new ArrayList<>();
        }
        TextBytes tb = new TextBytes(Encoder.encode(UserPasswordManager.getPassword(), title.getBytes()), Encoder.encode(UserPasswordManager.getPassword(), FieldMeta.serialize(meta)));
        this.textBytes.add(tb);
        tb.createFieldFragment(fragmentManager, title, fieldTypeId);
    }

    public void createPasswordField(FragmentManager fragmentManager, String title, int fieldTypeId) {
        FieldMeta meta = new FieldMeta();
        meta.setFieldType(fieldTypeId);
        if (null == this.passwordBytes) {
            this.passwordBytes = new ArrayList<>();
        }
        PasswordBytes pb = new PasswordBytes(Encoder.encode(UserPasswordManager.getPassword(), title.getBytes()), Encoder.encode(UserPasswordManager.getPassword(), FieldMeta.serialize(meta)));
        this.passwordBytes.add(pb);
        pb.createFieldFragment(fragmentManager, title, fieldTypeId);
    }
}
