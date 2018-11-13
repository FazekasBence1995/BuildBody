package com.example.kowansky.buildbody.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kowansky.buildbody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTrainingsFragment extends Fragment {


    public MyTrainingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_trainings, container, false);
    }

}
