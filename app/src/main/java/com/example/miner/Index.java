package com.example.miner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Index extends AppCompatActivity implements View.OnClickListener {
    AppCompatEditText editText;
    AppCompatButton button;
    public static final String EXTRA_MESSAGE="nameplayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        editText=(AppCompatEditText)findViewById(R.id.name);
        button=(AppCompatButton)findViewById(R.id.play);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText.getText().clear();
    }

    @Override
    public void onClick(View v) {
        Intent activityplay=new Intent(this,MainActivity.class);
        String name=editText.getText().toString();
        activityplay.putExtra(EXTRA_MESSAGE,name);
        startActivity(activityplay);
    }
}