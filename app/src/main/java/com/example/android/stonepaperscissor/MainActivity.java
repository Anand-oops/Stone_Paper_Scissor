package com.example.android.stonepaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void vsplayer(View  v){
        Intent intent=new Intent(this, vsplayer.class);
        startActivity(intent);
    }

    public void vscomputer(View v){
        Intent intent=new Intent(this,vscomputer.class);
        startActivity(intent);
    }
}
