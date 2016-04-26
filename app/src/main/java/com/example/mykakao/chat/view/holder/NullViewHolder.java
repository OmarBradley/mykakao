package com.example.mykakao.chat.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.pojo.NullData;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-26.
 */
public class NullViewHolder extends RecyclerView.ViewHolder implements SetDataInterface {

    @Bind(R.id.nullView) TextView nullView;

    public NullViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(ChatData chatData) {
        NullData nullData = (NullData) chatData;
        nullData.setContent("");
    }
}
