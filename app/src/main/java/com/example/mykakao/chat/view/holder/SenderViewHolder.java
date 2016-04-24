package com.example.mykakao.chat.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.pojo.SenderData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-23.
 */
public class SenderViewHolder extends RecyclerView.ViewHolder implements SetDataInterface {

    @Bind(R.id.contentView) TextView contentView;
    @Bind(R.id.dateView) TextView dateView;


    public SenderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(ChatData chatData) {
        SenderData data = (SenderData)chatData;
        contentView.setText(data.getContent());
        dateView.setText(data.getDate());
    }
}
