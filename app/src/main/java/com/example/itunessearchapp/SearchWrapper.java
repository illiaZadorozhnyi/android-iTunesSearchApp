package com.example.itunessearchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchWrapper {


    @SerializedName("results")
    @Expose
    private List<Search> searches = null;

    public List<Search> searches() {
        return searches;
    }
}
