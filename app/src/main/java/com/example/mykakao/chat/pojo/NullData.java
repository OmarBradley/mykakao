package com.example.mykakao.chat.pojo;

/**
 * Created by 재화 on 2016-04-26.
 */
public class NullData implements ChatData {
    private String nullContent;

    public void setContent(String content) {
        this.nullContent = content;
    }

    public String getContent() {
        return nullContent;
    }
}
