package com.example.kowansky.buildbody;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class BodyPartsListAdapter extends RecyclerView.Adapter<BodyPartsListAdapter.BodyPartViewHolder> {
    private ArrayList<BodyPart> bodyParts;
    private LayoutInflater inflater;

    public BodyPartsListAdapter(Context context, ArrayList<BodyPart> bodyParts) {
        inflater = LayoutInflater.from(context);
        this.bodyParts = bodyParts;
    }

    @NonNull
    @Override
    public BodyPartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.bodypart_listelement, viewGroup, false);
        return new BodyPartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartViewHolder bodyPartViewHolder, int i) {
        BodyPart bodyPart = bodyParts.get(i);

        bodyPartViewHolder.bodypartButton.setImageResource(bodyPart.getImageId());

        bodyPartViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: lISTENEREN KERESZTÜL A bODYPARTFREGMENTNEK ÁTADOM A NEVÉT (BODYPART.GETNAME)

                //todo: mainactivitynek visszaszól kell egy új Listener

                //todo: átnavigál egy új fragmentre amire majd az adatok betőltődnek és átadja a nevet(testrész nevét)
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
}
