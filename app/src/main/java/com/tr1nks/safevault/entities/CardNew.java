package com.tr1nks.safevault.entities;

import android.support.v4.app.FragmentManager;
import com.tr1nks.safevault.entities.fields.PasswordFieldEntity;
import com.tr1nks.safevault.entities.fields.TextFieldEntity;
import com.tr1nks.safevault.entities.meta.PasswordEntityMeta;
import com.tr1nks.safevault.entities.meta.TextEntityMeta;
import com.tr1nks.safevault.util.DBUtil;

import java.util.ArrayList;

public class CardNew {//todo implements Parcelable
    private CardTitleNew cardTitle;
    private ArrayList<TextFieldEntity> textEntities;

    public CardNew(CardTitleNew cardTitleNew) {
        this.cardTitle = cardTitleNew;
    }

    public CardTitleNew getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(CardTitleNew cardTitle) {
        this.cardTitle = cardTitle;
    }

    public void open(FragmentManager fragmentManager) {
        if (null == this.textEntities && !this.cardTitle.getTextIdsList().isEmpty()) {
            this.textEntities = DBUtil.getTextFieldEntitiesById(this.cardTitle.getTextIdsList());
        }
        if (this.textEntities != null && !this.textEntities.isEmpty()) {
            for (TextFieldEntity entity : this.textEntities) {
                entity.open(fragmentManager);
            }
        }
    }

    public void createPasswordField(FragmentManager fragmentManager, String fieldTitle, int fieldTypeId) {
        PasswordFieldEntity entity = new PasswordFieldEntity(fieldTitle);
        entity.setMetaObj(new PasswordEntityMeta(fieldTypeId));
        entity.open(fragmentManager);
    }

    public void createTextField(FragmentManager fragmentManager, String fieldTitle, int fieldTypeId) {
        TextFieldEntity entity = new TextFieldEntity(fieldTitle);
        entity.setMetaObj(new TextEntityMeta(fieldTypeId));
        entity.open(fragmentManager);
    }

    public void createNew(FragmentManager supportFragmentManager) {
        //todo
    }
}
