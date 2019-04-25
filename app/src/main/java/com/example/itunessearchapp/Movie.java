package com.example.itunessearchapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("trackName")
    String title;
    @SerializedName("longDescription")
    String imageDesc;
    @SerializedName("artworkUrl60")
    String imageURL;
    boolean isFavorited;

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }


    public Movie() {
    }

    public Movie(String title, String imageDesc, String imageURL, boolean isFavorited) {
        this.title = title;
        this.imageDesc = imageDesc;
        this.imageURL = imageURL;
        this.isFavorited = isFavorited;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
