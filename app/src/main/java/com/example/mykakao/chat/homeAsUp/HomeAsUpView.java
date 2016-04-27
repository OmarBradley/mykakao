package com.example.mykakao.chat.homeAsUp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mykakao.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-27.
 */
public class HomeAsUpView extends LinearLayout {

    @Bind(R.id.roomNameView) TextView roomNameView;
    @Bind(R.id.peopleCountView) TextView peopleCountView;

    public HomeAsUpView(Context context) {
        super(context);
        initView();
    }

    public HomeAsUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_chat_home_as_up, this, true);
        ButterKnife.bind(view, this);
    }

    public void setRoomNameText(String text){
        roomNameView.setText(text);
    }

    public void setPeopleCountText(int count){
        String countText = Integer.toString(count);
        peopleCountView.setText(countText);
    }




}
