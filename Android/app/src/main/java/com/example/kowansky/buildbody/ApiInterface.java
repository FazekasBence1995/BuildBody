package com.example.kowansky.buildbody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("users")
    Call<User> performRegistration(@Body User user);

    @POST("users/login")
    Call<LoginData> performUserLogin(@Body LoginUserDto loginUserDto);
}
