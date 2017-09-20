package com.tr1nks.safevault.entities.bytes;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.activities.CardActivity;
import com.tr1nks.safevault.util.DBUtil;

public class TitleBytes extends Bytes {
    private CardActivity activity;
    private byte[] title;
    private byte[] icon;
    private byte[] text_ids;
    private byte[] password_ids;
    private byte[] image_ids;
    private byte[] userIcon_ids;

    public TitleBytes(CardActivity cardActivity) {
        this.activity = cardActivity;
    }

    public TitleBytes(int id, byte[] title, byte[] icon, byte[] meta, byte[] text_ids, byte[] password_ids, byte[] image_ids, byte[] userIcon_ids) {
        super(id, meta);
        this.title = title;
        this.icon = icon;
        this.text_ids = text_ids;
        this.password_ids = password_ids;
        this.image_ids = image_ids;
        this.userIcon_ids = userIcon_ids;
    }

    public TitleBytes(byte[] title, byte[] icon, byte[] meta, byte[] text_ids, byte[] password_ids, byte[] image_ids, byte[] userIcon_ids) {
        this.title = title;
        this.icon = icon;
        this.meta = meta;
        this.text_ids = text_ids;
        this.password_ids = password_ids;
        this.image_ids = image_ids;
        this.userIcon_ids = userIcon_ids;
    }


    @Override
    public Object[] toInsertArr() {
        return new byte[][]{title, icon, meta, text_ids, password_ids, image_ids, icon};
    }

    @Override
    public Object[] toUpdateArr() {
        return new Object[]{title, icon, meta, text_ids, password_ids, image_ids, userIcon_ids, id};
    }

    @Override
    public void onParentPauseAction() {
        this.title = activity.getTitleBytesTitle();
        this.icon = activity.getTitleBytesIcon();

        if (this.id == 0) {
            this.id = DBUtil.insertTitleBytes(this);
        } else {
            DBUtil.updateTitleBytes(this);
        }
    }

    @Override
    public void createFieldFragment(FragmentManager fragmentManager, String title, int fieldTypeId) {

    }

    //get set

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

    public byte[] getTextIds() {
        return text_ids;
    }

    public void setText_ids(byte[] text_ids) {
        this.text_ids = text_ids;
    }

    public byte[] getPasswordIds() {
        return password_ids;
    }

    public void setPassword_ids(byte[] password_ids) {
        this.password_ids = password_ids;
    }

    public byte[] getImageIds() {
        return image_ids;
    }

    public void setImage_ids(byte[] image_ids) {
        this.image_ids = image_ids;
    }

    public byte[] getUserIconIds() {
        return userIcon_ids;
    }

    public void setUserIcon_ids(byte[] userIcon_ids) {
        this.userIcon_ids = userIcon_ids;
    }
}
