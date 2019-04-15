package com.example.itunessearchapp;

import android.os.Bundle;
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

    // below section is for Recycler View implementation
    private static final String TAG = "MainActivity";
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mImageDesrcs = new ArrayList<>();
    private ArrayList<String> movieNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        init();

    }

    private void init() {

        final EditText searchInput = findViewById(R.id.search_input);
        Button searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capturedMovie = searchInput.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://itunes.apple.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                iTunesSearchApi = retrofit.create(ITunesSearchApi.class);
                retrieveSearchResults(capturedMovie);
                initRecyclerView();

            }
        });
    }

    private void retrieveSearchResults(String searchTerm) {

        Call<SearchWrapper> call = iTunesSearchApi.getSearchResult(searchTerm, "us", "movie");

        call.enqueue(new Callback<SearchWrapper>() {
            @Override
            public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                if(!response.isSuccessful()){
                    resultText.setText("Response code: " + response.code() + "\n");
                    return;
                }

                List<Search> searchResults = response.body().searches();

                for(Search result : searchResults){
//                    initLists(mImageUrls, result.getArtworkUrl60());
                    initLists(mImageUrls, result.getArtworkUrl60());
                    initLists(mImageDesrcs, result.getLongDescription());
                    initLists(movieNames, result.getMovieName());
                }
            }

            @Override
            public void onFailure(Call<SearchWrapper> call, Throwable t) {
//                resultText.setText(t.getMessage());
            }
        });
    }

    private void initLists(List<String> list, String item) {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        list.add(item);
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mImageDesrcs, mImageUrls, movieNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
