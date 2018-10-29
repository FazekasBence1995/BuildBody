package com.example.kowansky.buildbody.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kowansky.buildbody.Practice;
import com.example.kowansky.buildbody.R;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageButton;

public class PracticesListAdapter extends RecyclerView.Adapter<PracticesListAdapter.PracticeViewHolder> {
        private ArrayList<Practice> gifs;
    private LayoutInflater inflater;


    public PracticesListAdapter(Context context, ArrayList<Practice> gifs) {
        inflater = LayoutInflater.from(context);
        this.gifs = gifs;
    }

    @NonNull
    @Override
    public PracticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.practice_listelement, viewGroup, false);
        return new PracticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PracticesListAdapter.PracticeViewHolder gifViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    class PracticeViewHolder extends RecyclerView.ViewHolder{
        GifImageButton bodypartButton;
        ConstraintLayout constraintLayout;

        public PracticeViewHolder(@NonNull View itemView) {
            super(itemView);

            bodypartButton = (GifImageButton) itemView.findViewById(R.id.practiceGifImageButton);
            constraintLayout = itemView.findViewById(R.id.constraintListElement);
        }
    }
}
