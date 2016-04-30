package com.example.mykakao.chat.view.adder;

/**
 * Created by 재화 on 2016-04-24.
 */
public enum ViewAndIdPool {
    RECEIVER_VIEW(1), SENDER_VIEW(2), NULL_VIEW(3);

    ViewAndIdPool(int viewId) {
        this.viewId = viewId;
    }

    int viewId;

    public int getViewId() {
        return viewId;
    }

}
