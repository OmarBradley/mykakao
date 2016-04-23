package com.example.mykakao.chat.view;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Collector;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Supplier;
import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.pojo.ReceiverData;
import com.example.mykakao.chat.pojo.SenderData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 재화 on 2016-04-23.
 */
// like facade pattern
public enum ViewType {

    Receiver(ReceiverViewHolder.class, ReceiverData.class, 1, R.layout.view_chat_receive),
    Sender(SenderViewHolder.class, SenderData.class, 2, R.layout.view_chat_send);

    ViewType(Class viewHolderClass, Class chatDataClass, int viewId, @LayoutRes int resourceId ){
        this.viewHolderClass = viewHolderClass;
        this.chatDataClass = chatDataClass;
        this.viewId = viewId;
        this.resourceId = resourceId;
    }

    Class viewHolderClass;
    Class chatDataClass;
    int viewId;
    @LayoutRes int resourceId;

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

    public static int getViewId(ChatData data) {
        ViewType[] viewTypes = ViewType.class.getEnumConstants();
        Map<Class, Integer> idMap = new HashMap<>();
        Stream.of(viewTypes).forEach(e -> idMap.put(e.getChatDataClass(), e.getViewId()));
        return idMap.get(data.getClass());
    }

    public static RecyclerView.ViewHolder getViewHolder(int viewId, ViewGroup parent) {
        ViewType[] viewTypes = ViewType.class.getEnumConstants();
        Map<Integer, Supplier<RecyclerView.ViewHolder>> idAndViewHolder = new HashMap<>();
        Stream.of(viewTypes).forEach(e -> {
            idAndViewHolder.put(e.getViewId(), () -> {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(e.getResourceId(), parent, false);
                Constructor<? extends RecyclerView.ViewHolder> c = null;
                try {
                    c = e.getViewHolderClass().getConstructor(View.class);
                    return c.newInstance(view);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            });
        });
        return idAndViewHolder.get(viewId).get();
    }

    ;



}
