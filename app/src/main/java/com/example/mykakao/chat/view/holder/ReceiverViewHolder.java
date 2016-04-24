package com.example.mykakao.chat.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.pojo.ReceiverData;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 재화 on 2016-04-23.
 */
public class ReceiverViewHolder extends RecyclerView.ViewHolder implements SetDataInterface {

    @Bind(R.id.ImageView) CircleImageView ImageView;
    @Bind(R.id.nameView) TextView nameView;
    @Bind(R.id.contentView) TextView contentView;
    @Bind(R.id.dateView) TextView dateView;

    public ReceiverViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

   @Override
    public void setData(ChatData chatData) {
        ReceiverData data = (ReceiverData)chatData;
        ImageView.setImageBitmap(data.getImage());
        nameView.setText(data.getName());
        contentView.setText(data.getContent());
        dateView.setText(data.getDate());
    }
}
