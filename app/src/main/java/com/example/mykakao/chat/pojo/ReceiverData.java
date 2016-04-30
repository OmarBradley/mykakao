package com.example.mykakao.chat.pojo;

import android.graphics.Bitmap;

/**
 * Created by 재화 on 2016-04-23.
 */
public class ReceiverData implements ChatData {

    private Bitmap image;
    private String name;
    private String date;
    private String content;

    public ReceiverData(String content, String date, Bitmap image, String name) {
        this.content = content;
        this.date = date;
        this.image = image;
        this.name = name;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
