package com.example.mykakao.chat.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mykakao.chat.pojo.ChatData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 재화 on 2016-04-23.
 */
public class ChatViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ChatData> items = new ArrayList<>();

    public void add(ChatData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ChatData data = items.get(position);
        return ViewType.getViewId(data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewType.getViewHolder(viewType, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
