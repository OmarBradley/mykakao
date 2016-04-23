package com.example.mykakao.chat.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ReceiverData;
import com.example.mykakao.chat.pojo.SenderData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-23.
 */
public class SenderViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.contentView) TextView contentView;
    @Bind(R.id.dateView) TextView dateView;


    public SenderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(SenderData data) {
        contentView.setText(data.getContent());
        dateView.setText(data.getDate());
    }

}
