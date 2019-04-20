package com.example.itunessearchapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ITunesSearchApi iTunesSearchApi;
    TextView resultText;

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    private ArrayList<Movie> mMovieList;
    private Context context;
    private FloatingActionButton favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        init();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iTunesSearchApi = retrofit.create(ITunesSearchApi.class);
        initRecyclerView();

    }

    private void init() {

        final EditText searchInput = findViewById(R.id.search_input);
        Button searchButton = findViewById(R.id.search_button);
        favoritesButton = findViewById(R.id.favorites_list_button);
        context = this;

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capturedMovie = searchInput.getText().toString();
                retrieveSearchResults(capturedMovie);

            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }


    private void retrieveSearchResults(String searchTerm) {
        Call<SearchWrapper> call = iTunesSearchApi.getSearchResult(searchTerm, "us", "movie");

        call.enqueue(new Callback<SearchWrapper>() {
            @Override
            public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                View FavListbutton = findViewById(R.id.favorites_list_button);

                if (!response.isSuccessful()) {
                    resultText.setText("Response code: " + response.code() + "\n");
                    return;
                }

                List<Search> searchResults = response.body().searches();
                mMovieList = new ArrayList<>();

                for (Search result : searchResults) {
                    Movie m = new Movie();
                    m.setImageDesc(result.getLongDescription());
                    m.setImageURL(result.getArtworkUrl60());
                    m.setTitle(result.getMovieName());
                    mMovieList.add(m);
                }
                adapter.setData(mMovieList);
                FavListbutton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<SearchWrapper> call, Throwable t) {
                resultText.setText(t.getMessage());
            }
        });
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerView");
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
