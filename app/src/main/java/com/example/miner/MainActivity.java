package com.example.miner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayoutCompat layoutbutton;
    ArrayList<ButtonSetValue> allbutton= new ArrayList<>();
    ArrayList<TableRow> buttonrow= new ArrayList<>();
    TextView realscoretext;
    TextView scoretogettext;
    TextView highscoretext;
    TextView winnning;
    TextView name;
    ImageButton home;
    ImageButton back;
    ImageButton reload;
    int max=0;
    int score=0;
    int scoretoget=0;
    int highscore=0;
    TableLayout maintable;
    int compt=0;
    ArrayList<Integer> tabtogetback=new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realscoretext=(TextView)findViewById(R.id.realscore);
        scoretogettext=(TextView)findViewById(R.id.scoretoget);
        highscoretext=(TextView)findViewById(R.id.highscore);
        winnning=(TextView)findViewById(R.id.winning);
        name=(TextView)findViewById(R.id.textname);
        home=(ImageButton)findViewById(R.id.HomeButton);
        back=(ImageButton)findViewById(R.id.BackButton);
        reload=(ImageButton)findViewById(R.id.ReloadButton);
        headlayout();
        buttonview();
        setlistener();
        createNoticationChannel();

    }

    public void headlayout(){
        Intent intent=getIntent();
        String nm=intent.getStringExtra(Index.EXTRA_MESSAGE);
        name.setText(nm);
        winnning.setText("");
        int high=getMax();
        highscoretext.setText(""+high);
        scoretoget=3*(int)(Math.random()*1000);
        scoretogettext.setText(""+scoretoget);
        realscoretext.setText(""+score);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LinearLayoutCompat buttonview(){
        layoutbutton=(LinearLayoutCompat)findViewById(R.id.layoutbutton);
        layoutbutton.setLayoutParams(makeLayoutParams(0,30,30,15,0));
        maintable= new TableLayout(this);
        maintable.setLayoutParams( new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        maintable.setOrientation(TableLayout.VERTICAL);
        maintable.setShrinkAllColumns(true);
        maintable.setStretchAllColumns(true);
        int listval[]= new int[20];
        int val;
        int compteur=0;
        ArrayList<Integer> dejaDedans=new ArrayList<Integer>();
        while (compteur<5){
            val=(int)(Math.random()*1000);
            int tmp=getNoReapeateInteger(dejaDedans,20);
            if(listval[tmp]==0){
                listval[tmp]=val;
                compteur++;
            }
        }
        for(int i=0; i<5;i++){
            buttonrow.add( new TableRow(this));
            buttonrow.get(i).setLayoutParams( new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            buttonrow.get(i).setOrientation(TableRow.HORIZONTAL);
            for(int j=0;j<4;j++){
                allbutton.add(new ButtonSetValue(this,(4*i+j),listval[4*i+j]));
                allbutton.get(4*i + j).setId(4*i + j);
                allbutton.get(4*i + j).setText("Push");
                buttonrow.get(i).addView(allbutton.get(4*i + j));
            }
            maintable.addView(buttonrow.get(i));
        }
        layoutbutton.addView(maintable);
        return layoutbutton ;
    }

    private LinearLayoutCompat.LayoutParams makeLayoutParams(int weight, int rightMargin, int leftMargin,
                                                             int topMarging, int bottomMarging) {
        LinearLayoutCompat.LayoutParams buttonLayoutParams = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.weight = weight;
        buttonLayoutParams.rightMargin = rightMargin;
        buttonLayoutParams.leftMargin = leftMargin;
        buttonLayoutParams.topMargin = topMarging;
        buttonLayoutParams.bottomMargin = bottomMarging;
        return buttonLayoutParams;
    }

    private int getNoReapeateInteger(ArrayList<Integer> dejaDdans,int v) {
        int variati=0;
        do{
            variati=new Random().nextInt(v);
        }while(dejaDdans.contains(variati));
        dejaDdans.add(variati);
        return variati;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BackButton:
                if(tabtogetback.size()>0){
                    ButtonSetValue buttonSetValue=findViewById(tabtogetback.get(tabtogetback.size()-1));
                    tabtogetback.remove(tabtogetback.size()-1);
                    buttonSetValue.setEnabled(true);
                    buttonSetValue.setText("PUSH");
                    compt--;
                    int valAdd=buttonSetValue.getAddition();
                    score-=valAdd;
                    realscoretext.setText(""+score);
                }
                break;
            case R.id.HomeButton:
                finish();
                break;
            case R.id.ReloadButton:
                compt=0;
                tabtogetback=new ArrayList<>();
                score=0;
                allbutton.clear();
                buttonrow.clear();
                layoutbutton.removeAllViews();
                headlayout();
                buttonview();
                setlistener();
                break;
            default:
                if (compt<5){
                    ButtonSetValue buttonclicked = findViewById(v.getId());
                    Toast.makeText(MainActivity.this,""+ buttonclicked.getAddition(),
                            Toast.LENGTH_SHORT).show();
                    score+=buttonclicked.getAddition();
                    realscoretext.setText(""+score);
                    buttonclicked.setText("Blocked");
                    buttonclicked.setEnabled(false);
                    compt++;
                    tabtogetback.add(v.getId());
                }
                if(compt>=5){
                    if( score<scoretoget){
                        blockbutton();
                        winnning.setText("You loose");
                        back.setEnabled(false);
                    }
                    else {
                        realscoretext.setText(""+score);
                        winnning.setText("You win");
                        DAO.addscore(name.getText().toString(),score);
                        score=0;
                        blockbutton();
                        setnotification();
                        back.setEnabled(false);
                    }
                    compt=0;
                }
                break;
        }
    }

    public int getMax(){
        if(DAO.tabscore.size()>1){
            for (int i=0;i<DAO.tabscore.size();i++) {
                if (DAO.tabscore.get(i).getScore() > max) {
                    max = DAO.tabscore.get(i).getScore();
                }
            }
            highscore=max;
        }
        else if(DAO.tabscore.size()>0 && DAO.tabscore.size()<2){
            highscore=DAO.tabscore.get(0).getScore();
        }
        return highscore;
    }

    public void setlistener(){
        for(int i=0;i<allbutton.size();i++) {
            allbutton.get(i).setOnClickListener(this);
        }
        home.setOnClickListener(this);
        back.setOnClickListener(this);
        reload.setOnClickListener(this);
    }

    public void blockbutton(){
        for(int i=0;i<allbutton.size();i++) {
            allbutton.get(i).setEnabled(false);
        }
    }

    public void setnotification(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,"notification");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setContentTitle("Score List");
        builder.setAutoCancel(true);

        Intent notifintent= new Intent(MainActivity.this,NotifiyListPlayer.class);
        notifintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,notifintent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager!=null){
            notificationManager.notify(0,builder.build());
        }
    }

    public void createNoticationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name=getString(R.string.channelnametest);
            String description =getString(R.string.testDescription);
            int importance=NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("notification",name,importance);
            channel.setDescription(description);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}