package com.example.kowansky.buildbody.Application;

import com.example.kowansky.buildbody.Training;
import com.example.kowansky.buildbody.UserInformation.LoginData;
import com.example.kowansky.buildbody.UserInformation.LoginUserDto;
import com.example.kowansky.buildbody.UserInformation.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("users")
    Call<User> performRegistration(@Body User user);

    @POST("users/login")
    Call<LoginData> performUserLogin(@Body LoginUserDto loginUserDto);

    @GET("trainings/bodypart")
    Call<ArrayList<Training>> performBodyParts(@Query("name") String name);

    @POST("userstrainings")
    Call<Void> performUsersTrainingsRegistration(@Body Training training, @Header("Authorization") String token);

    @GET("userstrainings/mytrainings")
    Call<ArrayList<Training>> performGetMyTrainings(@Query("name") String name, @Header("Authorization") String token);
}
