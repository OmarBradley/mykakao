package com.example.mykakao.chat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.pojo.ReceiverData;
import com.example.mykakao.chat.pojo.SenderData;
import com.example.mykakao.chat.view.adder.ViewAdder;
import com.example.mykakao.chat.view.adder.ViewElement;
import com.example.mykakao.chat.view.adder.ViewIdPool;
import com.example.mykakao.chat.view.holder.ReceiverViewHolder;
import com.example.mykakao.chat.view.holder.SenderViewHolder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 재화 on 2016-04-23.
 */
public class ChatViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ChatData> items = new ArrayList<>();
    ViewAdder adder;

    public ChatViewAdapter(){
        adder = new ViewAdder(setViewElements());
    }

    // view를 추가할 시 여기에 관련 view 요소들만 추가해주면 됨
    public List<ViewElement> setViewElements(){
        ViewElement receiverViewElement = new ViewElement()
                .setViewHolderClass(ReceiverViewHolder.class)
                .setViewId(ViewIdPool.RECEIVER_VIEW.getViewId())
                .setChatDataClass(ReceiverData.class)
                .setResourceId(R.layout.view_chat_receive);

        ViewElement senderViewElement = new ViewElement()
                .setViewHolderClass(SenderViewHolder.class)
                .setViewId(ViewIdPool.SENDER_VIEW.getViewId())
                .setChatDataClass(SenderData.class)
                .setResourceId(R.layout.view_chat_send);
        List<ViewElement> viewElements = Arrays.asList(receiverViewElement,senderViewElement);
        return viewElements;
    }

    public void add(ChatData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ChatData data = items.get(position);
        return adder.getViewId(data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adder.getViewHolder(viewType, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Class<?> c = adder.getViewHolderClass(getItemViewType(position));
        c.cast(holder);
        Method method = null;
        try {
            method = c.getMethod("setData", ChatData.class);
            method.invoke(holder, items.get(position));
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
    }


}
