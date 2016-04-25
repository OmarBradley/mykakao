package com.example.mykakao.chat.view.adder;

import android.support.annotation.LayoutRes;

/**
 * Created by 재화 on 2016-04-24.
 */
public class ViewElement {
    private Class viewHolderClass;
    private Class chatDataClass;
    private int viewId;
    @LayoutRes private int resourceId;

    public ViewElement setChatDataClass(Class chatDataClass) {
        this.chatDataClass = chatDataClass;
        return this;
    }

    public ViewElement setResourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ViewElement setViewHolderClass(Class viewHolderClass) {
        this.viewHolderClass = viewHolderClass;
        return this;
    }

    public ViewElement setViewId(int viewId) {
        this.viewId = viewId;
        return this;
    }

    public Class getChatDataClass() {
        return chatDataClass;
    }

    public int getResourceId() {
        return resourceId;
    }

    public Class getViewHolderClass() {
        return viewHolderClass;
    }

    public int getViewId() {
        return viewId;
    }

}
