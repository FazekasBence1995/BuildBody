package com.example.kowansky.buildbody.Application;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.kowansky.buildbody.R;

public class PrefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;
    private String calorie;

    public PrefConfig(Context _context){
        context = _context;
        sharedPreferences = _context.getSharedPreferences(_context.getString(R.string.pref_acces_token), Context.MODE_PRIVATE);
        sharedPreferences = _context.getSharedPreferences(_context.getString(R.string.calorie), Context.MODE_PRIVATE);
    }

    public void writeAccesToken(String status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_acces_token), status);
        editor.apply();
    }

    public String readAccesToken(){
        return sharedPreferences.getString(context.getString(R.string.pref_acces_token), "");
    }

    public void writeCalorie(String calorie){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.calorie), calorie);
        editor.apply();
    }

    public String readCalorie(){
        return sharedPreferences.getString(context.getString(R.string.calorie), "");
    }


}
