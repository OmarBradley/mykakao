package com.example.mykakao.chat;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.*;
import android.widget.Toast;

import com.example.mykakao.R;
import com.example.mykakao.chat.homeAsUp.HomeAsUpView;
import com.example.mykakao.chat.pojo.NullData;
import com.example.mykakao.chat.pojo.ReceiverData;
import com.example.mykakao.chat.pojo.SenderData;
import com.example.mykakao.chat.search.ChatSearchView;
import com.example.mykakao.chat.search.ChatSearchViewModel;
import com.example.mykakao.chat.view.adapter.ChatViewAdapter;
import com.example.mykakao.util.DateFormatter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @Bind(R.id.recycler) RecyclerView recyclerView;
    @Bind(R.id.btnReceive) Button btnReceive;
    @Bind(R.id.btnSend) Button btnSend;
    @Bind(R.id.chatText) EditText chatText;
    ChatViewAdapter chatViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setActionBar();
        setHomeAsUpView("가네야바시 세이콘", 1);
        setRecyclerView();
        setButtonClickAction();
        setButtonClickable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        return true;
    }

    boolean searchOpened = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            if (searchOpened) {
                closeSearchBar();
            } else {
                openSearchBar();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeSearchBar() {
        setHomeAsUpView("가네야바시 세이콘", 1);
        searchOpened = false;
    }
    private void openSearchBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        ChatSearchView view = new ChatSearchView(ChatActivity.this);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT );
        actionBar.setCustomView(view, layoutParams);
        ChatSearchViewModel model =  new ChatSearchViewModel(view, chatViewAdapter, recyclerView);
        model.executeSearchViewAction();
        searchOpened = true;
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.kakao_bg_transparent)));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.kakao_bg_transparent)));
    }

    private void setHomeAsUpView(String roomName, int roomCount){
        ActionBar actionBar = getSupportActionBar();
        HomeAsUpView view = new HomeAsUpView(ChatActivity.this);
        actionBar.setCustomView(view);
        actionBar.setDisplayShowCustomEnabled(true);
        view.setPeopleCountText(roomCount);
        view.setRoomNameText(roomName);
        view.setOnClickListener(v->{
            Toast.makeText(getApplicationContext(),"Click", Toast.LENGTH_SHORT).show();
        });
    }

    private void setRecyclerView() {
        chatViewAdapter = new ChatViewAdapter();
        recyclerView.setAdapter(chatViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setButtonClickAction() {
        chatViewAdapter.add(new NullData());
        btnReceive.setOnClickListener(view -> {
            chatViewAdapter.add(new ReceiverData(chatText.getText().toString(), DateFormatter.makeChatTimeString(), BitmapFactory.decodeResource(getResources(), R.drawable.person), "김성근"));
            hideSoftInput(view);
            scrollLastItem(recyclerView);
        });
        btnSend.setOnClickListener(view -> {
            chatViewAdapter.add(new SenderData(chatText.getText().toString(), DateFormatter.makeChatTimeString()));
            hideSoftInput(view);
            scrollLastItem(recyclerView);
        });
    }

    private void hideSoftInput(View view) {
        InputMethodManager iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        iMM.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void scrollLastItem(RecyclerView recyclerView) {
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    private void setButtonClickable() {
        btnReceive.setEnabled(false);
        btnSend.setEnabled(false);
        chatText.addTextChangedListener((OnTextChangeListener) (s, start, before, count) -> {
            String text = s.toString();
            if (text.length() == 0) {
                btnReceive.setEnabled(false);
                btnSend.setEnabled(false);
            } else {
                btnReceive.setEnabled(true);
                btnSend.setEnabled(true);
            }
        });
    }

}
