package com.example.haider.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameType extends AppCompatActivity {
    Button single,multi;
    String gete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        gete = getIntent().getStringExtra("fbName");
        single = (Button) findViewById(R.id.singleplayerbtn);
        multi = (Button) findViewById(R.id.multiplayerbtn);


        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singleint = new Intent(GameType.this,LevelSelection.class);
                singleint.putExtra("fbName",gete);
                startActivity(singleint);
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multint = new Intent(GameType.this,MainActivity.class);
                startActivity(multint);
            }
        });



    }
}
