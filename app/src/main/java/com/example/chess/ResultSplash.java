package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ResultSplash extends AppCompatActivity {


    private static final int RUN_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_result);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(ResultSplash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, RUN_TIME);
    }


}