package com.example.chess;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class GamesViewFragment extends Fragment implements ButtonClickListener {

    private RecyclerView recyclerView;
    private List<String> gameList;
    private List<String> winnersList;
    private CustomAdapter customAdapter;


    public static GamesViewFragment newInstance() {
        return new GamesViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_games_view, container, false);
        setupGames();
        setupRecyclerView(view);
        return view;


    }

    @Override
    public void onButtonClick(int position) {
        ((CustomAdapter)recyclerView.getAdapter()).removeGame(position);
    }

    private void setupGames(){
        //TODO database
        gameList = new ArrayList<>();

        gameList.add("Edgar Davids");
        gameList.add("Hernan Crespo");
        gameList.add("Andy Cole");
        winnersList = new ArrayList<>();
        winnersList.add("Edgar Davids");
        winnersList.add("Hernan Crespo");
        winnersList.add("Andy Cole");
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        customAdapter = new CustomAdapter(gameList, winnersList, this);
        recyclerView.setAdapter(customAdapter);
    }
}