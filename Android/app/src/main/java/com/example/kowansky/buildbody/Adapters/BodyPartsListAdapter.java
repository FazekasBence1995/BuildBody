package com.example.kowansky.buildbody.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kowansky.buildbody.Activitys.MainActivity;
import com.example.kowansky.buildbody.BodyPart;
import com.example.kowansky.buildbody.PracticeInformation.Training;
import com.example.kowansky.buildbody.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BodyPartsListAdapter extends RecyclerView.Adapter<BodyPartsListAdapter.BodyPartViewHolder> {
    private ArrayList<BodyPart> bodyParts;
    private LayoutInflater inflater;

    private OnBodyPartsListAdapterListener bodyPartsListAdapterListener;

    public BodyPartsListAdapter(Context context, ArrayList<BodyPart> bodyParts) {
        inflater = LayoutInflater.from(context);
        this.bodyParts = bodyParts;
    }

    public interface OnBodyPartsListAdapterListener{
        public void bodyPartsApply(String name);
    }

    @NonNull
    @Override
    public BodyPartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.bodypart_listelement, viewGroup, false);
        return new BodyPartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartViewHolder bodyPartViewHolder, int i) {
        final BodyPart bodyPart = bodyParts.get(i);

        bodyPartViewHolder.bodypartButton.setImageResource(bodyPart.getImageId());

        bodyPartViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(bodyPart.getName());

                Training training = new Training(bodyPart.getName(), "null", "null");
                Call<Training> call = MainActivity.apiInterface.performBodyParts(training);

                call.enqueue(new Callback<Training>() {
                    @Override
                    public void onResponse(Call<Training> call, Response<Training> response) {
                        if(response.isSuccessful()){
                            Training trainings = new Training("kacsa", "kacsa", "null");
                            trainings.setDescription(response.body().toString());
                            System.out.println(trainings.getDescription());
                            bodyPartsListAdapterListener.bodyPartsApply(bodyPart.getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<Training> call, Throwable t) {
                        System.out.println("itt vagyok");
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return bodyParts.size();
    }

    class BodyPartViewHolder extends RecyclerView.ViewHolder{
        ImageView bodypartButton;
        ConstraintLayout constraintLayout;

        public BodyPartViewHolder(@NonNull View itemView) {
            super(itemView);

            bodypartButton = (ImageView) itemView.findViewById(R.id.bodypartBt);
            constraintLayout = itemView.findViewById(R.id.constraintListElement);
        }
    }

    public void setBodyPartsListAdapterListener(OnBodyPartsListAdapterListener bodyPartsListAdapterListener){
        this.bodyPartsListAdapterListener = bodyPartsListAdapterListener;
    }
}
