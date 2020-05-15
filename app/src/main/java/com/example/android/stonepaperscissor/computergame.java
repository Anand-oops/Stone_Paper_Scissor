package com.example.android.stonepaperscissor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class computergame extends AppCompatActivity {
    private static final String TAG ="computergame" ;
    String player1,player2="Computer";
    int rounds,score1=0,score2=0;
    int i=1;
    String[] choicesbycomp= new String[]{"rock","paper","scissor"};
    Random rand = new Random();
    String choice1, choice2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computergame_layout);

        Intent IncomingIntent = getIntent();
        Bundle bundle = IncomingIntent.getExtras();
        if (bundle != null) {
            player1 = bundle.getString("player1");
            rounds = bundle.getInt("rounds");
        }
        TextView view;
        view=findViewById(R.id.player1name);
        view.setText("Player: "+player1);

        view=findViewById(R.id.score1);
        view.setText("Score: "+score1);

        view=findViewById(R.id.score2);
        view.setText("Score: "+score2);

        update();

    }
    public void update() {
        Log.i(TAG, "update: Update called");
        if (i > rounds) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(score1>score2){
                        Toast.makeText(getApplicationContext(), player1+ " Wins by "+score1+ ":"+score2,Toast.LENGTH_LONG).show();
                    }else if(score1==score2){
                        Toast.makeText(getApplicationContext(), "It's a draw. Score: "+score1+" each.",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), player2+ " Wins by "+score2+ ":"+score1,Toast.LENGTH_LONG).show();
                    }
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(80);
                    finish();
                }
            },1000);
        } else {
            TextView v;
            v = findViewById(R.id.current_round);
            v.setText("Round: " + i);
        }
    }


    public void onclickrock(View view) throws InterruptedException {
        Log.i(TAG, "onclickrock: Rock clicked");
        choice1 = "rock";
        analyse(choice1, choice2);
    }

    public void onclickpaper(View view) throws InterruptedException {
        Log.i(TAG, "onclickpaper: Paper clicked");
        choice1 = "paper";
        analyse(choice1, choice2);
    }

    public void onclickscissor(View view) throws InterruptedException {
        Log.i(TAG, "onclickscissor: Scissor clicked");
        choice1 = "scissor";
        analyse(choice1, choice2);
    }

    public void analyse(String choice1, String choice2) throws InterruptedException {
        Log.i(TAG, "analyse: Ananlyse called");
            TextView textView;
            LinearLayout layout=findViewById(R.id.layout);
            choice2= choicesbycomp[rand.nextInt(3)];

            if (choice1 == "rock") {
                if (choice2 == "rock") {
                    score1++;
                    score2++;
                    layout.setBackgroundColor(Color.BLUE);
                } else if (choice2 == "paper") {
                    score2++;
                    layout.setBackgroundColor(Color.RED);

                } else {score1++;
                   layout.setBackgroundColor(Color.GREEN);
                }
            }

            else if (choice1 == "paper") {
                if (choice2 == "rock") {
                    score1++;
                    layout.setBackgroundColor(Color.GREEN);
                } else if (choice2 == "paper") {
                    score1++;
                    score2++;
                    layout.setBackgroundColor(Color.BLUE);
                } else {score2++;
                    layout.setBackgroundColor(Color.RED);
                }
            }

            else {
                if (choice2 == "rock") {
                    score2++;
                    layout.setBackgroundColor(Color.RED);
                } else if (choice2 == "paper") {
                    score1++;
                    layout.setBackgroundColor(Color.GREEN);
                } else {
                    score1++;
                    score2++;
                    layout.setBackgroundColor(Color.BLUE);
                }
            }
            Toast.makeText(this, player1+"'s choice: "+choice1+"\n"+player2+"'s choice: "+choice2, Toast.LENGTH_LONG).show();

            Log.i(TAG, "analyse: choice1: "+choice1+" choice2: "+choice2);
            textView = findViewById(R.id.score1);
            textView.setText("Score: " + score1);

            textView = findViewById(R.id.score2);
            textView.setText("Score: " + score2);
            i++;
            update();
        }

}
