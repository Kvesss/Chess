package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chess.R;

public class ChessBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_chess);
    }

    public void onSurrenderWhite(View v){
        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
        startActivity(intent);
    }

    public void onSurrenderBlack(View v){
        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
        startActivity(intent);
    }

    public void onDraw(View v){
        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
        startActivity(intent);
    }
}