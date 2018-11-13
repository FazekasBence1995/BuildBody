package com.example.kowansky.buildbody.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kowansky.buildbody.Activitys.MainActivity;
import com.example.kowansky.buildbody.Adapters.MyTrainingsListAdapter;
import com.example.kowansky.buildbody.Application.PrefConfig;
import com.example.kowansky.buildbody.R;
import com.example.kowansky.buildbody.Training;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTrainingsFragment extends Fragment {
    MyTrainingsListAdapter adapter;
    ArrayList<Training> trainings = new ArrayList<Training>();
    String bodyPartName;
    PrefConfig prefConfig;

    public MyTrainingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_trainings, container, false);

        createApproach();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMyTrainings);
        adapter = new MyTrainingsListAdapter(getContext(), trainings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        return view;
    }

    public void createApproach() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bodyPartName = bundle.getString("bodyPartsName");
        }
        prefConfig = new PrefConfig(getContext());
        String token = prefConfig.readAccesToken();

        Call<ArrayList<Training>> call = MainActivity.apiInterface.performGetMyTrainings(bodyPartName, token);

        call.enqueue(new Callback<ArrayList<Training>>() {
            @Override
            public void onResponse(Call<ArrayList<Training>> call, Response<ArrayList<Training>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
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
