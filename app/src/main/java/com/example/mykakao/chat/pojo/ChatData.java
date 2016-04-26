package com.example.mykakao.chat.pojo;

/**
 * Created by 재화 on 2016-04-21.
 */
public interface ChatData {

    public default void setDate(String date){};
    public default String getDate(){return null;};

    public default void setContent(String content){};
    public default String getContent(){return null;};

}
