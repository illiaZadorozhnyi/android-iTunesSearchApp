package com.example.itunessearchapp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FavoriteCard extends CardView {

    private TextView title;
    private TextView description;
    private ImageView imageView;
    private Button buy;
    private Button rent;

    private String titleText;
    private String descriptionText;
    private String imageUrl;
    private String buyPrice;
    private String rentPrice;

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        title.setText(titleText);
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
        description.setText(descriptionText);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        imageView.setImageURI(Uri.parse(imageUrl));
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
        buy.setText("BUY: " + buyPrice);
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
        rent.setText("RENT: " + rentPrice);
    }

    public FavoriteCard(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.favorite_card, this);
        this.title = findViewById(R.id.card_title1);
        this.description = findViewById(R.id.card_description1);
        this.imageView = findViewById(R.id.card_image1);
        this.buy = findViewById(R.id.buy_button1);
        this.rent = findViewById(R.id.rent_button1);
    }
}
