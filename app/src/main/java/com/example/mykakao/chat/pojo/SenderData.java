package com.example.mykakao.chat.pojo;

/**
 * Created by 재화 on 2016-04-21.
 */
public class SenderData implements ChatData {

    private String date;
    private String content;

    public SenderData(String content, String date) {
        this.content = content;
        this.date = date;
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
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
