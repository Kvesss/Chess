package com.example.chess;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class StartGameFragment extends Fragment {
    private EditText player1;
    private EditText player2;


    public static StartGameFragment newInstance() {
        return new StartGameFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_game, container, false);
        Button btnStart = view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChessBoard.class);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        player1 = view.findViewById(R.id.etPlayerOne);
        player2 = view.findViewById(R.id.etPlayerTwo);

    }

    public void onStartClick(View v) {
        Intent intent = new Intent(getActivity(), ChessBoard.class);
        startActivity(intent);
    }
}
