package com.example.haider.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelection extends AppCompatActivity {
    Button easy,medium,hard;
    String gete="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        gete = getIntent().getStringExtra("fbName");
        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(LevelSelection.this,SinglePlayerBot.class);
                inte.putExtra("fbName",gete);
                startActivity(inte);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(LevelSelection.this,MediumActivity.class);
                inte.putExtra("fbName",gete);
                startActivity(inte);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(LevelSelection.this,HardActivity.class);
                inte.putExtra("fbName",gete);
                startActivity(inte);
            }
        });
    }
}
