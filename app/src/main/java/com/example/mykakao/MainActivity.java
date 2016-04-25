package com.example.mykakao;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.*;

import com.example.mykakao.chat.pojo.ReceiverData;
import com.example.mykakao.chat.pojo.SenderData;
import com.example.mykakao.chat.view.adapter.ChatViewAdapter;
import com.example.mykakao.util.DateFormatter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Bind(R.id.btnReceive)
    Button btnReceive;
    @Bind(R.id.btnSend)
    Button btnSend;
    @Bind(R.id.chatText)
    EditText chatText;
    ChatViewAdapter chatViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setActionBar("김성근");
        setRecyclerView();
        setButtonClickAction();
        setButtonClickable();
    }

    private void setActionBar(String receiverName) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(receiverName);
    }

    private void setRecyclerView() {
        chatViewAdapter = new ChatViewAdapter();
        recyclerView.setAdapter(chatViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setButtonClickAction() {
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
        chatText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() == 0) {
                    btnReceive.setEnabled(false);
                    btnSend.setEnabled(false);
                } else {
                    btnReceive.setEnabled(true);
                    btnSend.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
