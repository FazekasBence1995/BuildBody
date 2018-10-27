package com.example.kowansky.buildbody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnWelcomeListener {
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

    }

    @Override
    public void trainingsPerformed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BodypartsFragment()).addToBackStack(null).commit();
    }
}
