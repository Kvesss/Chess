package com.example.chess;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.NameViewHolder> {

    private final List<String> gameList;
    private final List<String> winnersList;
    private final ButtonClickListener buttonClickListener;

    public CustomAdapter(List<String> gameList, List<String> winnersList, ButtonClickListener buttonClickListener) {
        this.gameList = gameList;
        this.winnersList = winnersList;
        this.buttonClickListener = buttonClickListener;
    }


    @NonNull
    @Override
    public CustomAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NameViewHolder(listItemView, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.NameViewHolder holder, int position) {
        holder.setGame(gameList.get(position), winnersList.get(position));
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public void addNewGame(String players, String gameWinner){
        gameList.add(players);
        winnersList.add(gameWinner);
        notifyItemInserted(gameList.size());
    }

    public void removeGame(int position){
        if(gameList.size() > position){
            gameList.remove(position);
            winnersList.remove(position);
            notifyItemRemoved(position);
        }
    }


    public static class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ButtonClickListener buttonClickListener;
        private final TextView matchNames;
        private final TextView winner;
        private final ImageButton buttonRemove;

        public NameViewHolder(@NonNull View itemView, ButtonClickListener buttonClickListener) {
            super(itemView);
            matchNames = itemView.findViewById(R.id.tvMatchNames);
            winner = itemView.findViewById(R.id.tvGameWinner);
            buttonRemove = itemView.findViewById(R.id.btnRemove);
            this.buttonClickListener = buttonClickListener;
            buttonRemove.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            buttonClickListener.onButtonClick(getAdapterPosition());

        }

        public void setGame(String players, String gameWinner) {
            matchNames.setText(players);
            winner.setText(gameWinner);
        }
    }
}
