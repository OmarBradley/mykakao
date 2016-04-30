package com.example.mykakao.chat.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.annimon.stream.Stream;
import com.example.mykakao.R;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.search.scroll.ButtonEnabledState;
import com.example.mykakao.chat.view.adapter.ChatViewAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static com.annimon.stream.Collectors.*;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-29.
 */
public class ChatSearchView extends LinearLayout{

    @Bind(R.id.keywordVIew) EditText keywordVIew;
    @Bind(R.id.searchDownView) Button searchDownView;
    @Bind(R.id.searchUpView) Button searchUpView;
    ChatViewAdapter itemAdapter;
    List<Integer> itemPositions = new ArrayList<>();
    RecyclerView itemView;
    int position;
    ButtonEnabledState buttonEnabledState;


    public ChatSearchView(Context context, ChatViewAdapter itemAdapter, RecyclerView itemView) {
        super(context);
        this.itemAdapter = itemAdapter;
        this.itemView = itemView;
        initView();
        keywordVIewTextWatcher();
         buttonEnabledState = new ButtonEnabledState(searchDownView, searchUpView);
        setOnUpDown();
    }


    public ChatSearchView(Context context, AttributeSet attrs, ChatViewAdapter itemAdapter, RecyclerView itemView) {
        super(context, attrs);
        this.itemAdapter = itemAdapter;
        this.itemView = itemView;
        initView();
        keywordVIewTextWatcher();
        buttonEnabledState = new ButtonEnabledState(searchDownView, searchUpView);
        setOnUpDown();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_chat_search, this, true);
        ButterKnife.bind(view, this);
    }

    private void keywordVIewTextWatcher(){
        LinkedHashMap<ChatData, Integer> itemAndPositionTable = itemAdapter.getItemAndPositionTable();
        setDefaultButtonEnabled();
        keywordVIew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString() ==""){
                    searchDownView.setEnabled(false);
                    searchUpView.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString();
                if (keyword.length() > 0) {
                    itemPositions = Stream.of(itemAdapter.getItems()).filter(element -> {
                        return element.getContent().contains(keyword);
                    }).map(element -> {
                        return itemAndPositionTable.get(element);
                    }).collect(toList());
                    if (itemPositions.size() > 0) {
                        itemView.smoothScrollToPosition(itemPositions.get(itemPositions.size() - 1));
                    }
                    position = itemPositions.size() - 1;
                    Log.e("length", Integer.toString(itemPositions.size()));
                    buttonEnabledState.setEnabledState(position, itemPositions.size());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void setOnUpDown() {
        position = itemPositions.size() - 1;
        searchDownView.setOnClickListener(view -> {
            if (position < itemPositions.size() - 1) {
                itemView.smoothScrollToPosition(itemPositions.get(position));
                ++position;
                buttonEnabledState.setEnabledState(position, itemPositions.size());
            }
        });
        searchUpView.setOnClickListener(view -> {
            if (position > 0) {
                itemView.smoothScrollToPosition(itemPositions.get(position));
                --position;
                buttonEnabledState.setEnabledState(position, itemPositions.size());
            }
        });
    }

    private void setDefaultButtonEnabled() {
        if (keywordVIew.getText().toString().length() == 0) {
            searchDownView.setEnabled(false);
            searchUpView.setEnabled(false);
        }
    }

}
