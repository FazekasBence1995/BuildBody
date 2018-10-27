package com.example.kowansky.buildbody;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    private Button myTrainingPlan;
    private Button trainings;

    OnWelcomeListener welcomeListener;

    public interface OnWelcomeListener{
        public void myTrainingPlanPerformed();
        public void trainingsPerformed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        welcomeListener = (MainActivity) context;
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        myTrainingPlan = view.findViewById(R.id.myTrainingPlan);
        trainings = view.findViewById(R.id.trainings);

        myTrainingPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeListener.myTrainingPlanPerformed();
            }
        });

        trainings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeListener.trainingsPerformed();
            }
        });
        return view;
    }

}