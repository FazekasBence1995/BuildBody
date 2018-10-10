package com.example.kowansky.buildbody;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://192.168.0.206:3000/";
    private static Retrofit retrofit = null;

//TODO: 401-ES STATUSKÓD KEZELÉS GLOBÁLISAN!
    public static Retrofit getApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
