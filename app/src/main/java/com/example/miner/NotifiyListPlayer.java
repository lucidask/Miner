package com.example.miner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotifiyListPlayer extends AppCompatActivity {
    ListView scoreliste;
    ArrayAdapter<Player> tabplayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiy_list_player);
        scoreliste=(ListView)findViewById(R.id.scorelist);
        String message =getIntent().getStringExtra("message");
        getscore();
    }

    public void backtoplay(View view) {
        finish();
    }

    public void getscore(){
        tabplayerAdapter = new ArrayAdapter<>(NotifiyListPlayer.this, android.R.layout.simple_list_item_1, DAO.tabscore);
        scoreliste.setAdapter(tabplayerAdapter);
    }
}