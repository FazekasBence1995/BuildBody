package com.example.kowansky.buildbody.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kowansky.buildbody.Adapters.PracticesListAdapter;
import com.example.kowansky.buildbody.Practice;
import com.example.kowansky.buildbody.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticesFragment extends Fragment {
    PracticesListAdapter adapter;
    ArrayList<Practice> practices = new ArrayList<Practice>();

    public PracticesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practices, container, false);

        createApproach();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPractices);
        adapter = new PracticesListAdapter(getContext(), practices);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        return view;
    }

    public void createApproach(){
        Practice example = new Practice("Mell", R.drawable.asdasd);

        practices.add(example);
    }

}
