package com.example.haider.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;

public class MediumActivity extends AppCompatActivity {

    GridView maingrid;
    TextView playername,txt,gamesplayed,gamesnum;

    Button playagain;
    int count=0;
    int click=0;

    int[] arrayclickeditems = new int[9];
    String[] players = new String[2];
    String currentplayer="";
    HashSet<PlayerClass> playerturns = new HashSet<PlayerClass>();
    PlayerClass pc = new PlayerClass();
    String temp=""; String temp1="";
    AlertDialog.Builder builder,innerbuilder;
    AlertDialog al,inneral;String gete="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        gete = getIntent().getStringExtra("fbName");
        if(gete.equals(null)){ gete = "Anonymous";}
        playername = (TextView) findViewById(R.id.mediumnametxt);
        txt = (TextView) findViewById(R.id.mediumname);
        playagain = (Button) findViewById(R.id.mediumrestartgame);
        gamesplayed = (TextView) findViewById(R.id.mediumplayed);
        gamesnum = (TextView) findViewById(R.id.mediumgamenum);
        maingrid = (GridView) findViewById(R.id.mediumgamegrid);


        if(playername.getText().equals("") && count == 0){
            players[0] = gete;
            players[1] = "Bot";

            playername.setText(players[0]);
            Random ra = new Random();
            currentplayer = players[0];
            Toast.makeText(this, currentplayer+" Playing ", Toast.LENGTH_SHORT).show();
            maingrid.setAdapter(new GridAdapter(this));
        }


        if(count==0){

//            count++;
            gamesnum.setText(String.valueOf(count));
        }


        builder = new AlertDialog.Builder(MediumActivity.this);
        builder.setTitle("Play More ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                again();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });


        maingrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("CHECK", "onItemClick: Click Handled");
                try {
                    if (currentplayer.equals(players[0])) {

                        if(maingrid.getChildAt(position).isEnabled()) {
                            ImageView imgcross = (ImageView) view;
                            imgcross.setImageResource(R.drawable.cross3);
                            maingrid.getChildAt(position).setEnabled(false);
                            pc.AddPlayer1(players[0], position);
                            playerturns.add(pc);
                            currentplayer = players[1];
                            int a = pc.arr[position];
                            temp += String.valueOf(a);
                            String h = "";
//                            Toast.makeText(MediumActivity.this,players[0]+" ticks  "+temp, Toast.LENGTH_SHORT).show();
                            if (click >= 4) {
                                h = pc.wins(temp);
                                if (h.equals("Wins")) {
//                                    Toast.makeText(MediumActivity.this, players[0] + " Wins ", Toast.LENGTH_LONG).show();

                                    maingrid.setAdapter(new GridAdapter(MediumActivity.this));

//                                    AlertDialog al = builder.create();
//                                    al.show();
//                                    return;

                                }
//                                else{
//                                    Toast.makeText(MediumActivity.this, "Game is Continue", Toast.LENGTH_SHORT).show();
//                                }
                            }
//                        Toast.makeText(MainActivity.this, players[1]+" turn", Toast.LENGTH_SHORT).show();
                            click++;

//                            Toast.makeText(MediumActivity.this, "Player Executed Successfully", Toast.LENGTH_SHORT).show();
                            if (!h.equals("Wins")) {
                                BotTurn();
                            }
                            else{
                                innerbuilder = new AlertDialog.Builder(MediumActivity.this);
                                innerbuilder.setTitle("You Won");
                                innerbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        AlertDialog al = builder.create();
                                        al.show();
                                        return;
                                    }
                                });
                                AlertDialog all = innerbuilder.create();
                                all.show();

                            }
                        }
                        else{
                            Toast.makeText(MediumActivity.this, "Already Checked", Toast.LENGTH_SHORT).show();

                        }


                    }


//                    else {
//
//                    }
                }
                catch (Exception ex){
                    Log.d("Item CLICK ERROR", "onItemClick: "+ex.getMessage().toString());
                }
            }
        });



        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                again();

            }
        });





    }
    public void again(){
        count++;

        maingrid.setAdapter(new GridAdapter(this));
        click=0;
        temp=temp1="";
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
        gamesnum.setText(String.valueOf(count));
        currentplayer = players[0];
    }

    public void BotTurn() {
        int val=0;
        View robotview = null;
//        Random rnd = new Random();
//        int[] exc = pc.exclude;
//        String g="";
//        for(int i=0;i<exc.length;i++){
//            g+=String.valueOf(exc[i]);
//        }
//        Toast.makeText(this, "Exclude Array Values "+g, Toast.LENGTH_SHORT).show();
//        int val = pc.getRandomWithExclusion(rnd, 0, 8, exc);
        if (click <= 7) {
            if(click <= 1) {
                val = pc.getRandomInteger(9, 0);
                robotview = maingrid.getChildAt(val);
            }
            else{
                val = checkBot(temp1);
                robotview = maingrid.getChildAt(val);
            }
            if (maingrid.getChildAt(val).isEnabled()) {
                ImageView imgcross = (ImageView) robotview;
                imgcross.setImageResource(R.drawable.zero3);
                maingrid.getChildAt(val).setEnabled(false);
                pc.AddPlayer2(players[1], val);
                playerturns.add(pc);
                currentplayer = players[0];
                int a = pc.arr1[val];
                temp1 += String.valueOf(a);
//            Toast.makeText(MediumActivity.this,players[1]+" ticks  "+temp1, Toast.LENGTH_SHORT).show();
                if (click >= 4) {
                    String h = pc.wins(temp1);
                    if (h.equals("Wins")) {

                        Toast.makeText(MediumActivity.this, players[1] + " Wins ", Toast.LENGTH_SHORT).show();
                        innerbuilder = new AlertDialog.Builder(MediumActivity.this);
                        innerbuilder.setTitle(players[1]+" Wins");
                        innerbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog al = builder.create();
                                al.show();
                                return;
                            }
                        });
                        AlertDialog all = innerbuilder.create();
                        all.show();
                        return;

                    }
//                else{
//                    Toast.makeText(this, "Game is Continue", Toast.LENGTH_SHORT).show();
//                }
                }
//            Toast.makeText(MainActivity.this, players[0]+" turn", Toast.LENGTH_SHORT).show();

                click++;


//            Toast.makeText(this, "Bot Executed Successfully", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    BotTurn();
                } catch (Exception ex) {
                    Log.e("BOT", "BotTurn: ", ex);
                    Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }


        }
        else{
            Toast.makeText(this, "Bot is out of Turns", Toast.LENGTH_SHORT).show();
        }
    }

    public int checkBot(String temp1){
        int val=0;
        Random rnd = new Random();
        if(temp1.contains("1") && temp1.contains("2") && !temp1.contains("0") && val ==0 ){
            val=0;
        }
        if(temp1.contains("0") && temp1.contains("2") && !temp1.contains("1") && val ==0){
            val=1;
        }
        if(temp1.contains("0") && temp1.contains("1") && !temp1.contains("2") && val ==0){
            val=2;
        }
        if(temp1.contains("4") && temp1.contains("5") && !temp1.contains("3") && val ==0){
            val=3;
        }
        if(temp1.contains("3") && temp1.contains("5") && !temp1.contains("4") && val ==0){
            val=4;
        }
        if(temp1.contains("3") && temp1.contains("4") && !temp1.contains("5") && val ==0){
            val=5;
        }
        if(temp1.contains("7") && temp1.contains("8") && !temp1.contains("6") && val ==0){
            val=6;
        }
        if(temp1.contains("6") && temp1.contains("8") && !temp1.contains("7") && val ==0){
            val=7;
        }
        if(temp1.contains("6") && temp1.contains("7") && !temp1.contains("8") && val ==0){
            val=8;
        }
        if(temp1.contains("1") && temp1.contains("2") && this.temp.contains("0") && val ==0){
            val= rnd.nextInt(8);
        }
        if(temp1.contains("0") && temp1.contains("2") && this.temp.contains("1") && val ==0){
            val=1;
        }
        if(temp1.contains("0") && temp1.contains("1") && this.temp.contains("2") && val ==0){
            val=2;
        }
        if(temp1.contains("4") && temp1.contains("5") && this.temp.contains("3") || val ==0){
            val= rnd.nextInt(8);
        }
        if(temp1.contains("3") && temp1.contains("5") && this.temp.contains("4") && val ==0){
            val=4;
        }
        if(temp1.contains("3") && temp1.contains("4") && this.temp.contains("5") && val ==0){
            val=5;
        }
        if(temp1.contains("7") && temp1.contains("8") && this.temp.contains("6") && val ==0){
            val=6;
        }
        if(temp1.contains("6") && temp1.contains("8") && this.temp.contains("7") && val ==0){
            val=7;
        }
        if(temp1.contains("6") && temp1.contains("7") && this.temp.contains("8") && val ==0){
            val=rnd.nextInt(8);

        }

        return val;
    }
}
