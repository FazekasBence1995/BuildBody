package com.example.kowansky.buildbody.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kowansky.buildbody.Application.PrefConfig;
import com.example.kowansky.buildbody.R;

import java.util.Date;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class TokenValidationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_validation);
        check();
    }

    public void check(){
        PrefConfig prefConfig = new PrefConfig(getApplicationContext());
        String token = prefConfig.readAccesToken();
        String calorie = prefConfig.readCalorie();

        if (token.isEmpty()){
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(this, MainActivity.class);
            myIntent.putExtra("calorie", calorie);
            startActivity(myIntent);
        }
    }
}
