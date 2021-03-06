package com.example.kowansky.buildbody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Training {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("URL")
    @Expose
    private String url;
    @SerializedName("Description")
    @Expose
    private String description;

    public Training(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
