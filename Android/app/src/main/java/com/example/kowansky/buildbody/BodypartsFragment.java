package com.example.kowansky.buildbody;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.http.Body;

/**
 * A simple {@link Fragment} subclass.
 */
public class BodypartsFragment extends Fragment {
    BodyPartsListAdapter adapter;
    ArrayList<BodyPart> bodyParts = new ArrayList<BodyPart>();

    public BodypartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bodyparts, container, false);

        createApproach();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBodyParts);
        adapter = new BodyPartsListAdapter(getContext(), bodyParts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return view;
    }

    public void createApproach(){
        BodyPart breast = new BodyPart("Mell", R.drawable.breast);
        BodyPart back = new BodyPart("Hát", R.drawable.back);
        BodyPart thigh = new BodyPart("Comb", R.drawable.thigh);
        BodyPart calf = new BodyPart("Vádli", R.drawable.calf);
        BodyPart biceps = new BodyPart("Bicepsz", R.drawable.biceps);
        BodyPart triceps = new BodyPart("Tricepsz", R.drawable.triceps);
        BodyPart shoulder = new BodyPart("Váll", R.drawable.shoulder);
        BodyPart stomach = new BodyPart("Has", R.drawable.stomach);

        bodyParts.add(breast);
        bodyParts.add(back);
        bodyParts.add(thigh);
        bodyParts.add(calf);
        bodyParts.add(biceps);
        bodyParts.add(triceps);
        bodyParts.add(shoulder);
        bodyParts.add(stomach);
    }

}
