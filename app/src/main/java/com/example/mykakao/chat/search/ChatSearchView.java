package com.example.mykakao.chat.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mykakao.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-29.
 */
public class ChatSearchView extends LinearLayout {

    @Bind(R.id.keywordVIew) EditText keywordVIew;
    @Bind(R.id.searchDownView) Button searchDownView;
    @Bind(R.id.searchUpView) Button searchUpView;

    public ChatSearchView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_chat_search, this, true);
        ButterKnife.bind(view, this);
    }

    public Button getSearchDownView() {
        return searchDownView;
    }

    public Button getSearchUpView() {
        return searchUpView;
    }

    public EditText getKeywordVIew() {
        return keywordVIew;
    }
}
