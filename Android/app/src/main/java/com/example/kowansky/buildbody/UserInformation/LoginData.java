package com.example.kowansky.buildbody.UserInformation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("token")
    @Expose
    public String Token;
}
