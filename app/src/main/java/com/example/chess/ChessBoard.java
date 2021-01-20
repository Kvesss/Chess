package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chess.R;

public class ChessBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_chess);
    }
}