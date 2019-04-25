package com.example.itunessearchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchWrapper {


    @SerializedName("results")
    @Expose
    private List<Movie> searches = null;

    public List<Movie> searches() {
        return searches;
    }
}
