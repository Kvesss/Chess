package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class ResultSplash extends AppCompatActivity {


    private static final int RUN_TIME = 5000;
    private String winner;
    private TextView tvWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_result);
        Bundle bundle = getIntent().getExtras();
        winner = bundle.getString("Winner");
        tvWinner = findViewById(R.id.tvWinner);
        tvWinner.setText(winner);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(ResultSplash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, RUN_TIME);
    }


}