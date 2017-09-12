package com.tr1nks.safevault.entities;

/**
 * запись таблицы данные
 */
public class Record {
    private String id;
    private String title;
    private String titleImgName;
    private byte[] data;

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
    public byte[] getData() {
        return data;
    }

    /**
     * установить данные записи
     *
     * @param data данные записи
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}
