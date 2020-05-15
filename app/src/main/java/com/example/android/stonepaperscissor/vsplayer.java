package com.example.android.stonepaperscissor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class vsplayer extends AppCompatActivity {
    String player1,player2;
    int rounds;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vsplayer_layout);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public void playergame(View v){
        Intent intent=new Intent(this, playergame.class);

        boolean valid=true;
        try {
            text=findViewById(R.id.rounds);
            rounds= Integer.parseInt(text.getText().toString());
        }catch(NumberFormatException e){
            valid=false;}

        intent.putExtra("rounds",rounds);
        if(valid){
            text=findViewById(R.id.player1name);
            player1=text.getText().toString();
            intent.putExtra("player1",player1);

            text=findViewById(R.id.player2name);
            player2=text.getText().toString();
            intent.putExtra("player2",player2);

            text=findViewById(R.id.rounds);
            intent.putExtra("rounds",rounds);
            startActivity(intent);
        }else{
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }
}
