package com.example.kowansky.buildbody.Application;

import com.example.kowansky.buildbody.BodyPart;
import com.example.kowansky.buildbody.PracticeInformation.Training;
import com.example.kowansky.buildbody.UserInformation.LoginData;
import com.example.kowansky.buildbody.UserInformation.LoginUserDto;
import com.example.kowansky.buildbody.UserInformation.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("users")
    Call<User> performRegistration(@Body User user);

    @POST("users/login")
    Call<LoginData> performUserLogin(@Body LoginUserDto loginUserDto);

    @POST("practices/bodypart")
    Call<Training> performBodyParts(@Body Training training);
}
