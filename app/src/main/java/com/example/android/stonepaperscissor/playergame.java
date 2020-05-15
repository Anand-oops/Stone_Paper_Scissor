package com.example.android.stonepaperscissor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class playergame extends AppCompatActivity {
    private static final String TAG = "playergame";
    String player1 = "", player2 = "";
    int rounds, score1 = 0, score2 = 0;
    int j = 0; int i=1;
    String choice1, choice2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playergame_layout);

        Intent IncomingIntent = getIntent();
        Bundle bundle = IncomingIntent.getExtras();
        if (bundle != null) {
            player1 = bundle.getString("player1");
            player2 = bundle.getString("player2");
            rounds = bundle.getInt("rounds");
        }
        Log.i(TAG, "onCreate:" + player1 + player2 + rounds);
        TextView v;
        v = findViewById(R.id.player1name);
        v.setText("Player: " + player1);

        v = findViewById(R.id.score1);
        v.setText("Score: " + score1);

        v = findViewById(R.id.player2name);
        v.setText("Player: " + player2);

        v = findViewById(R.id.score2);
        v.setText("Score: " + score2);

        update();
    }

    public void update() {
        Log.i(TAG, "update: Update called");
        if(i>rounds){
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);
            if(score1>score2){
                Toast.makeText(this, player1+ " Wins by "+score1+ ":"+score2,Toast.LENGTH_LONG).show();
            }else if(score1==score2){
                Toast.makeText(this, "It's a draw. Score: "+score1+" each.",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, player2+ " Wins by "+score2+ ":"+score1,Toast.LENGTH_LONG).show();
            }
            super.finish();}
            TextView v;
            v = findViewById(R.id.current_round);
            v.setText("Round: "+i);

            v = findViewById(R.id.current_player);
            if ((j % 2 == 0)) {
                v.setText(player1 + "'s Turn");
            } else {
                v.setText(player2 + "'s Turn");
            }

        }


    public void onclickrock(View view) {
        Log.i(TAG, "onclickrock: Rock clicked");
            if (j % 2 == 0) {
                choice1 = "rock";
                j++;
            } else {
                choice2 = "rock";
                j++;
            }
            analyse(choice1, choice2);
        }

    public void onclickpaper(View view) {
        Log.i(TAG, "onclickpaper: Paper clicked");
        if (j % 2 == 0) {
            choice1 = "paper";
            j++;
        } else {
            choice2 = "paper";
            j++;
        }
        analyse(choice1, choice2);
    }

    public void onclickscissor(View view) {
        Log.i(TAG, "onclickscissor: Scissor clicked");
        if (j % 2 == 0) {
            choice1 = "scissor";
            j++;
        } else {
            choice2 = "scissor";
            j++;
        }
        analyse(choice1, choice2);
    }

    public void analyse(String choice1, String choice2) {
        Log.i(TAG, "analyse: Ananlyse called");
            if (j % 2 == 0) {
                TextView textView;

                if (choice1 == "rock") {
                    if (choice2 == "rock") {
                        score1++;
                        score2++;
                    } else if (choice2 == "paper") {
                        score2++;
                    } else {score1++;}
                }

                else if (choice1 == "paper") {
                    if (choice2 == "rock") {
                        score1++;
                    } else if (choice2 == "paper") {
                        score1++;
                        score2++;
                    } else {score2++;}
                }

                else {
                    if (choice2 == "rock") {
                        score2++;
                    } else if (choice2 == "paper") {
                        score1++;
                    } else {
                        score1++;
                        score2++;
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
            else update();
    }
}
