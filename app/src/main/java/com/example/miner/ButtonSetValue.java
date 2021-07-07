package com.example.miner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;



public class ButtonSetValue extends AppCompatButton {
    private int pointer;
    private int addition;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ButtonSetValue(Context context, int pointer, int addition) {
        super(context);
        this.pointer = pointer;
        this.addition = addition;
        this.setTextColor(Color.rgb(255,255,255));
        this.setHeight(220);
        this.setBackgroundTintList(ColorStateList.valueOf(Color.rgb((int)(Math.random()*1000),(int)(Math.random()*1000),(int)(Math.random()*1000))));
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getAddition() {
        return addition;
    }

    public void setAddition(int addition) {
        this.addition = addition;
    }
}
