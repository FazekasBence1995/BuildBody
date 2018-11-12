package com.example.kowansky.buildbody.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kowansky.buildbody.Activitys.MainActivity;
import com.example.kowansky.buildbody.Adapters.TrainingsListAdapter;
import com.example.kowansky.buildbody.Training;
import com.example.kowansky.buildbody.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingsFragment extends Fragment {
    TrainingsListAdapter adapter;
    ArrayList<Training> trainings = new ArrayList<Training>();
    String bodyPartName;

    public TrainingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trainings, container, false);

        createApproach();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTrainings);
        adapter = new TrainingsListAdapter(getContext(), trainings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        return view;
    }

   public void createApproach() {
       Bundle bundle = this.getArguments();
       if (bundle != null) {
           bodyPartName = bundle.getString("bodyPartsName");
       }

       Call<ArrayList<Training>> call = MainActivity.apiInterface.performBodyParts(bodyPartName);

       call.enqueue(new Callback<ArrayList<Training>>() {
           @Override
           public void onResponse(Call<ArrayList<Training>> call, Response<ArrayList<Training>> response) {
               if (response.isSuccessful()) {
                   for (int i = 0; i < response.body().size(); i++) {
                       response.body().get(i).setImageId(R.drawable.proba);
                       trainings.add(response.body().get(i));
                   }
                   adapter.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(Call<ArrayList<Training>> call, Throwable t) {
               t.printStackTrace();
           }
       });
    }
}
