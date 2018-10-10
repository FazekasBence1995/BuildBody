package com.example.kowansky.buildbody;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context _context){
        context = _context;
        sharedPreferences = _context.getSharedPreferences(_context.getString(R.string.pref_acces_token), Context.MODE_PRIVATE);
    }

    public void writeAccesToken(String status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_acces_token), status);
        editor.apply();
    }

    public String readAccesToken(){
        return sharedPreferences.getString(context.getString(R.string.pref_acces_token), "");
    }

    public void displayTost(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
