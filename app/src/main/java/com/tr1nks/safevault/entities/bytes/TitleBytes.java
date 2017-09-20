package com.tr1nks.safevault.entities.bytes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.activities.CardActivity;
import com.tr1nks.safevault.activities.fragments.CardTitleFragment;
import com.tr1nks.safevault.util.DBUtil;

public class TitleBytes extends Bytes implements Parcelable {
    private transient CardActivity activity;
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

    public TitleBytes() {

    }


    protected TitleBytes(Parcel in) {
        title = in.createByteArray();
        icon = in.createByteArray();
        text_ids = in.createByteArray();
        password_ids = in.createByteArray();
        image_ids = in.createByteArray();
        userIcon_ids = in.createByteArray();
    }

    public static final Creator<TitleBytes> CREATOR = new Creator<TitleBytes>() {
        @Override
        public TitleBytes createFromParcel(Parcel in) {
            return new TitleBytes(in);
        }

        @Override
        public TitleBytes[] newArray(int size) {
            return new TitleBytes[size];
        }
    };

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
        this.meta = new byte[0];//debug

        if (this.id == 0) {
            this.id = DBUtil.insertTitleBytes(this);
        } else {
            DBUtil.updateTitleBytes(this);
        }
    }

    public void createMainActivityFieldFragment(FragmentManager fragmentManager) {
        CardTitleFragment fragment = new CardTitleFragment();
        fragment.setBytes(this);
        fragmentManager
                .beginTransaction()
                .add(R.id.selectCardLinearLayout, fragment, "") //todo correct tag
                .addToBackStack(null)
                .commit();
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

    public void openCard(String mode, Context context) {
        Intent intent = new Intent(context, CardActivity.class);
//        intent.putExtra("password", getIntent().getByteArrayExtra("password"));
//        intent.putExtra("mode", mode);
        Bundle bundle = new Bundle();
        bundle.putString("mode", mode);
        bundle.putParcelable("titleBytes", this);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(title);
        parcel.writeByteArray(icon);
        parcel.writeByteArray(text_ids);
        parcel.writeByteArray(password_ids);
        parcel.writeByteArray(image_ids);
        parcel.writeByteArray(userIcon_ids);
    }

    public void setCardActivity(CardActivity cardActivity) {
        this.activity = cardActivity;
    }
}
