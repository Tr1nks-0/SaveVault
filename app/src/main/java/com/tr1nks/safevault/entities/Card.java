package com.tr1nks.safevault.entities;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.tr1nks.safevault.activities.CardActivity;
import com.tr1nks.safevault.entities.bytes.*;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

import java.util.ArrayList;

public class Card {
    private TitleBytes titleBytes;
    private ArrayList<TextBytes> textBytes;
    private ArrayList<PasswordBytes> passwordBytes;
    private ArrayList<ImageBytes> imageBytes;
    private ArrayList<UserIconBytes> userIconBytes;

    public Card(CardActivity cardActivity) {
        this.titleBytes=new TitleBytes(cardActivity);
    }


    public void onParentPauseAction() {
//        ArrayList<Integer> text_ids = new ArrayList<>();
//        ArrayList<Integer> password_ids = new ArrayList<>();
//        ArrayList<Integer> image_ids = new ArrayList<>();
//        ArrayList<Integer> userIcon_ids = new ArrayList<>();
        if (null != textBytes && !textBytes.isEmpty()) {
            for (TextBytes tb : textBytes) {
                tb.onParentPauseAction();
                Log.d("","");
//                text_ids.add(tb.getId());
            }
        }
        if (null != passwordBytes && !passwordBytes.isEmpty()) {
            for (PasswordBytes pb : passwordBytes) {
                pb.onParentPauseAction();
//                password_ids.add(pb.getId());
            }
        }
        if (null != imageBytes && !imageBytes.isEmpty()) {
            for (ImageBytes ib : imageBytes) {
                ib.onParentPauseAction();
//                image_ids.add(ib.getId());
            }
        }
        if (null != userIconBytes && !userIconBytes.isEmpty()) {
            for (UserIconBytes ub : userIconBytes) {
                ub.onParentPauseAction();
//                userIcon_ids.add(ub.getId());
            }
        }
        titleBytes.onParentPauseAction();
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
