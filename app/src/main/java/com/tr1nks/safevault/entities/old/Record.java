package com.tr1nks.safevault.entities.old;

import com.tr1nks.safevault.entities.old.fields.Field;

import java.util.ArrayList;

/**
 * запись таблицы данные
 */
public class Record {
    private String id;
    private String title;
    private String titleImgName;
    private ArrayList<Field> data;

    /**
     * получить заголовок
     *
     * @return заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * установить заголовок
     *
     * @param title заголовок
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * получить id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * задать id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * получить имя иконки записи
     *
     * @return имя иконки записи
     */
    public String getTitleImgName() {
        return titleImgName;
    }

    /**
     * установить имя иконки записи
     *
     * @param titleImgName имя иконки записи
     */
    public void setTitleImgName(String titleImgName) {
        this.titleImgName = titleImgName;
    }

    /**
     * получить данные записи
     *
     * @return данные записи
     */
    public ArrayList<Field> getData() {
        return data;
    }

    /**
     * установить данные записи
     *
     * @param data данные записи
     */
    public void setData(ArrayList<Field> data) {
        this.data = data;
    }
}
