package com.example.itunessearchapp;

import com.google.gson.annotations.SerializedName;

public class Search {

    private String artistName;

    @SerializedName("trackName")
    private String movieName;

    private String artworkUrl60;
    private double trackPrice;
    private double trackRentalPrice;
    private String primaryGenreName;
    private String contentAdvisoryRating;

    public String getArtistName() {
        return artistName;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public double getTrackRentalPrice() {
        return trackRentalPrice;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public String getLongDescription() {
        return longDescription;
    }

    private String longDescription;

}
