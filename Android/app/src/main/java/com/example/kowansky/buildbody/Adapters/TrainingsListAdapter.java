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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kowansky.buildbody.Activitys.MainActivity;
import com.example.kowansky.buildbody.Application.ApiClient;
import com.example.kowansky.buildbody.Application.ErrorUtil;
import com.example.kowansky.buildbody.Application.PrefConfig;
import com.example.kowansky.buildbody.Application.ValidationError;
import com.example.kowansky.buildbody.Fragments.TrainingsFragment;
import com.example.kowansky.buildbody.R;
import com.example.kowansky.buildbody.Training;
import com.squareup.picasso.Picasso;

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
    private PrefConfig prefConfig;
    private Context context;

    public TrainingsListAdapter(Context context, ArrayList<Training> gifs) {
        prefConfig = new PrefConfig(context);
        this.context = context;
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
        final String gifURL = ApiClient.GIF_URL+gifs.get(i).getUrl()+".gif";

        Glide.with(context).asGif().load(gifURL).into(gifViewHolder.gifButton);

        gifViewHolder.myAddTraingingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUsersTrainings(gifs.get(i), v);
            }
        });

        gifViewHolder.gifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup container = (ViewGroup) inflater.inflate(R.layout.popup,null);

                popupWindow = new PopupWindow(container, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY,0,225);
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

        ImageButton myAddTraingingsButton;

        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);

            gifButton = itemView.findViewById(R.id.trainingGifImageButton);
            constraintLayout = itemView.findViewById(R.id.constraintListElement);
            myAddTraingingsButton = itemView.findViewById(R.id.addMyTrainingPlan);
        }
    }

    public void setUsersTrainings(Training training, final View v){
        String token = prefConfig.readAccesToken();
        Call<Void> call = MainActivity.apiInterface.performUsersTrainingsRegistration(training, token);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    displayAddUsersTrainingsToast(v);
                }
                else if(response.code()==403){
                    ValidationError validationError = ErrorUtil.parseError(response);
                    Toast.makeText(v.getContext(), validationError.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void displayAddUsersTrainingsToast(View v){
        Toast.makeText(v.getContext(), R.string.add, Toast.LENGTH_LONG).show();
    }
}
