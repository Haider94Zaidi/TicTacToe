package com.example.haider.tictactoe;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class HardActivity extends AppCompatActivity {

    int c[][];
    int i, j, k = 0;
    Button b[][];
    TextView textView,signinname;
    AI ai;
    Button restart;
    String s="",gete="";
    int count=0;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);

        gete = getIntent().getExtras().getString("fbName");
        restart = (Button) findViewById(R.id.restart_btn);
        signinname = (TextView) findViewById(R.id.signin_name);
        s = signinname.getText().toString();
        signinname.setText(gete);



        setBoard();


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float deg = restart.getRotation() + 180F;
                float dg = deg+180F;
                restart.animate().rotation(dg).setInterpolator(new AccelerateDecelerateInterpolator());
                textView.setTextColor(Color.BLACK);
                int c[][];
                int i, j, k = 0;
                Button b[][];
                TextView textView;
                AI ai;
                setBoard();
            }
        });

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            MenuItem item = menu.add("New Game");
            return true;
        }

    public boolean onOptionsItemSelected(MenuItem item) {
        setBoard();
        return true;
    }

    // Set up the game board.
    private void setBoard() {
        ai = new AI();
        b = new Button[4][4];
        c = new int[4][4];


        textView = (TextView) findViewById(R.id.dialogue);


        b[1][3] = (Button) findViewById(R.id.one);
        b[1][2] = (Button) findViewById(R.id.two);
        b[1][1] = (Button) findViewById(R.id.three);


        b[2][3] = (Button) findViewById(R.id.four);
        b[2][2] = (Button) findViewById(R.id.five);
        b[2][1] = (Button) findViewById(R.id.six);


        b[3][3] = (Button) findViewById(R.id.seven);
        b[3][2] = (Button) findViewById(R.id.eight);
        b[3][1] = (Button) findViewById(R.id.nine);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }


        textView.setText("Click a button to start.");



        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }


    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                b[x][y].setText("O");
                b[x][y].setTextColor(Color.BLUE);
                b[x][y].setTextSize(55);
                c[x][y] = 0;
                textView.setText("");
                if (!checkBoard()) {
                    ai.takeTurn();
                }
            }
        }
    }


    private class AI {
        public void takeTurn() {
            if (c[1][1] == 2 &&
                    ((c[1][2] == 1 && c[1][3] == 1) ||
                            (c[2][2] == 1 && c[3][3] == 1) ||
                            (c[2][1] == 1 && c[3][1] == 1))) {
                markSquare(1, 1);
            } else if (c[1][2] == 2 &&
                    ((c[2][2] == 1 && c[3][2] == 1) ||
                            (c[1][1] == 1 && c[1][3] == 1))) {
                markSquare(1, 2);
            } else if (c[1][3] == 2 &&
                    ((c[1][1] == 1 && c[1][2] == 1) ||
                            (c[3][1] == 1 && c[2][2] == 1) ||
                            (c[2][3] == 1 && c[3][3] == 1))) {
                markSquare(1, 3);
            } else if (c[2][1] == 2 &&
                    ((c[2][2] == 1 && c[2][3] == 1) ||
                            (c[1][1] == 1 && c[3][1] == 1))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[1][1] == 1 && c[3][3] == 1) ||
                            (c[1][2] == 1 && c[3][2] == 1) ||
                            (c[3][1] == 1 && c[1][3] == 1) ||
                            (c[2][1] == 1 && c[2][3] == 1))) {
                markSquare(2, 2);
            } else if (c[2][3] == 2 &&
                    ((c[2][1] == 1 && c[2][2] == 1) ||
                            (c[1][3] == 1 && c[3][3] == 1))) {
                markSquare(2, 3);
            } else if (c[3][1] == 2 &&
                    ((c[1][1] == 1 && c[2][1] == 1) ||
                            (c[3][2] == 1 && c[3][3] == 1) ||
                            (c[2][2] == 1 && c[1][3] == 1))) {
                markSquare(3, 1);
            } else if (c[3][2] == 2 &&
                    ((c[1][2] == 1 && c[2][2] == 1) ||
                            (c[3][1] == 1 && c[3][3] == 1))) {
                markSquare(3, 2);
            } else if (c[3][3] == 2 &&
                    ((c[1][1] == 1 && c[2][2] == 1) ||
                            (c[1][3] == 1 && c[2][3] == 1) ||
                            (c[3][1] == 1 && c[3][2] == 1))) {
                markSquare(3, 3);
            } else if (c[1][1] == 2 &&
                    ((c[1][2] == 0 && c[1][3] == 0) ||
                            (c[2][2] == 0 && c[3][3] == 0) ||
                            (c[2][1] == 0 && c[3][1] == 0))) {
                markSquare(1, 1);
            } else if (c[1][2] == 2 &&
                    ((c[2][2] == 0 && c[3][2] == 0) ||
                            (c[1][1] == 0 && c[1][3] == 0))) {
                markSquare(1, 2);
            } else if (c[1][3] == 2 &&
                    ((c[1][1] == 0 && c[1][2] == 0) ||
                            (c[3][1] == 0 && c[2][2] == 0) ||
                            (c[2][3] == 0 && c[3][3] == 0))) {
                markSquare(1, 3);
            } else if (c[2][1] == 2 &&
                    ((c[2][2] == 0 && c[2][3] == 0) ||
                            (c[1][1] == 0 && c[3][1] == 0))) {
                markSquare(2, 1);
            } else if (c[2][2] == 2 &&
                    ((c[1][1] == 0 && c[3][3] == 0) ||
                            (c[1][2] == 0 && c[3][2] == 0) ||
                            (c[3][1] == 0 && c[1][3] == 0) ||
                            (c[2][1] == 0 && c[2][3] == 0))) {
                markSquare(2, 2);
            } else if (c[2][3] == 2 &&
                    ((c[2][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[3][3] == 0))) {
                markSquare(2, 3);
            } else if (c[3][1] == 2 &&
                    ((c[1][1] == 0 && c[2][1] == 0) ||
                            (c[3][2] == 0 && c[3][3] == 0) ||
                            (c[2][2] == 0 && c[1][3] == 0))) {
                markSquare(3, 1);
            } else if (c[3][2] == 2 &&
                    ((c[1][2] == 0 && c[2][2] == 0) ||
                            (c[3][1] == 0 && c[3][3] == 0))) {
                markSquare(3, 2);
            } else if (c[3][3] == 2 &&
                    ((c[1][1] == 0 && c[2][2] == 0) ||
                            (c[1][3] == 0 && c[2][3] == 0) ||
                            (c[3][1] == 0 && c[3][2] == 0))) {
                markSquare(3, 3);
            } else {
                if (c[2][2] == 2) {
                    markSquare(2, 2);
                } else {
                    Random rand = new Random();
                    int[] aa = {1,3};
                    int[] bb = {3,1};
                    int a_ = aa[rand.nextInt(aa.length)];
                    int b_ = bb[rand.nextInt(bb.length)];
                    while (a_ == 0 || b_ == 0 || c[a_][b_] != 2) {
                        a_ = aa[rand.nextInt(aa.length)];
                        b_ = bb[rand.nextInt(bb.length)];
                    }
                    markSquare(a_, b_);
                }

            }
        }



        private void markSquare(int x, int y) {
            b[x][y].setEnabled(false);
            b[x][y].setText("X");
            b[x][y].setTextColor(Color.MAGENTA);
            b[x][y].setTextSize(55);
            c[x][y] = 1;
            checkBoard();
        }
    }


    // check the board to see if someone has won
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            textView.setText("Game over. You win!");
            textView.setTextColor(Color.MAGENTA);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            gameOver = true;
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            textView.setText("Game over. You lost!");
            textView.setTextColor(Color.RED);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            gameOver = true;
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                textView.setText("Game over. It's a draw!");
                textView.setTextColor(Color.BLUE);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }
        return gameOver;
    }

}

