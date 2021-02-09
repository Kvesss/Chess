package com.example.chess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.NameViewHolder> {

    private final List<Game> gameList;
    private final ButtonClickListener buttonClickListener;
    Context context;

    public CustomAdapter(List<Game> gameList, ButtonClickListener buttonClickListener, Context context) {
        this.gameList = gameList;
        this.buttonClickListener = buttonClickListener;
        this.context = context;
    }


    @NonNull
    @Override
    public CustomAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NameViewHolder(listItemView, buttonClickListener, this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.NameViewHolder holder, int position) {
        holder.setGame(gameList.get(position).getPlayers(), gameList.get(position).getWinner(), gameList.get(position).getDateTime());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }


    public void removeGame(int position){
        if(gameList.size() > position){
            gameList.remove(position);
            notifyItemRemoved(position);
        }
    }


    public static class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ButtonClickListener buttonClickListener;
        private final TextView matchNames;
        private final TextView winner;
        private final TextView tvDateTime;
        private final ImageButton buttonRemove;
        private Context context;

        public NameViewHolder(@NonNull View itemView, ButtonClickListener buttonClickListener, Context context) {
            super(itemView);
            matchNames = itemView.findViewById(R.id.tvMatchNames);
            winner = itemView.findViewById(R.id.tvGameWinner);
            buttonRemove = itemView.findViewById(R.id.btnRemove);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            this.buttonClickListener = buttonClickListener;
            buttonRemove.setOnClickListener(this);
            this.context = context;

        }

        @Override
        public void onClick(View v) {
            buttonClickListener.onButtonClick(getAdapterPosition());

        }

        public void setGame(String players, String gameWinner, String dateTime) {
            matchNames.setText(players);
            winner.setText(!gameWinner.equals(context.getString(R.string.draw)) ? context.getString(R.string.tvWinnerHistory) + gameWinner : gameWinner);
            tvDateTime.setText(dateTime);
        }
    }
}
