package com.example.kowansky.buildbody.UserInformation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("calorie")
    @Expose
    public String calorie;

    public String getToken() {
        return token;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }
}
