package com.example.kowansky.buildbody.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kowansky.buildbody.Application.ApiClient;
import com.example.kowansky.buildbody.Application.ApiInterface;
import com.example.kowansky.buildbody.Fragments.LoginFragment;
import com.example.kowansky.buildbody.Application.PrefConfig;
import com.example.kowansky.buildbody.R;
import com.example.kowansky.buildbody.Fragments.RegistrationFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, RegistrationFragment.OnRegistrationListener {
    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
        }
    }

    @Override
    public void registerPerformed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void loginPerformed(String token, String calorie, String email) {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("token", token);
        myIntent.putExtra("calorie", calorie);
        myIntent.putExtra("email", email);
        startActivity(myIntent);
    }

    @Override
    public void cancelPerformed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
    }

    @Override
    public void applyPerformed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
    }
}
