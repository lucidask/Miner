package com.example.miner;

import java.util.ArrayList;

public class DAO {
    public static ArrayList<Player> tabscore= new ArrayList<>();
    public static void addscore(String fullname,int score){
        tabscore.add(new Player(fullname,score));
    }
}
