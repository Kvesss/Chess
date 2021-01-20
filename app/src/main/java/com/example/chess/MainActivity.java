package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etPlayerOne;
    private EditText etPlayertwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
    }

    public void onStartClick(View v){
        Intent intent = new Intent(MainActivity.this, ChessBoard.class);
        startActivity(intent);
    }
}