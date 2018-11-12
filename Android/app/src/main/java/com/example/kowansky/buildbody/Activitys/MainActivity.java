package com.example.kowansky.buildbody.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kowansky.buildbody.Application.ApiClient;
import com.example.kowansky.buildbody.Application.ApiInterface;
import com.example.kowansky.buildbody.Adapters.BodyPartsListAdapter;
import com.example.kowansky.buildbody.Fragments.BodypartsFragment;
import com.example.kowansky.buildbody.Application.PrefConfig;
import com.example.kowansky.buildbody.Fragments.TrainingsFragment;
import com.example.kowansky.buildbody.R;
import com.example.kowansky.buildbody.Fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnWelcomeListener, BodyPartsListAdapter.OnBodyPartsListAdapterListener {
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).addToBackStack(null).commit();
    }

    @Override
    public void myTrainingPlanPerformed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BodypartsFragment()).addToBackStack(null).commit();
    }

    @Override
    public void trainingsPerformed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BodypartsFragment()).addToBackStack(null).commit();
    }

    @Override
    public void bodyPartsApply(String name) {
        TrainingsFragment trainingsFragment = new TrainingsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("bodyPartsName",name);
        trainingsFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, trainingsFragment).addToBackStack(null).commit();
    }
}
