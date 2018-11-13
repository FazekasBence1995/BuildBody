package com.example.kowansky.buildbody.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.kowansky.buildbody.Activitys.MainActivity;
import com.example.kowansky.buildbody.R;
import com.example.kowansky.buildbody.Training;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingsListAdapter extends RecyclerView.Adapter<TrainingsListAdapter.TrainingViewHolder> {
    private ArrayList<Training> gifs;
    private LayoutInflater inflater;
    private PopupWindow popupWindow;
    private ConstraintLayout constraintLayout;
    private TextView popUpTextView;

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
    public void onBindViewHolder(@NonNull TrainingViewHolder gifViewHolder, final int i) {

        gifViewHolder.myTraingingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setUsersTrainings("android", gifs.get(i));
            }
        });

        gifViewHolder.gifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup container = (ViewGroup) inflater.inflate(R.layout.popup,null);

                popupWindow = new PopupWindow(container,400,400,true);
                popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY,500,500);
                popUpTextView = container.findViewById(R.id.popUp);
                popUpTextView.setText(gifs.get(i).getDescription());
            }
        });


    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    class TrainingViewHolder extends RecyclerView.ViewHolder{
        GifImageButton gifButton;

        Button myTraingingsButton;


        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);

            gifButton = itemView.findViewById(R.id.trainingGifImageButton);
            constraintLayout = itemView.findViewById(R.id.constraintListElement);
            myTraingingsButton = itemView.findViewById(R.id.addMyTrainingPlan);
        }
    }

    public void setUsersTrainings(String email, Training training){
        Call<String> call = MainActivity.apiInterface.performUsersTrainingsRegistration(email,training);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("asd", "joba vagyok");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
