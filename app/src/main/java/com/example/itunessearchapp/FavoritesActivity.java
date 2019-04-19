package com.example.itunessearchapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    LinearLayout favoritesPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorites);
        favoritesPage = findViewById(R.id.favorites_page);
        populateFavorites();

    }

    private void populateFavorites() {

        for (int i = 1; i <= 10; i++) {
            FavoriteCard currentCard = new FavoriteCard(this);
            currentCard.setBuyPrice("3.99");
            currentCard.setRentPrice("5.99");
            currentCard.setTitleText("MovieOne");
            currentCard.setDescriptionText("kjahdfkjhsafjksdfksjhfkjhskjhfjh");
            favoritesPage.addView(currentCard);
// setting each property for current card will need to be set to whatever was passed from previous activity via intent/ persisted/ favorited
        }

    }
}
