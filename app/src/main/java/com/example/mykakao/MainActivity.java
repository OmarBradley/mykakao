package com.example.mykakao;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.example.mykakao.chat.pojo.ReceiverData;
import com.example.mykakao.chat.pojo.SenderData;
import com.example.mykakao.chat.view.adapter.ChatViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler) RecyclerView recyclerView;
    @Bind(R.id.btnReceive) Button btnReceive;
    @Bind(R.id.btnSend) Button btnSend;
    @Bind(R.id.chatText) EditText chatText;
    ChatViewAdapter chatViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRecyclerView();

        btnReceive.setOnClickListener(view -> {
            chatViewAdapter.add(new ReceiverData(chatText.getText().toString(), "4시 27분", BitmapFactory.decodeResource(getResources(), R.drawable.person), "김성근"));
        });

        btnSend.setOnClickListener(view -> {
            chatViewAdapter.add(new SenderData(chatText.getText().toString(), "4시 27분"));
        });
    }

    private void setRecyclerView(){
        chatViewAdapter = new ChatViewAdapter();
        recyclerView.setAdapter(chatViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }







}
