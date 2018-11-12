package com.example.kowansky.buildbody.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kowansky.buildbody.R;
import com.example.kowansky.buildbody.Training;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageButton;

public class TrainingsListAdapter extends RecyclerView.Adapter<TrainingsListAdapter.TrainingViewHolder> {
    private ArrayList<Training> gifs;
    private LayoutInflater inflater;


    public TrainingsListAdapter(Context context, ArrayList<Training> gifs) {
        inflater = LayoutInflater.from(context);
        this.gifs = gifs;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.training_listelement, viewGroup, false);
        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingsListAdapter.TrainingViewHolder gifViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    class TrainingViewHolder extends RecyclerView.ViewHolder{
        GifImageButton bodypartButton;
        ConstraintLayout constraintLayout;

        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);

            bodypartButton = (GifImageButton) itemView.findViewById(R.id.trainingGifImageButton);
            constraintLayout = itemView.findViewById(R.id.constraintListElement);
        }
    }
}
