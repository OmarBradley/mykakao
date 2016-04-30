package com.example.mykakao.chat.search;

import android.support.v7.widget.RecyclerView;

import com.annimon.stream.Stream;
import com.example.mykakao.chat.pojo.ChatData;
import com.example.mykakao.chat.view.adapter.ChatViewAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.annimon.stream.Collectors.toList;

/**
 * Created by 재화 on 2016-04-30.
 */

public class ChatSearchViewModel {

    ChatViewAdapter itemAdapter;
    List<Integer> itemPositions = new ArrayList<>();
    RecyclerView itemView;
    int position;
    ButtonEnabledState buttonEnabledState;

    ChatSearchView view;

    public ChatSearchViewModel(ChatSearchView view, ChatViewAdapter itemAdapter, RecyclerView itemView) {
        this.view = view;
        this.itemAdapter = itemAdapter;
        this.itemView = itemView;
        this.itemPositions = new ArrayList<>();
        this.position = 0;
        this.buttonEnabledState = new ButtonEnabledState(view.getSearchDownView(),view.getSearchUpView());
    }

    public void executeSearchViewAction(){
        setTextWatcher();
        setOnUpDown();
    }

    private void setTextWatcher() {
        LinkedHashMap<ChatData, Integer> itemAndPositionTable = itemAdapter.getItemAndPositionTable();
        buttonEnabledState.setEnabledState(buttonEnabledState.DEFAULT_POSITION, buttonEnabledState.DEFAULT_ITEM_POSITION_SIZE);
        view.getKeywordVIew().addTextChangedListener((OnTextChangeListener) (s, start, before, count) -> {
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
                buttonEnabledState.setEnabledState(position, itemPositions.size());
            }
        });
    }

    private void setOnUpDown() {
        position = itemPositions.size() - 1;
        view.getSearchDownView().setOnClickListener(view -> {
            if (position < itemPositions.size() - 1) {
                itemView.smoothScrollToPosition(itemPositions.get(++position));
                buttonEnabledState.setEnabledState(position, itemPositions.size());
            }
        });
        view.getSearchUpView().setOnClickListener(view -> {
            if (position >= 0) {
                itemView.smoothScrollToPosition(itemPositions.get(position--));
                buttonEnabledState.setEnabledState(position, itemPositions.size());
            }
        });
    }

}
