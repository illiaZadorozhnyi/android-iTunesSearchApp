package com.example.itunessearchapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITunesSearchApi {

    @GET("search")
    Call<List<Search>> getSearchResult(
            @Query("term") String movieName,
            @Query("country") String country,
            @Query("media") String mediaType
    );

}
