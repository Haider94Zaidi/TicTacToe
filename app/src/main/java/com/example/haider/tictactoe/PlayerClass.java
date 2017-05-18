package com.example.haider.tictactoe;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Haider on 3/30/2017.
 */

public class PlayerClass {

    int count=0;
    private int position;
    private int position1;
    private String PlayerName;
    int[] arr = new int[9];
    int[] arr1 = new int[9];
    int[] exclude;

    public PlayerClass(){
        exclude = new int[1];
    }


    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPosition1(int position1) {
        this.position1 = position1;
    }

    public void AddPlayer1(String name,int position){
        setPlayerName(name);
        setPosition(position);
        arr[position]=position;
        exclude[count] = position;
        exclude = Arrays.copyOf(exclude,exclude.length+1);
        count++;

    }
    public void AddPlayer2(String name,int position){
        setPlayerName(name);
        setPosition1(position);
        arr1[position1]=position;
        exclude[count] = position;
        exclude = Arrays.copyOf(exclude,exclude.length+1);
        count++;


    }

    public String wins(String s){
        String w = "";
            for(int i=0;i<s.length();i++){
                if(s.contains("0")){
                    if(s.contains("1") && s.contains("2") || s.contains("3") && s.contains("6") || s.contains("4") && s.contains("8"))
                        {
                            w = "Wins";
                            break;
                        }
                     else
                         {
                            w = "";
                         }
                }
                if(s.contains("1")){
                    if(s.contains("4") && s.contains("7") || s.contains("0") && s.contains("2"))
                        {
                            w = "Wins";
                            this.arr = null;
                            this.arr1 = null;
                            break;
                        }
                    else
                        {
                            w = "";
                        }
                }
                if(s.contains("2")){
                    if(s.contains("5") && s.contains("8") || s.contains("4") && s.contains("6"))
                    {
                            w = "Wins";
                            break;
                    }
                    else
                        {
                            w = "";
                        }
                }
                if(s.contains("6")){
                    if(s.contains("7") && s.contains("8"))
                    {
                        w = "Wins";
                        break;
                    }
                    else
                    {
                        w = "";
                    }
                }
                if(s.contains("4")){
                    if(s.contains("3") && s.contains("5"))
                    {
                        w = "Wins";
                        break;
                    }
                    else
                    {
                        w = "";
                    }
                }

            }
        this.arr = null;
        this.arr1 = null;
        this.arr = new int[9];
        this.arr1 = new int[9];
        return w;
    }

    public int getRandomWithExclusion(Random rnd, int start, int end, int[] exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    public int getRandomInteger(int maximum, int minimum){ return ((int) (Math.random()*(maximum - minimum))) + minimum; }


}
