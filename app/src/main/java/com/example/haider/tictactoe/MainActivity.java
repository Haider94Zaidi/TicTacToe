package com.example.haider.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;

import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    GridView maingrid;
    TextView playername1,player1win,playername2,player2win,gamesplayed,gamesnum;
    EditText et,et1;
    Button playagain,ClearMemory;
    int count=0;
    int click=0;
    CallbackManager callbackManager;
    public static final String Pref_Name="GameInfo";
    public static final String Pref_playedgames = "Played";
    public static final String Pref_player1 = "Player1Name";
    public static final String Pref_player2 = "Player2Name";
    public static final String Pref_player1win = "Player1Win";
    public static final String Pref_player2win = "Player2Win";
    public static final String Pref_currentPlayer = "CurrentPlayer";
    SharedPreferences sharedPreferences;
    int[] arrayclickeditems = new int[9];
    String[] players = new String[2];
    String currentplayer="";
    HashSet<PlayerClass> playerturns = new HashSet<PlayerClass>();
    PlayerClass pc = new PlayerClass();
    View myview;
    String temp=""; String temp1="";
    int arr[] = new int[9];
    int cc=0,player1score=0,player2score=0;
    int combarrplayer1[] = new int[9];
    LinearLayout ll;
    AlertDialog.Builder builder,innerbuilder;
    AlertDialog al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playername1 = (TextView) findViewById(R.id.p1nametxt);
        player1win = (TextView) findViewById(R.id.p1win);
        playername2 = (TextView) findViewById(R.id.p2nametxt);
        player2win = (TextView) findViewById(R.id.p2win);

        playagain = (Button) findViewById(R.id.restartgame);
        ClearMemory = (Button) findViewById(R.id.clear_memory);
        gamesplayed = (TextView) findViewById(R.id.played);
        gamesnum = (TextView) findViewById(R.id.gamenum);
        maingrid = (GridView) findViewById(R.id.gamegrid);



        et = new EditText(this);        // This Editext is in AlertDialog
        et.setHint("Player1 Name");
        et1 = new EditText(this);        // This Editext is in AlertDialog
        et1.setHint("Player2 Name");
        ll =new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(et);
        ll.addView(et1);
        GetSaveSettings();

        if(sharedPreferences.getString(Pref_player1,"").equals("")){
            SetPlayer();
        }
        else{

            playerturns.clear();
            pc = null;
            pc = new PlayerClass();
            Random ra = new Random();
            currentplayer = players[ra.nextInt(players.length)];
            Toast.makeText(this,currentplayer+ " Playing", Toast.LENGTH_SHORT).show();
//            maingrid.setAdapter(new GridAdapter(MainActivity.this));
        }



        if(count==0){

//            count++;
            gamesnum.setText(String.valueOf(count));
        }





        maingrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            try {
                if (currentplayer.equals(players[0])) {


                    if(maingrid.getChildAt(position).isEnabled()){
                        ImageView imgcross = (ImageView) view;
                        imgcross.setImageResource(R.drawable.cross);
                        maingrid.getChildAt(position).setEnabled(false);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You Cannot Undo The Turn", Toast.LENGTH_SHORT).show();
                        return;
                    }


                        pc.AddPlayer1(players[0], position);
                        playerturns.add(pc);
                        currentplayer = players[1];
                        int a = pc.arr[position];
                        temp += String.valueOf(a);
//                        Toast.makeText(MainActivity.this,players[0]+" ticks  "+temp, Toast.LENGTH_SHORT).show();
                        if (click >= 4) {
                            String h = pc.wins(temp);
                            if (h.equals("Wins")) {
                                builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle(players[0]+" Wins");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        innerbuilder = new AlertDialog.Builder(MainActivity.this);
                                        innerbuilder.setTitle("Play More ?");
                                        innerbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                player1score++;
                                                player1win.setText(String.valueOf(player1score));
                                                playerturns.clear();
                                                playagain();
                                            }
                                        });
                                        innerbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                player1score++;
                                                player1win.setText(String.valueOf(player1score));
                                                playerturns.clear();
                                                finish();
                                            }
                                        });
                                        AlertDialog all = innerbuilder.create();
                                        all.show();

                                    }
                                });
                                AlertDialog al = builder.create();
                                al.show();


//                                Toast.makeText(MainActivity.this, players[0] + " Wins ", Toast.LENGTH_SHORT).show();
//                                player1score++;

                            }
                        }
//                        Toast.makeText(MainActivity.this, players[1]+" turn", Toast.LENGTH_SHORT).show();
                        click++;


                    }



                 else {


                    if(maingrid.getChildAt(position).isEnabled()){
                        ImageView imgzero = (ImageView) view;
                        imgzero.setImageResource(R.drawable.zero);
                        maingrid.getChildAt(position).setEnabled(false);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You Cannot Undo The Turn", Toast.LENGTH_SHORT).show();
                        return;
                    }

                        pc.AddPlayer2(players[1], position);
                        playerturns.add(pc);
                        currentplayer = players[0];
                        int a = pc.arr1[position];
                        temp1 += String.valueOf(a);
                        if (click >= 4) {
                            String h = pc.wins(temp1);
                            if (h.equals("Wins")) {
                                builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setCancelable(false);
                                builder.setTitle(players[1]+" Wins");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        innerbuilder = new AlertDialog.Builder(MainActivity.this);
                                        innerbuilder.setCancelable(false);
                                        innerbuilder.setTitle("Play More ?");
                                        innerbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                player2score++;
                                                player2win.setText(String.valueOf(player2score));
                                                playerturns.clear();
                                                playagain();
                                            }
                                        });
                                        innerbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                player2score++;
                                                player2win.setText(String.valueOf(player2score));
                                                playerturns.clear();
                                                finish();
                                            }
                                        });
                                        AlertDialog all = innerbuilder.create();
                                        all.show();
                                    }
                                });
                                AlertDialog al = builder.create();
                                al.show();


//                                Toast.makeText(MainActivity.this, players[1] + " Wins ", Toast.LENGTH_SHORT).show();




                            }
                        }
//                        Toast.makeText(MainActivity.this, players[0]+" turn", Toast.LENGTH_SHORT).show();
                        click++;
                    }

            }
            catch (Exception ex){
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
         }
        });



        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                click=0;
                temp=temp1="";
                playerturns.clear();
                pc = null;
                pc = new PlayerClass();
                gamesnum.setText(String.valueOf(count));
                players[0] = playername1.getText().toString();
                players[1] = playername2.getText().toString();

                Random ra = new Random();
                currentplayer = players[ra.nextInt(players.length)];
                Toast.makeText(MainActivity.this, currentplayer+" Playing ", Toast.LENGTH_SHORT).show();
                maingrid.setAdapter(new GridAdapter(MainActivity.this));
            }
        });

        ClearMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(Pref_Name, Context.MODE_PRIVATE);
                SharedPreferences.Editor cleareditor = sharedPreferences.edit();
                String s = sharedPreferences.getString(Pref_currentPlayer,"");
                cleareditor.remove(Pref_currentPlayer);
                cleareditor.remove(Pref_player1);
                cleareditor.remove(Pref_player2);
                cleareditor.remove(Pref_player1win);
                cleareditor.remove(Pref_player2win);
                cleareditor.remove(Pref_playedgames);
                cleareditor.clear();
                if(cleareditor.commit()) {

                    SetPlayer();
                }
                else{
                    Toast.makeText(MainActivity.this, "Can't clear the memory", Toast.LENGTH_SHORT).show();
                }
//                SetPlayer();

            }
        });


    }
    @Override
    public void onPause(){
        super.onPause();
        maingrid.setAdapter(new GridAdapter(MainActivity.this));
        click=0;
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
            SaveSettings();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        maingrid.setAdapter(new GridAdapter(MainActivity.this));
        click=0;
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
        SaveSettings();
    }@Override
    public void onResume(){
        super.onResume();
        maingrid.setAdapter(new GridAdapter(MainActivity.this));
        click=0;
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
        SaveSettings();
    }
    @Override
    public void onStop(){
        super.onStop();
        maingrid.setAdapter(new GridAdapter(MainActivity.this));
        click=0;
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
            SaveSettings();

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        maingrid.setAdapter(new GridAdapter(MainActivity.this));
        click=0;
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
            SaveSettings();

    }



    public void SetPlayer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Enter Your Name");
        builder.setView(ll);
        builder.setPositiveButton("Enter Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                String ett = et.getText().toString();
//                String ett1 = et1.getText().toString();
                if(!et.getText().toString().equals("") && !et1.getText().toString().equals("") && count == 0){

                playername1.setText(et.getText().toString());
                player1win.setText(String.valueOf(player1score));
                playername2.setText(et1.getText().toString());
                player2win.setText(String.valueOf(player2score));


//            SetPlayer();
                    players[0] = playername1.getText().toString();
                    players[1] = playername2.getText().toString();

                    Random ra = new Random();
                    currentplayer = players[ra.nextInt(players.length)];
                    Toast.makeText(MainActivity.this, currentplayer+" Playing ", Toast.LENGTH_SHORT).show();
                    maingrid.setAdapter(new GridAdapter(MainActivity.this));
                }
                else{
                    SetPlayer();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog al = builder.create();
        al.show();
    }

    private void SaveSettings(){
        sharedPreferences = getSharedPreferences(this.Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Pref_player1,playername1.getText().toString());
        editor.putString(Pref_player2,playername2.getText().toString());
        editor.putString(Pref_player1win,player1win.getText().toString());
        editor.putString(Pref_player2win,player2win.getText().toString());
        editor.putString(Pref_playedgames,gamesnum.getText().toString());
        editor.putString(Pref_currentPlayer,currentplayer);

        editor.commit();
    }

    private void GetSaveSettings(){
        sharedPreferences = getSharedPreferences(this.Pref_Name,Context.MODE_PRIVATE);

            String x = sharedPreferences.getString(Pref_playedgames,"");
            if(x=="") {
                x = "0";
                try {
                    this.count = Integer.parseInt(x);
                    gamesnum.setText(String.valueOf(this.count));
                }
                catch(Exception e){
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }else{
                this.count = Integer.parseInt(x);
                gamesnum.setText(String.valueOf(this.count));
            }
        playername1.setText(sharedPreferences.getString(Pref_player1,""));
        playername2.setText(sharedPreferences.getString(Pref_player2,""));
        player1win.setText(sharedPreferences.getString(Pref_player1win,""));
        player2win.setText(sharedPreferences.getString(Pref_player2win,""));
        currentplayer = sharedPreferences.getString(Pref_currentPlayer,"");

        String p1win = sharedPreferences.getString(Pref_player1win,"");
        String p2win = sharedPreferences.getString(Pref_player2win,"");

        if(p1win != "" && p2win != ""){
            player1score = Integer.parseInt(p1win);
            player2score = Integer.parseInt(p2win);
        }
        players[0] = playername1.getText().toString();
        players[1] = playername2.getText().toString();
    }

    public void playagain(){

        count++;

        click=0;
        temp=temp1="";
        playerturns.clear();
        pc = null;
        pc = new PlayerClass();
        gamesnum.setText(String.valueOf(count));
        players[0] = playername1.getText().toString();
        players[1] = playername2.getText().toString();

        Random ra = new Random();
        currentplayer = players[ra.nextInt(players.length)];
        Toast.makeText(MainActivity.this, currentplayer+" Playing ", Toast.LENGTH_SHORT).show();
        maingrid.setAdapter(new GridAdapter(MainActivity.this));
    }
}
