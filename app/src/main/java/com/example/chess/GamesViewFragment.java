package com.example.chess;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GamesViewFragment extends Fragment implements ButtonClickListener {


    DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private List<Game> gameList;
    private CustomAdapter customAdapter;


    public static GamesViewFragment newInstance() {
        return new GamesViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_games_view, container, false);
        databaseHelper = new DatabaseHelper(getContext());
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
        gameList = databaseHelper.getAllGames();

    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        customAdapter = new CustomAdapter(gameList, this);
        recyclerView.setAdapter(customAdapter);
    }
}